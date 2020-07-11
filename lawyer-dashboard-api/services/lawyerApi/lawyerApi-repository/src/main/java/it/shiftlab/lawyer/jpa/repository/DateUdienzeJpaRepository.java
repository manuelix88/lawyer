package it.shiftlab.lawyer.jpa.repository;

import it.shiftlab.lawyer.jpa.entity.DateUdienzeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DateUdienzeJpaRepository extends JpaRepository<DateUdienzeEntity, Long> {
}
