package dm2e.cristian.basedatoscristiannieto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Seleccion_modificar extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seleccion_modificar);
    }
    public void onAtrasModificar(View view) {
        finish();
    }
    public void onModificarPrecio(View view) {
        Intent i = new Intent(this, Update_precio.class);
        startActivity(i);
    }

    public void onModificarMarca(View view) {
        Intent i = new Intent(this, Update_marca.class);
        startActivity(i);
    }
    public void onModificarNombre(View view) {
        Intent i = new Intent(this, Update_nombre_centro.class);
        startActivity(i);
    }
    public void onModificarDescripcion(View view) {
        Intent i = new Intent(this, Update_desc_oferta.class);
        startActivity(i);
    }
}
