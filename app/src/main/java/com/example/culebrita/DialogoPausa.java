package com.example.culebrita;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.Utiles.constantes;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class DialogoPausa {
    private  MainActivity mainActivity;

    public DialogoPausa(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void Pausa() {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this.mainActivity);
        dialogo.setCancelable(false);
        LinearLayout layout = new LinearLayout(this.mainActivity);
        layout.setOrientation(LinearLayout.VERTICAL);
        ImageView imagen = new ImageView(this.mainActivity);
        imagen.setImageResource(R.drawable.pausa);
        LinearLayout.LayoutParams margen = new LinearLayout.LayoutParams(750, 180);
        margen.setMargins(0, 0, 0, 30);
        imagen.setLayoutParams(margen);
        layout.addView(imagen);
        layout.setGravity(Gravity.CENTER);
        Button reanudar = new Button(this.mainActivity);
        layout.addView(reanudar);
        Button nuevo = new Button(this.mainActivity);
        layout.addView(nuevo);
        Button cerrar = new Button(this.mainActivity);
        layout.addView(cerrar);
        dialogo.setView(layout);
        final AlertDialog titulo = dialogo.create();
        reanudar.setText("Reanudar");
        //reanudar.setBackgroundColor(Color.RED);
        reanudar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titulo.cancel();
                reanudar();
            }
        });
        reanudar.setLayoutParams(new LinearLayout.LayoutParams(600,130));
        nuevo.setText("Nuevo Juego");
        nuevo.setBackgroundColor(Color.RED);
        nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titulo.cancel();
                iniciar();
            }
        });
        nuevo.setLayoutParams(new LinearLayout.LayoutParams(600,130));
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
       // titulo.setTitle("Pausa");

        titulo.show();
        titulo.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        //titulo.getWindow().setNavigationBarColor(Color.RED);
        titulo.getWindow().setLayout(750,750);
    }
    public void iniciar(){
        this.mainActivity.limpiar();
        this.mainActivity.Iniciar();
    }
    public void reanudar(){
        this.mainActivity.reanudar();
    }
    public void cerrar(){
        this.mainActivity.finish();
    }
}
