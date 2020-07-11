package it.shiftlab.lawyer.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class ReportAmministrativeDto implements Serializable {

    private Long idRepAmministrative;
    private String qualifica;
    private Date dataPagamento;
    private Integer numeroFaldone;
    private String ricordoCedu;
    private String altro;
    private String note;
    private String documentazione;

    public ReportAmministrativeDto() {
    }

    public ReportAmministrativeDto(Long idRepAmministrative, String qualifica, Date dataPagamento, Integer numeroFaldone, String ricordoCedu, String altro, String note, String documentazione) {
        this.idRepAmministrative = idRepAmministrative;
        this.qualifica = qualifica;
        this.dataPagamento = dataPagamento;
        this.numeroFaldone = numeroFaldone;
        this.ricordoCedu = ricordoCedu;
        this.altro = altro;
        this.note = note;
        this.documentazione = documentazione;
    }


    public Long getIdRepAmministrative() {
        return idRepAmministrative;
    }

    public void setIdRepAmministrative(Long idRepAmministrative) {
        this.idRepAmministrative = idRepAmministrative;
    }

    public String getQualifica() {
        return qualifica;
    }

    public void setQualifica(String qualifica) {
        this.qualifica = qualifica;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Integer getNumeroFaldone() {
        return numeroFaldone;
    }

    public void setNumeroFaldone(Integer numeroFaldone) {
        this.numeroFaldone = numeroFaldone;
    }

    public String getRicordoCedu() {
        return ricordoCedu;
    }

    public void setRicordoCedu(String ricordoCedu) {
        this.ricordoCedu = ricordoCedu;
    }

    public String getAltro() {
        return altro;
    }

    public void setAltro(String altro) {
        this.altro = altro;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDocumentazione() {
        return documentazione;
    }

    public void setDocumentazione(String documentazione) {
        this.documentazione = documentazione;
    }

}
