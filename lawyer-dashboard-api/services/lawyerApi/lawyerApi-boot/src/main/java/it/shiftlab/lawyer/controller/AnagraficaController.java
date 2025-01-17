package it.shiftlab.lawyer.controller;

import it.shiftlab.lawyer.dto.AnagraficaDto;
import it.shiftlab.lawyer.models.GenericResponseMessage;
import it.shiftlab.lawyer.service.AnagraficaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class AnagraficaController {

    private final AnagraficaService anagraficaService;

    @Autowired
    public AnagraficaController(AnagraficaService anagraficaService) {
        this.anagraficaService = anagraficaService;
    }

    @GetMapping("protected/retrieveAnagraficaById")
    public ResponseEntity<AnagraficaDto> retrieveAnagraficaById(@RequestParam("anagraficaId") long id) {
        return ResponseEntity.ok(anagraficaService.findById(id));
    }

    @PostMapping("protected/addAnagrafica")
    public ResponseEntity<?> addAnagrafica(@RequestBody AnagraficaDto anagraficaDto) {
        anagraficaService.saveAnagrafica(anagraficaDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("protected/updateAnagrafica")
    public ResponseEntity<?> updateAnagrafica(@RequestBody AnagraficaDto anagraficaDto) {
        anagraficaService.updateAnagrafica(anagraficaDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("protected/retrieveAllAnagrafica")
    public ResponseEntity<Page<AnagraficaDto>> listAmministrativeFilter(@Min(0) @Valid Integer page, @Min(0) @Max(100) @Valid Integer limit,
                                                                @RequestParam(value = "faldone", required = false) Integer faldone,
                                                                @RequestParam(value = "nome", required = false) String name,
                                                                @RequestParam(value = "cognome", required = false) String cognome,
                                                                @RequestParam(value = "codiceFiscale", required = false) String codiceFiscale,
                                                                @RequestParam(value = "qualifica", required = false) String qualifica,
                                                                @RequestParam(value = "documentazione", required = false) String documentazione
                                                              ) {
        Page<AnagraficaDto> pages = anagraficaService.listAnagraficaFilter(PageRequest.of(page, limit),faldone,name,cognome,codiceFiscale,qualifica,documentazione);

        return  ResponseEntity.ok(new PageImpl<>(pages.stream().collect(Collectors.toList()),pages.getPageable(),pages.getTotalElements()));
    }

    @GetMapping("protected/retrieveAllAnagraficaPatronato")
    public ResponseEntity<Page<AnagraficaDto>> listPatronatoFilter(@Min(0) @Valid Integer page, @Min(0) @Max(100) @Valid Integer limit,
                                                              @RequestParam(value = "nome", required = false) String name,
                                                              @RequestParam(value = "cognome", required = false) String cognome,
                                                              @RequestParam(value = "codiceFiscale", required = false) String codiceFiscale,
                                                              @RequestParam(value = "ruoloGenerale", required = false) String ruoloGenerale,
                                                              @RequestParam(value = "patronatoProvenienza", required = false) Long patronatoProvenienza,
                                                              @RequestParam(value = "avvocatoDelegato", required = false) Long avvocatoDelegato
    ) {
        Page<AnagraficaDto> pages = anagraficaService.listAnagraficaFilterReportPatronato(PageRequest.of(page, limit),name,cognome,codiceFiscale,ruoloGenerale,patronatoProvenienza,avvocatoDelegato);

        return  ResponseEntity.ok(new PageImpl<>(pages.stream().collect(Collectors.toList()),pages.getPageable(),pages.getTotalElements()));
    }


    @DeleteMapping("protected/deleteAnagrafica/{anagraficaId}")
    public ResponseEntity<?> deleteAnagrafica(@PathVariable(name = "anagraficaId", required = true) UUID anagraficaId) {
        anagraficaService.deleteAnagrafica(anagraficaId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
