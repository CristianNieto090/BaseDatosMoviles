package dm2e.cristian.basedatoscristiannieto;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

public class Update_precio extends AppCompatActivity {
    private EditText productIdEditText;
    private EditText nuevoPrecioEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_precio);

        productIdEditText = findViewById(R.id.idUpdatePrecio);
        nuevoPrecioEditText = findViewById(R.id.UpdatePrecio);
    }

    public void onPrecioUpdate(View view) {

        String productIdStr = productIdEditText.getText().toString();
        String nuevoPrecioStr = nuevoPrecioEditText.getText().toString();

        if (productIdStr.isEmpty()) {
            Toast.makeText(this, "El id de producto no puede estar vacío", Toast.LENGTH_SHORT).show();
            return;
        }
        if (nuevoPrecioStr.isEmpty()) {
            Toast.makeText(this, "El nuevo precio no puede estar vacío", Toast.LENGTH_SHORT).show();
            return;
        }

        int productId = Integer.parseInt(productIdStr);
        double nuevoPrecio = Double.parseDouble(nuevoPrecioStr);
        if(nuevoPrecio <= 0){
            Toast.makeText(this, "El nuevo precio no puede ser menor o igual que 0", Toast.LENGTH_SHORT).show();
            return;
        }

        BaseDatosSQLiteOpenHelper dbHelper = new BaseDatosSQLiteOpenHelper(this,null);
        boolean isUpdated = dbHelper.updateProductPrecio(productId, nuevoPrecio);

        if (isUpdated) {
            Toast.makeText(this, "Precio del producto actualizado correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al actualizar el precio del producto", Toast.LENGTH_SHORT).show();
        }

        finish();
    }

    public void onAtrasPrecio(View view) {
        finish();
    }
}

