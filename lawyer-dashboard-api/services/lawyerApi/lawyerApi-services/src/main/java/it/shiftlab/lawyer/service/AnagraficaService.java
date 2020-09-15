package it.shiftlab.lawyer.service;

import it.shiftlab.lawyer.dto.AnagraficaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AnagraficaService {

    AnagraficaDto findById(long id);

    void saveAnagrafica(AnagraficaDto anagraficaDto);

    void updateAnagrafica(AnagraficaDto anagraficaDto);

    Page<AnagraficaDto> listAnagraficaFilter(Pageable pageable, Integer faldone, String name, String cognome, String codiceFiscale, String qualifica, String documentazione , String ruoloGenerale);
}
