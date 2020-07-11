package it.shiftlab.lawyer.jpa.repository;

import it.shiftlab.lawyer.jpa.entity.ReportAmministrativeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportAmministrativeRepository extends JpaRepository<ReportAmministrativeEntity, Long> {
}
