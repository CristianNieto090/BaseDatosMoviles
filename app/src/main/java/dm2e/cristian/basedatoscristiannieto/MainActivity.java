package dm2e.cristian.basedatoscristiannieto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String PREFERENCIAS_NOINSERTAR = "FicheroPrefs";
    private static final String DATOS_INSERTADOS = "DataInserted";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        SharedPreferences prefs = getSharedPreferences(PREFERENCIAS_NOINSERTAR, MODE_PRIVATE);
        boolean datosInsertados = prefs.getBoolean(DATOS_INSERTADOS, false);

        if (!datosInsertados) {
            BaseDatosSQLiteOpenHelper baseDatos = new BaseDatosSQLiteOpenHelper(this, null);
            SQLiteDatabase db = baseDatos.getWritableDatabase();

            if (db != null) {
                db.execSQL("INSERT INTO producto (nombre, precio, marca) " +
                        "VALUES('Cerveza', 3, 'Mahou' )");
                db.execSQL("INSERT INTO ubicacion (calle, ciudad, codigoPostal) " +
                        "VALUES('Padre Claret, 1', 'Madrid', 28002 )");
                db.execSQL("INSERT INTO oferta (nombre, descripcion) " +
                        "VALUES('CervecitaRica', '3x2 en cervezas Mahou' )");
                db.execSQL("INSERT INTO centro (nombre, ubicacion, max_aforo) " +
                        "VALUES('Entrecañas', 'Padre Claret, 1', 500 )");

                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean(DATOS_INSERTADOS, true);
                editor.apply();
            }

            db.close();
        }
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