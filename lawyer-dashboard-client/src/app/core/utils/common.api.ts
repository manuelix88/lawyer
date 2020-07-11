export function toArray(...x: Array<Object>) {
  const a = [];

  for (const xi of x) {
    for (const k in xi) {
      a.push(xi[k]);
    }
  }
  return a;
}

export function isFunction(functionToCheck) {
  const getType = {};
  return functionToCheck && getType.toString.call(functionToCheck) === '[object Function]';
}


function functionIdentifier(f) {
  let id = f.toString();
  if (f.prototype.newState) {
    id += f.prototype.newState.toString();
  }
  return id;
}


export class StoreService<S> {

  private subscriber: (S) => void;

  get store() {
    return this._store;
  }

  constructor(private _store: S) {
  }


  update<P>(action: StoreAction<S>) {
    this._store = action.newState(this._store);
    if (this.subscriber) {
      this.subscriber(this._store);
    }
  }

  subscribe(f: (S) => void) {
    this.subscriber = f;
  }

}

export interface StoreAction<S> {
  newState(prevState: S): S;
}

export abstract class IStore {
  abstract addStoreModule<T>(module: any): T;

  abstract get<T>(module: any): T;

  abstract updateStore<T>(action: StoreAction<T>);
}
/*
*
* Usage:
*
* STORE SERVICE:
*
* import * as login from "./login/login.store";
*
* @Injectable({provideIn: 'root'})
export class AppStore extends Store {
  loginStore: ILoginData;

  constructor() {
    super()
    super.logActions() // log actions in console with several info (state, inputs, stack, ...)
    this.loginStore = this.addStoreModule(login)
  }

  updateStore<T>(action: StoreAction<T>) {
    // do something at each execution
    super.updateStore(action)
  }

}
*
*
* ACTIONS FILE + INITIAL STATE:
*
* import {ILoginData} from './login.component';
import {StoreAction} from './common.api';


export class LoginStarted implements StoreAction<ILoginData> {
  constructor(private username: string, private password: string) {
  }

  newState(prevState: ILoginData): ILoginData {
    prevState.username = this.username;
    prevState.password = this.password;
    return prevState;
  }

}

export const INITIAL: ILoginData = {
  loading: false,
  username: null,
  password: null,
};
*
*
*
* LAUNCHING NEW ACTION:
*
* this.store.updateStore(new LoginStarted(username, password));
*
*
* NOTES:
* - this version works on modern browsers and IE9+ (it does not uses Maps)
* - in order not to use Maps, there is a limitation: there could not be two actions
*   with exactly the same instructions in the newState method (it will show an error: Action already defined.
*   the workaround is to add a console.log('something') instruction to diversify the action from the one already defined.
*
* */
export class Store implements IStore {

  storeIds: any = {};
  globalStore: any = {};
  globalActions: { [key: string]: { action: Function, store: StoreService<any> } } = {};
  observers: StoreObserver[] = [];
  private historyManager = new ActionsHistoryManager();

  addStoreModule(input: any): any {

    const s = input.state || input.INITIAL;
    if (!s) {
      throw new Error('A store module should export an initial state named state or INITIAL');
    }
    const fs = [];
    for (const f in input) {
      if (f !== 'state' && isFunction(input[f])) {
        fs.push(input[f]);
      }
    }
    const st = this.addStore(s, ...fs);
    this.storeIds[this.moduleId(input)] = st;
    return st;
  }

  private moduleId(input: any) {
    let idpart = '';
    for (const o in input) {
      if (isFunction(o)) {
        idpart += functionIdentifier(o);
      }
    }
    return Object.keys(input).join(',') + idpart;
  }

  get<T>(module: any): T {
    return this.storeIds[this.moduleId(module)];
  }

  addStore<T>(input: T, ...actions: Function[]): T {
    const ss = new StoreService<T>(input);
    this.add(ss, functionIdentifier(input.constructor), ...actions);
    return ss.store;
  }


