package com.cestto.cestto;

public class TestFinalizarFeira {
    public static void main(String[] args) {
        Feira feira = new Feira("feira mes", "Mix mateus");

        boolean resultado = feira.finalizar();
        System.out.println(resultado);

        boolean resultado2 = feira.finalizar();
        System.out.println(resultado2);
    }
}
