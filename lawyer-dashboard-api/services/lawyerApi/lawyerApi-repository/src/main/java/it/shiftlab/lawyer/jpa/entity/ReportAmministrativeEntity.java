package it.shiftlab.lawyer.jpa.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "REPORT_AMMINISTRATIVE", schema = "lawyer_", catalog = "")
public class ReportAmministrativeEntity {
    private long idRepAmministrative;
    private String qualifica;
    private Date dataPagamento;
    private Integer numeroFaldone;
    private String ricordoCedu;
    private String altro;
    private String note;
    private String documentazione;
    private AnagraficaClienteEntity anagraficaClientesByIdRepAmministrative;

    public ReportAmministrativeEntity() {
    }

    public ReportAmministrativeEntity(long idRepAmministrative, String qualifica, Date dataPagamento, Integer numeroFaldone, String ricordoCedu, String altro, String note, String documentazione) {
        this.idRepAmministrative = idRepAmministrative;
        this.qualifica = qualifica;
        this.dataPagamento = dataPagamento;
        this.numeroFaldone = numeroFaldone;
        this.ricordoCedu = ricordoCedu;
        this.altro = altro;
        this.note = note;
        this.documentazione = documentazione;
    }


    public ReportAmministrativeEntity(long idRepAmministrative, String qualifica, Date dataPagamento, Integer numeroFaldone, String ricordoCedu, String altro, String note, AnagraficaClienteEntity anagraficaClientesByIdRepAmministrative) {
        this.idRepAmministrative = idRepAmministrative;
        this.qualifica = qualifica;
        this.dataPagamento = dataPagamento;
        this.numeroFaldone = numeroFaldone;
        this.ricordoCedu = ricordoCedu;
        this.altro = altro;
        this.note = note;
        this.anagraficaClientesByIdRepAmministrative = anagraficaClientesByIdRepAmministrative;
    }

    @Id
    @Column(name = "ID_REP_AMMINISTRATIVE")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getIdRepAmministrative() {
        return idRepAmministrative;
    }

    public void setIdRepAmministrative(long idRepAmministrative) {
        this.idRepAmministrative = idRepAmministrative;
    }

    @Basic
    @Column(name = "QUALIFICA")
    public String getQualifica() {
        return qualifica;
    }

    public void setQualifica(String qualifica) {
        this.qualifica = qualifica;
    }

    @Basic
    @Column(name = "DATA_PAGAMENTO")
    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    @Basic
    @Column(name = "NUMERO_FALDONE")
    public Integer getNumeroFaldone() {
        return numeroFaldone;
    }

    public void setNumeroFaldone(Integer numeroFaldone) {
        this.numeroFaldone = numeroFaldone;
    }

    @Basic
    @Column(name = "RICORDO_CEDU")
    public String getRicordoCedu() {
        return ricordoCedu;
    }

    public void setRicordoCedu(String ricordoCedu) {
        this.ricordoCedu = ricordoCedu;
    }

    @Basic
    @Column(name = "ALTRO")
    public String getAltro() {
        return altro;
    }

    public void setAltro(String altro) {
        this.altro = altro;
    }

    @Basic
    @Column(name = "NOTE")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Basic
    @Column(name = "DOCUMENTAZIONE")
    public String getDocumentazione() {
        return documentazione;
    }

    public void setDocumentazione(String documentazione) {
        this.documentazione = documentazione;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportAmministrativeEntity that = (ReportAmministrativeEntity) o;
        return idRepAmministrative == that.idRepAmministrative &&
                Objects.equals(qualifica, that.qualifica) &&
                Objects.equals(dataPagamento, that.dataPagamento) &&
                Objects.equals(numeroFaldone, that.numeroFaldone) &&
                Objects.equals(ricordoCedu, that.ricordoCedu) &&
                Objects.equals(altro, that.altro) &&
                Objects.equals(note, that.note) &&
                Objects.equals(documentazione, that.documentazione);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRepAmministrative, qualifica, dataPagamento, numeroFaldone, ricordoCedu, altro, note, documentazione);
    }

    @OneToOne(mappedBy = "reportAmministrativeByIdRepAmministrative")
    public AnagraficaClienteEntity getAnagraficaClientesByIdRepAmministrative() {
        return anagraficaClientesByIdRepAmministrative;
    }

    public void setAnagraficaClientesByIdRepAmministrative(AnagraficaClienteEntity anagraficaClientesByIdRepAmministrative) {
        this.anagraficaClientesByIdRepAmministrative = anagraficaClientesByIdRepAmministrative;
    }
}
