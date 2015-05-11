package com.softbwh.jesus.dsp2;

/**
 * Created by jesus on 28/04/15.
 */
public class RespuestaGrafica extends Respuesta {

    private int rutaImagen;
    public RespuestaGrafica(String r, String ri, String id_r) {
        super(r, id_r);
        rutaImagen= Integer.valueOf(ri);
    }
}
