import {ReportPatronato} from './report-patronato';
import {ReportAmministrative} from './report-amministrative';

export class AnagraficaCliente {
    idAnagrafica: number;
    nome: string;
    cognome: string;
    email?: string
    codice?: string
    cap?: string
    uuid?: string
    codiceFiscale: string;
    nazione?: string;
    indirizzo?: string;
    citta: string;
    provincia?: string;
    dataNascita?: string;
    reportPatronato: ReportPatronato;
    reportAmministrative: ReportAmministrative;

}

