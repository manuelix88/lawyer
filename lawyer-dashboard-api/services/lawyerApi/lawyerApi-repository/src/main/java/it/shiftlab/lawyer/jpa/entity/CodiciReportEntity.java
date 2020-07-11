package it.shiftlab.lawyer.jpa.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "codici_report", schema = "lawyer2", catalog = "")
public class CodiciReportEntity {
    private long id;
    private String code;
    private ReportPatronatoEntity reportPatronatoesById;

    @Id
    @Column(name = "id")
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
