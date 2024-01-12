package dm2e.cristian.basedatoscristiannieto;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Add_oferta extends AppCompatActivity {
    private EditText nombreEditText;
    private EditText descripcionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_oferta);

        nombreEditText = findViewById(R.id.AddOfertaNombre);
        descripcionEditText = findViewById(R.id.AddOfertaDescripcion);
    }

    public void onOfertaA(View view) {
        String nombre = nombreEditText.getText().toString();
        String descripcion = descripcionEditText.getText().toString();

        if (nombre.isEmpty()||descripcion.isEmpty()) {
            Toast.makeText(this, "El nombre y la descripción de la oferta son obligatorio", Toast.LENGTH_SHORT).show();
            return;
        }

        BaseDatosSQLiteOpenHelper dbHelper = new BaseDatosSQLiteOpenHelper(this,null);
        dbHelper.insertOferta(nombre, descripcion);

        Toast.makeText(this, "Oferta añadida correctamente", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void onAtrasAddOferta(View view) {
        finish();
    }
}