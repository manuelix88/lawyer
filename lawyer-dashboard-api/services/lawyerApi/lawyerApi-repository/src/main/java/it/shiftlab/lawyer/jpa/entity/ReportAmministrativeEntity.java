package it.shiftlab.lawyer.jpa.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "report_amministrative", schema = "lawyer2", catalog = "")
public class ReportAmministrativeEntity {
    private long idRepAmministrative;
    private String altro;
    private Date dataPagamento;
    private String note;
    private Integer numeroFaldone;
    private String qualifica;
    private String ricordoCedu;
    private String documentazione;
    private AnagraficaClienteEntity anagraficaClientesByIdRepAmministrative;

    @Id
    @Column(name = "id_rep_amministrative")
    public long getIdRepAmministrative() {
        return idRepAmministrative;
    }

    public void setIdRepAmministrative(long idRepAmministrative) {
        this.idRepAmministrative = idRepAmministrative;
    }

    @Basic
    @Column(name = "altro")
    public String getAltro() {
        return altro;
    }

    public void setAltro(String altro) {
        this.altro = altro;
    }

    @Basic
    @Column(name = "data_pagamento")
    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    @Basic
    @Column(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Basic
    @Column(name = "numero_faldone")
    public Integer getNumeroFaldone() {
        return numeroFaldone;
    }

    public void setNumeroFaldone(Integer numeroFaldone) {
        this.numeroFaldone = numeroFaldone;
    }

    @Basic
    @Column(name = "qualifica")
    public String getQualifica() {
        return qualifica;
    }

    public void setQualifica(String qualifica) {
        this.qualifica = qualifica;
    }

    @Basic
    @Column(name = "ricordo_cedu")
    public String getRicordoCedu() {
        return ricordoCedu;
    }

    public void setRicordoCedu(String ricordoCedu) {
        this.ricordoCedu = ricordoCedu;
    }

    @Basic
    @Column(name = "documentazione")
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
                Objects.equals(altro, that.altro) &&
                Objects.equals(dataPagamento, that.dataPagamento) &&
                Objects.equals(note, that.note) &&
                Objects.equals(numeroFaldone, that.numeroFaldone) &&
                Objects.equals(qualifica, that.qualifica) &&
                Objects.equals(ricordoCedu, that.ricordoCedu) &&
                Objects.equals(documentazione, that.documentazione);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRepAmministrative, altro, dataPagamento, note, numeroFaldone, qualifica, ricordoCedu, documentazione);
    }

    @OneToOne(mappedBy = "reportAmministrativeByIdRepAmministrative")
    public AnagraficaClienteEntity getAnagraficaClientesByIdRepAmministrative() {
        return anagraficaClientesByIdRepAmministrative;
    }

    public void setAnagraficaClientesByIdRepAmministrative(AnagraficaClienteEntity anagraficaClientesByIdRepAmministrative) {
        this.anagraficaClientesByIdRepAmministrative = anagraficaClientesByIdRepAmministrative;
    }
}
