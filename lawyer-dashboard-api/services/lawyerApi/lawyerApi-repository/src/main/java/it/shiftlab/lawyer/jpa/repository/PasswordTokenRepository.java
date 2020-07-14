package it.shiftlab.lawyer.jpa.repository;

import it.shiftlab.lawyer.jpa.entity.PasswordResetTokenEntity;
import it.shiftlab.lawyer.jpa.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordTokenRepository extends JpaRepository<PasswordResetTokenEntity, Long> {
    PasswordResetTokenEntity findByToken(String token);
}
