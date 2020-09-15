package it.shiftlab.lawyer.mapper;

import it.shiftlab.lawyer.dto.PatronatoProvenienzaDto;
import it.shiftlab.lawyer.jpa.entity.PatronatiEntity;

public class PatronatoProvenienzaFactory {

    public static  PatronatiEntity mapToEntity(PatronatoProvenienzaDto dto) {
        return new PatronatiEntity(dto.getId(), dto.getPatronato());
    }

    public static PatronatoProvenienzaDto mapToDto(PatronatiEntity entity) {
        return new PatronatoProvenienzaDto(entity.getId(), entity.getPatronato());
    }
}