  storeGlobally() {
    (window as any).store = this;
  }


  add<T>(storeService: StoreService<T>, name: string, ...actions: Function[]) {
    this.globalStore[name] = storeService;
    for (const a of actions) {
      if (this.globalActions[functionIdentifier(a)]) {
        throw new Error(`The action ${functionIdentifier(a)} already exists`);
      }
      this.globalActions[functionIdentifier(a)] = {action: a, store: storeService};
    }
  }

  addObserver(o: StoreObserver) {
    this.observers.push(o);
  }

  updateStore<T>(action: StoreAction<T>) {
    const name = functionIdentifier(action.constructor);
    if (!this.globalActions[name]) {
      throw new Error(`${name} not registered into this store!`);
    }
    const ss = (this.globalActions[name].store as StoreService<T>);
    ss.update(action);
    for (const o of this.observers) {
      o.observe(action, ss, name);
    }
  }

  state(name: string) {
    return this.globalStore[name].store;
  }

  manageHistory() {
    this.addObserver(this.historyManager);
  }

  logActions() {
    this.addObserver(new ActionsLogger());
  }

  reloadHistory() {
    const actions = new StoreSaver().load(ActionsHistoryManager.STORE_ACTIONS_KEY);
    for (const a of actions) {
      const as = this.globalActions[functionIdentifier(a)];
      const action = new (as.action as any)();
      for (const k in a.action) {
        action[k] = a.action[k];
      }
      as.store.update(action);
      new ActionsLogger().log(action, as.store.store);

    }
  }

  takeSnapshot() {
    this.historyManager.takeSnapshot();
  }

  clearHistory() {
    new StoreSaver().save(ActionsHistoryManager.STORE_ACTIONS_KEY, []);
  }

}

class ActionsLogger implements StoreObserver {

  private index: number;

  observe(action: StoreAction<any>, service: StoreService<any>, name: string) {
    this.log(action, service.store);
  }

  log(action: StoreAction<any>, nextState: any) {


    if (this.index !== undefined) {
      console.groupCollapsed(`${action.constructor.name} (${this.index})`);
      this.index++;
    } else {
      console.groupCollapsed(action.constructor.name);
    }

    console.log({action: functionIdentifier(action.constructor)});
    console.log(action);
    console.log('NEXT STATE:');

    console.groupEnd();

    console.log(nextState);
    console.groupCollapsed(`stack`);
    (console as any).trace();
    console.groupEnd();
  }

}

class ActionsHistoryManager implements StoreObserver {
  public static STORE_ACTIONS_KEY = 'STORE_ACTIONS';

  actions = [];
  saver = new StoreSaver();

  observe(action: StoreAction<any>, service: StoreService<any>, name: string) {
    this.actions.push(action);
  }

  takeSnapshot() {
    this.saver.save(ActionsHistoryManager.STORE_ACTIONS_KEY, this.actions);
  }

}

export interface StoreObserver {
  observe(action: StoreAction<any>, service: StoreService<any>, name: string);
}

export class StoreSaver {

  serialize = JSON.stringify;
  deserialize = JSON.parse;

  save<T>(name: string, actions: StoreAction<T>[]) {
    const actionsAndNames = actions.map((a) => {
      return {action: a, name: functionIdentifier(a.constructor)};
    });
    localStorage.setItem(name, this.serialize(actionsAndNames));
  }

  load(name: string) {
    let actions = localStorage.getItem(name) as any;
    actions = actions ? this.deserialize(actions) : [];
    return actions;
  }
}

export let merge: Function = Object.assign || function (target) {
  for (let i = 1; i < arguments.length; i++) {
    const source = arguments[i];
    for (const key in source) {
      if (Object.prototype.hasOwnProperty.call(source, key)) {
        target[key] = source[key];
      }
    }
  }
  return target;
};

