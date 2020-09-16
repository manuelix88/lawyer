import {Codice, Status, Tribunale} from '../../modules/admin/customer-view/model/report-patronato';
import {StoreAction} from '../utils/common.api';
import {Avvocati} from '../../modules/admin/customer-view/model/avvocati';
import {PatronatoProvenienza} from '../../modules/admin/customer-view/model/patronatoProvenienza';
import {TipoPratiche} from '../../modules/admin/customer-view/model/tipo-pratiche';

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

export class AvvocatiDelegatiArray implements StoreAction<GenericsStore> {
    constructor(private avvocati: Avvocati[]) {}
    newState(prevState: GenericsStore): GenericsStore {
        prevState.avvocati = this.avvocati;
        return prevState;
    }
}

export class PatronatiArray implements StoreAction<GenericsStore> {
    constructor(private patronati: PatronatoProvenienza[]) {}
    newState(prevState: GenericsStore): GenericsStore {
        prevState.patronati = this.patronati;
        return prevState;
    }
}

export class TipoPraticheArray implements StoreAction<GenericsStore> {
    constructor(private pratiche: TipoPratiche[]) {}
    newState(prevState: GenericsStore): GenericsStore {
        prevState.tipoPratiche = this.pratiche;
        return prevState;
    }
}
export const INITIAL: GenericsStore = {
    status: [],
    tribunali:[],
    codici: [],
    avvocati: [],
    patronati: [],
    tipoPratiche: [],
}
// tslint:disable-next-line:no-empty-interface
export interface GenericsStore {
    status: Status[];
    tribunali: Tribunale[];
    codici: Codice[];
    avvocati: Avvocati[];
    patronati: PatronatoProvenienza[];
    tipoPratiche: TipoPratiche[];

}
