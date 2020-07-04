package it.shiftlab.lawyer.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@Builder
public class ReportPatronatoDto implements Serializable {

    private long idRepPatronato;

    private String convenzione;

    private String spese;

    private String decorrenzaSuccessiva;

    private String codice;

    private String tipoPratica;

    private String tribunale;

    private String ruoloGenerale;

    private Date dataUltimaUdienza;

    private String patronatoProvenienza;

    private String giudice;

    private String avvocatoDelegato;

    private String note;

    public ReportPatronatoDto(long idRepPatronato, String convenzione, String spese, String decorrenzaSuccessiva, String codice, String tipoPratica, String tribunale, String ruoloGenerale, Date dataUltimaUdienza, String patronatoProvenienza, String giudice, String avvocatoDelegato, String note) {
        this.idRepPatronato = idRepPatronato;
        this.convenzione = convenzione;
        this.spese = spese;
        this.decorrenzaSuccessiva = decorrenzaSuccessiva;
        this.codice = codice;
        this.tipoPratica = tipoPratica;
        this.tribunale = tribunale;
        this.ruoloGenerale = ruoloGenerale;
        this.dataUltimaUdienza = dataUltimaUdienza;
        this.patronatoProvenienza = patronatoProvenienza;
        this.giudice = giudice;
        this.avvocatoDelegato = avvocatoDelegato;
        this.note = note;
    }

    public long getIdRepPatronato() {
        return idRepPatronato;
    }

    public void setIdRepPatronato(long idRepPatronato) {
        this.idRepPatronato = idRepPatronato;
    }

    public String getConvenzione() {
        return convenzione;
    }

    public void setConvenzione(String convenzione) {
        this.convenzione = convenzione;
    }

    public String getSpese() {
        return spese;
    }

    public void setSpese(String spese) {
        this.spese = spese;
    }

    public String getDecorrenzaSuccessiva() {
        return decorrenzaSuccessiva;
    }

    public void setDecorrenzaSuccessiva(String decorrenzaSuccessiva) {
        this.decorrenzaSuccessiva = decorrenzaSuccessiva;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getTipoPratica() {
        return tipoPratica;
    }

    public void setTipoPratica(String tipoPratica) {
        this.tipoPratica = tipoPratica;
    }

    public String getTribunale() {
        return tribunale;
    }

    public void setTribunale(String tribunale) {
        this.tribunale = tribunale;
    }

    public String getRuoloGenerale() {
        return ruoloGenerale;
    }

    public void setRuoloGenerale(String ruoloGenerale) {
        this.ruoloGenerale = ruoloGenerale;
    }

    public Date getDataUltimaUdienza() {
        return dataUltimaUdienza;
    }

    public void setDataUltimaUdienza(Date dataUltimaUdienza) {
        this.dataUltimaUdienza = dataUltimaUdienza;
    }

    public String getPatronatoProvenienza() {
        return patronatoProvenienza;
    }

    public void setPatronatoProvenienza(String patronatoProvenienza) {
        this.patronatoProvenienza = patronatoProvenienza;
    }

    public String getGiudice() {
        return giudice;
    }

    public void setGiudice(String giudice) {
        this.giudice = giudice;
    }

    public String getAvvocatoDelegato() {
        return avvocatoDelegato;
    }

    public void setAvvocatoDelegato(String avvocatoDelegato) {
        this.avvocatoDelegato = avvocatoDelegato;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
