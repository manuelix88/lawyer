package it.shiftlab.lawyer.dto;


import java.sql.Timestamp;
import java.util.Date;

public class DataUdienzaDto {
    private long id;
    private Boolean enable;
    private Date dataUdienza;

    public DataUdienzaDto() {
    }

    public DataUdienzaDto(long id, Boolean enable, Date dataUdienza) {
        this.id = id;
        this.enable = enable;
        this.dataUdienza = dataUdienza;
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
}
