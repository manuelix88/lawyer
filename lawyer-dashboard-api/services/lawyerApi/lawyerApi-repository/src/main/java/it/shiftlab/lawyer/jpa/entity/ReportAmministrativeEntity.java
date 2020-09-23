package it.shiftlab.lawyer.jpa.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "REPORT_AMMINISTRATIVE")
public class ReportAmministrativeEntity {
    private Long idRepAmministrative;
    private String altro;
    private Date dataPagamento;
    private String note;
    private Integer numeroFaldone;
    private String qualifica;
    private String ricordoCedu;
    private String documentazione;
    private AnagraficaClienteEntity anagraficaClientesByIdRepAmministrative;
    private UUID uuid;

    public ReportAmministrativeEntity() {
    }

    public ReportAmministrativeEntity(Long idRepAmministrative, String qualifica, Date dataPagamento, Integer numeroFaldone,
                                      String ricordoCedu, String altro, String note, String documentazione, UUID uuid) {
        this.idRepAmministrative = idRepAmministrative;
        this.altro = altro;
        this.dataPagamento = dataPagamento;
        this.note = note;
        this.numeroFaldone = numeroFaldone;
        this.qualifica = qualifica;
        this.ricordoCedu = ricordoCedu;
        this.documentazione = documentazione;
        this.uuid = uuid;
    }

    @Id
    @Column(name = "id_rep_amministrative")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdRepAmministrative() {
        return idRepAmministrative;
    }

    public void setIdRepAmministrative(Long idRepAmministrative) {
        this.idRepAmministrative = idRepAmministrative;
    }

    @Basic
    @Column(name = "altro")
    public String getAltro() {
        return altro;
    }

    public void setAltro(String altro) {
        this.altro = altro;
    }

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "data_pagamento")
    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
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
    @Column(name = "numero_faldone")
    public Integer getNumeroFaldone() {
        return numeroFaldone;
    }

    public void setNumeroFaldone(Integer numeroFaldone) {
        this.numeroFaldone = numeroFaldone;
    }

    @Basic
    @Column(name = "qualifica")
    public String getQualifica() {
        return qualifica;
    }

    public void setQualifica(String qualifica) {
        this.qualifica = qualifica;
    }

    @Basic
    @Column(name = "ricordo_cedu")
    public String getRicordoCedu() {
        return ricordoCedu;
    }

    public void setRicordoCedu(String ricordoCedu) {
        this.ricordoCedu = ricordoCedu;
    }

    @Basic
    @Column(name = "documentazione")
    public String getDocumentazione() {
        return documentazione;
    }

    public void setDocumentazione(String documentazione) {
        this.documentazione = documentazione;
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
        return Objects.hash(idRepAmministrative, altro, dataPagamento, note, numeroFaldone, qualifica, ricordoCedu, documentazione);
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_anagrafica", referencedColumnName = "id_anagrafica")
    public AnagraficaClienteEntity getAnagraficaClientesByIdRepAmministrative() {
        return anagraficaClientesByIdRepAmministrative;
    }

    public void setAnagraficaClientesByIdRepAmministrative(AnagraficaClienteEntity anagraficaClientesByIdRepAmministrative) {
        this.anagraficaClientesByIdRepAmministrative = anagraficaClientesByIdRepAmministrative;
    }
}
