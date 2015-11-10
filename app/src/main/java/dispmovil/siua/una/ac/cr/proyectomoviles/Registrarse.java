package dispmovil.siua.una.ac.cr.proyectomoviles;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

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
public class Registrarse extends Activity {
    private EditText et1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        et1 = (EditText) findViewById(R.id.nombreR);


    }
    public boolean ValidarUsuario(String nombreUsuario){
        boolean estado=false;
        File myFile = new File("/sdcard/"+nombreUsuario+".txt");
        if (myFile.exists()) {
            estado = true;
        }
        return estado;
    }
    public void registrarse(View v) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Esta seguro que desea crear el usuario?");
        alertDialog.setTitle("Crear usuario");
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("Sí", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                try {
                    String usuario=et1.getText().toString();
                    if(usuario.equals("")){
                        Toast.makeText(getBaseContext(), "Debe indicar el nombre del usuario", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Registrarse.this, Inicio.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }else {
                        boolean sdDisponible = false;
                        String estado = Environment.getExternalStorageState();
                        if (estado.equals(Environment.MEDIA_MOUNTED)) {
                            sdDisponible = true;
                        }
                        if (ValidarUsuario(usuario) == false && sdDisponible == true) {
                            File myFile = new File("/sdcard/" + usuario + ".txt");
                            String puntaje = "0";
                            String vida = "2";
                            String separator = System.getProperty("line.separator");
                            FileOutputStream fOut = new FileOutputStream(myFile);
                            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);

                            myOutWriter.append(usuario + "," + puntaje + "," + vida);
                            myOutWriter.append(separator);
                            myOutWriter.close();
                            fOut.close();
                            Toast.makeText(getBaseContext(), "Guardando", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getBaseContext(), "No se puede registrar el usuario", Toast.LENGTH_SHORT).show();
                        }

                        Intent intent = new Intent(Registrarse.this, Inicio.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                Intent intent = new Intent(Registrarse.this, Inicio.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registrarse, menu);
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
