package dispmovil.siua.una.ac.cr.proyectomoviles;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Timer;
import java.util.TimerTask;

import dispmovil.siua.una.ac.cr.proyectomoviles.Modelo.Bala;
import dispmovil.siua.una.ac.cr.proyectomoviles.Modelo.BalaInvader;
import dispmovil.siua.una.ac.cr.proyectomoviles.Modelo.Base;
import dispmovil.siua.una.ac.cr.proyectomoviles.Modelo.GameOver;
import dispmovil.siua.una.ac.cr.proyectomoviles.Modelo.Ganar;
import dispmovil.siua.una.ac.cr.proyectomoviles.Modelo.Invader1;
import dispmovil.siua.una.ac.cr.proyectomoviles.Modelo.Invader2;
import dispmovil.siua.una.ac.cr.proyectomoviles.Modelo.Invader3;
import dispmovil.siua.una.ac.cr.proyectomoviles.Modelo.InvaderValidar;
import dispmovil.siua.una.ac.cr.proyectomoviles.Modelo.Nave;
import dispmovil.siua.una.ac.cr.proyectomoviles.Modelo.Salir;
import dispmovil.siua.una.ac.cr.proyectomoviles.Modelo.VidaNave;

/*
<ProyectoMoviles is a basic game designed in Android.>
    Copyright (C) <2015>  <JomajoUNA>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

 */
public class Paneljuego extends SurfaceView implements SurfaceHolder.Callback{

    Nave nave;
    Bala bala;
    Salir salir;
    InvaderValidar invaderValidarInicio;
    InvaderValidar invaderValidarFinal;
    GameOver gameover;
    Ganar ganar;
    BalaInvader balaInvader;
    BalaInvader balaInvader1;
    BalaInvader balaInvader2;
    Hiloprincipal hilo;
    Bitmap background;
    Bitmap puntos;
    Bitmap vida;
    Bitmap choque;
    int ancho1;
    int alto1;
    Bitmap bitmap;
    Base arraybase[]=new Base[3];
    Invader1 arrayinvader1[]=new Invader1[9];
    Invader2 arrayinvader2[]=new Invader2[9];
    Invader2 arrayinvader3[]=new Invader2[9];
    Invader3 arrayinvader4[]=new Invader3[9];
    Invader3 arrayinvader5[]=new Invader3[9];
    VidaNave arrayvida[]=new VidaNave[3];
    String arraydatosjugador[]=new String[3];
    Timer timer = new Timer();
    private Bitmap bmpUp;
    private Bitmap bmpRight;
    private Bitmap bmpLeft;
    int valor;
    int marcador=0;
    String usuario="";
    boolean bandera=false;
    MediaPlayer mediaPlayer1;
    MediaPlayer mediaPlayer2;
    MediaPlayer soundwin;
    MediaPlayer soundgameover;
    MediaPlayer mediaPlayer4;
    boolean choco1=false;
    boolean choco2=false;
    boolean choco3=false;
    boolean choco4=false;
    boolean choco5=false;
    int numeroChoco1=12;
    int numeroChoco2=12;
    int numeroChoco3=12;
    int numeroChoco4=12;
    int numeroChoco5=12;
    int numeroChocoBase=4;
    boolean disparar=false;
    boolean izquierda;
    boolean derecha;
    boolean exit;
    Context context1;
    int vidasNave=0;
    int contador=0;
    int contador1=0;
    int contador2=0;
    String usuarioArchivo="";





    public Paneljuego(Context context,int ancho,int alto,Bitmap bmp,MediaPlayer mediaPlayer,MediaPlayer mediaPlayer3,MediaPlayer mediaPlayer5,MediaPlayer win,MediaPlayer over,String usuarioAr){
        super(context);
        getHolder().addCallback(this);
        setFocusable(true);
        usuarioArchivo=usuarioAr;
        leer();
        vidasNave=Integer.parseInt(arraydatosjugador[2].toString());
        marcador=Integer.parseInt(arraydatosjugador[1].toString());
        usuario=arraydatosjugador[0].toString();
        context1=context;
        ancho1=ancho;
        alto1=alto;
        mediaPlayer1=mediaPlayer;
        mediaPlayer2=mediaPlayer3;
        mediaPlayer4=mediaPlayer5;
        soundwin=win;
        soundgameover=over;
        System.out.println(usuarioArchivo);
        bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.nave);
        nave = new Nave(ancho/2,alto-25,bitmap);
        bala=new Bala(ancho/2,alto-25,BitmapFactory.decodeResource(getResources(), R.drawable.bala));
        salir = new Salir(ancho/2,100,BitmapFactory.decodeResource(getResources(), R.drawable.salir));
        arraybase[0]= new Base(220,alto-125,BitmapFactory.decodeResource(getResources(), R.drawable.base1));
        arraybase[1]= new Base(ancho/2,alto-125,BitmapFactory.decodeResource(getResources(), R.drawable.base1));
        arraybase[2]= new Base(ancho-200,alto-125,BitmapFactory.decodeResource(getResources(), R.drawable.base1));


