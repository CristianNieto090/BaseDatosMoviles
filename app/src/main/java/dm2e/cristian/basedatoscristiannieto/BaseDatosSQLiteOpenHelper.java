package dm2e.cristian.basedatoscristiannieto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

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


    //Métodos para insertar
    public void insertCentro(String nombre, int aforo, String ubicacion) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("max_aforo", aforo);
        values.put("ubicacion", ubicacion);
        db.insert("centro", null, values);
        db.close();
    }

    public void insertProducto(String nombre, double precio, String marca) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("precio", precio);
        values.put("marca", marca);
        db.insert("producto", null, values);
        db.close();
    }

    public void insertOferta(String nombre, String descripcion) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("descripcion", descripcion);
        db.insert("oferta",null,values);
        db.close();
    }

    public void insertUbicacion(String calle, String ciudad, int cp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("calle", calle);
        values.put("ciudad", ciudad);
        values.put("codigoPostal", cp);
        db.insert("ubicacion", null, values);
        db.close();
    }

    //Métodos para borrar
    public boolean deleteCentro(int centroId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete("centro", "idCentro" + "=?", new String[]{String.valueOf(centroId)});
        db.close();

        return rowsAffected > 0;
    }

    public boolean deleteOferta(int ofertaId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete("oferta", "ofertaId" + "=?", new String[]{String.valueOf(ofertaId)});
        db.close();

        return rowsAffected > 0;
    }

    public boolean deleteProducto(int productoId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete("producto", "productoId" + "=?", new String[]{String.valueOf(productoId)});
        db.close();

        return rowsAffected > 0;
    }

    public boolean deleteUbicacion(int ubicacionId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete("ubicacion", "ubicacionId" + "=?", new String[]{String.valueOf(ubicacionId)});
        db.close();

        return rowsAffected > 0;
    }

    //métodos update
    public boolean updateOfertaDescripcion(int ofertaId, String nuevaDescripcion) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("descripcion", nuevaDescripcion);

        int rowsAffected = db.update("oferta", values, "descripcion" + "=?", new String[]{String.valueOf(ofertaId)});
        db.close();

        return rowsAffected > 0;
    }

    public boolean updateProductoMarca(int productId, String nuevoMarca) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("marca", nuevoMarca);

        int rowsAffected = db.update("producto", values, "marca" + "=?", new String[]{String.valueOf(productId)});
        db.close();

        return rowsAffected > 0;
    }

    public boolean updateCentroNombre(int centroId, String nuevoNombre) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("centro", nuevoNombre);

        int rowsAffected = db.update("centro", values, "nombre" + "=?", new String[]{String.valueOf(centroId)});
        db.close();

        return rowsAffected > 0;
    }

    public boolean updateProductPrecio(int productId, double nuevoPrecio) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("producto", nuevoPrecio);

        int rowsAffected = db.update("producto", values, "productoId" + "=?", new String[]{String.valueOf(productId)});
        db.close();

        return rowsAffected > 0;
    }
    // métodos select
    public TextView mostrarCentro(){
        TextView tvCentro = null;
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM centro", null);

        if (cursor.moveToFirst()) {
            StringBuilder data = new StringBuilder();
            do {
                int idIndex = cursor.getColumnIndex("id");
                int nombreIndex = cursor.getColumnIndex("nombre");

                int centroId = cursor.getInt(idIndex);
                String centroNombre = cursor.getString(nombreIndex);

                data.append("Centro ID: ").append(centroId).append(", Nombre: ").append(centroNombre).append("\n");

            } while (cursor.moveToNext());

            cursor.close();
            db.close();

            tvCentro.setText(data.toString());
        } else {
            tvCentro.setText("No hay datos de Centro.");
        }

        return tvCentro;
    }
    public TextView mostrarUbicacion() {
        TextView tvUbicacion = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM ubicacion", null);

        if (cursor.moveToFirst()) {
            StringBuilder data = new StringBuilder();
            do {
                int idIndex = cursor.getColumnIndex("id");
                int nameIndex = cursor.getColumnIndex("nombre");

                int ubicacionId = cursor.getInt(idIndex);
                String ubicacionNombre = cursor.getString(nameIndex);

                data.append("Ubicacion ID: ").append(ubicacionId).append(", Nombre: ").append(ubicacionNombre).append("\n");

            } while (cursor.moveToNext());

            cursor.close();
            db.close();

            tvUbicacion.setText(data.toString());
        } else {
            tvUbicacion.setText("No hay datos de Ubicacion.");
        }
        return tvUbicacion;
    }
    public TextView mostrarProducto () {
        TextView tvProducto = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM producto", null);

        if (cursor.moveToFirst()) {
            StringBuilder data = new StringBuilder();
            do {
                int idIndex = cursor.getColumnIndex("id");
                int nameIndex = cursor.getColumnIndex("nombre");
                int descriptionIndex = cursor.getColumnIndex("descripcion");

                int productoId = cursor.getInt(idIndex);
                String productoNombre = cursor.getString(nameIndex);
                String productoDescripcion = cursor.getString(descriptionIndex);

                data.append("Producto ID: ").append(productoId)
                        .append(", Nombre: ").append(productoNombre)
                        .append(", Descripcion: ").append(productoDescripcion).append("\n");

            } while (cursor.moveToNext());

            cursor.close();
            db.close();

            tvProducto.setText(data.toString());
        } else {
            tvProducto.setText("No hay datos disponibles de la tabla Producto.");
        }
        return tvProducto;
    }
    public TextView mostrarOferta() {
        TextView tvOferta = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Oferta", null);

        if (cursor.moveToFirst()) {
            StringBuilder data = new StringBuilder();
            do {
                int idIndex = cursor.getColumnIndex("id");
                int descriptionIndex = cursor.getColumnIndex("descripcion");
                int priceIndex = cursor.getColumnIndex("precio");

                int ofertaId = cursor.getInt(idIndex);
                String ofertaDescripcion = cursor.getString(descriptionIndex);
                double ofertaPrecio = cursor.getDouble(priceIndex);

                data.append("Oferta ID: ").append(ofertaId)
                        .append(", Descripcion: ").append(ofertaDescripcion)
                        .append(", Precio: ").append(ofertaPrecio).append("\n");

            } while (cursor.moveToNext());

            cursor.close();
            db.close();

            tvOferta.setText(data.toString());
        } else {
            tvOferta.setText("No hay datos disponibles de la tabla Oferta");
        }
        return tvOferta;
    }
    public TextView mostrarCentroUbicacionJoin () {

        TextView tvJoin = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT Centro.nombre, Ubicacion.calle FROM Centro INNER JOIN Ubicacion ON Centro.ubicacion = Ubicacion.calle";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            StringBuilder datos = new StringBuilder();
            do {

                int centroNameIndex = cursor.getColumnIndex("Centro.nombre");

                int ubicacionDetailsIndex = cursor.getColumnIndex("Ubicacion.calle");
                String centroName = cursor.getString(centroNameIndex);

                String ubicacionCalle = cursor.getString(ubicacionDetailsIndex);
                datos.append(", Nombre centro: ").append(centroName)
                        .append(", Ubicación: ").append(ubicacionCalle).append("\n");

            } while (cursor.moveToNext());

            cursor.close();
            db.close();

            tvJoin.setText(datos.toString());
        } else {
            tvJoin.setText("No hay datos de estas tablas");
        }
        return tvJoin;
    }




}

