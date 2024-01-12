package dm2e.cristian.basedatoscristiannieto;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Delete_producto extends AppCompatActivity {
    private EditText productoIdEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_producto);

        productoIdEditText = findViewById(R.id.DeleteProducto);
    }

    public void onProductoD(View view) {
        String productoIdStr = productoIdEditText.getText().toString();

        if (productoIdStr.isEmpty()) {
            Toast.makeText(this, "El id del producto no puede estar vacío", Toast.LENGTH_SHORT).show();
            return;
        }

        int productoId = Integer.parseInt(productoIdStr);

        BaseDatosSQLiteOpenHelper dbHelper = new BaseDatosSQLiteOpenHelper(this,null);
        boolean isDeleted = dbHelper.deleteProducto(productoId);

        if (isDeleted) {
            Toast.makeText(this, "Producto borrado correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al borrar el producto", Toast.LENGTH_SHORT).show();
        }

        finish();
    }

    public void onAtrasDeleteProducto(View view) {
        finish();
    }
}
