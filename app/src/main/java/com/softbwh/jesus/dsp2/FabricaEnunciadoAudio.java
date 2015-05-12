package com.softbwh.jesus.dsp2;

import java.util.ArrayList;


public class FabricaEnunciadoAudio extends FabricaEnunciado {

    public EnunciadoAudio crearEnunciadoAudio(){
        return null;
    }

    @Override
    public Enunciado crearEnunciado(Pregunta p, ArrayList<Respuesta> r) {
        Enunciado e=new EnunciadoAudio(p,r);
        return e;
    }
}
