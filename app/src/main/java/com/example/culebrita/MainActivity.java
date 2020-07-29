package com.example.culebrita;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.example.Controlador.ControlPausa;
import com.example.Controlador.ControlTouch;
import com.example.Logica.Tablero;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private ControlTouch controlTouch;
    private Tablero tablero;
    private DibujarTablero dibujarTablero;
    private DibujarTiempo dibujarTiempo;
    private DIbujarPuntuacion dIbujarPuntuacion;
    private ControlPausa controlPausa;
    private DialogoPausa dialogoPausa;
    private DialogoPerder dialogoPerder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.dIbujarPuntuacion = new DIbujarPuntuacion(this);
        Iniciar();

    }

    public void Iniciar() {
        this.controlTouch = new ControlTouch(this);
        this.controlPausa = new ControlPausa(this);
        this.dialogoPausa = new DialogoPausa(this);
        this.dibujarTablero = new DibujarTablero(this) ;
        this.dialogoPerder = new DialogoPerder(this);
        this.dibujarTiempo = new DibujarTiempo(this);
        this.tablero = new Tablero();
        this.tablero.addObserver(this.dialogoPerder);
        this.tablero.addObserver(this.dibujarTiempo);
        this.tablero.addObserver(this.dIbujarPuntuacion);
        this.tablero.addObserver(this.dibujarTablero);
        this.tablero.JuegoNuevo();
    }

    public void limpiar(){
        this.dibujarTablero.limpiar();
    }
    public void dialogo(){
        this.tablero.setRun(false);
        this.dialogoPausa.Pausa();
    }

    public void reanudar(){
        this.tablero.setRun(true);
        this.tablero.timerTiempo();
        this.tablero.timerTask();
    }
    public void moverCulebra(String movimiento) {
        this.tablero.setOrientacionT(movimiento);
    }
}
