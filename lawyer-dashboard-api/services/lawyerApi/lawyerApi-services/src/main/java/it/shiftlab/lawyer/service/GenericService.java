package it.shiftlab.lawyer.service;

import it.shiftlab.lawyer.dto.*;

import java.util.List;

public interface GenericService {

    List<CodiceDto> findAllCodice();

    List<TribunaleDto> findAllTribunali();

    List<StatusDto> findAllStatus();

    List<AvvocatoDelegatoDto> findAllAvvocati();

    List<PatronatoProvenienzaDto> findAllPatronati();

    List<TipoPraticheDto> findAllTipoPratiche();


}
