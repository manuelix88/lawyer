package it.shiftlab.lawyer.controller;

import it.shiftlab.lawyer.dto.AnagraficaDto;
import it.shiftlab.lawyer.models.GenericResponseMessage;
import it.shiftlab.lawyer.service.AnagraficaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AnagraficaController {

    private AnagraficaService anagraficaService;

    @Autowired
    public AnagraficaController(AnagraficaService anagraficaService) {
        this.anagraficaService = anagraficaService;
    }

    @GetMapping("public/retrieveAnagraficaById")
    public ResponseEntity<AnagraficaDto> retrieveAnagraficaById(@RequestParam("anagraficaId") long id) {
        return ResponseEntity.ok(anagraficaService.findById(id));
    }

    @PostMapping("public/addAnagrafica")
    public ResponseEntity<?> addAnagrafica(@RequestBody AnagraficaDto anagraficaDto) {
        anagraficaService.saveAnagrafica(anagraficaDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
