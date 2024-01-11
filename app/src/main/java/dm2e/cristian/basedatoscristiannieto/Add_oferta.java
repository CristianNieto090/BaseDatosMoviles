package dm2e.cristian.basedatoscristiannieto;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Add_oferta extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_oferta);
    }
    public void onAtrasAddOferta(View view) {
        finish();
    }
}
