package estudos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloArthurController {

    @GetMapping("/api/hello/{nome}")
    public String hello(@PathVariable String nome) {
        return "Olá, "+nome +"! Bem-vindo ao Cesttô.";
    }
}
