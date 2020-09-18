import {TipoPratiche} from './tipo-pratiche';
import {Avvocati} from './avvocati';
import {PatronatoProvenienza} from './patronatoProvenienza';

export class ReportPatronato {
    idRepPatronato: number;
    convenzione?: string;
    spese?: string;
    decorrenzaSuccessiva?: string;
    codice?: Codice;
    tipoPratica?: TipoPratiche;
    tribunale?: Tribunale;
    ruoloGenerale?: string;
    dateUdienze?: DataUdienze[] = [];
    patronatoProvenienza?: PatronatoProvenienza;
    giudice: string;
    avvocatoDelegato?: Avvocati;
    note?: string;
    uuid: string;
    status: Status;
}

export class Status {
    id: number;
    status: string;
}

export class Codice {
    id: number;
    code: string;
}

export class Tribunale {
    id: number;
    tribunali?: string;
}

export class DataUdienze {
    id: number;
    enable: boolean;
    dataUdienza: string;
}
