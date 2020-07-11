package it.shiftlab.lawyer.jpa.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "CODICI_REPORT")
public class CodiciReportEntity {
    private long id;
    private String code;
    private ReportPatronatoEntity reportPatronatoesById;

    public CodiciReportEntity() {
    }

    public CodiciReportEntity(long id, String code) {
        this.id = id;
        this.code = code;
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
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodiciReportEntity that = (CodiciReportEntity) o;
        return id == that.id &&
                Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code);
    }

    @OneToOne(mappedBy = "codiciReportByCodiciReportId")
    public ReportPatronatoEntity getReportPatronatoesById() {
        return reportPatronatoesById;
    }

    public void setReportPatronatoesById(ReportPatronatoEntity reportPatronatoesById) {
        this.reportPatronatoesById = reportPatronatoesById;
    }
}