        arrayinvader1[0]=new Invader1(context,220,alto/2-150,true);
        arrayinvader1[1]=new Invader1(context,arrayinvader1[0].getAncho1()+100,alto/2-150,true);
        arrayinvader1[2]=new Invader1(context,arrayinvader1[1].getAncho1()+100,alto/2-150,true);
        arrayinvader1[3]=new Invader1(context,arrayinvader1[2].getAncho1()+100,alto/2-150,true);
        arrayinvader1[4]=new Invader1(context,arrayinvader1[3].getAncho1()+100,alto/2-150,true);
        arrayinvader1[5]=new Invader1(context,arrayinvader1[4].getAncho1()+100,alto/2-150,true);
        arrayinvader1[6]=new Invader1(context,arrayinvader1[5].getAncho1()+100,alto/2-150,true);
        arrayinvader1[7]=new Invader1(context,arrayinvader1[6].getAncho1()+100,alto/2-150,true);
        arrayinvader1[8]=new Invader1(context,arrayinvader1[7].getAncho1()+100,alto/2-150,true);
        //arrayinvader1[9]=new Invader1(context,arrayinvader1[8].getAncho1()+100,alto/2-250,true);
       // arrayinvader1[10]=new Invader1(context,arrayinvader1[9].getAncho1()+100,alto/2-250,true);


        arrayinvader2[0]=new Invader2(context,220,arrayinvader1[0].getLargo1()+50,true);
        arrayinvader2[1]=new Invader2(context,arrayinvader2[0].getAncho1()+100,arrayinvader1[0].getLargo1()+50,true);
        arrayinvader2[2]=new Invader2(context,arrayinvader2[1].getAncho1()+100,arrayinvader1[0].getLargo1()+50,true);
        arrayinvader2[3]=new Invader2(context,arrayinvader2[2].getAncho1()+100,arrayinvader1[0].getLargo1()+50,true);
        arrayinvader2[4]=new Invader2(context,arrayinvader2[3].getAncho1()+100,arrayinvader1[0].getLargo1()+50,true);
        arrayinvader2[5]=new Invader2(context,arrayinvader2[4].getAncho1()+100,arrayinvader1[0].getLargo1()+50,true);
        arrayinvader2[6]=new Invader2(context,arrayinvader2[5].getAncho1()+100,arrayinvader1[0].getLargo1()+50,true);
        arrayinvader2[7]=new Invader2(context,arrayinvader2[6].getAncho1()+100,arrayinvader1[0].getLargo1()+50,true);
        arrayinvader2[8]=new Invader2(context,arrayinvader2[7].getAncho1()+100,arrayinvader1[0].getLargo1()+50,true);
        //arrayinvader2[9]=new Invader2(context,arrayinvader2[8].getAncho1()+100,arrayinvader1[0].getLargo1()+50,true);
        //arrayinvader2[10]=new Invader2(context,arrayinvader2[9].getAncho1()+100,arrayinvader1[0].getLargo1()+50,true);

        arrayinvader3[0]=new Invader2(context,220,arrayinvader2[0].getLargo1()+50,true);
        arrayinvader3[1]=new Invader2(context,arrayinvader3[0].getAncho1()+100,arrayinvader2[0].getLargo1()+50,true);
        arrayinvader3[2]=new Invader2(context,arrayinvader3[1].getAncho1()+100,arrayinvader2[0].getLargo1()+50,true);
        arrayinvader3[3]=new Invader2(context,arrayinvader3[2].getAncho1()+100,arrayinvader2[0].getLargo1()+50,true);
        arrayinvader3[4]=new Invader2(context,arrayinvader3[3].getAncho1()+100,arrayinvader2[0].getLargo1()+50,true);
        arrayinvader3[5]=new Invader2(context,arrayinvader3[4].getAncho1()+100,arrayinvader2[0].getLargo1()+50,true);
        arrayinvader3[6]=new Invader2(context,arrayinvader3[5].getAncho1()+100,arrayinvader2[0].getLargo1()+50,true);
        arrayinvader3[7]=new Invader2(context,arrayinvader3[6].getAncho1()+100,arrayinvader2[0].getLargo1()+50,true);
        arrayinvader3[8]=new Invader2(context,arrayinvader3[7].getAncho1()+100,arrayinvader2[0].getLargo1()+50,true);
        //arrayinvader3[9]=new Invader2(context,arrayinvader3[8].getAncho1()+100,arrayinvader2[0].getLargo1()+50,true);
       // arrayinvader3[10]=new Invader2(context,arrayinvader3[9].getAncho1()+100,arrayinvader2[0].getLargo1()+50,true);

