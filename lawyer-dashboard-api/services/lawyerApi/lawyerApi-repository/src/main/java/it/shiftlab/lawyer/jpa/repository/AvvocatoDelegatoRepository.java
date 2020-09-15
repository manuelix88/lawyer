package it.shiftlab.lawyer.jpa.repository;

import it.shiftlab.lawyer.jpa.entity.AvvocatoDelegatoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvvocatoDelegatoRepository  extends JpaRepository<AvvocatoDelegatoEntity, Long> {

    Optional<AvvocatoDelegatoEntity> findByAvvocatoDelegato(String avvocatoDelegato);
}
