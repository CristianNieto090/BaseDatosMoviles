package dm2e.cristian.basedatoscristiannieto;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Delete_oferta extends AppCompatActivity {
    private EditText ofertaIdEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_oferta);

        ofertaIdEditText = findViewById(R.id.DeleteOferta);
    }

    public void onOfertaDelete(View view) {
        String ofertaIdStr = ofertaIdEditText.getText().toString();

        if (ofertaIdStr.isEmpty()) {
            Toast.makeText(this, "El campo id oferta no puede estar vacío", Toast.LENGTH_SHORT).show();
            return;
        }

        int ofertaId = Integer.parseInt(ofertaIdStr);

        BaseDatosSQLiteOpenHelper dbHelper = new BaseDatosSQLiteOpenHelper(this,null);
        boolean isDeleted = dbHelper.deleteOferta(ofertaId);

        if (isDeleted) {
            Toast.makeText(this, "Oferta borrada correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al borrar la oferta", Toast.LENGTH_SHORT).show();
        }

        finish();
    }

    public void onAtrasDeleteOferta(View view) {
        finish();
    }
}

