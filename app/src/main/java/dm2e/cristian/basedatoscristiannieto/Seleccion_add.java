package dm2e.cristian.basedatoscristiannieto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Seleccion_add extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seleccion_add);
    }
    public void onAtrasAdd(View view) {
        finish();
    }
    public void onCentroAdd(View view) {
        Intent i = new Intent(this, Add_centro.class);
        startActivity(i);
    }
    public void onProductoAdd(View view) {
        Intent i = new Intent(this, Add_producto.class);
        startActivity(i);
    }
    public void onUbicacionAdd(View view) {
        Intent i = new Intent(this, Add_ubicacion.class);
        startActivity(i);
    }
}
