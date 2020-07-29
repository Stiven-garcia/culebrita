package com.example.culebrita;

import android.graphics.Color;
import android.graphics.Point;
import android.view.Display;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Logica.Tablero;
import com.example.Utiles.constantes;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class DibujarTablero implements Observer {
    private MainActivity mainActivity;
    private LinearLayout linearT;
    private GridLayout gridLayoutTablero;
    private ImageView cuadros [][];

    public DibujarTablero(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.cuadros = new ImageView[Tablero.FILAS][Tablero.COLUMNAS];
        this.gridLayoutTablero = new GridLayout(this.mainActivity);
        this.gridLayoutTablero.setColumnCount(Tablero.COLUMNAS);
        this.gridLayoutTablero.setRowCount(Tablero.FILAS);
        this.linearT = this.mainActivity.findViewById(R.id.layoutTablero);
        Display display = this.mainActivity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        for(int i=0; i<Tablero.FILAS; i++){
            for(int j=0; j<Tablero.COLUMNAS; j++){
                this.cuadros[i][j] = new ImageView(this.mainActivity);
              //  this.cuadros[i][j].setWidth((int)(width/Tablero.COLUMNAS));
               // this.cuadros[i][j].setHeight((int)(width/Tablero.COLUMNAS));
              this.cuadros[i][j].setLayoutParams(new LinearLayout.LayoutParams((int)(width/Tablero.COLUMNAS),(int)(width/Tablero.COLUMNAS)));
              this.cuadros[i][j].setBackgroundColor(Color.WHITE);
           //   this.cuadros[i][j].setImageResource(R.drawable.nulo);
                this.gridLayoutTablero.addView(this.cuadros[i][j]);
            }
        }
        this.linearT.addView(this.gridLayoutTablero);
    }
    public void limpiar(){
        this.linearT.removeView(this.gridLayoutTablero);
    }

    @Override
    public void update(Observable o, Object arg) {
        try{
            ArrayList<Object> elemento = (ArrayList<Object>) arg;
            int segmentos[][] = (int[][])elemento.get(0);
            if(elemento.size() > 1){
                    int segmentosAnteriores[][] = (int[][])elemento.get(1);
                    for(int i=0; i<segmentosAnteriores.length; i++){
                        this.cuadros[segmentosAnteriores[i][0]][segmentosAnteriores[i][1]].setBackgroundColor(Color.WHITE);
                      //  this.cuadros[segmentosAnteriores[i][0]][segmentosAnteriores[i][1]].setImageResource(0);
                    }
            }

            for(int i=0; i<segmentos.length; i++){
               //this.cuadros[segmentos[i][0]][segmentos[i][1]].setImageResource(R.drawable.cuerpo);
               this.cuadros[segmentos[i][0]][segmentos[i][1]].setBackgroundColor(segmentos[i][2]);
            }
        }catch (Exception e){
            System.out.println("Observador 2 no notificado");

        }


    }
}
