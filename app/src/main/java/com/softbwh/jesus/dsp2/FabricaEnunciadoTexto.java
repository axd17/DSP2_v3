package com.softbwh.jesus.dsp2;

import java.util.ArrayList;


public class FabricaEnunciadoTexto extends FabricaEnunciado {

    public EnunciadoTexto crearEnunciadoTexto(){
        return null;
    }

    @Override
    public Enunciado crearEnunciado(Pregunta p, ArrayList<Respuesta> r) {
        Enunciado e=new EnunciadoTexto(p,r);
        return e;
    }
}
