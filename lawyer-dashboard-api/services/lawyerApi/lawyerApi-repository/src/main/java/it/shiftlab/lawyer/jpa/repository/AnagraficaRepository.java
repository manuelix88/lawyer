package it.shiftlab.lawyer.jpa.repository;

import it.shiftlab.lawyer.jpa.entity.AnagraficaClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnagraficaRepository extends JpaRepository<AnagraficaClienteEntity, Long> {

    Optional<AnagraficaClienteEntity> findByNome(String nome);

    Optional<AnagraficaClienteEntity> findByCodiceFiscale(String codicefiscale);
}
