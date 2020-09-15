package it.shiftlab.lawyer.dto;

public class PatronatoProvenienzaDto {

    private long id;

    private String patronato;

    public PatronatoProvenienzaDto() {
    }

    public PatronatoProvenienzaDto(String patronato) {
        this.patronato = patronato;
    }

    public PatronatoProvenienzaDto(long id, String patronato) {
        this.id = id;
        this.patronato = patronato;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPatronato() {
        return patronato;
    }

    public void setPatronato(String patronato) {
        this.patronato = patronato;
    }
}
