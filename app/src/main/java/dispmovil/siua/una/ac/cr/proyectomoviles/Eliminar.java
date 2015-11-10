package dispmovil.siua.una.ac.cr.proyectomoviles;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

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

public class Eliminar extends Activity {
    private EditText elimina;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);
        elimina = (EditText) findViewById(R.id.nombreE);
    }
     public void eliminar(View v){
         AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
         alertDialog.setMessage("Esta seguro que desea eliminar el usuario?");
         alertDialog.setTitle("Eliminar usuario");
         alertDialog.setCancelable(false);
         alertDialog.setPositiveButton("Sí", new DialogInterface.OnClickListener()
         {
             public void onClick(DialogInterface dialog, int which)
             {
         String usuario=elimina.getText().toString();
         File myFile = new File("/sdcard/"+usuario+".txt");
         Intent intent = new Intent(Eliminar.this, Inicio.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
         if (myFile.delete()) {
             Toast.makeText(getBaseContext(), "El usuario ha sido borrado satisfactoriamente", Toast.LENGTH_SHORT).show();
             startActivity(intent);
             finish();
         }else {
             Toast.makeText(getBaseContext(), "El usuario no puede ser borrado", Toast.LENGTH_SHORT).show();
             startActivity(intent);
             finish();
         }
             }
         });
         alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener()
         {
             public void onClick(DialogInterface dialog, int which)
             {
                 Intent intent = new Intent(Eliminar.this, Inicio.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                 startActivity(intent);
                 finish();
             }
         });
         alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_eliminar, menu);
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
