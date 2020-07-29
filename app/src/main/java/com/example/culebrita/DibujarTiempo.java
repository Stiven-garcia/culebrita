package com.example.culebrita;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class DibujarTiempo implements Observer {
    private MainActivity mainActivity;
    private TextView tiempo;
    public DibujarTiempo(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.tiempo = this.mainActivity.findViewById(R.id.Tiempo);
    }

    @Override

    public void update(Observable o, Object arg) {
        try{
            String tiempo = (String) arg;
            this.tiempo.setText(tiempo);
        }catch (Exception e){
            System.out.println("Observador 3 no notificado");
        }


    }
}
