package it.shiftlab.lawyer.mapper;

import it.shiftlab.lawyer.dto.TribunaleDto;
import it.shiftlab.lawyer.jpa.entity.TribunaliEntity;

public class TribunaleFactory {

    public static TribunaleDto mapToDto(TribunaliEntity tribunaliEntity){
        return tribunaliEntity == null ? new TribunaleDto() : new TribunaleDto(tribunaliEntity.getId(), tribunaliEntity.getTribunali());
    }

    public static TribunaliEntity mapToEntity(TribunaleDto tribunaliEntity){
        return new TribunaliEntity(tribunaliEntity.getId(), tribunaliEntity.getTribunali());
    }
}
