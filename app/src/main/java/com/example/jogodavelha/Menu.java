package com.example.jogodavelha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;

public class Menu extends AppCompatActivity {

    RadioGroup playModeGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();

        Button playBtn = findViewById(R.id.playBtn);
        playModeGroup = findViewById(R.id.playModeGroup);

        playBtn.setOnClickListener((view) -> {
            playGame();
        });
    }

    private void playGame() {
        Intent it = new Intent(Menu.this, MainActivity.class);
        int selectedMode = playModeGroup.getCheckedRadioButtonId();
        if (selectedMode == findViewById(R.id.multiplayer).getId()) {
            it.putExtra("mode", Modes.MULTIPLAYER);
        } else if (selectedMode == findViewById(R.id.singleplayer).getId()) {
            it.putExtra("mode", Modes.SINGLEPLAYER);
        }
        startActivity(it);
    }
}