package it.shiftlab.lawyer.dto;

public class AvvocatoDelegatoDto {

    private long id;

    private String avvocatoDelegato;

    public AvvocatoDelegatoDto() {
    }

    public AvvocatoDelegatoDto(String avvocatoDelegato) {
        this.avvocatoDelegato = avvocatoDelegato;
    }

    public AvvocatoDelegatoDto(long id, String avvocatoDelegato) {
        this.id = id;
        this.avvocatoDelegato = avvocatoDelegato;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAvvocatoDelegato() {
        return avvocatoDelegato;
    }

    public void setAvvocatoDelegato(String avvocatoDelegato) {
        this.avvocatoDelegato = avvocatoDelegato;
    }
}
