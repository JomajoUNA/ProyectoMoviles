package dispmovil.siua.una.ac.cr.proyectomoviles;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

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
public class Principal extends Activity{
    Paneljuego paneljuego;
    int alto=0;
    int ancho=0;
    String usuarioArchivo="";
    Point size = new Point();
    MediaPlayer mediaPlayer;
    MediaPlayer mediaPlayer1;
    MediaPlayer mediaPlayer2;
    MediaPlayer mediaPlayer3;
    MediaPlayer win;
    MediaPlayer gameover;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_principal);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Display display = getWindowManager().getDefaultDisplay();
        display.getSize(size);
        ancho = size.x;
        alto = size.y;
        mediaPlayer1= MediaPlayer.create(this,R.raw.shoot);
        mediaPlayer2= MediaPlayer.create(this,R.raw.invaderkilled);
        mediaPlayer3= MediaPlayer.create(this,R.raw.explosion);
        win= MediaPlayer.create(this,R.raw.win);
        gameover= MediaPlayer.create(this,R.raw.gameover);
        usuarioArchivo=getIntent().getStringExtra("nombreusuario");
        Bitmap bmp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.fondo),ancho,alto,true);
        paneljuego = new Paneljuego(this,ancho,alto,bmp,mediaPlayer1,mediaPlayer2,mediaPlayer3,win,gameover,usuarioArchivo);
        setContentView(paneljuego);
        mediaPlayer= MediaPlayer.create(this,R.raw.fastinvader1);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }
    @Override
    protected void onPause() {
        super.onPause();

        mediaPlayer.stop();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
