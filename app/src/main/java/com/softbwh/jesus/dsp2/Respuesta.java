package com.softbwh.jesus.dsp2;

/**
 * Created by jesus on 28/04/15.
 */
public abstract class Respuesta {

    private String respuesta;
    private String id;
    private String descripcion;

    public Respuesta(String r, String d, String id_r){
        respuesta=r;
        id = id_r;
        descripcion = d;
    }

    public String getContenidoRespuesta(){
        return respuesta;
    }

    public String getIdRespuesta(){
        return id;
    }

    public String getDescripcion(){
        return descripcion;
    }
}
