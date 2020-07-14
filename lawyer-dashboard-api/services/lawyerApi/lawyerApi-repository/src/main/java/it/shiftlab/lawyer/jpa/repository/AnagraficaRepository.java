package it.shiftlab.lawyer.jpa.repository;

import it.shiftlab.lawyer.jpa.entity.AnagraficaClienteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnagraficaRepository extends PagingAndSortingRepository<AnagraficaClienteEntity, Long> {

    Optional<AnagraficaClienteEntity> findByNome(String nome);

    Optional<AnagraficaClienteEntity> findByCodiceFiscale(String codicefiscale);

    @Query(value=" from AnagraficaClienteEntity a where (:name is null or upper(a.nome) like :name%) " +
            "and (:cognome is null or upper(a.cognome)  like :cognome%) " +
            "and (:codiceFiscale is null or upper(a.codiceFiscale)  like :codiceFiscale%) " +
            "and (:faldone is null or a.reportAmministrativeByIdRepAmministrative.numeroFaldone = :faldone) " +
            "and (:qualifica is null or upper(a.reportAmministrativeByIdRepAmministrative.qualifica) = :qualifica) " +
            "and (:documentazione is null or upper(a.reportAmministrativeByIdRepAmministrative.documentazione) like :documentazione%) ")
    Page<AnagraficaClienteEntity> listAnagraficaFilter(Pageable pageable,
                                                       @Param("faldone") Integer faldone,
                                                       @Param("name") String name, @Param("cognome") String cognome,
                                                       @Param("codiceFiscale") String codiceFiscale, @Param("qualifica") String qualifica, @Param("documentazione") String documentazione);
}
