package it.shiftlab.lawyer.jpa.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tribunali", schema = "lawyer2", catalog = "")
public class TribunaliEntity {
    private long id;
    private String tribunali;
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
    @Column(name = "tribunali")
    public String getTribunali() {
        return tribunali;
    }

    public void setTribunali(String tribunali) {
        this.tribunali = tribunali;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TribunaliEntity that = (TribunaliEntity) o;
        return id == that.id &&
                Objects.equals(tribunali, that.tribunali);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tribunali);
    }

    @OneToOne(mappedBy = "tribunaliByTribunaliId")
    public ReportPatronatoEntity getReportPatronatoesById() {
        return reportPatronatoesById;
    }

    public void setReportPatronatoesById(ReportPatronatoEntity reportPatronatoesById) {
        this.reportPatronatoesById = reportPatronatoesById;
    }
}
