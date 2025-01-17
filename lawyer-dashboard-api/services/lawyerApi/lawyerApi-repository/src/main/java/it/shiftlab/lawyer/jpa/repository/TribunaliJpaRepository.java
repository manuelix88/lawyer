package it.shiftlab.lawyer.jpa.repository;

import it.shiftlab.lawyer.jpa.entity.TribunaliEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TribunaliJpaRepository extends JpaRepository<TribunaliEntity, Long> {

    Optional<TribunaliEntity> findByTribunali(String code);
}
