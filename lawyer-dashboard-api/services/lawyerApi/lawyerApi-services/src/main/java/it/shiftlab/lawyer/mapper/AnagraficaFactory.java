package it.shiftlab.lawyer.mapper;

import it.shiftlab.lawyer.dto.AnagraficaDto;
import it.shiftlab.lawyer.dto.ReportAmministrativeDto;
import it.shiftlab.lawyer.dto.ReportPatronatoDto;
import it.shiftlab.lawyer.jpa.entity.AnagraficaClienteEntity;
import it.shiftlab.lawyer.jpa.entity.ReportAmministrativeEntity;
import it.shiftlab.lawyer.jpa.entity.ReportPatronatoEntity;

import java.util.UUID;

public class AnagraficaFactory {

    public AnagraficaFactory() {
    }

    public static AnagraficaDto mapAnagraficaEntityToDto(AnagraficaClienteEntity entity){
        return new AnagraficaDto(
                entity.getIdAnagrafica(),
                entity.getNome(),
                entity.getCognome(),
                entity.getEmail(),
                entity.getCodiceFiscale(),
                entity.getNazione(),
                entity.getIndirizzo(),
                entity.getCitta(),
                entity.getProvincia(),
                entity.getDataNasacita(),
                mapRepPatronatoEntityToDto(entity.getReportPatronatoByIdRepPatronato()),
                mapRepAmmEntitytoDto(entity.getReportAmministrativeByIdRepAmministrative()),
                entity.getUuid().toString());
    }

    public static ReportAmministrativeDto mapRepAmmEntitytoDto(ReportAmministrativeEntity entity){
        return entity== null ? new ReportAmministrativeDto() : new ReportAmministrativeDto(entity.getIdRepAmministrative(),
                entity.getQualifica(),
                entity.getDataPagamento(),
                entity.getNumeroFaldone(), entity.getRicordoCedu(), entity.getAltro(), entity.getNote(), entity.getDocumentazione(), entity.getUuid().toString());
    }

    public static ReportPatronatoDto mapRepPatronatoEntityToDto(ReportPatronatoEntity entity) {
        return entity == null ? new ReportPatronatoDto() : new ReportPatronatoDto(entity.getIdRepPatronato(),StatusFactory.mapToDto(entity.getStatusByStatusId()), entity.getConvenzione(), entity.getSpese(),
                entity.getDecorrenzaSuccessiva(),CodiceFactory.mapToDto(entity.getCodiciReportByCodiciReportId()), entity.getTipoPratica(),
                TribunaleFactory.mapToDto(entity.getTribunaliByTribunaliId()),entity.getRuoloGenerale(), DateUdienzeFactory.mapToDto(entity.getDateUdienzesByIdRepPatronato()),
                entity.getPatronatoProvenienza(),entity.getGiudice(), entity.getAvvocatoDelegato(), entity.getNote(), entity.getUuid().toString());

    }


    public static AnagraficaClienteEntity mapAnagraficaDtoToEntity(AnagraficaDto dto){
        return new AnagraficaClienteEntity(
                dto.getIdAnagrafica(),
                dto.getNome(),
                dto.getCognome(),
                dto.getEmail(),
                dto.getCodiceFiscale(),
                dto.getNazione(),
                dto.getIndirizzo(),
                dto.getCitta(),
                dto.getProvincia(),
                dto.getDataNascita(),
                dto.getUuid() != null ? UUID.fromString(dto.getUuid()) : UUID.randomUUID());
    }

    public static ReportPatronatoEntity mapRepPatronatoDtoToEntity(ReportPatronatoDto dto) {
        return new ReportPatronatoEntity(dto.getIdRepPatronato(),
                dto.getAvvocatoDelegato(),
                dto.getConvenzione(),
                dto.getDecorrenzaSuccessiva(),
                dto.getGiudice(),
                dto.getNote(),
                dto.getPatronatoProvenienza(),
                dto.getRuoloGenerale(),
                dto.getSpese(),
                dto.getTipoPratica(),
                dto.getUuid() != null ? UUID.fromString(dto.getUuid()) : UUID.randomUUID()
        );
    }

    public static ReportAmministrativeEntity mapRepAmmDtoToEntity(ReportAmministrativeDto dto){
        return new ReportAmministrativeEntity(dto.getIdRepAmministrative(),
                dto.getQualifica(),
                dto.getDataPagamento(),
                dto.getNumeroFaldone(),
                dto.getRicordoCedu(),
                dto.getAltro(),
                dto.getNote(),
                dto.getDocumentazione(),
                dto.getUuid() != null ? UUID.fromString(dto.getUuid()) : UUID.randomUUID());
    }
}
