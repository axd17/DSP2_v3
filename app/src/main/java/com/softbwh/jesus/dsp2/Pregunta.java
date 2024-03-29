package com.softbwh.jesus.dsp2;


public abstract class Pregunta {

    private String contenido;
    private Respuesta respuesta;
    private String descripcion;
    private String tipo;

    public Pregunta(String p, Respuesta r, String des, String tip){
        contenido=p;
        respuesta=r;
        descripcion=des;
        tipo=tip;
    }

    public String getContenido(){
        return contenido;
    }

    public Respuesta getRespuesta(){
        return respuesta;
    }

    public String getIdRespuesta(){
        return respuesta.getIdRespuesta();
    }

    public String getDescripcion(){
        return descripcion;
    }

    public String getTipo(){
        return tipo;
    }
}
