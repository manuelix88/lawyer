package it.shiftlab.lawyer.jpa.repository;

import it.shiftlab.lawyer.jpa.entity.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusJpaRepository extends JpaRepository<StatusEntity,Long> {

    Optional<StatusEntity> findByStatus(String status);
}
