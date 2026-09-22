package estudos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaudacaoController {

    @GetMapping("/api/saudacao")
    public String saudacao(@RequestParam String nome) {

        return "Olá," +nome+"! Bem-vindo ao Cesttô.";
    }
}
