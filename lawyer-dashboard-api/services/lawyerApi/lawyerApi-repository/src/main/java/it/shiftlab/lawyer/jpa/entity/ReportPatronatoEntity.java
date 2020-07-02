package it.shiftlab.lawyer.jpa.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "REPORT_PATRONATO", schema = "lawyer_", catalog = "")
public class ReportPatronatoEntity {
    private int idRepPatronato;
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

    @Id
    @Column(name = "ID_REP_PATRONATO")
    public int getIdRepPatronato() {
        return idRepPatronato;
    }

    public void setIdRepPatronato(int idRepPatronato) {
        this.idRepPatronato = idRepPatronato;
    }

    @Basic
    @Column(name = "CONVENZIONE")
    public String getConvenzione() {
        return convenzione;
    }

    public void setConvenzione(String convenzione) {
        this.convenzione = convenzione;
    }

    @Basic
    @Column(name = "SPESE")
    public String getSpese() {
        return spese;
    }

    public void setSpese(String spese) {
        this.spese = spese;
    }

    @Basic
    @Column(name = "DECORRENZA_SUCCESSIVA")
    public String getDecorrenzaSuccessiva() {
        return decorrenzaSuccessiva;
    }

    public void setDecorrenzaSuccessiva(String decorrenzaSuccessiva) {
        this.decorrenzaSuccessiva = decorrenzaSuccessiva;
    }

    @Basic
    @Column(name = "CODICE")
    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    @Basic
    @Column(name = "TIPO_PRATICA")
    public String getTipoPratica() {
        return tipoPratica;
    }

    public void setTipoPratica(String tipoPratica) {
        this.tipoPratica = tipoPratica;
    }

    @Basic
    @Column(name = "TRIBUNALE")
    public String getTribunale() {
        return tribunale;
    }

    public void setTribunale(String tribunale) {
        this.tribunale = tribunale;
    }

    @Basic
    @Column(name = "RUOLO_GENERALE")
    public String getRuoloGenerale() {
        return ruoloGenerale;
    }

    public void setRuoloGenerale(String ruoloGenerale) {
        this.ruoloGenerale = ruoloGenerale;
    }

    @Basic
    @Column(name = "DATA_ULTIMA_UDIENZA")
    public Date getDataUltimaUdienza() {
        return dataUltimaUdienza;
    }

    public void setDataUltimaUdienza(Date dataUltimaUdienza) {
        this.dataUltimaUdienza = dataUltimaUdienza;
    }

    @Basic
    @Column(name = "PATRONATO_PROVENIENZA")
    public String getPatronatoProvenienza() {
        return patronatoProvenienza;
    }

    public void setPatronatoProvenienza(String patronatoProvenienza) {
        this.patronatoProvenienza = patronatoProvenienza;
    }

    @Basic
    @Column(name = "GIUDICE")
    public String getGiudice() {
        return giudice;
    }

    public void setGiudice(String giudice) {
        this.giudice = giudice;
    }

    @Basic
    @Column(name = "AVVOCATO_DELEGATO")
    public String getAvvocatoDelegato() {
        return avvocatoDelegato;
    }

    public void setAvvocatoDelegato(String avvocatoDelegato) {
        this.avvocatoDelegato = avvocatoDelegato;
    }

    @Basic
    @Column(name = "NOTE")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportPatronatoEntity that = (ReportPatronatoEntity) o;
        return idRepPatronato == that.idRepPatronato &&
                Objects.equals(convenzione, that.convenzione) &&
                Objects.equals(spese, that.spese) &&
                Objects.equals(decorrenzaSuccessiva, that.decorrenzaSuccessiva) &&
                Objects.equals(codice, that.codice) &&
                Objects.equals(tipoPratica, that.tipoPratica) &&
                Objects.equals(tribunale, that.tribunale) &&
                Objects.equals(ruoloGenerale, that.ruoloGenerale) &&
                Objects.equals(dataUltimaUdienza, that.dataUltimaUdienza) &&
                Objects.equals(patronatoProvenienza, that.patronatoProvenienza) &&
                Objects.equals(giudice, that.giudice) &&
                Objects.equals(avvocatoDelegato, that.avvocatoDelegato) &&
                Objects.equals(note, that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRepPatronato, convenzione, spese, decorrenzaSuccessiva, codice, tipoPratica, tribunale, ruoloGenerale, dataUltimaUdienza, patronatoProvenienza, giudice, avvocatoDelegato, note);
    }
}
