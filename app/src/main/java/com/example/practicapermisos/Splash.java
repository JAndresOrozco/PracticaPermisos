package com.example.practicapermisos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent it = new Intent(getApplicationContext(),MainActivity.class);
                Persona pp = new Persona("Andres","20");
                it.putExtra("Persona",pp);
                startActivity(it);
            }
        },4000);






    }
}
