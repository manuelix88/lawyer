package it.shiftlab.lawyer.jpa.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "anagrafica_cliente", schema = "lawyer2", catalog = "")
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
    private ReportAmministrativeEntity reportAmministrativeByIdRepAmministrative;
    private ReportPatronatoEntity reportPatronatoByIdRepPatronato;

    @Id
    @Column(name = "id_anagrafica")
    public long getIdAnagrafica() {
        return idAnagrafica;
    }

    public void setIdAnagrafica(long idAnagrafica) {
        this.idAnagrafica = idAnagrafica;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnagraficaClienteEntity entity = (AnagraficaClienteEntity) o;
        return idAnagrafica == entity.idAnagrafica &&
                Objects.equals(citta, entity.citta) &&
                Objects.equals(codiceFiscale, entity.codiceFiscale) &&
                Objects.equals(cognome, entity.cognome) &&
                Objects.equals(dataNasacita, entity.dataNasacita) &&
                Objects.equals(email, entity.email) &&
                Objects.equals(indirizzo, entity.indirizzo) &&
                Objects.equals(nazione, entity.nazione) &&
                Objects.equals(nome, entity.nome) &&
                Objects.equals(provincia, entity.provincia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAnagrafica, citta, codiceFiscale, cognome, dataNasacita, email, indirizzo, nazione, nome, provincia);
    }

    @OneToOne
    @JoinColumn(name = "id_rep_amministrative", referencedColumnName = "id_rep_amministrative")
    public ReportAmministrativeEntity getReportAmministrativeByIdRepAmministrative() {
        return reportAmministrativeByIdRepAmministrative;
    }

    public void setReportAmministrativeByIdRepAmministrative(ReportAmministrativeEntity reportAmministrativeByIdRepAmministrative) {
        this.reportAmministrativeByIdRepAmministrative = reportAmministrativeByIdRepAmministrative;
    }

    @OneToOne
    @JoinColumn(name = "id_rep_patronato", referencedColumnName = "id_rep_patronato")
    public ReportPatronatoEntity getReportPatronatoByIdRepPatronato() {
        return reportPatronatoByIdRepPatronato;
    }

    public void setReportPatronatoByIdRepPatronato(ReportPatronatoEntity reportPatronatoByIdRepPatronato) {
        this.reportPatronatoByIdRepPatronato = reportPatronatoByIdRepPatronato;
    }
}
