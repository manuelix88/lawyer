export class ReportAmministrative {
    idRepAmministrative: number;
    qualifica?: string;
    dataPagamento: string;
    numeroFaldone: number;
    ricordoCedu?: string;
    altro: string;
    note: string;
    documentazione:string;

    constructor() {
       this.idRepAmministrative = 0;
       this.qualifica = '';
       this.dataPagamento= '';
       this.numeroFaldone = 0;
       this.ricordoCedu= '';
       this.altro= '';
       this.note= '';
       this.documentazione = '';
    }

}
