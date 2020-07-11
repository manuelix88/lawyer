package it.shiftlab.lawyer.mapper;

import it.shiftlab.lawyer.dto.DataUdienzaDto;
import it.shiftlab.lawyer.jpa.entity.DateUdienzeEntity;
import it.shiftlab.lawyer.jpa.entity.ReportPatronatoEntity;

import java.util.ArrayList;
import java.util.List;

public class DateUdienzeFactory {

    public static List<DataUdienzaDto> mapToDto(List<DateUdienzeEntity> udienzeEntities) {
        List<DataUdienzaDto> list = new ArrayList<>();
        DataUdienzaDto dto = new DataUdienzaDto();
        if (udienzeEntities != null) {

            for (DateUdienzeEntity entity  : udienzeEntities) {
                dto = new DataUdienzaDto(entity.getId(), entity.getEnable(), entity.getDataUdienza());
                list.add(dto);
            }
        }
        return list;
    }

    public static List<DateUdienzeEntity> mapToEntity(List<DataUdienzaDto> dtos, ReportPatronatoEntity savePatronato) {
        List<DateUdienzeEntity> list = new ArrayList<>();
        DateUdienzeEntity enity = new DateUdienzeEntity();
        for (DataUdienzaDto dto : dtos) {
            enity = new DateUdienzeEntity(dto.getId(), dto.getEnable(), dto.getDataUdienza(),savePatronato);
            list.add(enity);
        }
        return list;
    }
}
