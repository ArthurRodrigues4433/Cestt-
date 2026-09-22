package estudos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SomaController {

    @GetMapping("/api/soma/{numero1}/{numero2}")
    public String soma(@PathVariable int numero1,@PathVariable int numero2){

        int soma = numero1 + numero2;

        return soma + "\n";
    }
}
