package it.shiftlab.lawyer.batch.model;

public class BatchreportPatronatoDto {

    private String nome;

    private String cod;

    private String status;

    private String convenzione;

    private String spese;

    private String decorrenzaSuccessiva;

    private String tipoPratica;

    private String tribunale;

    private String ruoloGenerale;

    private String giudice;

    private String dateUdienza;

    private String note;

    private String patronatoProvenienza;

    private String avvocatoDelegato;

    public BatchreportPatronatoDto() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getConvenzione() {
        return convenzione;
    }

    public void setConvenzione(String convenzione) {
        this.convenzione = convenzione;
    }

    public String getSpese() {
        return spese;
    }

    public void setSpese(String spese) {
        this.spese = spese;
    }

    public String getDecorrenzaSuccessiva() {
        return decorrenzaSuccessiva;
    }

    public void setDecorrenzaSuccessiva(String decorrenzaSuccessiva) {
        this.decorrenzaSuccessiva = decorrenzaSuccessiva;
    }

    public String getTipoPratica() {
        return tipoPratica;
    }

    public void setTipoPratica(String tipoPratica) {
        this.tipoPratica = tipoPratica;
    }

    public String getTribunale() {
        return tribunale;
    }

    public void setTribunale(String tribunale) {
        this.tribunale = tribunale;
    }

    public String getRuoloGenerale() {
        return ruoloGenerale;
    }

    public void setRuoloGenerale(String ruoloGenerale) {
        this.ruoloGenerale = ruoloGenerale;
    }

    public String getGiudice() {
        return giudice;
    }

    public void setGiudice(String giudice) {
        this.giudice = giudice;
    }

    public String getDateUdienza() {
        return dateUdienza;
    }

    public void setDateUdienza(String dateUdienza) {
        this.dateUdienza = dateUdienza;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPatronatoProvenienza() {
        return patronatoProvenienza;
    }

    public void setPatronatoProvenienza(String patronatoProvenienza) {
        this.patronatoProvenienza = patronatoProvenienza;
    }

    public String getAvvocatoDelegato() {
        return avvocatoDelegato;
    }

    public void setAvvocatoDelegato(String avvocatoDelegato) {
        this.avvocatoDelegato = avvocatoDelegato;
    }

    @Override
    public String toString() {
        return "BatchreportPatronatoDto{" +
                "nome='" + nome + '\'' +
                ", cod='" + cod + '\'' +
                ", status='" + status + '\'' +
                ", convenzione='" + convenzione + '\'' +
                ", spese='" + spese + '\'' +
                ", decorrenzaSuccessiva='" + decorrenzaSuccessiva + '\'' +
                ", tipoPratica='" + tipoPratica + '\'' +
                ", tribunale='" + tribunale + '\'' +
                ", ruoloGenerale='" + ruoloGenerale + '\'' +
                ", giudice='" + giudice + '\'' +
                ", dateUdienza='" + dateUdienza + '\'' +
                ", note='" + note + '\'' +
                ", patronatoProvenienza='" + patronatoProvenienza + '\'' +
                ", avvocatoDelegato='" + avvocatoDelegato + '\'' +
                '}';
    }
}
