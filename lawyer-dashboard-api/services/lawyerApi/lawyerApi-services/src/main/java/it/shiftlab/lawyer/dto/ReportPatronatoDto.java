package it.shiftlab.lawyer.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class ReportPatronatoDto implements Serializable {

    private Long idRepPatronato;

    private StatusDto status;

    private String convenzione;

    private String spese;

    private String decorrenzaSuccessiva;

    private CodiceDto codice;

    private String tipoPratica;

    private TribunaleDto tribunale;

    private String ruoloGenerale;

    private List<DataUdienzaDto> dateUdienze;

    private String patronatoProvenienza;

    private String giudice;

    private String avvocatoDelegato;

    private String note;

    public ReportPatronatoDto() {
    }

    public ReportPatronatoDto(Long idRepPatronato, StatusDto status, String convenzione, String spese, String decorrenzaSuccessiva, CodiceDto codice, String tipoPratica,
                              TribunaleDto tribunale, String ruoloGenerale, List<DataUdienzaDto> dateUdienze, String patronatoProvenienza, String giudice, String avvocatoDelegato, String note) {
        this.idRepPatronato = idRepPatronato;
        this.status = status;
        this.convenzione = convenzione;
        this.spese = spese;
        this.decorrenzaSuccessiva = decorrenzaSuccessiva;
        this.codice = codice;
        this.tipoPratica = tipoPratica;
        this.tribunale = tribunale;
        this.ruoloGenerale = ruoloGenerale;
        this.dateUdienze = dateUdienze;
        this.patronatoProvenienza = patronatoProvenienza;
        this.giudice = giudice;
        this.avvocatoDelegato = avvocatoDelegato;
        this.note = note;
    }

    public ReportPatronatoDto(StatusDto status, String convenzione, String spese, CodiceDto codice, String tipoPratica, TribunaleDto tribunale, String ruoloGenerale,
                              List<DataUdienzaDto> dateUdienze, String patronatoProvenienza, String giudice, String avvocatoDelegato, String note) {
        this.status = status;
        this.convenzione = convenzione;
        this.spese = spese;
        this.codice = codice;
        this.tipoPratica = tipoPratica;
        this.tribunale = tribunale;
        this.ruoloGenerale = ruoloGenerale;
        this.dateUdienze = dateUdienze;
        this.patronatoProvenienza = patronatoProvenienza;
        this.giudice = giudice;
        this.avvocatoDelegato = avvocatoDelegato;
        this.note = note;
    }

    public Long getIdRepPatronato() {
        return idRepPatronato;
    }

    public void setIdRepPatronato(Long idRepPatronato) {
        this.idRepPatronato = idRepPatronato;
    }

    public StatusDto getStatus() {
        return status;
    }

    public void setStatus(StatusDto status) {
        this.status = status;
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

    public CodiceDto getCodice() {
        return codice;
    }

    public void setCodice(CodiceDto codice) {
        this.codice = codice;
    }

    public String getTipoPratica() {
        return tipoPratica;
    }

    public void setTipoPratica(String tipoPratica) {
        this.tipoPratica = tipoPratica;
    }

    public TribunaleDto getTribunale() {
        return tribunale;
    }

    public void setTribunale(TribunaleDto tribunale) {
        this.tribunale = tribunale;
    }

    public String getRuoloGenerale() {
        return ruoloGenerale;
    }

    public void setRuoloGenerale(String ruoloGenerale) {
        this.ruoloGenerale = ruoloGenerale;
    }

    public List<DataUdienzaDto> getDateUdienze() {
        return dateUdienze;
    }

    public void setDateUdienze(List<DataUdienzaDto> dateUdienze) {
        this.dateUdienze = dateUdienze;
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
