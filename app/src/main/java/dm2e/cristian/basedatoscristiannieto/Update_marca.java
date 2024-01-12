package dm2e.cristian.basedatoscristiannieto;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

public class Update_marca extends AppCompatActivity {
    private EditText productIdEditText;
    private EditText nuevoMarcaEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_marca);

        productIdEditText = findViewById(R.id.idUpdateMarca);
        nuevoMarcaEditText = findViewById(R.id.UpdateMarca);
    }

    public void onMarcaUpdate(View view) {
        String productIdStr = productIdEditText.getText().toString();
        String nuevoMarca = nuevoMarcaEditText.getText().toString();

        if (productIdStr.isEmpty() || nuevoMarca.isEmpty()) {
            Toast.makeText(this, "Ingrese la ID del producto y la nueva marca", Toast.LENGTH_SHORT).show();
            return;
        }

        int productId = Integer.parseInt(productIdStr);

        BaseDatosSQLiteOpenHelper dbHelper = new BaseDatosSQLiteOpenHelper(this,null);
        boolean isUpdated = dbHelper.updateProductoMarca(productId, nuevoMarca);

        if (isUpdated) {
            Toast.makeText(this, "Marca del producto actualizada correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al actualizar la marca del producto", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    public void onAtrasMarca(View view) {
        finish();
    }
}
