package com.softbwh.jesus.dsp2;

import java.util.ArrayList;


public class EnunciadoGrafico extends Enunciado {

    private ArrayList<RespuestaGrafica> res;
    public EnunciadoGrafico(Pregunta p, ArrayList<Respuesta> r) {
        super(p, r);
        //for(Respuesta rg : r){
         //   res.add(new RespuestaGrafica(rg.getContenidoRespuesta(), String.valueOf(rg.getDescripcion()), rg.getIdRespuesta()));
        //}
    }
}
