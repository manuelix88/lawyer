package it.shiftlab.lawyer.jpa.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "ANAGRAFICA_CLIENTE")
public class AnagraficaClienteEntity {
    private long idAnagrafica;
    private String citta;
    private String codiceFiscale;
    private String cognome;
    private Date dataNasacita;
    private String email;
    private String indirizzo;
    private String nazione;
    private String nome;
    private String provincia;
    private String codice;
    private ReportAmministrativeEntity reportAmministrativeByIdRepAmministrative;
    private ReportPatronatoEntity reportPatronatoByIdRepPatronato;
    private UUID uuid;


    public AnagraficaClienteEntity() {

    }

    public AnagraficaClienteEntity(long idAnagrafica, String nome, String cognome, String email, String codiceFiscale,
                                   String nazione, String indirizzo, String citta, String provincia, Date dataNascita, UUID uuid, String codice) {
        this.idAnagrafica = idAnagrafica;
        this.citta = citta;
        this.codiceFiscale = codiceFiscale;
        this.cognome = cognome;
        this.email = email;
        this.indirizzo = indirizzo;
        this.nazione = nazione;
        this.nome = nome;
        this.provincia = provincia;
        this.dataNasacita = dataNascita;
        this.uuid = uuid;
        this.codice = codice;
    }

    @Id
    @Column(name = "id_anagrafica")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getIdAnagrafica() {
        return idAnagrafica;
    }

    public void setIdAnagrafica(long idAnagrafica) {
        this.idAnagrafica = idAnagrafica;
    }

    public String getCodice() {
        return codice;
    }

    @Column(name = "codice")
    public void setCodice(String codice) {
        this.codice = codice;
    }

    @Basic
    @Column(name = "citta")
    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    @Basic
    @Column(name = "codice_fiscale")
    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    @Basic
    @Column(name = "cognome")
    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "data_nasacita")
    public Date getDataNasacita() {
        return dataNasacita;
    }

    public void setDataNasacita(Date dataNasacita) {
        this.dataNasacita = dataNasacita;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "indirizzo")
    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    @Basic
    @Column(name = "nazione")
    public String getNazione() {
        return nazione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    @Basic
    @Column(name = "nome")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Basic
    @Column(name = "provincia")
    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
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
        return Objects.hash(idAnagrafica, citta, codiceFiscale, cognome, dataNasacita, email, indirizzo, nazione, nome, provincia);
    }

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rep_amministrative", referencedColumnName = "id_rep_amministrative")
    public ReportAmministrativeEntity getReportAmministrativeByIdRepAmministrative() {
        return reportAmministrativeByIdRepAmministrative;
    }

    public void setReportAmministrativeByIdRepAmministrative(ReportAmministrativeEntity reportAmministrativeByIdRepAmministrative) {
        this.reportAmministrativeByIdRepAmministrative = reportAmministrativeByIdRepAmministrative;
    }
//    @OneToOne(mappedBy = "anagraficaClientesByIdRepPatronato", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rep_patronato", referencedColumnName = "id_rep_patronato")
    public ReportPatronatoEntity getReportPatronatoByIdRepPatronato() {
        return reportPatronatoByIdRepPatronato;
    }

    public void setReportPatronatoByIdRepPatronato(ReportPatronatoEntity reportPatronatoByIdRepPatronato) {
        this.reportPatronatoByIdRepPatronato = reportPatronatoByIdRepPatronato;
    }

}
