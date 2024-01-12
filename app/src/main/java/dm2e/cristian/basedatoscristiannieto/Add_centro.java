package dm2e.cristian.basedatoscristiannieto;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Add_centro extends AppCompatActivity {
    private EditText nombreEditText;
    private EditText aforoEditText;
    private EditText ubicacionEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_centro);

        nombreEditText = findViewById(R.id.AddCentroNombre);
        aforoEditText = findViewById(R.id.AddCentroAforo);
        ubicacionEditText = findViewById(R.id.AddCentroUbicacion);
    }

    public void onCentroAdd(View view) {
        String nombre = nombreEditText.getText().toString();
        int aforo = Integer.parseInt(aforoEditText.getText().toString());
        String ubicacion = ubicacionEditText.getText().toString();

        BaseDatosSQLiteOpenHelper dbHelper = new BaseDatosSQLiteOpenHelper(this, null);
        dbHelper.insertCentro(nombre,aforo,ubicacion);

        Toast.makeText(this, "Centro añadido correctamente", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void onAtrasAddCentro(View view) {
        finish();
    }
}