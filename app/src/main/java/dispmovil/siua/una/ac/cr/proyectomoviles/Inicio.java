package dispmovil.siua.una.ac.cr.proyectomoviles;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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

public class Inicio extends Activity {
    static final int READ_BLOCK_SIZE = 100;
    private EditText te;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        te = (EditText) findViewById(R.id.nombreI);
    }

    public boolean ValidarUsuario(String nombreUsuario){
        boolean estado=false;
        File myFile = new File("/sdcard/"+nombreUsuario+".txt");
        if (myFile.exists()) {
            estado = true;
        }
        return estado;
    }

    public void iniciar(View v) {

        String usuario=te.getText().toString();
        boolean sdDisponible = false;
        String estado = Environment.getExternalStorageState();
        if (estado.equals(Environment.MEDIA_MOUNTED))
        {
            sdDisponible = true;
        }
       if(ValidarUsuario(usuario)==true && sdDisponible==true){
           Intent intent = new Intent(Inicio.this, Principal.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
           intent.putExtra("nombreusuario", usuario);
           startActivity(intent);
           finish();
       }else{
           Toast.makeText(getBaseContext(), "No se puede iniciar sesión, el usuario no existe", Toast.LENGTH_SHORT).show();
       }
    }
    public void registrarse(View view) {
        Intent intent = new Intent(Inicio.this, Registrarse.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void eliminar(View view) {
        Intent intent = new Intent(Inicio.this, Eliminar.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inicio, menu);
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
