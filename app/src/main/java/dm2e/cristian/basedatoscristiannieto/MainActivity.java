package dm2e.cristian.basedatoscristiannieto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Abrimos la base de datos en modo escritura
        BaseDatosSQLiteOpenHelper baseDatos = new BaseDatosSQLiteOpenHelper(this,  null);
     //   SQLiteDatabase db = baseDatos.getWritableDatabase();
        //Si hemos abierto correctamente la base de datos
       /* if(db != null) {
            //Inserto 1 ejemplo por tabla
            db.execSQL("INSERT INTO producto (nombre, precio, marca) " +
                    "VALUES('Cerveza', 3, 'Mahou' )");
            db.execSQL("INSERT INTO ubicacion (calle, ciudad, codigoPostal) " +
                    "VALUES('Padre Claret, 1', 'Madrid', 28002 )");
            db.execSQL("INSERT INTO oferta (nombre, descripcion) " +
                    "VALUES('CervecitaRica', '3x2 en cervezas Mahou' )");
            db.execSQL("INSERT INTO centro (nombre, ubicacion, max_aforo) " +
                    "VALUES('Entrecañas', 'Padre Claret, 1', 500 )");
        }
        db.close();*/
    }

    public void onBorrar(View view) {
        Intent i = new Intent(this, Seleccion_borrar.class);
        startActivity(i);
    }


    public void onAñadir(View view) {
        Intent i = new Intent(this, Seleccion_add.class);
        startActivity(i);
    }

    public void onMostrar(View view) {
        Intent i = new Intent(this, Seleccion_mostrar.class);
        startActivity(i);
    }
    public void onModificar(View view) {
        Intent i = new Intent(this, Seleccion_modificar.class);
        startActivity(i);
    }
}