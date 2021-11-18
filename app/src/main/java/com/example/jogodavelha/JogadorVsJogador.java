package com.example.jogodavelha;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class JogadorVsJogador extends AppCompatActivity {

    Jogadores jogador;
    int jogadas = 0;

    List<List<Button>> buttons = new ArrayList<>();
    Button btnReset;
    TextView jogadorLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setJogadorLabel(findViewById(R.id.txtPlayer));
        setBtnReset(findViewById(R.id.btnReset));

        initialize();

        getButtons().forEach(line -> line.forEach(button -> button.setOnClickListener(view -> {
            button.setText(jogador.toString());
            button.setClickable(false);
            setJogadas(jogadas + 1);
            if (isWinner()) {
                makeDialog("Fim de jogo!", "O jogador " + getJogador().toString() + " ganhou!").show();
            } else if (getJogadas() == 9) /* (Empate) */ {
                makeDialog("Empate!", "Nenhum jogador ganhou").show();
            } else {
                changeJogador();
                changePlayerLabelText();
            }
        })));

        getBtnReset().setOnClickListener(view -> reset());

    }

    private AlertDialog.Builder makeDialog(String title, String message) {
        AlertDialog.Builder model = new AlertDialog.Builder(JogadorVsJogador.this);
        model.setTitle(title);
        model.setMessage(message);
        model.setPositiveButton("OK", (dialogInterface, i) -> reset());
        return model;
    }

    private void reset() {
        getButtons().forEach(line -> line.forEach(button -> {
            button.setText("");
            button.setClickable(true);
        }));
        setJogadas(0);
        setJogador(Jogadores.X);
        changePlayerLabelText();
    }

    private boolean isWinner() {
        // Vertical e horizontal
        for (int i = 0; i < 3; i++) {
            if (
                areButtonValuesEqual(findButton(i, 0),  findButton(i, 1), findButton(i, 2))
                ||
                areButtonValuesEqual(findButton(0, i), findButton(1, i), findButton(2, i))
            ) {
                return true;
            }
        }
        // Diagonal 1
        Button d1btn1 = buttons.get(0).get(0);
        Button d1btn2 = buttons.get(1).get(1);
        Button d1btn3 = buttons.get(2).get(2);
        // Diagonal 2
        Button d2btn1 = buttons.get(2).get(0);
        Button d2btn2 = buttons.get(1).get(1);
        Button d2btn3 = buttons.get(0).get(2);
        return areButtonValuesEqual(d1btn1, d1btn2, d1btn3) || areButtonValuesEqual(d2btn1, d2btn2, d2btn3) ;
    }

    private String getButtonText(Button btn) {
        return btn.getText().toString();
    }

    private boolean areButtonValuesEqual(Button btn1, Button btn2,  Button btn3) {
        if (getButtonText(btn1).equals("") || getButtonText(btn2).equals("") || getButtonText(btn3).equals("")) {
            return false;
        }
        return getButtonText(btn1).equals(getButtonText(btn2)) && getButtonText(btn1).equals(getButtonText(btn3));
    }

    private void changeJogador() {
        if (getJogador() == Jogadores.O) {
            setJogador(Jogadores.X);
        } else {
            setJogador(Jogadores.O);
        }
    }

    private void initialize() {
        linkButtons();
        setJogador(Jogadores.X);
        changePlayerLabelText();
    }

    private void changePlayerLabelText() {
        getJogadorLabel().setText(getString(R.string.player_label, jogador.toString()));
    }

    private Button findButton(int i, int j) {
        return getButtons().get(i).get(j);
    }

    private void linkButtons() {
        List<Button> line1 = new ArrayList<>();
        line1.add(findViewById(R.id.btn11));
        line1.add(findViewById(R.id.btn12));
        line1.add(findViewById(R.id.btn13));

        List<Button> line2 = new ArrayList<>();
        line2.add(findViewById(R.id.btn21));
        line2.add(findViewById(R.id.btn22));
        line2.add(findViewById(R.id.btn23));

        List<Button> line3 = new ArrayList<>();
        line3.add(findViewById(R.id.btn31));
        line3.add(findViewById(R.id.btn32));
        line3.add(findViewById(R.id.btn33));

        buttons.add(line1);
        buttons.add(line2);
        buttons.add(line3);
    }

    public Jogadores getJogador() {
        return jogador;
    }

    public void setJogador(Jogadores jogador) {
        this.jogador = jogador;
    }

    public TextView getJogadorLabel() {
        return jogadorLabel;
    }

    public void setJogadorLabel(TextView jogadorLabel) {
        this.jogadorLabel = jogadorLabel;
    }

    public Button getBtnReset() {
        return btnReset;
    }

    public void setBtnReset(Button btnReset) {
        this.btnReset = btnReset;
    }

    public List<List<Button>> getButtons() {
        return buttons;
    }

    public int getJogadas() {
        return jogadas;
    }

    public void setJogadas(int jogadas) {
        this.jogadas = jogadas;
    }
}