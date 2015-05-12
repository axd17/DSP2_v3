package com.softbwh.jesus.dsp2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class OtrosJuegos extends Activity {
    private ImageButton imJuego1, imJuego2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otros_juegos);

        imJuego1 = (ImageButton) findViewById(R.id.imJuego1);
        imJuego2 = (ImageButton) findViewById(R.id.imJuego2);

        imJuego1.setBackgroundResource(R.drawable.preguntados);
        imJuego2.setBackgroundResource(R.drawable.clashofclans);

        imJuego1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                openWebURL("https://play.google.com/store/apps/details?id=com.etermax.preguntados.lite&hl=es");
            }
        });

        imJuego2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                openWebURL("https://play.google.com/store/apps/details?id=com.supercell.clashofclans&hl=es");
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.otros_juegos, menu);
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

    public void openWebURL( String inURL ) {
        Intent browse = new Intent( Intent.ACTION_VIEW , Uri.parse( inURL ) );

        startActivity( browse );
    }
}
