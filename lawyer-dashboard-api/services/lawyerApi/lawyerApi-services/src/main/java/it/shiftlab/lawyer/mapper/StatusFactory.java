package it.shiftlab.lawyer.mapper;

import it.shiftlab.lawyer.dto.StatusDto;
import it.shiftlab.lawyer.jpa.entity.StatusEntity;

public class StatusFactory {

    public static StatusDto mapToDto(StatusEntity entity) {
        return entity == null ? new StatusDto() : new StatusDto(entity.getId(), entity.getStatus());
    }

    public static StatusEntity mapToEntity(StatusDto dto) {
        return  new StatusEntity(dto.getId(), dto.getStatus());
    }
}
