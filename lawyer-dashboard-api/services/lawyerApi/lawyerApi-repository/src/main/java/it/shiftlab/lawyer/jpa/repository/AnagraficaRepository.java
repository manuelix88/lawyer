package it.shiftlab.lawyer.jpa.repository;

import it.shiftlab.lawyer.jpa.entity.AnagraficaClienteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AnagraficaRepository extends PagingAndSortingRepository<AnagraficaClienteEntity, Long> {

    Optional<AnagraficaClienteEntity> findByNome(String nome);

    void deleteAllByUuid(UUID anagraficaId);

    Optional<AnagraficaClienteEntity> findByCodiceFiscale(String codicefiscale);

    @Query(value ="from AnagraficaClienteEntity a " +
            "where (:name is null or upper(a.nome) like :name%) " +
            "and (:cognome is null or upper(a.cognome)  like :cognome%) " +
            "and (:codiceFiscale is null or upper(a.codiceFiscale)  = :codiceFiscale) " +
            "and (:faldone is null or a.reportAmministrativeByIdRepAmministrative.numeroFaldone = :faldone) " +
            "and (:qualifica is null or upper(a.reportAmministrativeByIdRepAmministrative.qualifica) = :qualifica) " +
            "and (:documentazione is null or upper(a.reportAmministrativeByIdRepAmministrative.documentazione) like :documentazione%) "
    )
    Page<AnagraficaClienteEntity> findAnagraficaFilterReportAmministrative(Pageable pageable,
                                                                           @Param("faldone") Integer faldone,
                                                                           @Param("name") String name,
                                                                           @Param("cognome") String cognome,
                                                                           @Param("codiceFiscale") String codiceFiscale,
                                                                           @Param("qualifica") String qualifica,
                                                                           @Param("documentazione") String documentazione
    );



    @Query(value ="from AnagraficaClienteEntity a " +
//            "left outer join a.reportPatronatoByIdRepPatronato.avvocatoDelegato " +
//            "left outer  join a.reportPatronatoByIdRepPatronato.patronatoProvenienza " +
            "where (:nome is null or upper(a.nome) like :nome%) " +
            "and (:cognome is null or upper(a.cognome)  like :cognome%) " +
            "and (:codiceFiscale is null or upper(a.codiceFiscale)  = :codiceFiscale) " +
            "and (:ruoloGenerale is null or upper(a.reportPatronatoByIdRepPatronato.ruoloGenerale) = :ruoloGenerale) " +
            "and (:patronato is null or upper(a.reportPatronatoByIdRepPatronato.patronatoProvenienza.patronato) = :patronato) " +
            "and (:avvocatoDelegato is null or upper(a.reportPatronatoByIdRepPatronato.avvocatoDelegato.avvocatoDelegato) = :avvocatoDelegato) "
//            "and a.reportPatronatoByIdRepPatronato.idRepPatronato is not null"
    )
    Page<AnagraficaClienteEntity> filterReportPatronato(Pageable pageable,
                                             @Param("nome") String name,
                                             @Param("cognome") String cognome,
                                             @Param("codiceFiscale") String codiceFiscale,
                                             @Param("ruoloGenerale") String ruoloGenerale,
                                             @Param("patronato") String patronato,
                                             @Param("avvocatoDelegato") String avvocatoDelegato
    );


//    @Query(nativeQuery=true, value = "select * from ANAGRAFICA_CLIENTE a inner join REPORT_PATRONATO rep on " +
//            "a.id_rep_patronato = rep.id_rep_patronato where  (:nome is null or upper(a.nome) like :nome%) " +
//            "and (:cognome is null or upper(a.cognome)  like :cognome%) " +
//            "and (:codiceFiscale is null or upper(a.codiceFiscale)  = :codiceFiscale) " +
//            "and (:ruoloGenerale is null or upper(rep.ruoloGenerale) = :ruoloGenerale) "
//    )
//    Page<AnagraficaClienteEntity> asd(Pageable pageable,
//                                      @Param("nome") String name,
//                                      @Param("cognome") String cognome,
//                                      @Param("codiceFiscale") String codiceFiscale,
//                                      @Param("ruoloGenerale") String ruoloGenerale,
//                                      @Param("patronato") String patronato,
//                                      @Param("avvocatoDelegato") String avvocatoDelegato
//    );

}
