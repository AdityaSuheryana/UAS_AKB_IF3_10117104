package e.user.uas_akb_if3_10117104.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.fragment.app.Fragment;


/*
    tgl pengerjaan  : 11-Mei-2020
    Nim             : 10117104
    Nama            : Aditya Suheryana
    Kelas           : IF-3 / AKB-3
 */


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION =2 ;

    static final String DATABASE_NAME = "TempatWisata.db";

    public static final String TABLE_NAME = "tempat";

    public static final String COLUMN_1 = "id";
    public static final String COLUMN_2 = "nama";
    public static final String COLUMN_3 = "harga";
    public static final String COLUMN_6 = "foto";
    public static final String COLUMN_4 = "lat";
    public static final String COLUMN_5 = "lng";
    public static final String COLUMN_7 = "desk";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_1 + " INTEGER PRIMARY KEY autoincrement, " +
                COLUMN_2 + " TEXT  NOT NULL, " +
                COLUMN_3 + " TEXT NOT NULL," +
                COLUMN_4 + " TEXT NOT NULL," +
                COLUMN_5 + " TEXT NOT NULL," +
                COLUMN_6 + " TEXT NOT NULL,"+
                COLUMN_7 + " TEXT NOT NULL"+")";

        db.execSQL(SQL_CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public ArrayList<HashMap<String, String>> getAllData() {
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(COLUMN_1, cursor.getString(0));
                map.put(COLUMN_2, cursor.getString(1));
                map.put(COLUMN_3, cursor.getString(2));
                map.put(COLUMN_4, cursor.getString(3));
                map.put(COLUMN_5, cursor.getString(4));
                map.put(COLUMN_6, cursor.getString(5));
                map.put(COLUMN_7, cursor.getString(6));

                wordList.add(map);
            } while (cursor.moveToNext());
        }

        Log.e("select sqlite ", "" + wordList);

        database.close();
        return wordList;
    }

    public void insert(String nama, String harga, String lat, String lng, String foto, String desk) {
        SQLiteDatabase database = this.getWritableDatabase();
        String queryValues = "INSERT INTO " + TABLE_NAME + " (nama, harga, lat, lng, foto, desk) " +
                "VALUES ('" + nama + "', '" + harga + "', '" + lat + "', '" + lng + "', '" + foto + "','"+ desk +"')";

        Log.e("insert sqlite ", "" + queryValues);
        database.execSQL(queryValues);
        database.close();
    }

    public void update(int id, String nama, String harga, String lat, String lng, String foto, String desk) {
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "UPDATE " + TABLE_NAME + " SET "
                + COLUMN_2 + "='" + nama + "', "
                + COLUMN_3 + "='" + harga + "',"
                + COLUMN_4 + "='" + lat + "',"
                + COLUMN_5 + "='" + lng + "',"
                + COLUMN_6 + "='" + foto + "',"
                + COLUMN_7 + "='" + desk + "',"

                + " WHERE " + COLUMN_1 + "=" + "'" + id + "'";
        Log.e("update sqlite ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

    public void delete(int id) {
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_1 + "=" + "'" + id + "'";
        Log.e("update sqlite ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }
}
