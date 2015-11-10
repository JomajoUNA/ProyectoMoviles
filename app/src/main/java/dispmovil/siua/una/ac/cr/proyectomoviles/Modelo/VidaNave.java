package dispmovil.siua.una.ac.cr.proyectomoviles.Modelo;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Josue-Pc on 05/06/2015.
 */
public class VidaNave {
    int x;
    int y;
    Bitmap bitmap;
    int alto;
    int ancho;



    public VidaNave(int x,int y, Bitmap bitmap){
        this.x=x;
        this.y=y;
        this.bitmap=bitmap;
        this.alto=bitmap.getHeight();
        this.ancho=bitmap.getWidth();
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getAlto() {
        return alto;
    }

    public int getAncho() {
        return ancho;
    }
    public void setAlto(int alto) {
        this.alto = alto;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public void draw(Canvas canvas){


        canvas.drawBitmap(bitmap,x - (bitmap.getWidth()/2), y - (bitmap.getHeight()/2),null);
    }

}
