package it.shiftlab.lawyer.dto;

public class TribunaleDto {

    private long id;
    private String tribunali;

    public TribunaleDto() {
    }

    public TribunaleDto(long id, String tribunali) {
        this.id = id;
        this.tribunali = tribunali;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTribunali() {
        return tribunali;
    }

    public void setTribunali(String tribunali) {
        this.tribunali = tribunali;
    }
}
