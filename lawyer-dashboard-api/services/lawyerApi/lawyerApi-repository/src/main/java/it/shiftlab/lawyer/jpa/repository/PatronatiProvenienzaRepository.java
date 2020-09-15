package it.shiftlab.lawyer.jpa.repository;

import it.shiftlab.lawyer.jpa.entity.PatronatiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatronatiProvenienzaRepository extends JpaRepository<PatronatiEntity, Long> {

    Optional<PatronatiEntity> findByPatronato(String patronato);
}
