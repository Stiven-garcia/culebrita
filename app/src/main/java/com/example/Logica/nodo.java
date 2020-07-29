package com.example.Logica;

import android.graphics.Color;

public class nodo {
    private int posciciones [];
    private int color;

    public nodo(int x, int y, int color ) {
        this.posciciones = new int [2];
        this.posciciones [0] = x;
        this.posciciones [1] = y;
        this.color = color;
    }

    public int[] getPosciciones() {
        return posciciones;
    }

    public int getColor() {
        return color;
    }

    public void setPosciciones(int x, int y) {

        this.posciciones[0] = x;
        this.posciciones[1] = y;
    }
}