        arrayinvader4[0]=new Invader3(context,220,arrayinvader3[0].getLargo1()+50,true);
        arrayinvader4[1]=new Invader3(context,arrayinvader4[0].getAncho1()+100,arrayinvader3[0].getLargo1()+50,true);
        arrayinvader4[2]=new Invader3(context,arrayinvader4[1].getAncho1()+100,arrayinvader3[0].getLargo1()+50,true);
        arrayinvader4[3]=new Invader3(context,arrayinvader4[2].getAncho1()+100,arrayinvader3[0].getLargo1()+50,true);
        arrayinvader4[4]=new Invader3(context,arrayinvader4[3].getAncho1()+100,arrayinvader3[0].getLargo1()+50,true);
        arrayinvader4[5]=new Invader3(context,arrayinvader4[4].getAncho1()+100,arrayinvader3[0].getLargo1()+50,true);
        arrayinvader4[6]=new Invader3(context,arrayinvader4[5].getAncho1()+100,arrayinvader3[0].getLargo1()+50,true);
        arrayinvader4[7]=new Invader3(context,arrayinvader4[6].getAncho1()+100,arrayinvader3[0].getLargo1()+50,true);
        arrayinvader4[8]=new Invader3(context,arrayinvader4[7].getAncho1()+100,arrayinvader3[0].getLargo1()+50,true);
       // arrayinvader4[9]=new Invader3(context,arrayinvader4[8].getAncho1()+100,arrayinvader3[0].getLargo1()+50,true);
       // arrayinvader4[10]=new Invader3(context,arrayinvader4[9].getAncho1()+100,arrayinvader3[0].getLargo1()+50,true);

        arrayinvader5[0]=new Invader3(context,220,arrayinvader4[0].getLargo1()+50,true);
        arrayinvader5[1]=new Invader3(context,arrayinvader5[0].getAncho1()+100,arrayinvader4[0].getLargo1()+50,true);
        arrayinvader5[2]=new Invader3(context,arrayinvader5[1].getAncho1()+100,arrayinvader4[0].getLargo1()+50,true);
        arrayinvader5[3]=new Invader3(context,arrayinvader5[2].getAncho1()+100,arrayinvader4[0].getLargo1()+50,true);
        arrayinvader5[4]=new Invader3(context,arrayinvader5[3].getAncho1()+100,arrayinvader4[0].getLargo1()+50,true);
        arrayinvader5[5]=new Invader3(context,arrayinvader5[4].getAncho1()+100,arrayinvader4[0].getLargo1()+50,true);
        arrayinvader5[6]=new Invader3(context,arrayinvader5[5].getAncho1()+100,arrayinvader4[0].getLargo1()+50,true);
        arrayinvader5[7]=new Invader3(context,arrayinvader5[6].getAncho1()+100,arrayinvader4[0].getLargo1()+50,true);
        arrayinvader5[8]=new Invader3(context,arrayinvader5[7].getAncho1()+100,arrayinvader4[0].getLargo1()+50,true);
        //arrayinvader5[9]=new Invader3(context,arrayinvader5[8].getAncho1()+100,arrayinvader4[0].getLargo1()+50,true);
        //arrayinvader5[10]=new Invader3(context,arrayinvader5[9].getAncho1()+100,arrayinvader4[0].getLargo1()+50,true);

        balaInvader= new BalaInvader(arrayinvader1[0].getAncho1(),arrayinvader1[0].getLargo1(),BitmapFactory.decodeResource(getResources(), R.drawable.bala1));
        balaInvader1= new BalaInvader(arrayinvader1[4].getAncho1(),arrayinvader1[4].getLargo1(),BitmapFactory.decodeResource(getResources(), R.drawable.bala1));
        balaInvader2= new BalaInvader(arrayinvader1[7].getAncho1(),arrayinvader1[7].getLargo1(),BitmapFactory.decodeResource(getResources(), R.drawable.bala1));

        invaderValidarInicio=new InvaderValidar(arrayinvader1[0].getAncho1(),arrayinvader1[0].getLargo1(),BitmapFactory.decodeResource(getResources(), R.drawable.invaderinvisible));
        invaderValidarFinal=new InvaderValidar(arrayinvader1[arrayinvader1.length-1].getAncho1(),arrayinvader1[arrayinvader1.length-1].getLargo1(),BitmapFactory.decodeResource(getResources(), R.drawable.invaderinvisible));

        gameover=new GameOver (ancho/2, alto/2, BitmapFactory.decodeResource(getResources(), R.drawable.gameover));
        ganar=new Ganar (ancho/2, alto/2, BitmapFactory.decodeResource(getResources(), R.drawable.ganar));
        arrayvida[0]=new VidaNave(ancho - 165,100, BitmapFactory.decodeResource(getResources(), R.drawable.nave1));
        arrayvida[1]=new VidaNave(ancho - 115,100, BitmapFactory.decodeResource(getResources(), R.drawable.nave1));
        arrayvida[2]=new VidaNave(ancho - 65,100, BitmapFactory.decodeResource(getResources(), R.drawable.nave1));


