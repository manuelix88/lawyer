package it.shiftlab.lawyer.jpa.repository;

import it.shiftlab.lawyer.jpa.entity.TipoPraticheEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoPraticheRepository extends JpaRepository<TipoPraticheEntity, Long> {

    Optional<TipoPraticheEntity> findByTipoPratica(String tipoPratica);
}
