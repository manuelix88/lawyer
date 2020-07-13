package it.shiftlab.lawyer.jpa.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "STATUS")
public class StatusEntity {
    private long id;
    private String status;
//    private ReportPatronatoEntity reportPatronatoesById;

    public StatusEntity() {
    }

    public StatusEntity(long id, String status) {
        this.id = id;
        this.status = status;
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
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusEntity that = (StatusEntity) o;
        return id == that.id &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status);
    }

//    @OneToOne(mappedBy = "statusByStatusId")
//    public ReportPatronatoEntity getReportPatronatoesById() {
//        return reportPatronatoesById;
//    }
//
//    public void setReportPatronatoesById(ReportPatronatoEntity reportPatronatoesById) {
//        this.reportPatronatoesById = reportPatronatoesById;
//    }
}
