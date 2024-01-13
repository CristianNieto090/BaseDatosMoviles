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
    public String mostrarCentro() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] campos = new String[]{"idCentro", "nombre", "max_aforo"};
        Cursor cursor = db.query("Centro", campos, null, null, null, null, null);
        String[][] datos = new String[cursor.getCount() + 1][3];

        // Cabecera de la tabla
        datos[0][0] = "Centro ID";
        datos[0][1] = "Nombre";
        datos[0][2] = "Aforo";

        int rowIndex = 1;

        if (cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getInt(0);
                String nombreIndex = cursor.getString(1);
                int aforoIndex = cursor.getInt(2);

                // Llenar datos en la matriz
                datos[rowIndex][0] = String.valueOf(idIndex);
                datos[rowIndex][1] = nombreIndex;
                datos[rowIndex][2] = String.valueOf(aforoIndex);

                rowIndex++;
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        // Llamar a la función construirTablaSQL con los datos
        return construirTablaSQL(datos);
    }
    public String mostrarUbicacion() {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] campos = new String[]{"idUbicacion", "calle", "ciudad","codigoPostal"};
        Cursor cursor = db.query("ubicacion", campos, null, null, null, null, null);
        String[][] datos = new String[cursor.getCount() + 1][4];

        // Cabecera de la tabla
        datos[0][0] = "iD Ubicacion";
        datos[0][1] = "Calle";
        datos[0][2] = "Ciudad";
        datos[0][3]= "codigoPostal";

        int rowIndex = 1;

        if (cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getInt(0);
                String calleIndex = cursor.getString(1);
                String ciudadIndex = cursor.getString(2);
                int codigoPostalIndex = cursor.getInt(3);

                // Llenar datos en la matriz
                datos[rowIndex][0] = String.valueOf(idIndex);
                datos[rowIndex][1] = calleIndex;
                datos[rowIndex][2] = ciudadIndex;
                datos[rowIndex][3] = String.valueOf(codigoPostalIndex);

                rowIndex++;
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        // Llamar a la función construirTablaSQL con los datos
        return construirTablaSQL(datos);
    }
    public String mostrarProducto () {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] campos = new String[]{"idProducto", "nombre", "precio","marca"};
        Cursor cursor = db.query("producto", campos, null, null, null, null, null);
        String[][] datos = new String[cursor.getCount() + 1][4];

        // Cabecera de la tabla
        datos[0][0] = "ID Producto";
        datos[0][1] = "Nombre";
        datos[0][2] = "Precio";
        datos[0][3]= "Marca";

        int rowIndex = 1;

        if (cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getInt(0);
                String nombreIndex = cursor.getString(1);
                double precioIndex = cursor.getDouble(2);
                String marcaPostalIndex = cursor.getString(3);

                // Llenar datos en la matriz
                datos[rowIndex][0] = String.valueOf(idIndex);
                datos[rowIndex][1] = nombreIndex;
                datos[rowIndex][2] = String.valueOf(precioIndex);
                datos[rowIndex][3] = marcaPostalIndex;

                rowIndex++;
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        // Llamar a la función construirTablaSQL con los datos
        return construirTablaSQL(datos);
    }
    public String mostrarOferta() {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] campos = new String[]{"idOferta", "nombre", "descripcion"};
        Cursor cursor = db.query("oferta", campos, null, null, null, null, null);
        String[][] datos = new String[cursor.getCount() + 1][3];

        // Cabecera de la tabla
        datos[0][0] = "ID Oferta";
        datos[0][1] = "Nombre";
        datos[0][2] = "Descripcion";


        int rowIndex = 1;

        if (cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getInt(0);
                String nombreIndex = cursor.getString(1);
                String descripcionIndex = cursor.getString(2);

                // Llenar datos en la matriz
                datos[rowIndex][0] = String.valueOf(idIndex);
                datos[rowIndex][1] = nombreIndex;
                datos[rowIndex][2] = descripcionIndex;

                rowIndex++;
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        // Llamar a la función construirTablaSQL con los datos
        return construirTablaSQL(datos);
    }
    public String mostrarCentroUbicacionJoin () {
//select * from centro c join ubicacion u  on c.ubicacion=u.calle;
        SQLiteDatabase db = this.getReadableDatabase();

        String[] campos1 = new String[]{"idCentro", "nombre", "ubicacion"};

        String[] campos2 = new String[]{"calle", "ciudad","codigoPostal"};

        Cursor cursor1 = db.query("centro", campos1, null, null, null, null, null);
        String[][] datos = new String[cursor1.getCount() + 1][5];

        // Cabecera de la tabla
        datos[0][0] = "ID Centro";
        datos[0][1] = "Nombre";
        datos[0][2]= "Calle";
        datos[0][3]= "Ciudad";
        datos[0][4]= "CodigoPostal";
        int rowIndex = 1;

        if (cursor1.moveToFirst()) {
            do {

                int idIndex = cursor1.getInt(0);
                String nombreIndex = cursor1.getString(1);
                String ubicacionIndex[] = new String[]{cursor1.getString(2)};
                Cursor cursor2 = db.query("ubicacion", campos2, "calle=?", ubicacionIndex, null, null, null);
                cursor2.moveToFirst();

                String calleIndex = cursor2.getString(0);
                String ciudadIndex = cursor2.getString(1);
                int cpIndex = cursor2.getInt(2);

                // Llenar datos en la matriz
                datos[rowIndex][0] = String.valueOf(idIndex);
                datos[rowIndex][1] = nombreIndex;
                datos[rowIndex][2] = calleIndex;
                datos[rowIndex][3] = ciudadIndex;
                datos[rowIndex][4] = String.valueOf(cpIndex);

                rowIndex++;
            } while (cursor1.moveToNext());
        }

        cursor1.close();
        db.close();

        // Llamar a la función construirTablaSQL con los datos
        return construirTablaSQL(datos);
    }

    public static String construirTablaSQL(String[][] datos) {
        StringBuilder tablaSQL = new StringBuilder();

        // Obtener la longitud máxima de cada columna
        int[] longitudesMaximas = new int[datos[0].length];
        for (int i = 0; i < datos[0].length; i++) {
            int longitudMaxima = datos[0][i].length(); // Iniciar con la longitud de la cabecera
            for (String[] fila : datos) {
                longitudMaxima = Math.max(longitudMaxima, fila[i].length());
            }
            longitudesMaximas[i] = longitudMaxima;
        }

        // Construimos la cabecera de la tabla
        for (int i = 0; i < datos[0].length; i++) {
            tablaSQL.append("| ").append(ajustarTexto(datos[0][i], longitudesMaximas[i])).append(" ");
        }
        tablaSQL.append("|\n");

        // Construimos la línea separadora
        for (int i = 0; i < datos[0].length; i++) {
            tablaSQL.append("+");
            for (int j = 0; j < longitudesMaximas[i]+1; j++) {
                tablaSQL.append("-");
            }
        }
        tablaSQL.append("+\n");

        // Construimos las filas de datos
        for (int i = 1; i < datos.length; i++) {
            for (int j = 0; j < datos[i].length; j++) {
                tablaSQL.append("| ").append(ajustarTexto(datos[i][j], longitudesMaximas[j])).append(" ");
            }
            tablaSQL.append("|\n");
        }

        return tablaSQL.toString();
    }
    private static String ajustarTexto(String texto, int longitudMaxima) {
        return String.format("%-" + longitudMaxima + "s", texto);
    }



}

