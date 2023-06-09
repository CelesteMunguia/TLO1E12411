package com.example.tlo1e12411.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSRION = 1;
    private static final String DATABASE_NOMBRE = "agenda.db";
    public static final String TABLE_CONTACTOS = "t_contactos";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSRION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_CONTACTOS + "(" +
                " id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "pais TEXT NOT NULL," +
                "nombre TEXT NOT NULL," +
                "telefono TEXT NOT NULL," +
                "nota TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
sqLiteDatabase.execSQL("DROP TABLE "+ TABLE_CONTACTOS);
onCreate(sqLiteDatabase);
    }
}
