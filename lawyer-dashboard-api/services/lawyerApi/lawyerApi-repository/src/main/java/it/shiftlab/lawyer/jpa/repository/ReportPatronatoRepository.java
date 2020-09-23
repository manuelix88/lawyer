package it.shiftlab.lawyer.jpa.repository;

import it.shiftlab.lawyer.jpa.entity.ReportPatronatoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportPatronatoRepository extends JpaRepository<ReportPatronatoEntity, Long> {

    List<ReportPatronatoEntity> findByRuoloGenerale(String ruolo);
}