        choque= BitmapFactory.decodeResource(getResources(), R.drawable.invaderkilled);
        puntos= BitmapFactory.decodeResource(getResources(), R.drawable.puntaje);
        vida= BitmapFactory.decodeResource(getResources(), R.drawable.vida);
        bmpLeft = BitmapFactory.decodeResource(getResources(), R.drawable.left);
        bmpRight = BitmapFactory.decodeResource(getResources(), R.drawable.right);
        bmpUp= BitmapFactory.decodeResource(getResources(), R.drawable.disparo);
        hilo = new Hiloprincipal(getHolder(),this);
        background = bmp;

        timer.scheduleAtFixedRate(timerTask1, 100, 300);

    }

    public void leer() {
        try {
            File myFile = new File("/sdcard/"+usuarioArchivo+".txt");
            FileInputStream fIn = new FileInputStream(myFile);
            BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
            String aDataRow = "";
            String aBuffer = "";

            while ((aDataRow = myReader.readLine()) != null) {
                aBuffer += aDataRow + "\n";
                String usuarioArray[] = aDataRow.split(",");
                if(aDataRow.equals("")){

                }else{
                    arraydatosjugador[0]=usuarioArray[0];
                    arraydatosjugador[1]=usuarioArray[1];
                    arraydatosjugador[2]=usuarioArray[2];
                }
            }
            myReader.close();
        } catch (Exception e) {

        }
    }
    public void escribir() {

                try {
                    File myFile = new File("/sdcard/"+usuarioArchivo+".txt");
                    String separator = System.getProperty("line.separator");
                    FileOutputStream fOut = new FileOutputStream(myFile);
                    OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
                        myOutWriter.append(usuario+","+marcador+","+vidasNave);
                        myOutWriter.append(separator);
                        myOutWriter.close();
                        fOut.close();
                } catch (Exception e) {
          }
    }
    public void escribirwinover() {

        try {
            File myFile = new File("/sdcard/"+usuarioArchivo+".txt");
            String separator = System.getProperty("line.separator");
            FileOutputStream fOut = new FileOutputStream(myFile);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append(usuario+",0,2");
            myOutWriter.append(separator);
            myOutWriter.close();
            fOut.close();
        } catch (Exception e) {
      }
    }
    public void dibujarExplocion(Canvas canvas){
        for (int i = 0; i < arrayinvader5.length; i++) {
            if(choco5==true){
                arrayinvader5[numeroChoco5]=new Invader3(context1,arrayinvader5[numeroChoco5].getAncho1(),arrayinvader5[numeroChoco5].getLargo1(),false);
                esperar(1);
                arrayinvader5[numeroChoco5].draw(canvas);
                esperar(1);
                arrayinvader5[numeroChoco5].setLargo1(alto1+500);
                arrayinvader5[numeroChoco5].setAncho1(ancho1+500);
                choco5=false;
            }
            if(i!=numeroChoco5) {
                arrayinvader5[i].draw(canvas);
            }

        }
        for (int i = 0; i < arrayinvader4.length; i++) {
            if(choco4==true){
                arrayinvader4[numeroChoco4]=new Invader3(context1,arrayinvader4[numeroChoco4].getAncho1(),arrayinvader4[numeroChoco4].getLargo1(),false);
                esperar(1);
                arrayinvader4[numeroChoco4].draw(canvas);
                esperar(1);
                arrayinvader4[numeroChoco4].setLargo1(alto1+500);
                arrayinvader4[numeroChoco4].setAncho1(ancho1+500);
                choco4=false;
            }
            if(i!=numeroChoco4) {
                arrayinvader4[i].draw(canvas);
            }
        }
        for (int i = 0; i < arrayinvader3.length; i++) {
            if(choco3==true){
                arrayinvader3[numeroChoco3]=new Invader2(context1,arrayinvader3[numeroChoco3].getAncho1(),arrayinvader3[numeroChoco3].getLargo1(),false);
                esperar(1);
                arrayinvader3[numeroChoco3].draw(canvas);
                esperar(1);
                arrayinvader3[numeroChoco3].setLargo1(alto1+100);
                arrayinvader3[numeroChoco3].setAncho1(ancho1+500);
                choco3=false;
            }
            if(i!=numeroChoco3) {
                arrayinvader3[i].draw(canvas);
            }
        }
        for (int i = 0; i < arrayinvader2.length; i++) {
            if(choco2==true){
                arrayinvader2[numeroChoco2]=new Invader2(context1,arrayinvader2[numeroChoco2].getAncho1(),arrayinvader2[numeroChoco2].getLargo1(),false);
                esperar(1);
                arrayinvader2[numeroChoco2].draw(canvas);
                esperar(1);
                arrayinvader2[numeroChoco2].setLargo1(alto1+100);
                arrayinvader2[numeroChoco2].setAncho1(ancho1+500);
                choco2=false;
            }
            if(i!=numeroChoco2) {
                arrayinvader2[i].draw(canvas);
            }
        }
        for (int i = 0; i < arrayinvader1.length; i++) {
            if(choco1==true){
                arrayinvader1[numeroChoco1]=new Invader1(context1,arrayinvader1[numeroChoco1].getAncho1(),arrayinvader1[numeroChoco1].getLargo1(),false);
                esperar(1);
                arrayinvader1[numeroChoco1].draw(canvas);
                esperar(1);
                arrayinvader1[numeroChoco1].setLargo1(alto1+100);
                arrayinvader1[numeroChoco1].setAncho1(ancho1+500);
                choco1=false;
            }
            if(i!=numeroChoco1) {
                arrayinvader1[i].draw(canvas);
            }
        }
    }
    public void winover(Canvas canvas){

        if(vidasNave<0) {
            gameover.draw(canvas);
            soundgameover.start();
            escribirwinover();
            hilo.setRunning(false);

            soundgameover.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Intent intent = new Intent(context1, Inicio.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context1.startActivity(intent);
                    ((Activity)(context1)).finish();
                }
            });
        }
        if(marcador==110) {
            ganar.draw(canvas);
            soundwin.start();
            escribirwinover();
            hilo.setRunning(false);

            soundwin.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Intent intent = new Intent(context1, Inicio.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context1.startActivity(intent);
                    ((Activity)(context1)).finish();
                }
            });
        }
    }
    public void ChocarBaseNave(){

        for (int i = 0; i < arraybase.length; i++) {
            if (bala.getY()+bala.getAlto() < arraybase[i].getY()+arraybase[i].getAlto()-2 && bala.getX() > arraybase[i].getX() - arraybase[i].getAncho() / 2 && bala.getX() < arraybase[i].getX() + arraybase[i].getAncho() / 2) {
                bala.setY(alto1 - 30);
                bala.setX(nave.getX());
                numeroChocoBase=i;
                if(numeroChocoBase==0){
                    contador++;
                }
                if(numeroChocoBase==1){
                    contador1++;
                }
                if(numeroChocoBase==2){
                    contador2++;
                }
                bandera = false;
            }
        }
    }
    public void ChocarBaseInvader(){

        for (int i = 0; i < arraybase.length; i++) {
            if (balaInvader.getY() + balaInvader.getAlto() / 2 >= arraybase[i].getY() - arraybase[i].getAlto() ) {
                if (balaInvader.getX() + balaInvader.getAncho() / 2 >= arraybase[i].getX() - arraybase[i].getAncho() / 2 && balaInvader.getX() - balaInvader.getAncho() / 2 <= arraybase[i].getX() + arraybase[i].getAncho() / 2) {
                    valor = (int) (Math.random() * 8) + 0;
                    balaInvader.setX(arrayinvader1[valor].getAncho1());
                    balaInvader.setY(arrayinvader1[valor].getLargo1());
                    numeroChocoBase=i;
                    if(numeroChocoBase==0){
                        contador++;
                    }
                    if(numeroChocoBase==1){
                        contador1++;
                    }
                    if(numeroChocoBase==2){
                        contador2++;
                    }
                }
            }
        }
        for (int i = 0; i < arraybase.length; i++) {
            if (balaInvader1.getY() + balaInvader1.getAlto() / 2 >= arraybase[i].getY() - arraybase[i].getAlto() ) {
                if (balaInvader1.getX() + balaInvader1.getAncho() / 2 >= arraybase[i].getX() - arraybase[i].getAncho() / 2 && balaInvader1.getX() - balaInvader1.getAncho() / 2 <= arraybase[i].getX() + arraybase[i].getAncho() / 2) {
                    valor = (int) (Math.random() * 8) + 0;
                    balaInvader1.setX(arrayinvader1[valor].getAncho1());
                    balaInvader1.setY(arrayinvader1[valor].getLargo1());
                    numeroChocoBase=i;
                    if(numeroChocoBase==0){
                        contador++;
                    }
                    if(numeroChocoBase==1){
                        contador1++;
                    }
                    if(numeroChocoBase==2){
                        contador2++;
                    }

                }
            }
        }
        for (int i = 0; i < arraybase.length; i++) {
            if (balaInvader2.getY() + balaInvader2.getAlto() / 2 >= arraybase[i].getY() - arraybase[i].getAlto() ) {
                if (balaInvader2.getX() + balaInvader2.getAncho() / 2 >= arraybase[i].getX() - arraybase[i].getAncho() / 2 && balaInvader2.getX() - balaInvader2.getAncho() / 2 <= arraybase[i].getX() + arraybase[i].getAncho() / 2) {
                    valor = (int) (Math.random() * 8) + 0;
                    balaInvader2.setX(arrayinvader1[valor].getAncho1());
                    balaInvader2.setY(arrayinvader1[valor].getLargo1());
                    numeroChocoBase=i;
                    if(numeroChocoBase==0){
                        contador++;
                    }
                    if(numeroChocoBase==1){
                        contador1++;
                    }
                    if(numeroChocoBase==2){
                        contador2++;
                    }

                }
            }
        }
    }
    public boolean ChocarInvader1() {
        for (int i = 0; i < arrayinvader1.length; i++) {
            if (bala.getY() < arrayinvader1[i].getLargo1() && bala.getX() > arrayinvader1[i].getAncho1() - arrayinvader1[i].getAnchoimagen() / 2 && bala.getX() < arrayinvader1[i].getAncho1() + arrayinvader1[i].getAnchoimagen() / 2) {
                marcador = marcador + 4;
                numeroChoco1 = i;
                choco1 = true;
                mediaPlayer2.start();
                bala.setX(nave.getX());
                bala.setY(alto1 - 20);
                bandera = false;
            }
        }
        return choco1;
    }

    public boolean ChocarInvader2() {
        for (int i = 0; i < arrayinvader2.length; i++) {
            if (bala.getY() < arrayinvader2[i].getLargo1()&&bala.getX() > arrayinvader2[i].getAncho1() - arrayinvader2[i].getAnchoimagen() / 2 && bala.getX() < arrayinvader2[i].getAncho1() + arrayinvader2[i].getAnchoimagen() / 2){
                marcador=marcador+2;
                numeroChoco2=i;
                choco2=true;
                mediaPlayer2.start();
                bala.setX(nave.getX());
                bala.setY(alto1-20);
                bandera=false;
            }
        }
        return choco2;
    }
    public boolean ChocarInvader3() {
        for (int i = 0; i < arrayinvader3.length; i++) {
            if (bala.getY() < arrayinvader3[i].getLargo1()&&bala.getX() > arrayinvader3[i].getAncho1() - arrayinvader3[i].getAnchoimagen() / 2 && bala.getX() < arrayinvader3[i].getAncho1() + arrayinvader3[i].getAnchoimagen() / 2){
                marcador=marcador+2;
                numeroChoco3=i;
                choco3=true;
                mediaPlayer2.start();
                bala.setX(nave.getX());
                bala.setY(alto1-20);
                bandera=false;
            }
        }
        return choco3;
    }
    public boolean ChocarInvader4() {
        for (int i = 0; i < arrayinvader4.length; i++) {
            if (bala.getY() < arrayinvader4[i].getLargo1()&&bala.getX() > arrayinvader4[i].getAncho1() - arrayinvader4[i].getAnchoimagen() / 2 && bala.getX() < arrayinvader4[i].getAncho1() + arrayinvader4[i].getAnchoimagen() / 2){
                marcador=marcador+1;
                numeroChoco4=i;
                choco4=true;
                mediaPlayer2.start();
                bala.setX(nave.getX());
                bala.setY(alto1-20);
                bandera=false;
            }
        }
        return choco4;
    }
    public boolean ChocarInvader5(){

        for (int i = 0; i < arrayinvader5.length; i++) {
            if (bala.getY() < arrayinvader5[i].getLargo1()&&bala.getX() > arrayinvader5[i].getAncho1() - arrayinvader5[i].getAnchoimagen() / 2 && bala.getX() < arrayinvader5[i].getAncho1() + arrayinvader5[i].getAnchoimagen() / 2){
                marcador=marcador+1;
                numeroChoco5=i;
                choco5=true;
                mediaPlayer2.start();
                bala.setX(nave.getX());
                bala.setY(alto1-20);
                bandera=false;
            }
        }
        return choco5;
}
    public void ChocarNave(){

        if(balaInvader.getY()+balaInvader.getAlto()/2>=nave.getY()-nave.getAlto()/2 ) {
            if (balaInvader.getX() + balaInvader.getAncho() / 2 >= nave.getX() - nave.getAncho() / 2 && balaInvader.getX() - balaInvader.getAncho() / 2 <= nave.getX() + nave.getAncho() / 2) {
                mediaPlayer4.start();
                vidasNave = vidasNave-1;

                valor = (int) (Math.random() * 8) + 0;
                balaInvader.setX(arrayinvader1[valor].getAncho1());
                balaInvader.setY(arrayinvader1[valor].getLargo1());
            }
        }
        if(balaInvader1.getY()+balaInvader1.getAlto()/2>=nave.getY()-nave.getAlto()/2 ) {
            if (balaInvader1.getX() + balaInvader1.getAncho() / 2 >= nave.getX() - nave.getAncho() / 2 && balaInvader1.getX() - balaInvader1.getAncho() / 2 <= nave.getX() + nave.getAncho() / 2) {
                mediaPlayer4.start();
                vidasNave = vidasNave-1;

                valor = (int) (Math.random() * 8) + 0;
                balaInvader1.setX(arrayinvader1[valor].getAncho1());
                balaInvader1.setY(arrayinvader1[valor].getLargo1());
            }
        }
        if(balaInvader2.getY()+balaInvader2.getAlto()/2>=nave.getY()-nave.getAlto()/2 ) {
            if (balaInvader2.getX() + balaInvader2.getAncho() / 2 >= nave.getX() - nave.getAncho() / 2 && balaInvader2.getX() - balaInvader2.getAncho() / 2 <= nave.getX() + nave.getAncho() / 2) {
                mediaPlayer4.start();

                vidasNave = vidasNave-1;
                valor = (int) (Math.random() * 8) + 0;
                balaInvader2.setX(arrayinvader1[valor].getAncho1());
                balaInvader2.setY(arrayinvader1[valor].getLargo1());
            }
        }
    }
    public void MoverseIzquierda(){

        if(invaderValidarInicio.getY()==186||invaderValidarInicio.getY()==206||invaderValidarInicio.getY()==226||invaderValidarInicio.getY()==246||invaderValidarInicio.getY()==266||invaderValidarInicio.getY()==286||invaderValidarInicio.getY()==306||invaderValidarInicio.getY()==326||invaderValidarInicio.getY()==346||invaderValidarInicio.getY()==366||invaderValidarInicio.getY()==386||invaderValidarInicio.getY()==406) {
            for (int i = 0; i < arrayinvader1.length; i++) {
                if (invaderValidarInicio.getX() >= 30) {

                    arrayinvader1[i].setAncho1(arrayinvader1[i].getAncho1() - 10);
                    arrayinvader2[i].setAncho1(arrayinvader2[i].getAncho1() - 10);
                    arrayinvader3[i].setAncho1(arrayinvader3[i].getAncho1() - 10);
                    arrayinvader4[i].setAncho1(arrayinvader4[i].getAncho1() - 10);
                    arrayinvader5[i].setAncho1(arrayinvader5[i].getAncho1() - 10);
                }
            }
            invaderValidarInicio.setX(invaderValidarInicio.getX() - 10);
            invaderValidarFinal.setX(invaderValidarFinal.getX() - 10);
        }
    }
    public void Bajar(){

        if(invaderValidarInicio.getY()+200<=alto1-150) {
            if (invaderValidarFinal.getX() >= ancho1 - 30 || invaderValidarInicio.getX() <= 30) {
                invaderValidarInicio.setY(invaderValidarInicio.getY() + 10);
                invaderValidarFinal.setY(invaderValidarFinal.getY() + 10);
                for (int i = 0; i < arrayinvader1.length; i++) {
                    arrayinvader1[i].setLargo1(arrayinvader1[i].getLargo1() + 10);
                    arrayinvader2[i].setLargo1(arrayinvader2[i].getLargo1() + 10);
                    arrayinvader3[i].setLargo1(arrayinvader3[i].getLargo1() + 10);
                    arrayinvader4[i].setLargo1(arrayinvader4[i].getLargo1() + 10);
                    arrayinvader5[i].setLargo1(arrayinvader5[i].getLargo1() + 10);
                }

            }
        }

    }
    public void MoverseDerecha(){

        if(invaderValidarInicio.getY()==176||invaderValidarInicio.getY()==196||invaderValidarInicio.getY()==216||invaderValidarInicio.getY()==236||invaderValidarInicio.getY()==256||invaderValidarInicio.getY()==276||invaderValidarInicio.getY()==296||invaderValidarInicio.getY()==316||invaderValidarInicio.getY()==336||invaderValidarInicio.getY()==356||invaderValidarInicio.getY()==376||invaderValidarInicio.getY()==396) {
            for (int i = 0; i < arrayinvader1.length; i++) {
                if (invaderValidarFinal.getX() < ancho1 - 30) {

                    arrayinvader1[i].setAncho1(arrayinvader1[i].getAncho1() + 10);
                    arrayinvader2[i].setAncho1(arrayinvader2[i].getAncho1() + 10);
                    arrayinvader3[i].setAncho1(arrayinvader3[i].getAncho1() + 10);
                    arrayinvader4[i].setAncho1(arrayinvader4[i].getAncho1() + 10);
                    arrayinvader5[i].setAncho1(arrayinvader5[i].getAncho1() + 10);
                }
            }
            invaderValidarInicio.setX(invaderValidarInicio.getX() + 10);
            invaderValidarFinal.setX((invaderValidarFinal.getX()+10));
        }
    }
    public void DispararInvader(){
        valor = (int) (Math.random() *8) + 0;

        balaInvader.setY(balaInvader.getY() + 45);
        balaInvader1.setY(balaInvader1.getY() + 50);
        balaInvader2.setY(balaInvader2.getY() + 55);
        if(balaInvader.getY()>alto1){
            balaInvader.setX(arrayinvader1[valor].getAncho1());
            balaInvader.setY(arrayinvader1[valor].getLargo1());
        }
        valor = (int) (Math.random() *8) + 0;
        if(balaInvader1.getY()>alto1){
            balaInvader1.setX(arrayinvader1[valor].getAncho1());
            balaInvader1.setY(arrayinvader1[valor].getLargo1());
        }
        valor = (int) (Math.random() *8) + 0;
        if(balaInvader2.getY()>alto1){
            balaInvader2.setX(arrayinvader1[valor].getAncho1());
            balaInvader2.setY(arrayinvader1[valor].getLargo1());
        }
    }

    TimerTask timerTask1 = new TimerTask() {
        public void run() {
            ChocarInvader5();
            ChocarInvader4();
            ChocarInvader3();
            ChocarInvader2();
            ChocarInvader1();
            ChocarBaseInvader();
            MoverseIzquierda();
            MoverseDerecha();
            Bajar();
            DispararInvader();
        }
    };

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder){
        hilo.setRunning(true);
        hilo.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    protected void checkMovement(float x2, float y2){
        int xRight = ancho1 - bmpUp.getWidth();
        int xLeft1 = bmpLeft.getWidth()+50;
        int xLeft = 0 ;
        int y = (alto1/2)-(bmpUp.getHeight()/2);
        int y1 = (alto1/2)-(bmpRight.getHeight()/2);

        int width = bmpUp.getWidth();
        int height = bmpUp.getHeight();
        int width1 = bmpRight.getWidth();
        int height1 = bmpRight.getHeight();

        if(x2>ancho1/2-salir.getAncho()/2 && x2<ancho1/2+salir.getAncho()/2 && y2>100-salir.getAlto()/2 && y2<100+salir.getAlto()/2){
            exit=true;
            disparar=false;
            izquierda=false;
            derecha=false;
        }else
        if(x2>xRight && x2<xRight + width && y2>y && y2<y+height){
            exit=false;
            disparar=true;
            izquierda=false;
            derecha=false;
        }else
        if(x2>xLeft && x2<xLeft+ width1 && y2>y1 && y2<y1+height1){
            exit=false;
            izquierda=true;
            derecha=false;
            disparar=false;
        }else{
            if(x2>xLeft1 && x2<xLeft1+ width1 && y2>y1 && y2<y1+height1){
                exit=false;
                izquierda=false;
                derecha=true;
                disparar=false;
            } else{
                exit=false;
                izquierda=false;
                derecha=false;
                disparar=false;
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event){

        checkMovement(event.getX(), event.getY());
        if(exit){
            hilo.setRunning(false);
            Intent intent = new Intent(context1, Inicio.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context1.startActivity(intent);
            ((Activity)(context1)).finish();
        }
        if(disparar) {
            bandera=true;
            mediaPlayer1.start();
        }
        if(izquierda) {
            if (nave.getX() > 30) {
                nave.setX(nave.getX() - 10);
                bala.setX(nave.getX()-5);
            }
        }
        if (derecha) {
            if (nave.getX() < ancho1 - 30) {
                nave.setX(nave.getX() + 10);
                bala.setX(nave.getX()+5);
            }
        }
        return true;
    }

    public void esperar (int segundos) {
        try {
            Thread.sleep (segundos*100);
        } catch (Exception e) {
      }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder){
        boolean retry=true;
        while(retry){
            try{
                hilo.join();
                retry=false;
            } catch (InterruptedException e){

            }
        }
    }

    public void render(Canvas canvas){

        if(canvas != null) {
            int x = ancho1 - bmpUp.getWidth();
            int y = (alto1 / 2) - (bmpUp.getHeight() / 2);
            ChocarNave();
            canvas.drawBitmap(background, 0, 0, null);
            Paint texto = new Paint();
            texto.setARGB(255, 255, 255, 255);
            texto.setTextSize(40);
            texto.setTypeface(Typeface.SERIF);
            canvas.drawText(marcador + "", 260, 114, texto);
            canvas.drawBitmap(bmpUp, x, y, null);
            canvas.drawBitmap(bmpRight, bmpLeft.getHeight() + 50, y, null);
            canvas.drawBitmap(bmpLeft, 0, y, null);
            canvas.drawBitmap(puntos, 100, 70, null);
            canvas.drawBitmap(vida, ancho1 - 350, 70, null);
            for (int i = 0; i <= vidasNave; i++) {
                arrayvida[i].draw(canvas);
            }

            if(bala.getY()<100 ) {
                bala.setY(alto1 - 30);
                bandera=false;
            }
            ChocarBaseNave();

            if(bandera==true){
                bala.setY(bala.getY()- 10);
            }
            bala.draw(canvas);

            for (int i = 0; i < arraybase.length; i++) {
                arraybase[i].draw(canvas);
            }
            if(contador==5||contador1==5||contador2==5){
                arraybase[numeroChocoBase]=new Base( arraybase[numeroChocoBase].getX(), arraybase[numeroChocoBase].getY(),BitmapFactory.decodeResource(getResources(), R.drawable.base));
                arraybase[numeroChocoBase].draw(canvas);
            }
            if(contador==10||contador1==10||contador2==10){
                arraybase[numeroChocoBase].setX(ancho1+500);
                arraybase[numeroChocoBase].setY(alto1 + 100);
            }
            balaInvader.draw(canvas);
            balaInvader1.draw(canvas);
            balaInvader2.draw(canvas);
            nave.draw(canvas);
            salir.draw(canvas);
            invaderValidarInicio.draw(canvas);
            invaderValidarFinal.draw(canvas);
            dibujarExplocion(canvas);
            escribir();
            winover(canvas);
        }
    }
}
