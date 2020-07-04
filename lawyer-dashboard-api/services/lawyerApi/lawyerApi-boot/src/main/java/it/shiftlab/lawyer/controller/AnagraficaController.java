package it.shiftlab.lawyer.controller;

import it.shiftlab.lawyer.service.AnagraficaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnagraficaController {

    private AnagraficaService anagraficaService;

    @Autowired
    public AnagraficaController(AnagraficaService anagraficaService) {
        this.anagraficaService = anagraficaService;
    }

    @GetMapping("public/retrieveAnagrafica")
    public void retrieveAnagrafica() {
        anagraficaService.findById();
    }
}
