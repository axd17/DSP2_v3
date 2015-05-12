package com.softbwh.jesus.dsp2;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;


public class Juego extends Activity {
    private int respuestaCorrecta;
    private int aciertos;
    private int nPreguntas;
    private String correcta;
    private int correctaI;
    private TextView tvPregunta, tvImagen;
    private Button boton1,boton2,boton3,boton4,botonA1,botonA2;
    private ArrayList<Enunciado> enunciados;
    private int contador = 0;
    private final String categoriaJuego = "futbol";
    MediaPlayer player;
    MediaPlayer sonidito;
    private int aciertosTotales=0;
    private int preguntasTotales=0;
    //se necesitara aqui tener el test generado ya

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        PYRDataSource datos = new PYRDataSource(this);

        Intent intent= getIntent();
        aciertosTotales+=intent.getIntExtra("aciertos",0);
        preguntasTotales+=intent.getIntExtra("preguntas",0);

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

        /*for(Enunciado e:enunciados){
            System.out.println(e.getPreguntaEnunciado());
            System.out.println(e.getRespuestaCorrecta().getContenidoRespuesta());
            System.out.println(e.getRespuestas().get(0).getContenidoRespuesta());
            System.out.println(e.getRespuestas().get(1).getContenidoRespuesta());
            System.out.println(e.getRespuestas().get(2).getContenidoRespuesta());
        }*/
        /* Asignamos la primera pregunta con sus respuestas de manera random se deberia asignar */
        /* Hay que comprobar de que tipo es la pregunta (grafico, audio, texto) */
        Enunciado e=enunciados.get(contador);
        if(e instanceof EnunciadoTexto) {
            tvPregunta.setText(enunciados.get(contador).getPreguntaEnunciado());
            tvImagen.setBackgroundResource(0);
            correcta = enunciados.get(contador).getRespuestaCorrecta().getContenidoRespuesta();
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
            boton1.setBackgroundResource(R.drawable.blue1);
            boton2.setBackgroundResource(R.drawable.blue1);
            boton3.setBackgroundResource(R.drawable.blue1);
            boton4.setBackgroundResource(R.drawable.blue1);
            boton1.setText(incorrectas.get(0));
            boton2.setText(incorrectas.get(1));
            boton3.setText(incorrectas.get(2));
            boton4.setText(incorrectas.get(3));

            System.out.println(e.getPreguntaEnunciado());
            for(String r:incorrectas) {
                System.out.print(r);
                if(r.matches(correcta)){
                    System.out.print(" <--Correcta");
                }
                System.out.println("");
            }

            botonA1.setVisibility(View.INVISIBLE);
            botonA1.setEnabled(false);
            botonA2.setVisibility(View.INVISIBLE);
            botonA2.setEnabled(false);
        }else if(e instanceof EnunciadoGrafico) {
            tvPregunta.setText(enunciados.get(contador).getPreguntaEnunciado());
            String ruta = enunciados.get(contador).getRespuestaCorrecta().getDescripcion();
            System.out.println("Ruta de la imagen: " + ruta);
            correctaI = getResources().getIdentifier(ruta, "drawable", getApplicationContext().getPackageName());
            System.out.println("Ruta de la imagen: " + ruta + ", id: " + correctaI);
            ArrayList<Integer> incorrectas = new ArrayList<>();
            incorrectas.add(correctaI);
            incorrectas.add(getResources().getIdentifier(enunciados.get(contador).getRespuestas().get(0).getDescripcion(), "drawable", getApplicationContext().getPackageName()));
            incorrectas.add(getResources().getIdentifier(enunciados.get(contador).getRespuestas().get(1).getDescripcion(), "drawable", getApplicationContext().getPackageName()));
            incorrectas.add(getResources().getIdentifier(enunciados.get(contador).getRespuestas().get(2).getDescripcion(), "drawable", getApplicationContext().getPackageName()));
            Collections.shuffle(incorrectas, new Random(System.nanoTime()));
            for(int i=0; i<incorrectas.size(); i++){
                if(incorrectas.get(i) == correctaI)
                    respuestaCorrecta = i+1;//numero del boton de la respuesta correcta (cambia en cada pregunta)*/
            }
            boton1.setBackgroundResource(incorrectas.get(0));
            boton2.setBackgroundResource(incorrectas.get(1));
            boton3.setBackgroundResource(incorrectas.get(2));
            boton4.setBackgroundResource(incorrectas.get(3));
            boton1.setText("");
            boton2.setText("");
            boton3.setText("");
            boton4.setText("");
            botonA1.setVisibility(View.INVISIBLE);
            botonA1.setEnabled(false);
            botonA2.setVisibility(View.INVISIBLE);
            botonA2.setEnabled(false);
        }else if(e instanceof EnunciadoAudio){
            tvPregunta.setText(enunciados.get(contador).getPreguntaEnunciado());
            tvImagen.setBackgroundResource(0);
            correcta = enunciados.get(contador).getRespuestaCorrecta().getContenidoRespuesta();
            ArrayList<String> incorrectas = new ArrayList<>();
            incorrectas.add(correcta);
            incorrectas.add(enunciados.get(contador).getRespuestas().get(0).getContenidoRespuesta());
            incorrectas.add(enunciados.get(contador).getRespuestas().get(1).getContenidoRespuesta());
            incorrectas.add(enunciados.get(contador).getRespuestas().get(2).getContenidoRespuesta());
            Collections.shuffle(incorrectas, new Random(System.nanoTime()));
            for(int i=0; i<incorrectas.size(); i++) {
                if(incorrectas.get(i).matches(correcta))
                    respuestaCorrecta = i + 1;//numero del boton de la respuesta correcta (cambia en cada pregunta)*/
            }
            boton1.setBackgroundResource(R.drawable.blue1);
            boton2.setBackgroundResource(R.drawable.blue1);
            boton3.setBackgroundResource(R.drawable.blue1);
            boton4.setBackgroundResource(R.drawable.blue1);
            boton1.setText(incorrectas.get(0));
            boton2.setText(incorrectas.get(1));
            boton3.setText(incorrectas.get(2));
            boton4.setText(incorrectas.get(3));
            botonA1.setVisibility(View.VISIBLE);
            botonA1.setEnabled(true);
            botonA1.setText("Play");
            botonA2.setVisibility(View.VISIBLE);
            botonA2.setEnabled(true);
            botonA2.setText("Pause");
            String audio = enunciados.get(contador).getPregunta().getDescripcion();
            int ruta = getResources().getIdentifier(audio, "raw", getApplicationContext().getPackageName());
            player = MediaPlayer.create(Juego.this, ruta);

            botonA1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    player.start();
                }
            });

            botonA2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    player.pause();
                }
            });
        }

        /* variables de control de las preguntas */
        aciertos = 0; //variable que suma los aciertos de 1 en 1 (no suma nada si falla)
        nPreguntas = 10; //numero de preguntas totales (soloc ambia al generar el test
        /* Poner una variable que diga cual es la respuesta correcta */

        mostrarEnunciado(boton1, 1);
        mostrarEnunciado(boton2, 2);
        mostrarEnunciado(boton3, 3);
        mostrarEnunciado(boton4, 4);
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

    public void mostrarEnunciado(Button boton, final int num){
        boton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                contador++;
                if (respuestaCorrecta == num) {
                    /* sonido correcto */
                    sonidito = MediaPlayer.create(Juego.this, R.raw.correcto);
                    sonidito.start();
                    aciertos++;
                    /* variable de aciertos++ */
                } else {
                    sonidito = MediaPlayer.create(Juego.this, R.raw.incorrect);
                    sonidito.start();
                    /* sonido mal */
                }
                /*comprobamos si es la ultima pregunta */
                if (nPreguntas == 1) {
                    /* ir a FinalJuego */
                    Intent i = new Intent(Juego.this, FinalJuego.class);
                    i.putExtra("aciertos", aciertos);
                    i.putExtra("preguntas", enunciados.size());
                    i.putExtra("aciertosTotales", aciertosTotales + aciertos);
                    i.putExtra("preguntasTotales", preguntasTotales + enunciados.size());
                    startActivity(i);
                } else {
                    Enunciado e = enunciados.get(contador);
                    if (e instanceof EnunciadoTexto) {
                        tvPregunta.setText(enunciados.get(contador).getPreguntaEnunciado());
                        tvImagen.setBackgroundResource(0);
                        correcta = enunciados.get(contador).getRespuestaCorrecta().getContenidoRespuesta();
                        ArrayList<String> incorrectas = new ArrayList<>();
                        incorrectas.add(correcta);
                        incorrectas.add(enunciados.get(contador).getRespuestasEnunciados().get(0));
                        incorrectas.add(enunciados.get(contador).getRespuestasEnunciados().get(1));
                        incorrectas.add(enunciados.get(contador).getRespuestasEnunciados().get(2));
                        Collections.shuffle(incorrectas, new Random(System.nanoTime()));
                        for (int i = 0; i < incorrectas.size(); i++) {
                            if (incorrectas.get(i).matches(correcta))
                                respuestaCorrecta = i + 1;//numero del boton de la respuesta correcta (cambia en cada pregunta)*/
                        }
                        boton1.setBackgroundResource(R.drawable.blue1);
                        boton2.setBackgroundResource(R.drawable.blue1);
                        boton3.setBackgroundResource(R.drawable.blue1);
                        boton4.setBackgroundResource(R.drawable.blue1);
                        boton1.setText(incorrectas.get(0));
                        boton2.setText(incorrectas.get(1));
                        boton3.setText(incorrectas.get(2));
                        boton4.setText(incorrectas.get(3));

                        System.out.println(e.getPreguntaEnunciado());
                        for (String r : incorrectas) {
                            System.out.print(r);
                            if (r.matches(correcta)) {
                                System.out.print(" <--Correcta");
                            }
                            System.out.println("");
                        }

                        botonA1.setVisibility(View.INVISIBLE);
                        botonA1.setEnabled(false);
                        botonA2.setVisibility(View.INVISIBLE);
                        botonA2.setEnabled(false);
                    } else if (e instanceof EnunciadoGrafico) {
                        tvPregunta.setText(enunciados.get(contador).getPreguntaEnunciado());
                        tvImagen.setBackgroundResource(0);
                        String ruta = enunciados.get(contador).getRespuestaCorrecta().getDescripcion();
                        System.out.println("Ruta de la imagen: "+ruta);
                        correctaI = getResources().getIdentifier(ruta, "drawable", getApplicationContext().getPackageName());
                        System.out.println("Ruta de la imagen: "+ruta+", id: "+correctaI);
                        ArrayList<Integer> incorrectas = new ArrayList<>();
                        incorrectas.add(correctaI);
                        incorrectas.add(getResources().getIdentifier(enunciados.get(contador).getRespuestas().get(0).getDescripcion(), "drawable", getApplicationContext().getPackageName()));
                        incorrectas.add(getResources().getIdentifier(enunciados.get(contador).getRespuestas().get(1).getDescripcion(), "drawable", getApplicationContext().getPackageName()));
                        incorrectas.add(getResources().getIdentifier(enunciados.get(contador).getRespuestas().get(2).getDescripcion(), "drawable", getApplicationContext().getPackageName()));
                        Collections.shuffle(incorrectas, new Random(System.nanoTime()));
                        for (int i = 0; i < incorrectas.size(); i++) {
                            if (incorrectas.get(i) == correctaI)
                                respuestaCorrecta = i + 1;//numero del boton de la respuesta correcta (cambia en cada pregunta)*/
                        }
                        //int a=R.drawable.esp;
                        boton1.setText("");
                        boton2.setText("");
                        boton3.setText("");
                        boton4.setText("");
                        boton1.setBackgroundResource(incorrectas.get(0));
                        boton2.setBackgroundResource(incorrectas.get(1));
                        boton3.setBackgroundResource(incorrectas.get(2));
                        boton4.setBackgroundResource(incorrectas.get(3));
                        botonA1.setVisibility(View.INVISIBLE);
                        botonA1.setEnabled(false);
                        botonA2.setVisibility(View.INVISIBLE);
                        botonA2.setEnabled(false);
                    } else if (e instanceof EnunciadoAudio) {
                        tvPregunta.setText(enunciados.get(contador).getPreguntaEnunciado());
                        tvImagen.setBackgroundResource(0);
                        correcta = enunciados.get(contador).getRespuestaCorrecta().getContenidoRespuesta();
                        ArrayList<String> incorrectas = new ArrayList<>();
                        incorrectas.add(correcta);
                        incorrectas.add(enunciados.get(contador).getRespuestas().get(0).getContenidoRespuesta());
                        incorrectas.add(enunciados.get(contador).getRespuestas().get(1).getContenidoRespuesta());
                        incorrectas.add(enunciados.get(contador).getRespuestas().get(2).getContenidoRespuesta());
                        Collections.shuffle(incorrectas, new Random(System.nanoTime()));
                        for (int i = 0; i < incorrectas.size(); i++) {
                            if (incorrectas.get(i).matches(correcta))
                                respuestaCorrecta = i + 1;//numero del boton de la respuesta correcta (cambia en cada pregunta)*/
                        }
                        boton1.setBackgroundResource(R.drawable.blue1);
                        boton2.setBackgroundResource(R.drawable.blue1);
                        boton3.setBackgroundResource(R.drawable.blue1);
                        boton4.setBackgroundResource(R.drawable.blue1);
                        boton1.setText(incorrectas.get(0));
                        boton2.setText(incorrectas.get(1));
                        boton3.setText(incorrectas.get(2));
                        boton4.setText(incorrectas.get(3));
                        botonA1.setVisibility(View.VISIBLE);
                        botonA1.setEnabled(true);
                        botonA1.setText("Play");
                        botonA2.setVisibility(View.VISIBLE);
                        botonA2.setEnabled(true);
                        botonA2.setText("Pause");
                        String audio = enunciados.get(contador).getPregunta().getDescripcion();;
                        int ruta = getResources().getIdentifier(audio, "raw", getApplicationContext().getPackageName());
                        player = MediaPlayer.create(Juego.this, ruta);

                        botonA1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                player.start();
                            }
                        });

                        botonA2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                player.pause();

                            }

                        });
                    }
                }
                nPreguntas--;
            }
        });
    }
}
