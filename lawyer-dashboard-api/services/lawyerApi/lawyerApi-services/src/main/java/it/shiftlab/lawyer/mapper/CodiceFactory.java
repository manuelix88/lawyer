package it.shiftlab.lawyer.mapper;

import it.shiftlab.lawyer.dto.CodiceDto;
import it.shiftlab.lawyer.jpa.entity.CodiciReportEntity;

public class CodiceFactory {

    public static CodiceDto mapToDto(CodiciReportEntity entity) {
        return entity == null ? new CodiceDto() : new CodiceDto(entity.getId(), entity.getCode());
    }

    public static CodiciReportEntity mapToEntity(CodiceDto  dto) {
        return new CodiciReportEntity(dto.getId(), dto.getCode());
    }
}
