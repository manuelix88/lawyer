package it.shiftlab.lawyer.dto;

public class CodiceDto {

    private long id;
    private String code;

    public CodiceDto(long id, String code) {
        this.id = id;
        this.code = code;
    }

    public CodiceDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

