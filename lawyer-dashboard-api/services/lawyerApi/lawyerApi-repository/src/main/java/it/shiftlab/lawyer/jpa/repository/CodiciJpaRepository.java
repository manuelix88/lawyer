package it.shiftlab.lawyer.jpa.repository;

import it.shiftlab.lawyer.jpa.entity.CodiciReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CodiciJpaRepository extends JpaRepository<CodiciReportEntity, Long> {

    Optional<CodiciReportEntity> findByCode(String code);
}