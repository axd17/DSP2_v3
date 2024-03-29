package com.softbwh.jesus.dsp2;

import java.util.ArrayList;


public abstract class Enunciado {
    private Pregunta pregunta;
    private ArrayList<Respuesta> respuestas=new ArrayList<Respuesta>(3);

    public Enunciado(Pregunta p, ArrayList<Respuesta> r){
        this.pregunta=p;
        this.respuestas=r;
    }

    public String getPreguntaEnunciado(){
        return pregunta.getContenido();
    }

    public Respuesta getRespuestaCorrecta(){
        return pregunta.getRespuesta();
    }

    public Pregunta getPregunta(){
        return pregunta;
    }

    public ArrayList<String> getRespuestasEnunciados(){
        ArrayList<String> ret=new ArrayList<String>(3);
        for (int i = 0; i <respuestas.size() ; i++) {
            ret.add(respuestas.get(i).getContenidoRespuesta());
        }
        return ret;
    }

    public ArrayList<Respuesta> getRespuestas(){
        return respuestas;
    }
}
