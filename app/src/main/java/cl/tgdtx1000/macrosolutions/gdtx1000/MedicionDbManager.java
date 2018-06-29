package cl.tgdtx1000.macrosolutions.gdtx1000;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by jorgejaramillo on 6/29/18.
 */

public class MedicionDbManager {
    //
    // Definimos constante con el nombre de la tabla
    //
    public static final String M_TABLA = "Medicion" ;

    //
    // Definimos constantes con el nombre de las columnas de la tabla
    //
    public static final String M_COLUMNA_ID_SERIE = "Id_Serie";
    public static final String M_COLUMNA_FECHA = "Fecha";
    public static final String M_COLUMNA_N_PARTICULAS = "N_Particulas";


    private Context contexto;
    private MedicionDbHelper dbHelper;
    private SQLiteDatabase db;

    //
    // Definimos lista de columnas de la tabla para utilizarla en las consultas a la base de datos
    //
    private String[] columnas = new String[]{
            M_COLUMNA_ID_SERIE,
            M_COLUMNA_FECHA,
            M_COLUMNA_N_PARTICULAS} ;
    //Constructor de la clase
    public MedicionDbManager(Context context){
        this.contexto = context;
    }
    //Abre y crea la bd si no existe
    public MedicionDbManager abrir(){
        dbHelper = new MedicionDbHelper(contexto);
        db=dbHelper.getWritableDatabase();
        return this;
    }
    //Cierra la bd
    public void cerrar(){
        dbHelper.close();
    }
    //Convierte un Objeto Cursor a un Objeto Medicion
    public static Medicion cursorToMedicion(Cursor c)
    {
        Medicion medicion = null;

        if (c != null)
        {
            medicion = new Medicion();

            medicion.setId_Serie(c.getInt(c.getColumnIndex(MedicionDbManager.M_COLUMNA_ID_SERIE)));
            medicion.setFecha(c.getString(c.getColumnIndex(MedicionDbManager.M_COLUMNA_FECHA)));
            medicion.setN_Particulas(c.getInt(c.getColumnIndex(MedicionDbManager.M_COLUMNA_N_PARTICULAS)));
        }
        return medicion ;
    }
    //Convierte objeto Contacto a objeto ContentValue
    private ContentValues contactoToContentValues(Medicion medicion)
    {
        ContentValues reg = new ContentValues();

        reg.put(MedicionDbManager.M_COLUMNA_ID_SERIE, medicion.getId_Serie());
        reg.put(MedicionDbManager.M_COLUMNA_FECHA, medicion.getFecha());
        reg.put(MedicionDbManager.M_COLUMNA_N_PARTICULAS, medicion.getN_Particulas());

        return reg;
    }
    /**
     * Devuelve cursor con todos los registros y columnas de la tabla
     */
    public Cursor getCursor(String filtro) throws SQLException {
        if (db == null)
            abrir();
        Cursor c = db.query(true, M_TABLA, columnas, filtro, null, null, null, null, null);
        return c;
    }
    /**
     * Devuelve cursor con todos las columnas del registro
     */
    public Cursor getRegistro(int id)
    {
        if (db == null) {
            abrir();
        }
        Cursor c = db.query( true, M_TABLA, columnas, M_COLUMNA_ID_SERIE + "=" + id, null, null, null, null, null);
        //Nos movemos al primer registro de la consulta
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }
    /**
     * Comprueba si existe el registro
     */
    public boolean exists(int id)
    {
        boolean exists ;

        if (db == null)
            abrir();

        Cursor c = db.query( true, M_TABLA, columnas, M_COLUMNA_ID_SERIE + "=" + id, null, null, null, null, null);

        exists = (c.getCount() > 0);

        c.close();

        return exists;
    }
    /**
     * Inserta los valores en un registro de la tabla
     */
    public long insert(Medicion medicion)
    {
        long nuevoId=-1;
        if (db == null)
            abrir();
        if(!exists(medicion.getId_Serie()) ){
            nuevoId= db.insert(M_TABLA, null, contactoToContentValues(medicion));
        }
        return nuevoId;
    }
    /**
     * Actualiza los valores en un registro de la tabla
     */
    public long update(Medicion medicion)
    {
        long result=-1;
        if (db == null)
            abrir();
        if(exists(medicion.getId_Serie()) ){
            //
            // Actualizamos el registro con el identificador que hemos extraido
            //
            result = db.update(M_TABLA, contactoToContentValues(medicion), "_id=" + medicion.getId_Serie(), null);
        }
        return result;
    }

    /**
     * Eliminar el registro con el identificador indicado
     */
    public long delete(int id)
    {
        if (db == null)
            abrir();

        return db.delete(M_TABLA, "_id=" + id, null);
    }

    /**
     * Devuelve ArrayList con todos los registros de la tabla
     * @param filtro
     * @return
     */
    public ArrayList<Medicion> getMedicion(String filtro)
    {
        ArrayList<Medicion> detector = new ArrayList<Medicion>();

        if (db == null)
            abrir();

        Cursor c = db.query(true, M_TABLA, columnas, filtro, null, null, null, null, null);

        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
        {
            detector.add((cursorToMedicion(c)));
        }

        c.close();

        return detector;
    }
}