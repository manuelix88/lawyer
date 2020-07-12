package it.shiftlab.lawyer.dto;

public class TribunaleDto {

    private Long id;
    private String tribunali;

    public TribunaleDto() {
    }

    public TribunaleDto(Long id, String tribunali) {
        this.id = id;
        this.tribunali = tribunali;
    }

    public TribunaleDto(String tribunali) {
        this.tribunali = tribunali;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTribunali() {
        return tribunali;
    }

    public void setTribunali(String tribunali) {
        this.tribunali = tribunali;
    }
}
