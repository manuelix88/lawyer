package it.shiftlab.lawyer.mapper;

import it.shiftlab.lawyer.dto.AnagraficaDto;
import it.shiftlab.lawyer.dto.ReportAmministrativeDto;
import it.shiftlab.lawyer.dto.ReportPatronatoDto;
import it.shiftlab.lawyer.jpa.entity.AnagraficaClienteEntity;
import it.shiftlab.lawyer.jpa.entity.ReportAmministrativeEntity;
import it.shiftlab.lawyer.jpa.entity.ReportPatronatoEntity;

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
                mapRepPatronatoEntityToDto(entity.getReportPatronatoByIdRepPatronato()),mapRepAmmEntitytoDto(entity.getReportAmministrativeByIdRepAmministrative()));
    }

    public static ReportAmministrativeDto mapRepAmmEntitytoDto(ReportAmministrativeEntity entity){
        return new ReportAmministrativeDto(entity.getIdRepAmministrative(),
                entity.getQualifica(),
                entity.getDataPagamento(),
                entity.getNumeroFaldone(), entity.getRicordoCedu(), entity.getAltro(), entity.getNote());
    }

    public static ReportPatronatoDto mapRepPatronatoEntityToDto(ReportPatronatoEntity entity) {
        return new ReportPatronatoDto(entity.getIdRepPatronato(),
                entity.getConvenzione(),
                entity.getSpese(), entity.getDecorrenzaSuccessiva(), entity.getCodice(),entity.getTipoPratica(),
                entity.getTribunale(),entity.getRuoloGenerale(),entity.getDataUltimaUdienza(),entity.getPatronatoProvenienza(),
                entity.getGiudice(),entity.getAvvocatoDelegato(),entity.getNote());
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
                dto.getDataNasacita());
//                mapRepPatronatoDtoToEntity(dto.getReportPatronatoDto()),mapRepAmmDtoToEntity(dto.getReportAmministrativeDto()));
    }
    public static ReportPatronatoEntity mapRepPatronatoDtoToEntity(ReportPatronatoDto dto) {
        return new ReportPatronatoEntity(dto.getIdRepPatronato(),
                dto.getConvenzione(),
                dto.getSpese(), dto.getDecorrenzaSuccessiva(), dto.getCodice(),dto.getTipoPratica(),
                dto.getTribunale(),dto.getRuoloGenerale(),dto.getDataUltimaUdienza(),dto.getPatronatoProvenienza(),
                dto.getGiudice(),dto.getAvvocatoDelegato(),dto.getNote());
    }

    public static ReportAmministrativeEntity mapRepAmmDtoToEntity(ReportAmministrativeDto dto){
        return new ReportAmministrativeEntity(dto.getIdRepAmministrative(),
                dto.getQualifica(),
                dto.getDataPagamento(),
                dto.getNumeroFaldone(),
                dto.getRicordoCedu(),
                dto.getAltro(),
                dto.getNote());
    }
}
