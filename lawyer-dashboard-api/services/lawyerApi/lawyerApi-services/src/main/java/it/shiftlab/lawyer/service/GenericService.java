package it.shiftlab.lawyer.service;

import it.shiftlab.lawyer.dto.CodiceDto;
import it.shiftlab.lawyer.dto.StatusDto;
import it.shiftlab.lawyer.dto.TribunaleDto;

import java.util.List;

public interface GenericService {

    List<CodiceDto> findAllCodice();

    List<TribunaleDto> findAllTribunali();

    List<StatusDto> findAllStatus();


}
