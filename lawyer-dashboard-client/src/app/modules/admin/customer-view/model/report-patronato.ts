export class ReportPatronato {
    idRepPatronato: number;
    convenzione?: string;
    spese?: string;
    decorrenzaSuccessiva?: string;
    codice?: Codice;
    tipoPratica?: string;
    tribunale?: Tribunale;
    ruoloGenerale?: string;
    dataUltimaUdienza?: any[];
    patronatoProvenienza?: string;
    giudice: string;
    avvocatoDelegato: string;
    note?: string;
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
