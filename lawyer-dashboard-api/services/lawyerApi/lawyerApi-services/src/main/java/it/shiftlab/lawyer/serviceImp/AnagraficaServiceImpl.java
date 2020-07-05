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
            throw new AnagraficaAlreadyExistException("Id angrafica non trovato: " + id);
        }

        AnagraficaDto anagraficaDto = AnagraficaFactory.mapAnagraficaEntityToDto(anagraficaById.get());
//        Optional<AnagraficaClienteEntity> byId = anagraficaRepository.findByNome("Pippo");
//        if(byId.isPresent()) {
//            byId.get().setNome("Alieno88");
//            byId.get().setCognome("caiser");
//            byId.get().setCitta("Roma");
//            byId.get().setCodiceFiscale("nomino");
//            byId.get().getReportAmministrativeByIdRepAmministrative().setNote("pisoliniasdsad");
//            byId.get().getReportAmministrativeByIdRepAmministrative().setNumeroFaldone(2);
//            byId.get().getReportAmministrativeByIdRepAmministrative().setAltro("Altro");
//            anagraficaRepository.save(byId.get());
//        }
        return anagraficaDto;
    }

    @Override
    @Transactional
    public void saveAnagrafica(AnagraficaDto anagraficaDto) {
        Optional<AnagraficaClienteEntity> byCodiceFiscale = anagraficaRepository.findByCodiceFiscale(anagraficaDto.getCodiceFiscale());

        if (byCodiceFiscale.isPresent()) {
            throw new DataAlreadyExistException("Esiste già un utente con il seguente codice fiscale " + anagraficaDto.getCodiceFiscale());
        }
        AnagraficaClienteEntity entity = AnagraficaFactory.mapAnagraficaDtoToEntity(anagraficaDto);
        ReportAmministrativeEntity repAmm = AnagraficaFactory.mapRepAmmDtoToEntity(anagraficaDto.getReportAmministrativeDto());
        ReportAmministrativeEntity save = reportAmministrativeRepository.save(repAmm);
        entity.setReportAmministrativeByIdRepAmministrative(save);
        ReportPatronatoEntity patronatoEntity = AnagraficaFactory.mapRepPatronatoDtoToEntity(anagraficaDto.getReportPatronatoDto());
        ReportPatronatoEntity savePatronato = reportPatronatoRepository.save(patronatoEntity);
        entity.setReportPatronatoByIdRepPatronato(savePatronato);
        anagraficaRepository.save(entity);
    }

    @Override
    @Transactional
    public void updateAnagrafica(AnagraficaDto anagraficaDto) {

    }
}
