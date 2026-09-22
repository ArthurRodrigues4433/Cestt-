package com.cestto.cestto;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeiraController {
    @PostMapping("/api/feiras")
    public String criarFeira(@RequestBody FeiraRequest feiraRequest) {
        feiraRequest.getNome();
        feiraRequest.getSupermercado();

        return "Feira: "+ feiraRequest.getNome() +" Criada no supermercado "+ feiraRequest.getSupermercado();
    }

}
