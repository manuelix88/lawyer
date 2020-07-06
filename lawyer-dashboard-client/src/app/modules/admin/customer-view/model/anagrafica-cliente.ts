import {ReportPatronato} from './report-patronato';
import {ReportAmministrative} from './report-amministrative';

export class AnagraficaCliente {
    idAnagrafica: number;
    nome: string;
    cognome: string;
    email?: string
    codiceFiscale: string;
    nazione?: string;
    indirizzo?: string;
    citta: string;
    provincia?: string;
    dataNascita?: string;
    reportPatronato: ReportPatronato;
    reportAmministrative: ReportAmministrative;

    // constructor() {
    //     this.idAnagrafica = 0;
    //     this.nome = '';
    //     this.cognome = '';
    //     this.email = '';
    //     this.codiceFiscale = '';
    //     this.nazione = '';
    //     this.indirizzo = '';
    //     this.citta = '';
    //     this.provincia = '';
    //     this.dataNascita = '';
    //     this.reportPatronato = new ReportPatronato();
    //     this.reportAmministrative = new ReportAmministrative();
    // }


}
