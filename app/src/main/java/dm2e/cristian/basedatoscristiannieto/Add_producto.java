package dm2e.cristian.basedatoscristiannieto;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Add_producto extends AppCompatActivity {
    private EditText nombreEditText;
    private EditText precioEditText;
    private EditText marcaEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_producto);

        // Initialize EditText fields
        nombreEditText = findViewById(R.id.AddProductoNombre);
        precioEditText = findViewById(R.id.AddProductoPrecio);
        marcaEditText = findViewById(R.id.AddProductoMarca);
    }

    public void onCentroAdd(View view) {
        String nombre = nombreEditText.getText().toString();
        double precio = Double.parseDouble(precioEditText.getText().toString());
        String marca = marcaEditText.getText().toString();

        if (nombre.isEmpty() || marca.isEmpty()) {
            Toast.makeText(this, "Nombre y marca son campos obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        if (precio <= 0){
            Toast.makeText(this, "El precio no puede ser menor o igual que 0", Toast.LENGTH_SHORT).show();
            return;
        }

        BaseDatosSQLiteOpenHelper dbHelper = new BaseDatosSQLiteOpenHelper(this,null);
        dbHelper.insertProducto(nombre, precio, marca);

        Toast.makeText(this, "Producto añadido correctamente", Toast.LENGTH_SHORT).show();

        finish();
    }

    public void onAtrasAddProducto(View view) {
        finish();
    }
}