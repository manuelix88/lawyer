package it.shiftlab.lawyer.serviceImp;

import it.shiftlab.lawyer.dto.CodiceDto;
import it.shiftlab.lawyer.dto.StatusDto;
import it.shiftlab.lawyer.dto.TribunaleDto;
import it.shiftlab.lawyer.jpa.entity.StatusEntity;
import it.shiftlab.lawyer.jpa.entity.TribunaliEntity;
import it.shiftlab.lawyer.jpa.repository.StatusJpaRepository;
import it.shiftlab.lawyer.jpa.repository.TribunaliJpaRepository;
import it.shiftlab.lawyer.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenericServiceImpl implements GenericService {

    private StatusJpaRepository statusJpaRepository;

    private TribunaliJpaRepository tribunaliJpaRepository;

    @Autowired
    public GenericServiceImpl(StatusJpaRepository statusJpaRepository,TribunaliJpaRepository tribunaliJpaRepository) {
        this.statusJpaRepository = statusJpaRepository;
//        this.codiciJpaRepository = codiciJpaRepository;
        this.tribunaliJpaRepository = tribunaliJpaRepository;
    }

    @Override
    public List<CodiceDto> findAllCodice() {
//        List<CodiciReportEntity> all = codiciJpaRepository.findAll();
//        List<CodiceDto> output = new ArrayList<>();
//        CodiceDto dto;
//        for (CodiciReportEntity entity : all) {
//            dto = new CodiceDto(entity.getId(), entity.getCode());
//            output.add(dto);
//        }
        return null;
    }

    @Override
    public List<TribunaleDto> findAllTribunali() {
        List<TribunaleDto> output = new ArrayList<>();
        List<TribunaliEntity> all = tribunaliJpaRepository.findAll();
        TribunaleDto  dto;
        for (TribunaliEntity entity : all) {
            dto = new TribunaleDto(entity.getId(),entity.getTribunali());
            output.add(dto);
        }
        return output;
    }

    @Override
    public List<StatusDto> findAllStatus() {
        List<StatusDto>  output = new ArrayList<>();
        StatusDto dto;
        List<StatusEntity> all = statusJpaRepository.findAll();
        for (StatusEntity entity: all ) {
            dto = new StatusDto(entity.getId(), entity.getStatus());
            output.add(dto);
        }
        return output;
    }
}
