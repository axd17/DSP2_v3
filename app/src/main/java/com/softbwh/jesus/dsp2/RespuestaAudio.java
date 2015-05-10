package com.softbwh.jesus.dsp2;

/**
 * Created by jesus on 28/04/15.
 */
public class RespuestaAudio extends Respuesta {

    String rutaAudio;
    public RespuestaAudio(String r, String ra, String id_r) {
        super(r, id_r);
        rutaAudio=ra;
    }
}
