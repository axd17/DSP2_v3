package com.softbwh.jesus.dsp2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Resultados extends Activity {
    private int aciertos=0;
    private int preguntas=0;
    private TextView tvAciertosTotales;
    private TextView tvFalladasTotales;
    private TextView tvPreguntasTotales;
    private Button btMainResultados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        Intent intent= getIntent();
        aciertos+=intent.getIntExtra("aciertos",0);
        preguntas+=intent.getIntExtra("preguntas",0);

        tvAciertosTotales = (TextView) findViewById(R.id.tvAcertadasTotales);
        tvFalladasTotales = (TextView) findViewById(R.id.tvFalladasTotales);
        tvPreguntasTotales = (TextView) findViewById(R.id.tvPreguntasTotales);

        tvFalladasTotales.setText("Respuestas falladas: "+ (preguntas-aciertos));
        tvAciertosTotales.setText("Respuestas acertadas: "+ aciertos);
        tvPreguntasTotales.setText("Preguntas totales: "+ preguntas);

        btMainResultados= (Button) findViewById(R.id.btMainResultados);
        btMainResultados.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(Resultados.this, Main.class);
                i.putExtra("aciertos", aciertos);
                i.putExtra("preguntas", preguntas);
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.resultados, menu);
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
