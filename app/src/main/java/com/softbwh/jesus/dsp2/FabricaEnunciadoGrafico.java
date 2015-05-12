package com.softbwh.jesus.dsp2;

import java.util.ArrayList;

/**
 * Created by jesus on 28/04/15.
 */
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
