package com.softbwh.jesus.dsp2;

import java.util.ArrayList;


public class FabricaEnunciadoGrafico extends FabricaEnunciado {

    public EnunciadoGrafico crearEnunciadoGrafico(){
        return null;
    }

    @Override
    public Enunciado crearEnunciado(Pregunta p, ArrayList<Respuesta> r) {
        Enunciado e=new EnunciadoGrafico(p,r);
        return e;
    }
}
