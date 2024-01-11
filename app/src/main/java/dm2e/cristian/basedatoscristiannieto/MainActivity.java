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
       /* //Abrimos la base de datos 'DBUsuarios' en modo escritura
        CentroSQLiteOpenHelper usdbh = new CentroSQLiteOpenHelper(this, "DBUsuarios", null, 1);
        SQLiteDatabase db = usdbh.getWritableDatabase();
        //Si hemos abierto correctamente la base de datos
        if(db != null) {
            //Insertamos 5 usuarios de ejemplo
            for(int i=1; i<=5; i++) {
                //Generamos los datos
                int telefono = 11111111+i;
                String nombre = "Usuario" + i;
                //Insertamos los datos en la tabla Usuarios
                db.execSQL("INSERT INTO Usuarios (nombre, telefono) " +
                        "VALUES('" + nombre +"', " + telefono + " )");
            }
            //Cerramos la base de datos
            db.close();
        }*/
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