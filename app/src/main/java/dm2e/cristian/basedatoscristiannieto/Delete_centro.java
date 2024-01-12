package dm2e.cristian.basedatoscristiannieto;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Delete_centro extends AppCompatActivity {
    private EditText centroIdEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_centro);

        centroIdEditText = findViewById(R.id.DeleteCentro);
    }

    public void onCentroDelete(View view) {
        String centroIdStr = centroIdEditText.getText().toString();

        if (centroIdStr.isEmpty()) {
            Toast.makeText(this, "el id no puede ser nulo, introduce un id válido", Toast.LENGTH_SHORT).show();
            return;
        }

        int centroId = Integer.parseInt(centroIdStr);

        BaseDatosSQLiteOpenHelper dbHelper = new BaseDatosSQLiteOpenHelper(this,null);
        boolean isDeleted = dbHelper.deleteCentro(centroId);

        if (isDeleted) {
            Toast.makeText(this, "Centro borrado correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al borrar el centro", Toast.LENGTH_SHORT).show();
        }

        finish();
    }

    public void onAtrasDeleteCentro(View view) {
        finish();
    }
}
