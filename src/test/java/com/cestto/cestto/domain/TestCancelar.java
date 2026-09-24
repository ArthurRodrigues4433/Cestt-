package com.cestto.cestto.domain;

public class TestCancelar {
    public static void main(String[] args) {
        Feira feira = new Feira(1l, "feira mes", "Mix mateus");

        boolean resultado = feira.cancelar();
        System.out.println(resultado);
        System.out.println(feira.getStatus());

        boolean resultado2 = feira.cancelar();
        System.out.println(resultado2);
        System.out.println(feira.getStatus());
    }
}
