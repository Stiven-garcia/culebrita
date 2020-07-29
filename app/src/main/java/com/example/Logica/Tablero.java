package com.example.Logica;

import android.graphics.Color;

import com.example.Utiles.constantes;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Tablero extends Observable {
    public final static int FILAS = 27;
    public final static int COLUMNAS = 20;
    public int tablero[][];
    private Culebrita culebrita;
    private nodo Comida;
    private Random aleatorio;
    private String orientacionT = null;
    private int puntaje;
    private static int best =0;
    private int h;
    private int m;
    private int s;
    private Timer timer;
    private TimerTask timerTask;
    private boolean run;
    private int periodo;
    private boolean aumentar;

    public void JuegoNuevo(){
        this.h=0;
        this.m=0;
        this.s=0;
        this.run=true;
        this.puntaje=0;
        this.periodo=250;
        this.aumentar = false;
        this.tablero= new int [this.FILAS] [this.COLUMNAS];
        this.culebrita = new Culebrita(this);
        this.aleatorio = new Random();
        ArrayList<nodo> cuerpo = this.culebrita.getCuerpo();
        ArrayList<Object> elemento = new ArrayList<Object>();
        int segmentos [][] = new int [cuerpo.size()][3];
        for(int i =0; i< cuerpo.size(); i++){
            int x = cuerpo.get(i).getPosciciones()[0];
            int y = cuerpo.get(i).getPosciciones()[1];
            segmentos [i][0] = x;
            segmentos [i][1] = y;
            segmentos [i][2] = cuerpo.get(i).getColor();
            this.tablero [x] [y]= 1;

        }
        elemento.add(segmentos);
        this.setChanged();
        this.notifyObservers(elemento);
        this.comidaNueva();
        this.orientacionT = constantes.DERECHA;
        this.timerTask();
        this.timerTiempo();
        this.puntaje(0);
    }
    public boolean getRun(){
        return this.run;
    }
    public void setRun(boolean run){
        this.run= run;
    }
    public int[][] getTablero() {
        return tablero;
    }

    public String getOrientacionT() {
        return orientacionT;
    }
    public void timerTiempo(){
        final Timer timerT = new Timer();
        TimerTask timerTaskT= new TimerTask(){

            @Override
            public void run() {

                if(getRun()){
                    tiempo();
                }else{
                    this.cancel();
                    timerT.purge();
                    timerT.cancel();

                }

            }
        };
        timerT.scheduleAtFixedRate(timerTaskT,0,1000);
    }
    public void setOrientacionT(String orientacionT) {
        this.orientacionT = orientacionT;
    }
    public void tiempo(){

        this.s++;
        if(s==60){
            this.s=0;
            this.m++;
            if(m==60){
                this.m=0;
                this.h++;
            }
        }
        String tiempo="";
        if(h<10){
            tiempo="0"+h;
        }else{
            tiempo=Integer.toString(h);
        }
        tiempo+=":";
       if(m<10) {
           tiempo += "0" + m;
       }else{
           tiempo+=m;
       }
        tiempo+=":";
        if(s<10) {
            tiempo += "0" + s;
        }else{
            tiempo+=s;
        }
        this.setChanged();
        this.notifyObservers(tiempo);
    }

    public void puntaje(int puntos){
        this.puntaje+=puntos;
        if(this.periodo-10>100){
            this.periodo-=10;
        }

       if(this.puntaje > this.best){
            this.best++;
        }
        this.setChanged();
        this.notifyObservers(this.puntaje);
    }
    public void comidaNueva() {
        int x;
        int y;
        do {
            x = this.aleatorio.nextInt(this.FILAS);
            y = this.aleatorio.nextInt(this.COLUMNAS);
        } while (this.tablero[x][y] != 0);
        this.Comida = new nodo(x, y, Color.BLUE);
        ArrayList<Object> elemento = new ArrayList<Object>();
        int segmentos[][] = new int[1][3];
        segmentos[0][0] = x;
        segmentos[0][1] = y;
        segmentos[0][2] = this.Comida.getColor();
        elemento.add(segmentos);
        this.tablero [x][y]= 2;
        this.setChanged();
        this.notifyObservers(elemento);
    }

    public boolean isAumentar() {
        return aumentar;
    }

    public void setAumentar(boolean aumentar) {
        this.aumentar = aumentar;
    }

    public void timerTask(){
        this.timer = new Timer();
        this.timerTask= new TimerTask(){
                public void run(){
                    if(!getRun()){
                      /*  if (isAumentar()){
                            System.out.println("Tiempo: "+ periodo);
                            setAumentar(false);
                        }*/
                        this.cancel();
                        timer.purge();
                        timer.cancel();

                    }else{
                        if(getOrientacionT()==constantes.IZQUIERDA){
                            movimiento(constantes.IZQUIERDA);
                        }else {
                            if(getOrientacionT()==constantes.DERECHA) {
                                movimiento(constantes.DERECHA);
                            }else {
                                if(getOrientacionT()==constantes.ARRIBA) {
                                    movimiento(constantes.ARRIBA);
                                }else {
                                    if(getOrientacionT()==constantes.ABAJO) {
                                        movimiento(constantes.ABAJO);
                                    }
                                }
                            }
                        }
                    }



                }

            };
        this.timer.scheduleAtFixedRate(timerTask, 0, this.periodo);
    }

    public void movimiento(String movimiento) {
        int [][]segmentosAnteriores = new int[1][2];
        segmentosAnteriores[0][0] = this.culebrita.getUltimo()[0];
        segmentosAnteriores[0][1] = this.culebrita.getUltimo()[1];
        this.tablero[this.culebrita.getUltimo()[0]][this.culebrita.getUltimo()[1]]=0;
        boolean hecho = false;
        int orientacion = this.culebrita.getOrientacion();
        if((movimiento == constantes.IZQUIERDA && orientacion!=0) || (movimiento == constantes.DERECHA && orientacion==1)){
            hecho = this.culebrita.izquierda();
            this.culebrita.setOrientacion(1);
        }else {
            if ((movimiento == constantes.DERECHA && orientacion!=1)|| (movimiento == constantes.IZQUIERDA && orientacion==0)) {
                hecho = this.culebrita.derecha();
                this.culebrita.setOrientacion(0);
            }else{
                if((movimiento == constantes.ABAJO && orientacion!=3)|| (movimiento == constantes.ARRIBA && orientacion==2)){
                    hecho = this.culebrita.abajo();
                    this.culebrita.setOrientacion(2);
                }else{
                    if((movimiento == constantes.ARRIBA && orientacion!=2) || (movimiento == constantes.ABAJO && orientacion==3)) {
                        hecho = this.culebrita.arriba();
                        this.culebrita.setOrientacion(3);
                    }
                }
            }
        }

        if(hecho){
            boolean eliminar = true;
            ArrayList<Object> elemento = new ArrayList<Object>();
            int segmentos[][] = new int[2][3];
            for(int i=0; i<2; i++){
                int x = this.culebrita.getCuerpo().get(i).getPosciciones()[0];
                int y = this.culebrita.getCuerpo().get(i).getPosciciones()[1];
                segmentos[i][0]=x;
                segmentos[i][1]=y;
                segmentos[i][2]=this.culebrita.getCuerpo().get(i).getColor();
                this.tablero[x][y]=1;
            }
            elemento.add(segmentos);
            if(this.tablero[this.Comida.getPosciciones()[0]][this.Comida.getPosciciones()[1]]!=2){
                eliminar = false;
            }else{
                elemento.add(segmentosAnteriores);
            }

            this.setChanged();
            this.notifyObservers(elemento);
            if(!eliminar){
                this.comidaNueva();
                this.puntaje(1);
                this.timer.cancel();
                this.timerTask();
            }
        }else{
            this.run=false;
            this.perder();

        }

    }

    public void perder(){
        int [] datos = new int[2];
        datos[0] = this.puntaje;
        datos[1] = this.best;
        this.setChanged();
        this.notifyObservers(datos);
    }
}
