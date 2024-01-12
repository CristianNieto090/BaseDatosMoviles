package dm2e.cristian.basedatoscristiannieto;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Delete_ubicacion extends AppCompatActivity {
    private EditText ubicacionIdEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_ubicacion);

        ubicacionIdEditText = findViewById(R.id.DeleteUbicacion);
    }

    public void onUbicacionDelete(View view) {
        String ubicacionIdStr = ubicacionIdEditText.getText().toString();

        if (ubicacionIdStr.isEmpty()) {
            Toast.makeText(this, "Ingrese la ID de la ubicacion", Toast.LENGTH_SHORT).show();
            return;
        }

        int ubicacionId = Integer.parseInt(ubicacionIdStr);

        BaseDatosSQLiteOpenHelper dbHelper = new BaseDatosSQLiteOpenHelper(this,null);
        boolean isDeleted = dbHelper.deleteUbicacion(ubicacionId);

        if (isDeleted) {
            Toast.makeText(this, "Ubicacion borrada correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al borrar la ubicacion", Toast.LENGTH_SHORT).show();
        }

        finish();
    }

    public void onAtrasDeleteUbicacion(View view) {
        finish();
    }
}
