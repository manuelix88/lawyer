package it.shiftlab.lawyer.batch.writer;

import it.shiftlab.lawyer.batch.model.BatchreportPatronatoDto;
import it.shiftlab.lawyer.dto.*;
import it.shiftlab.lawyer.service.AnagraficaService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ReportPatronatoWriter implements ItemWriter<BatchreportPatronatoDto> {
    @Autowired
    private AnagraficaService anagraficaService;
    @Override
    public void write(List<? extends BatchreportPatronatoDto> list) throws Exception {
        AnagraficaDto output;
        StatusDto statusDto;
        TribunaleDto tribunaleDto;
        ReportPatronatoDto reportPatronatoDto;
        DataUdienzaDto dataUdienzaDto;
        AvvocatoDelegatoDto avvocatoDelegatoDto;
        PatronatoProvenienzaDto patronatoProvenienzaDto;
        TipoPraticheDto tipoPraticheDto;
        List<DataUdienzaDto> udienzaDtoList;
        for (BatchreportPatronatoDto b : list) {
            statusDto = new StatusDto(b.getStatus());
            tribunaleDto = new TribunaleDto(b.getTribunale());
            avvocatoDelegatoDto = new AvvocatoDelegatoDto(b.getAvvocatoDelegato());
            patronatoProvenienzaDto = new PatronatoProvenienzaDto(b.getPatronatoProvenienza());
            tipoPraticheDto = new TipoPraticheDto(b.getTipoPratica());
            udienzaDtoList = new ArrayList<>();
            if (b.getDateUdienza() != null && !b.getDateUdienza().equals("")) {
                String[] s = b.getDateUdienza().split(" ");

                for (int i = 0; i < s.length; i++) {
                    if(!"".equalsIgnoreCase(s[i])) {
                        Date date =new SimpleDateFormat("dd/MM/yyyy").parse(s[i]);
                        dataUdienzaDto = new DataUdienzaDto(false, date,UUID.randomUUID().toString());
                        udienzaDtoList.add(dataUdienzaDto);

                    }
                }
            }
            reportPatronatoDto = new ReportPatronatoDto(statusDto,b.getConvenzione(),b.getSpese(),tipoPraticheDto,tribunaleDto, b.getRuoloGenerale(),
                    udienzaDtoList, patronatoProvenienzaDto, b.getGiudice(), avvocatoDelegatoDto, b.getNote(), UUID.randomUUID().toString());
            output = new AnagraficaDto(b.getNome(), reportPatronatoDto, UUID.randomUUID().toString(), b.getCod());
            anagraficaService.saveAnagrafica(output);

        }
    }
}
