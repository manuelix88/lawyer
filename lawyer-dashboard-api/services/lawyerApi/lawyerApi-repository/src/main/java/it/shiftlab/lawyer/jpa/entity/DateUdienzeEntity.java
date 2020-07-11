package it.shiftlab.lawyer.jpa.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "DATE_UDIENZE")
public class DateUdienzeEntity {
    private long id;
    private Boolean enable;
    private Date dataUdienza;
    private ReportPatronatoEntity reportPatronatoByIdRepPatronato;

    public DateUdienzeEntity() {
    }

    public DateUdienzeEntity(long id, Boolean enable, Date dataUdienza) {
        this.id = id;
        this.enable = enable;
        this.dataUdienza = dataUdienza;
    }

    public DateUdienzeEntity(long id, Boolean enable, Date dataUdienza, ReportPatronatoEntity reportPatronatoByIdRepPatronato) {
        this.id = id;
        this.enable = enable;
        this.dataUdienza = dataUdienza;
        this.reportPatronatoByIdRepPatronato = reportPatronatoByIdRepPatronato;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    public Date getDataUdienza() {
        return dataUdienza;
    }

    public void setDataUdienza(Date dataUdienza) {
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
