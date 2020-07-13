package it.shiftlab.lawyer.jpa.repository;

import it.shiftlab.lawyer.jpa.entity.CodiciReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CodiciJpaRepository extends JpaRepository<CodiciReportEntity, Long> {

    CodiciReportEntity findByCode(String code);

    CodiciReportEntity getByCode(String code);
}
