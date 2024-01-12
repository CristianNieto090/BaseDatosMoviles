package dm2e.cristian.basedatoscristiannieto;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Add_ubicacion extends AppCompatActivity {
    private EditText calleEditText;
    private EditText ciudadEditText;
    private EditText cpEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_ubicacion);

        calleEditText = findViewById(R.id.AddUbicacionCalle);
        ciudadEditText = findViewById(R.id.AddUbicacionCiudad);
        cpEditText = findViewById(R.id.AddUbicacionCP);
    }

    public void onUbicacionA(View view) {
        String calle = calleEditText.getText().toString();
        String ciudad = ciudadEditText.getText().toString();
        int cp = Integer.parseInt(cpEditText.getText().toString());

        if (calle.isEmpty() || ciudad.isEmpty()) {
            Toast.makeText(this, "Calle y ciudad son campos obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }
        if(cp <= 9999 || cp >= 99999){
            Toast.makeText(this, "El código postal debe tener 5 números y ser positivo", Toast.LENGTH_SHORT).show();
        }

        BaseDatosSQLiteOpenHelper dbHelper = new BaseDatosSQLiteOpenHelper(this, null);
        dbHelper.insertUbicacion(calle, ciudad, cp);

        Toast.makeText(this, "Ubicacion añadida correctamente", Toast.LENGTH_SHORT).show();

        finish();
    }

    public void onAtrasAddUbicacion(View view) {
        finish();
    }
}
