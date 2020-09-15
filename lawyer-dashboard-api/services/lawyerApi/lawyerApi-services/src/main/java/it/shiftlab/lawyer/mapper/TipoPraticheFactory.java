package it.shiftlab.lawyer.mapper;

import it.shiftlab.lawyer.dto.TipoPraticheDto;
import it.shiftlab.lawyer.jpa.entity.TipoPraticheEntity;

public class TipoPraticheFactory {

    public static TipoPraticheEntity mapToEntity(TipoPraticheDto dto) {
        return new TipoPraticheEntity(dto.getId(),dto.getTipoPratica());
    }

    public static TipoPraticheDto mapToDto(TipoPraticheEntity entity) {
        return entity == null ? new TipoPraticheDto() : new TipoPraticheDto(entity.getId(), entity.getTipoPratica());
    }
}
