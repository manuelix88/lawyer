package it.shiftlab.lawyer.dto;


import java.util.Date;

public class DataUdienzaDto {
    private long id;
    private Boolean enable;
    private Date dataUdienza;
    private String uuid;

    public DataUdienzaDto() {
    }

    public DataUdienzaDto(long id, Boolean enable, Date dataUdienza, String uuid) {
        this.id = id;
        this.enable = enable;
        this.dataUdienza = dataUdienza;
        this.uuid = uuid;
    }

    public DataUdienzaDto(Boolean enable, Date dataUdienza, String uuid) {
        this.enable = enable;
        this.dataUdienza = dataUdienza;
        this.uuid = uuid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Date getDataUdienza() {
        return dataUdienza;
    }

    public void setDataUdienza(Date dataUdienza) {
        this.dataUdienza = dataUdienza;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
