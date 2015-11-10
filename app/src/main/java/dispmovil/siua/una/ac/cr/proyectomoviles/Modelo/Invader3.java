package dispmovil.siua.una.ac.cr.proyectomoviles.Modelo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.view.View;

import java.io.InputStream;

import dispmovil.siua.una.ac.cr.proyectomoviles.R;

/**
 * Created by Josue-Pc on 04/06/2015.
 */
public class Invader3 extends View {

    private InputStream gifInputStream;
    private InputStream gifInputStream1;
    private Movie gifMovie;
    private int movieWidth, movieHeight;
    private long movieDuration;
    private long mMovieStart;



    int anchoimagen;
    int altoimagen;

    public int getAncho1() {
        return ancho1;
    }

    public int getLargo1() {
        return largo1;
    }

    int ancho1;

    public void setLargo1(int largo1) {
        this.largo1 = largo1;
    }

    int largo1;

    public Invader3(Context context,int ancho,int largo,boolean estado) {

        super(context);
        ancho1=ancho;
        largo1=largo;
        if(estado==true) {
            init(context);
        }else{
            init1(context);
        }
    }


    public void setAncho1(int ancho1) {
        this.ancho1 = ancho1;
    }

    private void init(Context context)
    {
        setFocusable(true);
        gifInputStream = context.getResources().openRawResource(R.drawable.invader3);

        gifMovie = Movie.decodeStream(gifInputStream);
        movieWidth = 800;
        movieHeight = 740;
        movieDuration = gifMovie.duration();
    }
    private void init1(Context context)
    {
        setFocusable(true);
        gifInputStream = context.getResources().openRawResource(R.drawable.invaderkilled);

        gifMovie = Movie.decodeStream(gifInputStream);
        movieWidth = 800;
        movieHeight = 740;
        movieDuration = gifMovie.duration();
    }
    public int getAnchoimagen() {
        return gifMovie.width();
    }

    public int getAltoimagen() {return gifMovie.height();}

    @Override
    protected void onMeasure(int widthMeasureSpec,
                             int heightMeasureSpec) {
        setMeasuredDimension(movieWidth, movieHeight);
    }

    public int getMovieWidth(){
        return movieWidth;
    }

    public int getMovieHeight(){
        return movieHeight;
    }

    public long getMovieDuration(){
        return movieDuration;
    }

    @Override
    protected void onDraw(Canvas canvas) {


        long now = android.os.SystemClock.uptimeMillis();
        if (mMovieStart == 0) {   // first time
            mMovieStart = now;
        }

        if (gifMovie != null) {

            int dur = gifMovie.duration();
            if (dur == 0) {
                dur = 1000;
            }

            int relTime = (int)((now - mMovieStart) % dur);

            gifMovie.setTime(relTime);

            gifMovie.draw(canvas,ancho1 - ((int)(gifMovie.width()/2)),  largo1 - ((int)(gifMovie.height()/2)),null);
            //gifMovie.draw(canvas, ancho1, largo1);
            invalidate();

        }

    }
}



