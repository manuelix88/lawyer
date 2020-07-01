package it.shiftlab.lawyer.jpa.repository;


import it.shiftlab.lawyer.jpa.entity.AuthoritiesEntity;
import it.shiftlab.lawyer.jpa.entity.AuthorityName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<AuthoritiesEntity, Long> {
    AuthoritiesEntity findByName(AuthorityName name);

}
