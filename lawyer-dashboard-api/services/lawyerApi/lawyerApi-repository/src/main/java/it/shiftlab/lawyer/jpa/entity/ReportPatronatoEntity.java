package it.shiftlab.lawyer.jpa.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.*;

@Data
@ToString
@Entity
@Table(name = "REPORT_PATRONATO")
public class ReportPatronatoEntity {

    private Long idRepPatronato;

    private AvvocatoDelegatoEntity avvocatoDelegato;

    private String convenzione;

    private Date dataUltimaUdienza;

    private String decorrenzaSuccessiva;

    private String giudice;

    private String note;

    private PatronatiEntity patronatoProvenienza;

    private String ruoloGenerale;

    private String spese;

    private TipoPraticheEntity tipoPratica;

    private AnagraficaClienteEntity anagraficaClientesByIdRepPatronato;

    private List<DateUdienzeEntity> dateUdienzesByIdRepPatronato;

    private StatusEntity statusByStatusId;

    private TribunaliEntity tribunaliByTribunaliId;

    private UUID uuid;

    public ReportPatronatoEntity() {
    }

    public ReportPatronatoEntity(Long idRepPatronato,  String convenzione,
                                 String decorrenzaSuccessiva, String giudice, String note,
                                 String ruoloGenerale, String spese, UUID uuid) {
        this.idRepPatronato = idRepPatronato;
        this.convenzione = convenzione;
        this.decorrenzaSuccessiva = decorrenzaSuccessiva;
        this.giudice = giudice;
        this.note = note;
        this.ruoloGenerale = ruoloGenerale;
        this.spese = spese;
        this.uuid = uuid;
    }

    @Id
    @Column(name = "id_rep_patronato")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdRepPatronato() {
        return idRepPatronato;
    }

    public void setIdRepPatronato(Long idRepPatronato) {
        this.idRepPatronato = idRepPatronato;
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

//    @Basic
//    @Column(name = "patronato_provenienza")
//    public String getPatronatoProvenienza() {
//        return patronatoProvenienza;
//    }

//    public void setPatronatoProvenienza(String patronatoProvenienza) {
//        this.patronatoProvenienza = patronatoProvenienza;
//    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "avvocati_delegati_id", referencedColumnName = "id", nullable = false)
    public AvvocatoDelegatoEntity getAvvocatoDelegato() {
        return avvocatoDelegato;
    }

    public void setAvvocatoDelegato(AvvocatoDelegatoEntity avvocatoDelegato) {
        this.avvocatoDelegato = avvocatoDelegato;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patronati_id", referencedColumnName = "id", nullable = false)
    public PatronatiEntity getPatronatoProvenienza() {
        return patronatoProvenienza;
    }

    public void setPatronatoProvenienza(PatronatiEntity patronatoProvenienza) {
        this.patronatoProvenienza = patronatoProvenienza;
    }
//    @Column(name = "codici_report")
//    public String getCodiciReport() {
//        return codiciReport;
//    }
//
//    public void setCodiciReport(String codiciReport) {
//        this.codiciReport = codiciReport;
//    }

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_pratica_id", referencedColumnName = "id", nullable = false)
    public TipoPraticheEntity getTipoPratica() {
        return tipoPratica;
    }

    public void setTipoPratica(TipoPraticheEntity tipoPratica) {
        this.tipoPratica = tipoPratica;
    }

    @Column(name="uuid", unique = true)
    @Type(type="org.hibernate.type.UUIDCharType")
    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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
    public List<DateUdienzeEntity> getDateUdienzesByIdRepPatronato() {
        return dateUdienzesByIdRepPatronato;
    }

    public void setDateUdienzesByIdRepPatronato(List<DateUdienzeEntity> dateUdienzesByIdRepPatronato) {
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

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "codici_report_id", referencedColumnName = "id", nullable = false)
//    public CodiciReportEntity getCodiciReportByCodiciReportId() {
//        return codiciReportByCodiciReportId;
//    }

//    public void setCodiciReportByCodiciReportId(CodiciReportEntity codiciReportByCodiciReportId) {
//        this.codiciReportByCodiciReportId = codiciReportByCodiciReportId;
//    }

    @OneToOne
    @JoinColumn(name = "tribunali_id", referencedColumnName = "id", nullable = false)
    public TribunaliEntity getTribunaliByTribunaliId() {
        return tribunaliByTribunaliId;
    }

    public void setTribunaliByTribunaliId(TribunaliEntity tribunaliByTribunaliId) {
        this.tribunaliByTribunaliId = tribunaliByTribunaliId;
    }
}
