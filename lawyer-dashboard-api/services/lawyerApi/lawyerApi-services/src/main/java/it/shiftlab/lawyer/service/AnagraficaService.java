package it.shiftlab.lawyer.service;

import it.shiftlab.lawyer.dto.AnagraficaDto;

public interface AnagraficaService {

    AnagraficaDto findById(long id);

    void saveAnagrafica(AnagraficaDto anagraficaDto);

    void updateAnagrafica(AnagraficaDto anagraficaDto);

}
