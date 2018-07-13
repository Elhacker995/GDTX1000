package cl.tgdtx1000.macrosolutions.gdtx_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jorgejaramillo on 11-07-18.
 */

public class Particulas extends SQLiteOpenHelper {


    public Particulas(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table PARTICULAS(Id integer primary key autoincrement, N_Particulas integer, Fecha text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists PARTICULAS");

    }

    public void Insert (String N_Particulas, String Fecha){

        ContentValues contentValues = new ContentValues();

        contentValues.put("N_Particulas", N_Particulas);
        contentValues.put("Fecha", Fecha);
        getWritableDatabase().insert("PARTICULAS", null, contentValues);
    }


    public List<CParticulas> getAll (){

        List<CParticulas> listaCParticulas = new ArrayList<>();

        Cursor cursor = getReadableDatabase().rawQuery("Select * from PARTICULAS", null);

        while (cursor.moveToNext())
        {

            listaCParticulas.add(new CParticulas(cursor.getInt(0), cursor.getInt(1), cursor.getString(2)));

        }

        return listaCParticulas;
    }

    public boolean delete(Integer Id){

        getWritableDatabase().delete("PARTICULAS", "Id='" + Id + "'", null);

        return true;
    }
}
