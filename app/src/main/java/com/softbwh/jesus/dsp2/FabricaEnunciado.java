package com.softbwh.jesus.dsp2;

import java.util.ArrayList;


public abstract class FabricaEnunciado {

    public abstract Enunciado crearEnunciado(Pregunta p, ArrayList<Respuesta> r);
}
