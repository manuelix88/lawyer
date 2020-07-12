package it.shiftlab.lawyer.batch.writer;

import it.shiftlab.lawyer.batch.model.ReportInfoAmministrativeDto;
import it.shiftlab.lawyer.dto.AnagraficaDto;
import it.shiftlab.lawyer.dto.ReportAmministrativeDto;
import it.shiftlab.lawyer.service.AnagraficaService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReportInfoAmministrativeWriter implements ItemWriter<ReportInfoAmministrativeDto> {

    @Autowired
    private AnagraficaService anagraficaService;


    @Override
    public void write(List<? extends ReportInfoAmministrativeDto> list) throws Exception {
        AnagraficaDto output;
        ReportAmministrativeDto repAmm ;
        for (ReportInfoAmministrativeDto rep : list) {
            repAmm = new ReportAmministrativeDto();
            repAmm.setQualifica(rep.getQualifica());
            repAmm.setNumeroFaldone(rep.getFaldone());
            repAmm.setRicordoCedu(rep.getRicorso());
            repAmm.setAltro(rep.getAltro());
            repAmm.setNote(rep.getNote());
            if (rep.getPagamento() != null && !rep.getPagamento().equals("")) {
                Date date =new SimpleDateFormat("dd/MM/yyyy").parse(rep.getPagamento().trim());
                repAmm.setDataPagamento(date);
            }
            repAmm.setDocumentazione(rep.getDocumentazione());
            output = new AnagraficaDto(rep.getNome(), rep.getFiscale(),rep.getProvincia(), repAmm);
            anagraficaService.saveAnagrafica(output);
        }
    }
}
