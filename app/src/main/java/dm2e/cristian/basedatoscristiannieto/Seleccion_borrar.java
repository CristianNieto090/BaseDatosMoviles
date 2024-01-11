package dm2e.cristian.basedatoscristiannieto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Seleccion_borrar extends AppCompatActivity{
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.seleccion_borrar);
        }
    public void onAtrasBorrar(View view) {
            finish();
    }
    public void onCentroBorrar(View view){
        Intent i = new Intent(this, Delete_centro.class);
        startActivity(i);
        }
    public void onOfertaBorrar(View view){
        Intent i = new Intent(this, Delete_oferta.class);
        startActivity(i);
        }
    public void onProductoBorrar(View view){
        Intent i = new Intent(this, Delete_producto.class);
        startActivity(i);
        }
    public void onUbicacionBorrar(View view){
        Intent i = new Intent(this, Delete_ubicacion.class);
        startActivity(i);
        }
    }


