package com.softbwh.jesus.dsp2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;


public class Juego extends Activity {
    private int respuestaCorrecta;
    private int aciertos;
    private int nPreguntas;
    private TextView tvPregunta, tvImagen;
    private Button boton1,boton2,boton3,boton4,botonA1,botonA2;
    private ArrayList<Enunciado> enunciados;
    private int contador = 0;
    private final String categoriaJuego = "futbol";
    //se necesitara aqui tener el test generado ya

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        PYRDataSource datos = new PYRDataSource(this);

        /* Asignamos los ids a los elementos */
        tvPregunta = (TextView) findViewById(R.id.tvPregunta);
        tvImagen = (TextView) findViewById(R.id.tvImagen);
        boton1 = (Button) findViewById(R.id.button);
        boton2 = (Button) findViewById(R.id.button2);
        boton3 = (Button) findViewById(R.id.button3);
        boton4 = (Button) findViewById(R.id.button4);
        botonA1 = (Button) findViewById(R.id.buttonA1);
        botonA2 = (Button) findViewById(R.id.buttonA2);

        /****** CREAR EL TEST ******/

        //enunciados = datos.obtenerEnunciados(categoriaJuego);
        enunciados = new ArrayList<>();
        enunciados = datos.obtenerEnunciados(categoriaJuego);


        //int a = R.drawable.esp;
        /****** CREAR EL TEST ******/

        /* Asignamos la primera pregunta con sus respuestas de manera random se deberia asignar */
        /* Hay que comprobar de que tipo es la pregunta (grafico, audio, texto) */
        tvPregunta.setText(enunciados.get(0).getPreguntaEnunciado());
        tvImagen.setBackgroundResource(0);
        String correcta = enunciados.get(0).getRespuestaCorrecta();
        ArrayList<String> incorrectas = new ArrayList<>();
        incorrectas.add(correcta);
        incorrectas.add(enunciados.get(0).getRespuestasEnunciados().get(0));
        incorrectas.add(enunciados.get(0).getRespuestasEnunciados().get(1));
        incorrectas.add(enunciados.get(0).getRespuestasEnunciados().get(2));
        Collections.shuffle(incorrectas, new Random(System.nanoTime()));
        for (int i = 0; i < incorrectas.size(); i++) {
            if (incorrectas.get(i).matches(correcta))
                respuestaCorrecta = i + 1;//numero del boton de la respuesta correcta (cambia en cada pregunta)*/
        }
        boton1.setText(incorrectas.get(0));
        boton2.setText(incorrectas.get(1));
        boton3.setText(incorrectas.get(2));
        boton4.setText(incorrectas.get(3));
        //botonA1.setVisibility(View.INVISIBLE);
        botonA1.setEnabled(true);
        //botonA2.setVisibility(View.INVISIBLE);
        botonA2.setEnabled(true);
        //boton1.setVisibility(View.INVISIBLE);

        /* variables de control de las preguntas */
        aciertos = 0; //variable que suma los aciertos de 1 en 1 (no suma nada si falla)
        nPreguntas = enunciados.size(); //numero de preguntas totales (soloc ambia al generar el test
        /* Poner una variable que diga cual es la respuesta correcta */

        mostrarEnunciado(boton1);
        mostrarEnunciado(boton2);
        mostrarEnunciado(boton3);
        mostrarEnunciado(boton4);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.juego, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void mostrarEnunciado(Button boton){
        boton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                contador++;
                if(respuestaCorrecta==1){
                    /* sonido correcto */
                    aciertos++;
                    /* variable de aciertos++ */
                }else{
                    /* sonido mal */
                }

                /*comprobamos si es la ultima pregunta */
                if(nPreguntas==1){
                    /* ir a FinalJuego */

                    Intent i = new Intent(Juego.this, FinalJuego.class);
                    i.putExtra("aciertos", aciertos);
                    startActivity(i);
                }else{
                    Enunciado e=enunciados.get(contador);
                    if(e instanceof EnunciadoTexto) {
                        tvPregunta.setText(enunciados.get(contador).getPreguntaEnunciado());
                        tvImagen.setBackgroundResource(0);
                        String correcta = enunciados.get(contador).getRespuestaCorrecta();
                        ArrayList<String> incorrectas = new ArrayList<>();
                        incorrectas.add(correcta);
                        incorrectas.add(enunciados.get(contador).getRespuestasEnunciados().get(0));
                        incorrectas.add(enunciados.get(contador).getRespuestasEnunciados().get(1));
                        incorrectas.add(enunciados.get(contador).getRespuestasEnunciados().get(2));
                        Collections.shuffle(incorrectas, new Random(System.nanoTime()));
                        for(int i=0; i<incorrectas.size(); i++){
                            if(incorrectas.get(i).matches(correcta))
                                respuestaCorrecta = i+1;//numero del boton de la respuesta correcta (cambia en cada pregunta)*/
                        }
                        boton1.setText(incorrectas.get(0));
                        boton2.setText(incorrectas.get(1));
                        boton3.setText(incorrectas.get(2));
                        boton4.setText(incorrectas.get(3));
                    }
                }
                nPreguntas--;
            }
        });
    }
}
