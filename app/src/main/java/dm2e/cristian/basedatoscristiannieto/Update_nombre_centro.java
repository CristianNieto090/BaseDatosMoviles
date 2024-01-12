package dm2e.cristian.basedatoscristiannieto;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;
public class Update_nombre_centro extends AppCompatActivity {
    private EditText centroIdEditText;
    private EditText nuevoNombreEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_nombre_centro);

        centroIdEditText = findViewById(R.id.idUpdateNombre);
        nuevoNombreEditText = findViewById(R.id.UpdateNombre);
    }

    public void onNombreUpdate(View view) {
        String centroIdStr = centroIdEditText.getText().toString();
        String nuevoNombre = nuevoNombreEditText.getText().toString();

        if (centroIdStr.isEmpty()) {
            Toast.makeText(this, "El id de centro no puede estar vacio", Toast.LENGTH_SHORT).show();
            return;
        }

        if (nuevoNombre.isEmpty()) {
            Toast.makeText(this, "El nuevo nombre del centro no puede estar vacio", Toast.LENGTH_SHORT).show();
            return;
        }

        int centroId = Integer.parseInt(centroIdStr);

        BaseDatosSQLiteOpenHelper dbHelper = new BaseDatosSQLiteOpenHelper(this,null);
        boolean isUpdated = dbHelper.updateCentroNombre(centroId, nuevoNombre);

        if (isUpdated) {
            Toast.makeText(this, "Nombre del centro actualizado correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al actualizar el nombre del centro", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    public void onAtrasNombre(View view) {
        finish();
    }
}


