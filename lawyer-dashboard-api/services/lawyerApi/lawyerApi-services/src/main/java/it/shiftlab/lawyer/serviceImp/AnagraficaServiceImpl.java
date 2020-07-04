package it.shiftlab.lawyer.serviceImp;

import it.shiftlab.lawyer.jpa.entity.AnagraficaClienteEntity;
import it.shiftlab.lawyer.jpa.entity.ReportAmministrativeEntity;
import it.shiftlab.lawyer.jpa.entity.ReportPatronatoEntity;
import it.shiftlab.lawyer.jpa.repository.AnagraficaRepository;
import it.shiftlab.lawyer.jpa.repository.ReportAmministrativeRepository;
import it.shiftlab.lawyer.jpa.repository.ReportPatronatoRepository;
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
    public void findById() {
        Optional<AnagraficaClienteEntity> byId = anagraficaRepository.findByNome("Pippo");
        if(byId.isPresent()) {
            byId.get().setNome("Alieno88");
            byId.get().setCognome("caiser");
            byId.get().setCitta("Roma");
            byId.get().setCodiceFiscale("nomino");
            byId.get().getReportAmministrativeByIdRepAmministrative().setNote("pisoliniasdsad");
            byId.get().getReportAmministrativeByIdRepAmministrative().setNumeroFaldone(2);
            byId.get().getReportAmministrativeByIdRepAmministrative().setAltro("Altro");
            anagraficaRepository.save(byId.get());
        } else {
            AnagraficaClienteEntity entity = new AnagraficaClienteEntity();
//            entity.setIdAnagrafica(1);
            entity.setNome("Pippo");
            entity.setCognome("pluto");
            entity.setCitta("Napoli");
            entity.setCodiceFiscale("asd45asd");

            ReportAmministrativeEntity repAmm = new ReportAmministrativeEntity();
            repAmm.setNote("asdasdas");
            repAmm.setNumeroFaldone(1);
            ReportAmministrativeEntity save = reportAmministrativeRepository.save(repAmm);
            entity.setReportAmministrativeByIdRepAmministrative(save);
            ReportPatronatoEntity patronatoEntity = new ReportPatronatoEntity();
            patronatoEntity.setAvvocatoDelegato("Cozzolino");
            patronatoEntity.setGiudice("Cucciolo");
            ReportPatronatoEntity savePatronato = reportPatronatoRepository.save(patronatoEntity);
            entity.setReportPatronatoByIdRepPatronato(savePatronato);
            anagraficaRepository.save(entity);
        }
    }
}
