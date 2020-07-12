package it.shiftlab.lawyer.serviceImp;

import it.shiftlab.lawyer.dto.AnagraficaDto;
import it.shiftlab.lawyer.dto.ReportPatronatoDto;
import it.shiftlab.lawyer.exception.clazz.AnagraficaAlreadyExistException;
import it.shiftlab.lawyer.exception.clazz.DataAlreadyExistException;
import it.shiftlab.lawyer.jpa.entity.*;
import it.shiftlab.lawyer.jpa.repository.*;
import it.shiftlab.lawyer.mapper.*;
import it.shiftlab.lawyer.service.AnagraficaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnagraficaServiceImpl implements AnagraficaService {


    private final AnagraficaRepository anagraficaRepository;

    private final ReportAmministrativeRepository reportAmministrativeRepository;

    private final ReportPatronatoRepository reportPatronatoRepository;

    private final DateUdienzeJpaRepository dateUdienzeJpaRepository;

    private final CodiciJpaRepository codiciJpaRepository;

    private final TribunaliJpaRepository tribunaliJpaRepository;

    private final StatusJpaRepository statusJpaRepository;



    @Autowired
    public AnagraficaServiceImpl(AnagraficaRepository anagraficaRepository, ReportAmministrativeRepository reportAmministrativeRepository, ReportPatronatoRepository reportPatronatoRepository, DateUdienzeJpaRepository dateUdienzeJpaRepository, CodiciJpaRepository codiciJpaRepository, TribunaliJpaRepository tribunaliJpaRepository, StatusJpaRepository statusJpaRepository) {
        this.anagraficaRepository = anagraficaRepository;
        this.reportAmministrativeRepository = reportAmministrativeRepository;
        this.reportPatronatoRepository = reportPatronatoRepository;
        this.dateUdienzeJpaRepository = dateUdienzeJpaRepository;
        this.codiciJpaRepository = codiciJpaRepository;
        this.tribunaliJpaRepository = tribunaliJpaRepository;
        this.statusJpaRepository = statusJpaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public AnagraficaDto findById(long id) {

        Optional<AnagraficaClienteEntity> anagraficaById = anagraficaRepository.findById(id);
        if (!anagraficaById.isPresent()) {
            throw new AnagraficaAlreadyExistException("Id anagrafica non trovato: " + id);
        }
        AnagraficaDto anagraficaDto = AnagraficaFactory.mapAnagraficaEntityToDto(anagraficaById.get());
        return anagraficaDto;
    }

    @Override
    @Transactional
    public void saveAnagrafica(AnagraficaDto anagraficaDto) {
        if (anagraficaDto.getCodiceFiscale() != null && !StringUtils.isEmpty(anagraficaDto.getCodiceFiscale())) {
            Optional<AnagraficaClienteEntity> byCodiceFiscale = anagraficaRepository.findByCodiceFiscale(anagraficaDto.getCodiceFiscale());

            if (byCodiceFiscale.isPresent()) {
                throw new DataAlreadyExistException("Esiste già un utente con il seguente codice fiscale " + anagraficaDto.getCodiceFiscale());
            }
        }

        AnagraficaClienteEntity entity = AnagraficaFactory.mapAnagraficaDtoToEntity(anagraficaDto);

        if(anagraficaDto.getReportAmministrative() != null) {
            ReportAmministrativeEntity repAmm = AnagraficaFactory.mapRepAmmDtoToEntity(anagraficaDto.getReportAmministrative());
            ReportAmministrativeEntity save = reportAmministrativeRepository.save(repAmm);
            entity.setReportAmministrativeByIdRepAmministrative(save);
        }
        //PATRONATO
        if(anagraficaDto.getReportPatronato() != null) {
            ReportPatronatoEntity patronatoEntity = AnagraficaFactory.mapRepPatronatoDtoToEntity(anagraficaDto.getReportPatronato());
            if (anagraficaDto.getReportPatronato().getCodice() != null) {
                if (anagraficaDto.getReportPatronato().getCodice().getCode() != null) {
                    if (anagraficaDto.getReportPatronato().getCodice().getId() != null) {
                        Optional<CodiciReportEntity> byCode = codiciJpaRepository.findByCode(anagraficaDto.getReportPatronato().getStatus().getStatus());
                        if(byCode.isPresent()) {
                            anagraficaDto.getReportPatronato().getCodice().setId(byCode.get().getId());
                        }
                    }
                    CodiciReportEntity codiciReportEntity = CodiceFactory.mapToEntity(anagraficaDto.getReportPatronato().getCodice());
                    patronatoEntity.setCodiciReportByCodiciReportId(codiciReportEntity);
                }
            }

            if (anagraficaDto.getReportPatronato().getTribunale()!= null) {
                if (anagraficaDto.getReportPatronato().getTribunale().getTribunali() != null) {
                    if (anagraficaDto.getReportPatronato().getTribunale().getId() != null) {
                        Optional<TribunaliEntity> byTribunali = tribunaliJpaRepository.findByTribunali(anagraficaDto.getReportPatronato().getTribunale().getTribunali());
                        if (byTribunali.isPresent()) {
                            anagraficaDto.getReportPatronato().getTribunale().setId(byTribunali.get().getId());
                        }
                    }
                    TribunaliEntity tribunaliEntity = TribunaleFactory.mapToEntity(anagraficaDto.getReportPatronato().getTribunale());
                    patronatoEntity.setTribunaliByTribunaliId(tribunaliEntity);
                }
            }

            if(anagraficaDto.getReportPatronato().getStatus() != null) {
                if(anagraficaDto.getReportPatronato().getStatus().getStatus() != null) {
                    if (anagraficaDto.getReportPatronato().getStatus().getId() == null) {
                        Optional<StatusEntity> byStatus = statusJpaRepository.findByStatus(anagraficaDto.getReportPatronato().getStatus().getStatus());
                        if (byStatus.isPresent()) {
                            anagraficaDto.getReportPatronato().getStatus().setId(byStatus.get().getId());
                        }
                    }
                    StatusEntity statusEntity = StatusFactory.mapToEntity(anagraficaDto.getReportPatronato().getStatus());
                    patronatoEntity.setStatusByStatusId(statusEntity);
                }
            }
            ReportPatronatoEntity savePatronato = reportPatronatoRepository.save(patronatoEntity);
            entity.setReportPatronatoByIdRepPatronato(savePatronato);

            if (anagraficaDto.getReportPatronato().getDateUdienze() != null) {
                List<DateUdienzeEntity> list = DateUdienzeFactory.mapToEntity(anagraficaDto.getReportPatronato().getDateUdienze(), savePatronato);
                dateUdienzeJpaRepository.saveAll(list);
//                patronatoEntity.setDateUdienzesByIdRepPatronato(dateUdienzeEntities);
            }


        }

        anagraficaRepository.save(entity);
    }

    private ReportPatronatoEntity patronato(ReportPatronatoDto reportPatronatoDto) {
        return null;
    }

    @Override
    @Transactional
    public void updateAnagrafica(AnagraficaDto anagraficaDto) {
        Optional<AnagraficaClienteEntity> byIdAnagrafica = anagraficaRepository.findById(anagraficaDto.getIdAnagrafica());

        if (!byIdAnagrafica.isPresent()) {
            throw new AnagraficaAlreadyExistException("Id anagrafica non trovato: " + anagraficaDto.getIdAnagrafica());
        }


        AnagraficaClienteEntity entity = AnagraficaFactory.mapAnagraficaDtoToEntity(anagraficaDto);
        if(anagraficaDto.getReportAmministrative() != null) {
            ReportAmministrativeEntity repAmm = AnagraficaFactory.mapRepAmmDtoToEntity(anagraficaDto.getReportAmministrative());
            ReportAmministrativeEntity save = reportAmministrativeRepository.save(repAmm);
            entity.setReportAmministrativeByIdRepAmministrative(save);
        }


        ReportPatronatoEntity patronatoEntity = AnagraficaFactory.mapRepPatronatoDtoToEntity(anagraficaDto.getReportPatronato());
        //PATRONATO
        if (anagraficaDto.getReportPatronato().getCodice() != null) {
            if (anagraficaDto.getReportPatronato().getCodice().getCode() != null) {
                CodiciReportEntity codiciReportEntity = CodiceFactory.mapToEntity(anagraficaDto.getReportPatronato().getCodice());
                patronatoEntity.setCodiciReportByCodiciReportId(codiciReportEntity);
            }
        }

        if (anagraficaDto.getReportPatronato().getTribunale()!= null) {
            if (anagraficaDto.getReportPatronato().getTribunale(). getTribunali() != null) {
                TribunaliEntity tribunaliEntity = TribunaleFactory.mapToEntity(anagraficaDto.getReportPatronato().getTribunale());
                patronatoEntity.setTribunaliByTribunaliId(tribunaliEntity);
            }
        }

        if(anagraficaDto.getReportPatronato().getStatus() != null) {
            if(anagraficaDto.getReportPatronato().getStatus().getStatus() != null) {
                StatusEntity statusEntity = StatusFactory.mapToEntity(anagraficaDto.getReportPatronato().getStatus());
                patronatoEntity.setStatusByStatusId(statusEntity);
            }
        }
        ReportPatronatoEntity savePatronato = reportPatronatoRepository.save(patronatoEntity);

        if (anagraficaDto.getReportPatronato().getDateUdienze() != null) {
            List<DateUdienzeEntity> list = DateUdienzeFactory.mapToEntity(anagraficaDto.getReportPatronato().getDateUdienze(), savePatronato);
            dateUdienzeJpaRepository.saveAll(list);
        }
        entity.setReportPatronatoByIdRepPatronato(savePatronato);

        anagraficaRepository.save(entity);
    }

    @Override
    public Page<AnagraficaDto> listAnagraficaFilter(Pageable pageable, Integer faldone, String name, String cognome, String codiceFiscale, String qualifica, String documentazione) {
        String nameUpper = name == null ? null : name.toUpperCase();
        String cognomeUpper = cognome == null ? null : cognome.toUpperCase();
        String codiceFiscaleUpper = codiceFiscale == null ? null : codiceFiscale.toUpperCase();
        String qualificaUpper = qualifica == null ? null : qualifica.toUpperCase();
        String documentazioneUpper = documentazione == null ? null : documentazione.toUpperCase();
        Page<AnagraficaClienteEntity> anagraficaClienteEntities = anagraficaRepository.listAnagraficaFilter(pageable, faldone, nameUpper, cognomeUpper, codiceFiscaleUpper, qualificaUpper, documentazioneUpper);
        return new PageImpl<>(anagraficaClienteEntities.stream().map(AnagraficaFactory::mapAnagraficaEntityToDto).collect(Collectors.toList()),pageable, anagraficaClienteEntities.getTotalElements());
    }
}
