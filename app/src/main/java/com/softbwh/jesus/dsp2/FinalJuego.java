package com.softbwh.jesus.dsp2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class FinalJuego extends Activity {

    private int aciertos;
    private int preguntas;
    private int preguntasTotales=0;
    private int aciertosTotales=0;
    private TextView tvCorrectas, tvFalladas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_juego);

        Intent intent= getIntent();
        aciertos=intent.getIntExtra("aciertos",0);
        preguntas=intent.getIntExtra("preguntas",0);
        preguntasTotales=intent.getIntExtra("preguntasTotales",0);
        aciertosTotales=intent.getIntExtra("aciertosTotales",0);

        tvCorrectas = (TextView) findViewById(R.id.tvCorrectas);
        tvFalladas = (TextView) findViewById(R.id.tvFalladas);

        tvFalladas.setText("Respuestas falladas: "+ (preguntas-aciertos));
        tvCorrectas.setText("Respuestas acertadas: "+ aciertos);

        Button botonMain = (Button) findViewById(R.id.btMain);
        botonMain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(FinalJuego.this, Main.class);
                i.putExtra("aciertos", aciertosTotales);
                i.putExtra("preguntas",preguntasTotales);
                i.putExtra("preguntasTotales", preguntasTotales);
                i.putExtra("aciertosTotales", aciertosTotales);
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.final_juego, menu);
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
}
