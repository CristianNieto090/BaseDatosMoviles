package dm2e.cristian.basedatoscristiannieto;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Update_desc_oferta extends AppCompatActivity {
    private EditText ofertaIdEditText;
    private EditText nuevaDescripcionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_desc_oferta);

        ofertaIdEditText = findViewById(R.id.idUpdateDescripcion);
        nuevaDescripcionEditText = findViewById(R.id.UpdateDescripcion);
    }

    public void onDescripcionUpdate(View view) {
        String ofertaIdStr = ofertaIdEditText.getText().toString();
        String nuevaDescripcion = nuevaDescripcionEditText.getText().toString();

        if (ofertaIdStr.isEmpty()) {
            Toast.makeText(this, "Introduce un id de oferta válido", Toast.LENGTH_SHORT).show();
            return;
        }
        if (nuevaDescripcion.isEmpty()) {
            Toast.makeText(this, "La nueva descripción de la oferta no puede estar vacía", Toast.LENGTH_SHORT).show();
            return;
        }

        int ofertaId = Integer.parseInt(ofertaIdStr);

        BaseDatosSQLiteOpenHelper dbHelper = new BaseDatosSQLiteOpenHelper(this,null);
        boolean isUpdated = dbHelper.updateOfertaDescripcion(ofertaId, nuevaDescripcion);

        if (isUpdated) {
            Toast.makeText(this, "Descripción de la oferta actualizada correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al actualizar la descripción de la oferta", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    public void onAtrasDescripcion(View view) {
        finish();
    }
}
