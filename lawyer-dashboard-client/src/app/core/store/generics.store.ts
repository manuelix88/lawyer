import {Codice, Status, Tribunale} from '../../modules/admin/customer-view/model/report-patronato';
import {StoreAction} from '../utils/common.api';

export class StatusArray implements StoreAction<Generics> {
    constructor(private status: Status[]) {}
    newState(prevState: Generics): Generics {
        prevState.status = this.status;
        return prevState;
    }
}
export const INITIAL: Generics = {
    status: [],
    tribunali:[],
    codici: [],
}
// tslint:disable-next-line:no-empty-interface
export interface Generics {
    status: Status[];
    tribunali: Tribunale[];
    codici: Codice[];

}
