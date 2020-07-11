package it.shiftlab.lawyer.controller;

import it.shiftlab.lawyer.dto.CodiceDto;
import it.shiftlab.lawyer.dto.StatusDto;
import it.shiftlab.lawyer.dto.TribunaleDto;
import it.shiftlab.lawyer.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenericsController {

    private final GenericService genericService;

    @Autowired
    public GenericsController(GenericService genericService) {
        this.genericService = genericService;
    }

    @GetMapping("public/generics/retrieveAllCode")
    public ResponseEntity<List<CodiceDto>> getAllCodice() {
        List<CodiceDto> allCodice = genericService.findAllCodice();
        return ResponseEntity.ok(allCodice);
    }

    @GetMapping("public/generics/retrieveAllTribunali")
    public ResponseEntity<List<TribunaleDto>> getAllTribunali() {
        List<TribunaleDto> all = genericService.findAllTribunali();
        return ResponseEntity.ok(all);
    }

    @GetMapping("public/generics/retrieveAllStatus")
    public ResponseEntity<List<StatusDto>> getAllStaus() {
        List<StatusDto> all = genericService.findAllStatus();
        return ResponseEntity.ok(all);
    }
}
