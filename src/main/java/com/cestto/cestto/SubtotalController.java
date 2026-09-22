package com.cestto.cestto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubtotalController {

    @GetMapping("/api/subtotal/{quantidade}/{preco}")
    public String subtotal(@PathVariable int quantidade, @PathVariable int preco) {

        int subtotal = quantidade * preco;

        return subtotal+"\n";
    }
}
