package com.example.jogodavelha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(() -> {
<<<<<<< HEAD
            Intent it = new Intent(SplashScreen.this, JogadorVsJogador.class);
=======
            Intent it = new Intent(SplashScreen.this, Menu.class);
>>>>>>> b56f908322e2c08b155d67e4ed0dffc9f6436515
            startActivity(it);
            finish();
        }, 2000);
    }
}