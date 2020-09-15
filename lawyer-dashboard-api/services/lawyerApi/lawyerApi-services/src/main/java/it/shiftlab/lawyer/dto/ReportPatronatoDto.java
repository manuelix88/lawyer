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

    private String codice;

    private TipoPraticheDto tipoPratica;

    private TribunaleDto tribunale;

    private String ruoloGenerale;

    private List<DataUdienzaDto> dateUdienze;

    private PatronatoProvenienzaDto patronatoProvenienza;

    private String giudice;

    private AvvocatoDelegatoDto avvocatoDelegato;

    private String note;

    private String uuid;

    public ReportPatronatoDto() {
    }

    public ReportPatronatoDto(StatusDto status, String convenzione, String spese, String codice, TipoPraticheDto tipoPratica, TribunaleDto tribunale, String ruoloGenerale,
                              List<DataUdienzaDto> dateUdienze, PatronatoProvenienzaDto patronatoProvenienza, String giudice, AvvocatoDelegatoDto avvocatoDelegato, String note, String uuid) {
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
        this.uuid = uuid;
    }

    public ReportPatronatoDto(Long idRepPatronato, StatusDto status,
                              String convenzione, String spese,
                              String decorrenzaSuccessiva,
                              String codice, TipoPraticheDto tipoPratica, TribunaleDto tribunale, String ruoloGenerale, List<DataUdienzaDto> dateUdienze,
                              PatronatoProvenienzaDto patronatoProvenienza, String giudice, AvvocatoDelegatoDto avvocatoDelegato, String note, String uuid) {
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
        this.uuid = uuid;
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

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public TipoPraticheDto getTipoPratica() {
        return tipoPratica;
    }

    public void setTipoPratica(TipoPraticheDto tipoPratica) {
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

    public PatronatoProvenienzaDto getPatronatoProvenienza() {
        return patronatoProvenienza;
    }

    public void setPatronatoProvenienza(PatronatoProvenienzaDto patronatoProvenienza) {
        this.patronatoProvenienza = patronatoProvenienza;
    }

    public String getGiudice() {
        return giudice;
    }

    public void setGiudice(String giudice) {
        this.giudice = giudice;
    }

    public AvvocatoDelegatoDto getAvvocatoDelegato() {
        return avvocatoDelegato;
    }

    public void setAvvocatoDelegato(AvvocatoDelegatoDto avvocatoDelegato) {
        this.avvocatoDelegato = avvocatoDelegato;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
