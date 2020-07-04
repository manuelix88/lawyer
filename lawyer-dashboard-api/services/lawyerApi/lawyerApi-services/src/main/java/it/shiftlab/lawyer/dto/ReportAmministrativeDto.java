package it.shiftlab.lawyer.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;

@Builder
@Getter @Setter
public class ReportAmministrativeDto implements Serializable {

    private long idRepAmministrative;
    private String qualifica;
    private Date dataPagamento;
    private Integer numeroFaldone;
    private String ricordoCedu;
    private String altro;
    private String note;

    public ReportAmministrativeDto(long idRepAmministrative, String qualifica, Date dataPagamento, Integer numeroFaldone, String ricordoCedu, String altro, String note) {
        this.idRepAmministrative = idRepAmministrative;
        this.qualifica = qualifica;
        this.dataPagamento = dataPagamento;
        this.numeroFaldone = numeroFaldone;
        this.ricordoCedu = ricordoCedu;
        this.altro = altro;
        this.note = note;
    }

    public long getIdRepAmministrative() {
        return idRepAmministrative;
    }

    public void setIdRepAmministrative(long idRepAmministrative) {
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
}
