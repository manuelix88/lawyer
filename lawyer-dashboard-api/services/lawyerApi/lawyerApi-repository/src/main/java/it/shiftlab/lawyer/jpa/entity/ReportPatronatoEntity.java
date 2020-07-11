package it.shiftlab.lawyer.jpa.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "report_patronato", schema = "lawyer2", catalog = "")
public class ReportPatronatoEntity {
    private long idRepPatronato;
    private String avvocatoDelegato;
    private String convenzione;
    private Date dataUltimaUdienza;
    private String decorrenzaSuccessiva;
    private String giudice;
    private String note;
    private String patronatoProvenienza;
    private String ruoloGenerale;
    private String spese;
    private String tipoPratica;
    private AnagraficaClienteEntity anagraficaClientesByIdRepPatronato;
    private Collection<DateUdienzeEntity> dateUdienzesByIdRepPatronato;
    private StatusEntity statusByStatusId;
    private CodiciReportEntity codiciReportByCodiciReportId;
    private TribunaliEntity tribunaliByTribunaliId;

    @Id
    @Column(name = "id_rep_patronato")
    public long getIdRepPatronato() {
        return idRepPatronato;
    }

    public void setIdRepPatronato(long idRepPatronato) {
        this.idRepPatronato = idRepPatronato;
    }

    @Basic
    @Column(name = "avvocato_delegato")
    public String getAvvocatoDelegato() {
        return avvocatoDelegato;
    }

    public void setAvvocatoDelegato(String avvocatoDelegato) {
        this.avvocatoDelegato = avvocatoDelegato;
    }

    @Basic
    @Column(name = "convenzione")
    public String getConvenzione() {
        return convenzione;
    }

    public void setConvenzione(String convenzione) {
        this.convenzione = convenzione;
    }

    @Basic
    @Column(name = "data_ultima_udienza")
    public Date getDataUltimaUdienza() {
        return dataUltimaUdienza;
    }

    public void setDataUltimaUdienza(Date dataUltimaUdienza) {
        this.dataUltimaUdienza = dataUltimaUdienza;
    }

    @Basic
    @Column(name = "decorrenza_successiva")
    public String getDecorrenzaSuccessiva() {
        return decorrenzaSuccessiva;
    }

    public void setDecorrenzaSuccessiva(String decorrenzaSuccessiva) {
        this.decorrenzaSuccessiva = decorrenzaSuccessiva;
    }

    @Basic
    @Column(name = "giudice")
    public String getGiudice() {
        return giudice;
    }

    public void setGiudice(String giudice) {
        this.giudice = giudice;
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
    @Column(name = "patronato_provenienza")
    public String getPatronatoProvenienza() {
        return patronatoProvenienza;
    }

    public void setPatronatoProvenienza(String patronatoProvenienza) {
        this.patronatoProvenienza = patronatoProvenienza;
    }

    @Basic
    @Column(name = "ruolo_generale")
    public String getRuoloGenerale() {
        return ruoloGenerale;
    }

    public void setRuoloGenerale(String ruoloGenerale) {
        this.ruoloGenerale = ruoloGenerale;
    }

    @Basic
    @Column(name = "spese")
    public String getSpese() {
        return spese;
    }

    public void setSpese(String spese) {
        this.spese = spese;
    }

    @Basic
    @Column(name = "tipo_pratica")
    public String getTipoPratica() {
        return tipoPratica;
    }

    public void setTipoPratica(String tipoPratica) {
        this.tipoPratica = tipoPratica;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportPatronatoEntity that = (ReportPatronatoEntity) o;
        return idRepPatronato == that.idRepPatronato &&
                Objects.equals(avvocatoDelegato, that.avvocatoDelegato) &&
                Objects.equals(convenzione, that.convenzione) &&
                Objects.equals(dataUltimaUdienza, that.dataUltimaUdienza) &&
                Objects.equals(decorrenzaSuccessiva, that.decorrenzaSuccessiva) &&
                Objects.equals(giudice, that.giudice) &&
                Objects.equals(note, that.note) &&
                Objects.equals(patronatoProvenienza, that.patronatoProvenienza) &&
                Objects.equals(ruoloGenerale, that.ruoloGenerale) &&
                Objects.equals(spese, that.spese) &&
                Objects.equals(tipoPratica, that.tipoPratica);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRepPatronato, avvocatoDelegato, convenzione, dataUltimaUdienza, decorrenzaSuccessiva, giudice, note, patronatoProvenienza, ruoloGenerale, spese, tipoPratica);
    }

    @OneToOne(mappedBy = "reportPatronatoByIdRepPatronato")
    public AnagraficaClienteEntity getAnagraficaClientesByIdRepPatronato() {
        return anagraficaClientesByIdRepPatronato;
    }

    public void setAnagraficaClientesByIdRepPatronato(AnagraficaClienteEntity anagraficaClientesByIdRepPatronato) {
        this.anagraficaClientesByIdRepPatronato = anagraficaClientesByIdRepPatronato;
    }

    @OneToMany(mappedBy = "reportPatronatoByIdRepPatronato")
    public Collection<DateUdienzeEntity> getDateUdienzesByIdRepPatronato() {
        return dateUdienzesByIdRepPatronato;
    }

    public void setDateUdienzesByIdRepPatronato(Collection<DateUdienzeEntity> dateUdienzesByIdRepPatronato) {
        this.dateUdienzesByIdRepPatronato = dateUdienzesByIdRepPatronato;
    }

    @OneToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
    public StatusEntity getStatusByStatusId() {
        return statusByStatusId;
    }

    public void setStatusByStatusId(StatusEntity statusByStatusId) {
        this.statusByStatusId = statusByStatusId;
    }

    @OneToOne
    @JoinColumn(name = "codici_report_id", referencedColumnName = "id", nullable = false)
    public CodiciReportEntity getCodiciReportByCodiciReportId() {
        return codiciReportByCodiciReportId;
    }

    public void setCodiciReportByCodiciReportId(CodiciReportEntity codiciReportByCodiciReportId) {
        this.codiciReportByCodiciReportId = codiciReportByCodiciReportId;
    }

    @OneToOne
    @JoinColumn(name = "tribunali_id", referencedColumnName = "id", nullable = false)
    public TribunaliEntity getTribunaliByTribunaliId() {
        return tribunaliByTribunaliId;
    }

    public void setTribunaliByTribunaliId(TribunaliEntity tribunaliByTribunaliId) {
        this.tribunaliByTribunaliId = tribunaliByTribunaliId;
    }
}
