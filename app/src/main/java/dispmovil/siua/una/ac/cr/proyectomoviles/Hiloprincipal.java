package dispmovil.siua.una.ac.cr.proyectomoviles;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

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
public class Hiloprincipal extends Thread{

    private SurfaceHolder surfaceHolder;
    private Paneljuego paneljuego;

    private boolean running;

    public Hiloprincipal(SurfaceHolder surfaceHolder, Paneljuego paneljuego){
        super();
        this.surfaceHolder=surfaceHolder;
        this.paneljuego=paneljuego;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }


    public void run(){
        Canvas canvas;


        while(running){
            canvas=null;

            try{
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){


                    this.paneljuego.render(canvas);


                }
            }finally {
                if(canvas != null){
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }

        }

    }


}
