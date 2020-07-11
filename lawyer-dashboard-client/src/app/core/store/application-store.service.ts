import { Injectable } from '@angular/core';
import {Store, StoreAction} from '../utils/common.api';
import {GenericsStore} from '../store/generics.store';
import * as genericData from './generics.store';
@Injectable({
    providedIn: 'root'
})
export class ApplicationStoreService extends Store{
    baseData: GenericsStore
    constructor() {
        super();
        this.baseData = this.addStoreModule(genericData);
    }
    // tslint:disable-next-line:typedef
    updateStore<T>(action: StoreAction<T>) {
        super.updateStore(action);
    }
}
