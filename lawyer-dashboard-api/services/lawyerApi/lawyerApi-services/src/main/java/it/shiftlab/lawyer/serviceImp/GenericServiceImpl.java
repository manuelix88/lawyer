package it.shiftlab.lawyer.serviceImp;

import it.shiftlab.lawyer.dto.*;
import it.shiftlab.lawyer.jpa.entity.*;
import it.shiftlab.lawyer.jpa.repository.*;
import it.shiftlab.lawyer.mapper.AvvocatoDelegatoFactory;
import it.shiftlab.lawyer.mapper.PatronatoProvenienzaFactory;
import it.shiftlab.lawyer.mapper.TipoPraticheFactory;
import it.shiftlab.lawyer.mapper.TribunaleFactory;
import it.shiftlab.lawyer.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenericServiceImpl implements GenericService {

    private final StatusJpaRepository statusJpaRepository;

    private final TribunaliJpaRepository tribunaliJpaRepository;

    private final AvvocatoDelegatoRepository avvocatoDelegatoRepository;

    private final PatronatiProvenienzaRepository patronatiProvenienzaRepository;

    private final TipoPraticheRepository tipoPraticheRepository;

    @Autowired
    public GenericServiceImpl(StatusJpaRepository statusJpaRepository, TribunaliJpaRepository tribunaliJpaRepository, AvvocatoDelegatoRepository avvocatoDelegatoRepository, PatronatiProvenienzaRepository patronatiProvenienzaRepository, TipoPraticheRepository tipoPraticheRepository) {
        this.statusJpaRepository = statusJpaRepository;
        this.tribunaliJpaRepository = tribunaliJpaRepository;
        this.avvocatoDelegatoRepository = avvocatoDelegatoRepository;
        this.patronatiProvenienzaRepository = patronatiProvenienzaRepository;
        this.tipoPraticheRepository = tipoPraticheRepository;
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
            dto = TribunaleFactory.mapToDto(entity);
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

    @Override
    public List<AvvocatoDelegatoDto> findAllAvvocati() {
        List<AvvocatoDelegatoDto>  output = new ArrayList<>();
        AvvocatoDelegatoDto dto;
        List<AvvocatoDelegatoEntity> all = avvocatoDelegatoRepository.findAll();
        for (AvvocatoDelegatoEntity entity: all ) {
            dto = AvvocatoDelegatoFactory.mapToDto(entity);
            output.add(dto);
        }
        return output;
    }

    @Override
    public List<PatronatoProvenienzaDto> findAllPatronati() {
        List<PatronatoProvenienzaDto>  output = new ArrayList<>();
        PatronatoProvenienzaDto dto;
        List<PatronatiEntity> all = patronatiProvenienzaRepository.findAll();
        for (PatronatiEntity entity: all ) {
            dto = PatronatoProvenienzaFactory.mapToDto(entity);
            output.add(dto);
        }
        return output;
    }

    @Override
    public List<TipoPraticheDto> findAllTipoPratiche() {
        List<TipoPraticheDto>  output = new ArrayList<>();
        TipoPraticheDto dto;
        List<TipoPraticheEntity> all = tipoPraticheRepository.findAll();
        for (TipoPraticheEntity entity: all ) {
            dto = TipoPraticheFactory.mapToDto(entity);
            output.add(dto);
        }
        return output;
    }
}
