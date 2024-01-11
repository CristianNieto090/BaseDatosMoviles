package dm2e.cristian.basedatoscristiannieto;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Delete_centro extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_centro);
    }
    public void onAtrasDeleteCentro(View view) {
        finish();
    }
}

