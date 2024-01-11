package dm2e.cristian.basedatoscristiannieto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
public class BaseDatosSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String BASEDATOS = "Ocio";
    private static final int VERSION = 1;
    String sqlCreate = "CREATE TABLE Centro (nombre TEXT, aforo NUMERIC, calle NUMERIC )";
    public BaseDatosSQLiteOpenHelper(Context contexto, CursorFactory factory) {
        super(contexto, BASEDATOS, factory, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        createCentro(db);
        createUbicacion(db);
        createOferta(db);
        createProducto(db);
    }

    private void createProducto(SQLiteDatabase db) {
        String crearProducto = "CREATE TABLE producto (" +
                "idProducto INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT," +
                "precio DOUBLE," +
                "marca TEXT)";
        db.execSQL(crearProducto);
    }

    private void createOferta(SQLiteDatabase db) {
        String crearOferta = "CREATE TABLE oferta (" +
                "idOferta INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT," +
                "descripcion TEXT)";
        db.execSQL(crearOferta);
    }

    private void createUbicacion(SQLiteDatabase db) {
        String crearUbicacion = "CREATE TABLE ubicacion (" +
                "idUbicacion INTEGER PRIMARY KEY AUTOINCREMENT," +
                "calle TEXT," +
                "ciudad TEXT," +
                "codigoPostal INTEGER)";
        db.execSQL(crearUbicacion);
    }

    private void createCentro(SQLiteDatabase db) {
        String crearCentro = "CREATE TABLE centro (" +
                "idCentro INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT," +
                "ubicacion TEXT," +
                "max_aforo NUMBER," +
                "foreign key(ubicacion) references ubicacion(calle))";
        db.execSQL(crearCentro);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS Usuarios");
        db.execSQL(sqlCreate);
    }
}
