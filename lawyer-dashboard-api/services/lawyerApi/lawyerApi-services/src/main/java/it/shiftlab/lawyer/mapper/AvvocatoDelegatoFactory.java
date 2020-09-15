package it.shiftlab.lawyer.mapper;

import it.shiftlab.lawyer.dto.AvvocatoDelegatoDto;
import it.shiftlab.lawyer.dto.CodiceDto;
import it.shiftlab.lawyer.jpa.entity.AvvocatoDelegatoEntity;
import it.shiftlab.lawyer.jpa.entity.CodiciReportEntity;

public class AvvocatoDelegatoMapper {

    public static AvvocatoDelegatoDto mapToDto(AvvocatoDelegatoEntity entity) {
        return entity == null ? new AvvocatoDelegatoDto() : new AvvocatoDelegatoDto(entity.getId(), entity.getAvvocatoDelegato());
    }

    public static AvvocatoDelegatoEntity mapToEntity(AvvocatoDelegatoDto  dto) {
        return new AvvocatoDelegatoEntity(dto.getId(), dto.getAvvocatoDelegato());
    }
}
