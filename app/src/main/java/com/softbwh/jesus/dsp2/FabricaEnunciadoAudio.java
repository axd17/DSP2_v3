package com.softbwh.jesus.dsp2;

import java.util.ArrayList;

/**
 * Created by jesus on 28/04/15.
 */
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
