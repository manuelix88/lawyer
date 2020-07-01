package it.shiftlab.lawyer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/test")
   String all() {
        return "home Saluta";
    }
}
