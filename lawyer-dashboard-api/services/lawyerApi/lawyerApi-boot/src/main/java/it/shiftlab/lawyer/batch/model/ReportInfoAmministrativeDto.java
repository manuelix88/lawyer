package it.shiftlab.lawyer.batch.model;

import org.springframework.util.StringUtils;

import java.util.Date;

public class ReportInfoAmministrativeDto {

    private String provincia;
    private String nome;
    private String fiscale;
    private String ricorso;
    private String documentazione;
    private String pagamento;
    private String qualifica;
    private Integer faldone;
    private String altro;
    private String note;

    public ReportInfoAmministrativeDto() {
    }

    public ReportInfoAmministrativeDto(String provincia, String nome, String fiscale, String ricorso, String documentazione, String pagamento, String qualifica, Integer faldone, String altro, String note) {
        this.provincia = provincia;
        this.nome = nome;
        this.fiscale = fiscale;
        this.ricorso = ricorso;
        this.documentazione = documentazione;
        this.pagamento = pagamento;
        this.qualifica = qualifica;
        this.faldone = faldone;
        this.altro = altro;
        this.note = note;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFiscale() {
        return fiscale;
    }

    public void setFiscale(String fiscale) {
        this.fiscale = fiscale;
    }

    public String getRicorso() {
        return ricorso;
    }

    public void setRicorso(String ricorso) {
        this.ricorso = ricorso;
    }

    public String getDocumentazione() {
        return documentazione;
    }

    public void setDocumentazione(String documentazione) {
        this.documentazione = documentazione;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    public String getQualifica() {
        return qualifica;
    }

    public void setQualifica(String qualifica) {
        this.qualifica = qualifica;
    }

    public Integer getFaldone() {
        return faldone;
    }

    public void setFaldone(Integer faldone) {
        this.faldone = faldone;
    }

    public String getAltro() {
        return altro;
    }

    public void setAltro(String altro) {
        this.altro = altro;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
