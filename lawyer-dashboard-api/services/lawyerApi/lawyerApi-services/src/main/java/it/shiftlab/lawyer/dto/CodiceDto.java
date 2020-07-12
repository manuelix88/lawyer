package it.shiftlab.lawyer.dto;

public class CodiceDto {

    private Long id;
    private String code;

    public CodiceDto(Long id, String code) {
        this.id = id;
        this.code = code;
    }

    public CodiceDto() {
    }

    public CodiceDto(String code) {
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

