package it.shiftlab.lawyer.service;

import it.shiftlab.lawyer.dto.AnagraficaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface AnagraficaService {

    AnagraficaDto findById(long id);

    void saveAnagrafica(AnagraficaDto anagraficaDto);

    void updateAnagrafica(AnagraficaDto anagraficaDto);

    void deleteAnagrafica(UUID anagraficaId);

    Page<AnagraficaDto> listAnagraficaFilter(Pageable pageable, Integer faldone, String name, String cognome, String codiceFiscale, String qualifica,
                                             String documentazione );

    Page<AnagraficaDto> listAnagraficaFilterReportPatronato(Pageable pageable, String name, String cognome,
                                                            String codiceFiscale, String ruoloGenerale,
                                                            Long patronatoProvenienza, Long avvocatoDelegato);
}
