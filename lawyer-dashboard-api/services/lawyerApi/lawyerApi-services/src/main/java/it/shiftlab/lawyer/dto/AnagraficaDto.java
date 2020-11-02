package it.shiftlab.lawyer.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;


public class AnagraficaDto{

    private long idAnagrafica;

    private String nome;

    private String cognome;

    private String codice;

    private String email;

    private String codiceFiscale;

    private String nazione;

    private String indirizzo;

    private String citta;

    private String provincia;

    private Date dataNascita;

    private ReportPatronatoDto reportPatronato;

    private ReportAmministrativeDto reportAmministrative;

    private String uuid;

    private String cap;

    public AnagraficaDto(long idAnagrafica, String nome, String cognome, String email, String codiceFiscale,
                         String nazione, String indirizzo, String citta, String provincia, Date dataNascita,
                         ReportPatronatoDto reportPatronato, ReportAmministrativeDto reportAmministrative, String uuid, String codice, String cap) {
        this.idAnagrafica = idAnagrafica;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.codiceFiscale = codiceFiscale;
        this.nazione = nazione;
        this.indirizzo = indirizzo;
        this.citta = citta;
        this.provincia = provincia;
        this.dataNascita = dataNascita;
        this.reportPatronato = reportPatronato;
        this.reportAmministrative = reportAmministrative;
        this.uuid = uuid;
        this.codice = codice;
        this.cap = cap;
    }

    public AnagraficaDto() {
    }

    public AnagraficaDto(String nome, String codiceFiscale, String provincia, ReportAmministrativeDto reportAmministrative, String uuid) {
        this.nome = nome;
        this.codiceFiscale = codiceFiscale;
        this.provincia = provincia;
        this.reportAmministrative = reportAmministrative;
        this.uuid = uuid;
    }

    public AnagraficaDto(String nome, ReportPatronatoDto reportPatronato, String uuid, String codice) {
        this.nome = nome;
        this.reportPatronato = reportPatronato;
        this.uuid = uuid;
        this.codice = codice;
    }

    public long getIdAnagrafica() {
        return idAnagrafica;
    }

    public void setIdAnagrafica(long idAnagrafica) {
        this.idAnagrafica = idAnagrafica;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getNazione() {
        return nazione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public Date getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }

    public ReportPatronatoDto getReportPatronato() {
        return reportPatronato;
    }

    public void setReportPatronato(ReportPatronatoDto reportPatronato) {
        this.reportPatronato = reportPatronato;
    }

    public ReportAmministrativeDto getReportAmministrative() {
        return reportAmministrative;
    }

    public void setReportAmministrative(ReportAmministrativeDto reportAmministrative) {
        this.reportAmministrative = reportAmministrative;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    @Override
    public String toString() {
        return "AnagraficaDto{" +
                "idAnagrafica=" + idAnagrafica +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                ", codiceFiscale='" + codiceFiscale + '\'' +
                ", nazione='" + nazione + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", citta='" + citta + '\'' +
                ", provincia='" + provincia + '\'' +
                ", dataNascita=" + dataNascita +
                ", reportPatronato=" + reportPatronato +
                ", reportAmministrative=" + reportAmministrative +
                '}';
    }
}
