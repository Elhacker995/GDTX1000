package cl.tgdtx1000.macrosolutions.gdtx1000;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by jorgejaramillo on 6/29/18.
 */

public class MedicionDbHelper extends SQLiteOpenHelper {

    private static int version = 1;
    private static String name = "MedicionDb" ;
    private static SQLiteDatabase.CursorFactory factory = null;

    public MedicionDbHelper(Context context)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        Log.i(this.getClass().toString(), "Creando base de datos");

        db.execSQL( "CREATE TABLE Medicion(" +
                " Id_Serie INTEGER PRIMARY KEY," +
                " Fecha TEXT NOT NULL, " +
                " N_Particulas INTEGER NOT NULL)" );

        db.execSQL( "CREATE UNIQUE INDEX Fecha ON Medicion(Fecha ASC)" );

        Log.i(this.getClass().toString(), "Tabla Medicion creada");

    /** Insertamos datos iniciales

        db.execSQL("INSERT INTO CONTACTO(Id_Serie, Fecha, N_Particulas) VALUES(1,'2018-06-29 13:45:10.001','356')");
        db.execSQL("INSERT INTO CONTACTO(Id_Serie, Fecha, N_Particulas) VALUES(2,'2018-06-29 13:45:10.002','352')");
        db.execSQL("INSERT INTO CONTACTO(Id_Serie, Fecha, N_Particulas) VALUES(3,'2018-06-29 13:45:10.003','367')");
        db.execSQL("INSERT INTO CONTACTO(Id_Serie, Fecha, N_Particulas) VALUES(4,'2018-06-29 13:45:10.004','324')");
        Log.i(this.getClass().toString(), "Datos iniciales Medicion insertados");
        */
        Log.i(this.getClass().toString(), "Base de datos creada");

        // Aplicamos las sucesivas actualizaciones

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // Actualización a versión 2

    }

}
