package com.example.culebrita;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.Observable;
import java.util.Observer;

public class DialogoPerder implements Observer {
    private  MainActivity mainActivity;
    private int [] datos ;

    public int[] getDatos() {
        return datos;
    }


    public DialogoPerder(MainActivity mainActivity) {
        this.mainActivity = mainActivity;

    }

    public void perder(int punt, int best) {
        AlertDialog.Builder dialogoPerder = new AlertDialog.Builder(this.mainActivity);
        dialogoPerder.setCancelable(false);
        LinearLayout layout = new LinearLayout(this.mainActivity);
        layout.setOrientation(LinearLayout.VERTICAL);
        ImageView imagen = new ImageView(this.mainActivity);
        imagen.setImageResource(R.drawable.gameover);
        LinearLayout.LayoutParams margen = new LinearLayout.LayoutParams(750,180);
        margen.setMargins(0,0,0,30);
        imagen.setLayoutParams(margen);
        layout.addView(imagen);
        layout.setGravity(Gravity.CENTER);
        LinearLayout layoutPuntaje = new LinearLayout(this.mainActivity);
        layoutPuntaje.setOrientation(LinearLayout.HORIZONTAL);
        layoutPuntaje.setGravity(Gravity.CENTER);
        TextView puntaje = new TextView(this.mainActivity);
        puntaje.setText("Puntaje: "+ punt+"   ");
        puntaje.setTextColor(Color.BLACK);
        puntaje.setTextSize(17);
        TextView bestP = new TextView(this.mainActivity);
        bestP.setText("Puntaje Maximo: "+ best);
        bestP.setTextColor(Color.BLACK);
        bestP.setTextSize(17);
        layoutPuntaje.addView(puntaje);
        layoutPuntaje.addView(bestP);
        layout.addView(layoutPuntaje);
        Button nuevo = new Button(this.mainActivity);
        layout.addView(nuevo);
        Button cerrar = new Button(this.mainActivity);
        layout.addView(cerrar);
        dialogoPerder.setView(layout);
        final AlertDialog tituloPerder = dialogoPerder.create();
        nuevo.setText("Nuevo Juego");
        nuevo.setBackgroundColor(Color.RED);
        nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tituloPerder.cancel();
                iniciar();
            }
        });
        LinearLayout.LayoutParams margen1 = new LinearLayout.LayoutParams(600,130);
        margen1.setMargins(0,30,0,0);
        nuevo.setLayoutParams(margen1);
        cerrar.setText("Cerrar");
        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cerrar();
            }
        });
        LinearLayout.LayoutParams parametros = new LinearLayout.LayoutParams(600,130);
        parametros.setMargins(0,0,0,30);
        cerrar.setLayoutParams(parametros);
        tituloPerder.show();
        tituloPerder.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        //titulo.getWindow().setNavigationBarColor(Color.RED);
        tituloPerder.getWindow().setLayout(750,800);
    }
    public void iniciar(){
        this.mainActivity.limpiar();
        this.mainActivity.Iniciar();
    }
    public void cerrar(){
        this.mainActivity.finish();
    }
    @Override
    public void update(Observable o, Object arg) {
        try {
            int [] datos = (int []) arg;
            this.datos = datos;
            this.mainActivity.runOnUiThread(new Runnable() {
                public void run() {
                    perder(getDatos()[0],getDatos()[1]);
                }
            });

        }catch (Exception e){
            System.out.println("Observador 4 no notificado");
        }
    }
}

