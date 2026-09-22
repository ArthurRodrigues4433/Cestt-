package estudos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DobroController {

    @GetMapping("/api/dobro/{numero}")
    public String dobro(@PathVariable int numero) {

        int dobro = numero * 2;

        return "Dobro: " + dobro;
    }
}
