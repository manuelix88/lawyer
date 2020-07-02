package it.shiftlab.lawyer.jpa.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "ANAGRAFICA_CLIENTE", schema = "lawyer_", catalog = "")
public class AnagraficaClienteEntity {
    private int idAnagrafica;
    private String nome;
    private String cognome;
    private String email;
    private String codiceFiscale;
    private String nazione;
    private String indirizzo;
    private String citta;
    private String provincia;
    private Date dataNasacita;

    @Id
    @Column(name = "ID_ANAGRAFICA")
    public int getIdAnagrafica() {
        return idAnagrafica;
    }

    public void setIdAnagrafica(int idAnagrafica) {
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
        AnagraficaClienteEntity that = (AnagraficaClienteEntity) o;
        return idAnagrafica == that.idAnagrafica &&
                Objects.equals(nome, that.nome) &&
                Objects.equals(cognome, that.cognome) &&
                Objects.equals(email, that.email) &&
                Objects.equals(codiceFiscale, that.codiceFiscale) &&
                Objects.equals(nazione, that.nazione) &&
                Objects.equals(indirizzo, that.indirizzo) &&
                Objects.equals(citta, that.citta) &&
                Objects.equals(provincia, that.provincia) &&
                Objects.equals(dataNasacita, that.dataNasacita);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAnagrafica, nome, cognome, email, codiceFiscale, nazione, indirizzo, citta, provincia, dataNasacita);
    }
}
