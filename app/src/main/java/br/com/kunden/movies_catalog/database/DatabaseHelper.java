package br.com.kunden.movies_catalog.database;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

public class DatabaseHelper extends SQLiteOpenHelper {
    //database info
    private static final String DATABASE = "KUNDEN";
    private static final int VERSION = 2;

    //common columns
    public static final String ID = "id";

    //movie table
    public static final String MOVIE_TABLE_NAME = "MOVIE";
    public static final String MOVIE_TITLE = "title";
    public static final String MOVIE_YEAR = "year";
    public static final String MOVIE_GENRE = "genre";
    public static final String MOVIE_DESCRIPTION = "description";
    public static final String MOVIE_IMAGE = "image";

    public DatabaseHelper(Context context){
        super(context, DATABASE, null, VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + MOVIE_TABLE_NAME);

        db.execSQL("CREATE TABLE " + MOVIE_TABLE_NAME + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MOVIE_TITLE + " VARCHAR(50), " +
                MOVIE_YEAR + " DATE, " +
                MOVIE_GENRE + " VARCHAR(50), " +
                MOVIE_DESCRIPTION + " VARCHAR(255), " +
                MOVIE_IMAGE + " VARCHAR(255));"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        this.onCreate(db);
    }


}