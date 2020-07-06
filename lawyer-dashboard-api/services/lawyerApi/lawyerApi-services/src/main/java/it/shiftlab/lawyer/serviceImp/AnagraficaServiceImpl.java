package it.shiftlab.lawyer.serviceImp;

import it.shiftlab.lawyer.dto.AnagraficaDto;
import it.shiftlab.lawyer.exception.clazz.AnagraficaAlreadyExistException;
import it.shiftlab.lawyer.exception.clazz.DataAlreadyExistException;
import it.shiftlab.lawyer.jpa.entity.AnagraficaClienteEntity;
import it.shiftlab.lawyer.jpa.entity.ReportAmministrativeEntity;
import it.shiftlab.lawyer.jpa.entity.ReportPatronatoEntity;
import it.shiftlab.lawyer.jpa.repository.AnagraficaRepository;
import it.shiftlab.lawyer.jpa.repository.ReportAmministrativeRepository;
import it.shiftlab.lawyer.jpa.repository.ReportPatronatoRepository;
import it.shiftlab.lawyer.mapper.AnagraficaFactory;
import it.shiftlab.lawyer.service.AnagraficaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AnagraficaServiceImpl implements AnagraficaService {

    @Autowired
    private AnagraficaRepository anagraficaRepository;

    @Autowired
    private ReportAmministrativeRepository reportAmministrativeRepository;

    @Autowired
    private ReportPatronatoRepository reportPatronatoRepository;

    @Override
    @Transactional
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
        Optional<AnagraficaClienteEntity> byCodiceFiscale = anagraficaRepository.findByCodiceFiscale(anagraficaDto.getCodiceFiscale());

        if (byCodiceFiscale.isPresent() && byCodiceFiscale.get().getCodiceFiscale() != null) {
            throw new DataAlreadyExistException("Esiste già un utente con il seguente codice fiscale " + anagraficaDto.getCodiceFiscale());
        }
        AnagraficaClienteEntity entity = AnagraficaFactory.mapAnagraficaDtoToEntity(anagraficaDto);
        ReportAmministrativeEntity repAmm = AnagraficaFactory.mapRepAmmDtoToEntity(anagraficaDto.getReportAmministrative());
        ReportAmministrativeEntity save = reportAmministrativeRepository.save(repAmm);
        entity.setReportAmministrativeByIdRepAmministrative(save);
        ReportPatronatoEntity patronatoEntity = AnagraficaFactory.mapRepPatronatoDtoToEntity(anagraficaDto.getReportPatronato());
        ReportPatronatoEntity savePatronato = reportPatronatoRepository.save(patronatoEntity);
        entity.setReportPatronatoByIdRepPatronato(savePatronato);
        anagraficaRepository.save(entity);
    }

    @Override
    @Transactional
    public void updateAnagrafica(AnagraficaDto anagraficaDto) {
        Optional<AnagraficaClienteEntity> byIdAnagrafica = anagraficaRepository.findById(anagraficaDto.getIdAnagrafica());

        if (!byIdAnagrafica.isPresent()) {
            throw new AnagraficaAlreadyExistException("Id anagrafica non trovato: " + anagraficaDto.getIdAnagrafica());
        }

        AnagraficaClienteEntity entity = AnagraficaFactory.mapAnagraficaDtoToEntity(anagraficaDto);
        ReportAmministrativeEntity repAmm = AnagraficaFactory.mapRepAmmDtoToEntity(anagraficaDto.getReportAmministrative());
        ReportAmministrativeEntity save = reportAmministrativeRepository.save(repAmm);
        entity.setReportAmministrativeByIdRepAmministrative(save);
        ReportPatronatoEntity patronatoEntity = AnagraficaFactory.mapRepPatronatoDtoToEntity(anagraficaDto.getReportPatronato());
        ReportPatronatoEntity savePatronato = reportPatronatoRepository.save(patronatoEntity);
        entity.setReportPatronatoByIdRepPatronato(savePatronato);
        anagraficaRepository.save(entity);
    }
}
