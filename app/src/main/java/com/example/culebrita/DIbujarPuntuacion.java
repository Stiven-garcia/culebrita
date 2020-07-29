package com.example.culebrita;

import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class DIbujarPuntuacion implements Observer {
    private MainActivity mainActivity;
    private TextView puntaje;
    public DIbujarPuntuacion(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.puntaje = this.mainActivity.findViewById(R.id.puntaje);
    }

    @Override

    public void update(Observable o, Object arg) {
        try{
            int punt = (int) arg;
            String puntos = Integer.toString(punt);
            this.puntaje.setText(puntos);
        }catch (Exception e){
            System.out.println("Observador 1 no notificado");
        }


    }
}
