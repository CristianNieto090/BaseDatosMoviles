package dm2e.cristian.basedatoscristiannieto;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;


public class Seleccion_mostrar extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seleccion_mostrar);
        textView = findViewById(R.id.textView);
    }

    public void onAtrasMostrar(View view) {
        finish();
    }

    public void onCentroMostrar(View view) {
        BaseDatosSQLiteOpenHelper db = new BaseDatosSQLiteOpenHelper(this, null);
        String datos = db.mostrarCentro();
        textView.setText(datos);
    }

    public void onUbicacionMostrar(View view) {
        BaseDatosSQLiteOpenHelper db = new BaseDatosSQLiteOpenHelper(this,null);
        String datos = db.mostrarUbicacion();
        textView.setText(datos);

    }

    public void onProductoMostrar(View view) {
        BaseDatosSQLiteOpenHelper db = new BaseDatosSQLiteOpenHelper(this,null);
        String datos = db.mostrarProducto();
        textView.setText(datos);
    }

    public void onOfertaMostrar(View view) {
       BaseDatosSQLiteOpenHelper db = new BaseDatosSQLiteOpenHelper(this,null);
       String datos = db.mostrarOferta();
       textView.setText(datos);
    }

    public void onCentroUbicacionesMostrar(View view) {
        BaseDatosSQLiteOpenHelper db = new BaseDatosSQLiteOpenHelper(this,null);
        String datos = db.mostrarCentroUbicacionJoin();
        textView.setText(datos);
    }
}
