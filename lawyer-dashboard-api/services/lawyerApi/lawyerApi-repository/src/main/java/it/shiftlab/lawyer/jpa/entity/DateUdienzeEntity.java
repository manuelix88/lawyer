package it.shiftlab.lawyer.jpa.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "date_udienze", schema = "lawyer2", catalog = "")
public class DateUdienzeEntity {
    private long id;
    private Boolean enable;
    private Timestamp dataUdienza;
    private ReportPatronatoEntity reportPatronatoByIdRepPatronato;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "enable")
    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @Basic
    @Column(name = "data_udienza")
    public Timestamp getDataUdienza() {
        return dataUdienza;
    }

    public void setDataUdienza(Timestamp dataUdienza) {
        this.dataUdienza = dataUdienza;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateUdienzeEntity that = (DateUdienzeEntity) o;
        return id == that.id &&
                Objects.equals(enable, that.enable) &&
                Objects.equals(dataUdienza, that.dataUdienza);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, enable, dataUdienza);
    }

    @ManyToOne
    @JoinColumn(name = "id_rep_patronato", referencedColumnName = "id_rep_patronato")
    public ReportPatronatoEntity getReportPatronatoByIdRepPatronato() {
        return reportPatronatoByIdRepPatronato;
    }

    public void setReportPatronatoByIdRepPatronato(ReportPatronatoEntity reportPatronatoByIdRepPatronato) {
        this.reportPatronatoByIdRepPatronato = reportPatronatoByIdRepPatronato;
    }
}
