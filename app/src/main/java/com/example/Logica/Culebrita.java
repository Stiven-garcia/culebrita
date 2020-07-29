package com.example.Logica;

import android.graphics.Color;

import com.example.culebrita.R;

import java.util.ArrayList;

public class Culebrita {
    private ArrayList<nodo> cuerpo = new ArrayList<nodo>();
    private nodo elemento;
    private int orientacion=0;
    private Tablero tablero;
    public Culebrita(Tablero tablero){
        elemento = new nodo(13, 6, Color.BLACK);
        cuerpo.add(elemento);
        elemento = new nodo(13, 5, Color.GRAY);
        cuerpo.add(elemento);
        elemento = new nodo(13, 4, Color.GRAY);
        cuerpo.add(elemento);
        elemento = new nodo(13, 3, Color.GRAY);
        cuerpo.add(elemento);
        this.tablero = tablero;
    }
    public int[] getUltimo(){
        return this.cuerpo.get(this.cuerpo.size()-1).getPosciciones();
    }

    public boolean arriba(){
        int x =this.cuerpo.get(0).getPosciciones()[0];
        int y = this.cuerpo.get(0).getPosciciones()[1];
        if(x==0 || this.tablero.getTablero()[x-1][y]==1){
            return false;
        }
        this.cuerpo.get(0).setPosciciones(x-1,y);
        nodo elemento = new nodo(x,y,Color.GRAY);
        this.cuerpo.add(1,elemento);
        if(this.tablero.getTablero()[x-1][y]!=2){
            this.cuerpo.remove(this.cuerpo.size()-1);
        }


        return true;
    }
    public boolean abajo(){
        int x =this.cuerpo.get(0).getPosciciones()[0];
        int y = this.cuerpo.get(0).getPosciciones()[1];
        if(x==Tablero.FILAS-1 || this.tablero.getTablero()[x+1][y]==1){
            return false;
        }
        this.cuerpo.get(0).setPosciciones(x+1,y);
        nodo elemento = new nodo(x,y,Color.GRAY);
        this.cuerpo.add(1,elemento);
        if(this.tablero.getTablero()[x+1][y]!=2){
            this.cuerpo.remove(this.cuerpo.size()-1);
        }

        return true;
    }
    public boolean izquierda(){
        int x =this.cuerpo.get(0).getPosciciones()[0];
        int y = this.cuerpo.get(0).getPosciciones()[1];
        if(y==0 || this.tablero.getTablero()[x][y-1]==1){
            return false;
        }
        this.cuerpo.get(0).setPosciciones(x,y-1);
        nodo elemento = new nodo(x,y,Color.GRAY);
        this.cuerpo.add(1,elemento);
        if(this.tablero.getTablero()[x][y-1]!=2){
            this.cuerpo.remove(this.cuerpo.size()-1);
        }
        return true;
    }
    public boolean derecha(){
        int x =this.cuerpo.get(0).getPosciciones()[0];
        int y = this.cuerpo.get(0).getPosciciones()[1];
        if(y==Tablero.COLUMNAS-1 || this.tablero.getTablero()[x][y+1]==1){
            return false;
        }

        this.cuerpo.get(0).setPosciciones(x,y+1);
        nodo elemento = new nodo(x,y,Color.GRAY);
        this.cuerpo.add(1,elemento);
        if(this.tablero.getTablero()[x][y+1]!=2){
            this.cuerpo.remove(this.cuerpo.size()-1);
        }

        return true;
    }

    public int getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(int orientacion) {
        this.orientacion = orientacion;
    }

    public ArrayList<nodo> getCuerpo() {
        return cuerpo;
    }
}
