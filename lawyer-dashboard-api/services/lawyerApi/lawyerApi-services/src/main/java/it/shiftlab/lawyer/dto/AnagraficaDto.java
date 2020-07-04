package it.shiftlab.lawyer.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Builder
@Getter @Setter
public class AnagraficaDto {

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

    private ReportPatronatoDto reportPatronatoDto;

    private ReportAmministrativeDto reportAmministrativeDto;

    public AnagraficaDto(long idAnagrafica, String nome, String cognome, String email, String codiceFiscale, String nazione, String indirizzo, String citta, String provincia, Date dataNasacita, ReportPatronatoDto reportPatronatoDto, ReportAmministrativeDto reportAmministrativeDto) {
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
        this.reportPatronatoDto = reportPatronatoDto;
        this.reportAmministrativeDto = reportAmministrativeDto;
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

    public Date getDataNasacita() {
        return dataNasacita;
    }

    public void setDataNasacita(Date dataNasacita) {
        this.dataNasacita = dataNasacita;
    }

    public ReportPatronatoDto getReportPatronatoDto() {
        return reportPatronatoDto;
    }

    public void setReportPatronatoDto(ReportPatronatoDto reportPatronatoDto) {
        this.reportPatronatoDto = reportPatronatoDto;
    }

    public ReportAmministrativeDto getReportAmministrativeDto() {
        return reportAmministrativeDto;
    }

    public void setReportAmministrativeDto(ReportAmministrativeDto reportAmministrativeDto) {
        this.reportAmministrativeDto = reportAmministrativeDto;
    }
}
