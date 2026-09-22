package estudos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApresentacaoController {

    @GetMapping("/api/apresentacao")
    public String apresentacao(@RequestParam String nome, @RequestParam String cidade) {
        return "Olá," +nome+"! Voce mora em"+cidade+".";
    }
}
