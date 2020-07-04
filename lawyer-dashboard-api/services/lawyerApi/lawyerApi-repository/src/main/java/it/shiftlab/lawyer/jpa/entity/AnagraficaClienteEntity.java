package it.shiftlab.lawyer.jpa.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "anagrafica_cliente", schema = "lawyer_", catalog = "")
public class AnagraficaClienteEntity {
    private long idAnagrafica;
    private String nome;
    private String cognome;
    private String email;
    private String codiceFiscale;
    private String nazione;
    private String indirizzo;
    private String citta;
    private String provincia;
    private Date dataNasacita;
    private ReportPatronatoEntity reportPatronatoByIdRepPatronato;
    private ReportAmministrativeEntity reportAmministrativeByIdRepAmministrative;

    public AnagraficaClienteEntity(long idAnagrafica, String nome, String cognome, String email, String codiceFiscale, String nazione, String indirizzo, String citta, String provincia, Date dataNasacita, ReportPatronatoEntity reportPatronatoByIdRepPatronato, ReportAmministrativeEntity reportAmministrativeByIdRepAmministrative) {
        this.idAnagrafica = idAnagrafica;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.codiceFiscale = codiceFiscale;
        this.nazione = nazione;
        this.indirizzo = indirizzo;
        this.citta = citta;
        this.provincia = provincia;
        this.dataNasacita = dataNasacita;
        this.reportPatronatoByIdRepPatronato = reportPatronatoByIdRepPatronato;
        this.reportAmministrativeByIdRepAmministrative = reportAmministrativeByIdRepAmministrative;
    }

    @Id
    @Column(name = "ID_ANAGRAFICA")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getIdAnagrafica() {
        return idAnagrafica;
    }

    public void setIdAnagrafica(long idAnagrafica) {
        this.idAnagrafica = idAnagrafica;
    }

    @Basic
    @Column(name = "NOME")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Basic
    @Column(name = "COGNOME")
    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    @Basic
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "CODICE_FISCALE")
    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    @Basic
    @Column(name = "NAZIONE")
    public String getNazione() {
        return nazione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    @Basic
    @Column(name = "INDIRIZZO")
    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    @Basic
    @Column(name = "CITTA")
    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    @Basic
    @Column(name = "PROVINCIA")
    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    @Basic
    @Column(name = "DATA_NASACITA")
    public Date getDataNasacita() {
        return dataNasacita;
    }

    public void setDataNasacita(Date dataNasacita) {
        this.dataNasacita = dataNasacita;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnagraficaClienteEntity entity = (AnagraficaClienteEntity) o;
        return idAnagrafica == entity.idAnagrafica &&
                Objects.equals(nome, entity.nome) &&
                Objects.equals(cognome, entity.cognome) &&
                Objects.equals(email, entity.email) &&
                Objects.equals(codiceFiscale, entity.codiceFiscale) &&
                Objects.equals(nazione, entity.nazione) &&
                Objects.equals(indirizzo, entity.indirizzo) &&
                Objects.equals(citta, entity.citta) &&
                Objects.equals(provincia, entity.provincia) &&
                Objects.equals(dataNasacita, entity.dataNasacita);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAnagrafica, nome, cognome, email, codiceFiscale, nazione, indirizzo, citta, provincia, dataNasacita);
    }

    @OneToOne
    @JoinColumn(name = "ID_REP_PATRONATO", referencedColumnName = "ID_REP_PATRONATO")
    public ReportPatronatoEntity getReportPatronatoByIdRepPatronato() {
        return reportPatronatoByIdRepPatronato;
    }

    public void setReportPatronatoByIdRepPatronato(ReportPatronatoEntity reportPatronatoByIdRepPatronato) {
        this.reportPatronatoByIdRepPatronato = reportPatronatoByIdRepPatronato;
    }

    @OneToOne
    @JoinColumn(name = "ID_REP_AMMINISTRATIVE", referencedColumnName = "ID_REP_AMMINISTRATIVE")
    public ReportAmministrativeEntity getReportAmministrativeByIdRepAmministrative() {
        return reportAmministrativeByIdRepAmministrative;
    }

    public void setReportAmministrativeByIdRepAmministrative(ReportAmministrativeEntity reportAmministrativeByIdRepAmministrative) {
        this.reportAmministrativeByIdRepAmministrative = reportAmministrativeByIdRepAmministrative;
    }
}
