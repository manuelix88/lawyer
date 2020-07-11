import {Codice, Status, Tribunale} from '../../modules/admin/customer-view/model/report-patronato';
import {StoreAction} from '../utils/common.api';

export class StatusArray implements StoreAction<GenericsStore> {
    constructor(private status: Status[]) {}
    newState(prevState: GenericsStore): GenericsStore {
        prevState.status = this.status;
        return prevState;
    }
}

export class TribunaleArray implements StoreAction<GenericsStore> {
    constructor(private tribunali: Tribunale[]) {}
    newState(prevState: GenericsStore): GenericsStore {
        prevState.tribunali = this.tribunali;
        return prevState;
    }
}

export class CodiceArray implements StoreAction<GenericsStore> {
    constructor(private codici: Codice[]) {}
    newState(prevState: GenericsStore): GenericsStore {
        prevState.codici = this.codici;
        return prevState;
    }
}
export const INITIAL: GenericsStore = {
    status: [],
    tribunali:[],
    codici: [],
}
// tslint:disable-next-line:no-empty-interface
export interface GenericsStore {
    status: Status[];
    tribunali: Tribunale[];
    codici: Codice[];

}
