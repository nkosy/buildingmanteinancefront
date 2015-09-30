package com.nkosi.buildingmanteinancefront.repository.slqlitedb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by NkosikhonaS on 9/30/2015.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "buildingManager.db";
    public static final String TABLE_NAME = "users";
    public static final String COLUMN_NAME_USER_ID = "user_id";
    public static final String COLUMN_NAME_USER_NAME = "user_name";
    public static final String COLUMN_NAME_PASSWORD = "password";
    public static final String COLUMN_NAME_FIRST_NAME = "first_name";
    public static final String COLUMN_NAME_LAST_NAME = "last_name";
    public static final String COLUMN_NAME_EMAIL = "email";
    public static final int DATABASE_VERSION = 1;
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    //Database creation sql statement
    private static final String DATABASE_CREATE =  "CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_NAME_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME_USER_NAME + TEXT_TYPE + COMMA_SEP +
            COLUMN_NAME_PASSWORD + TEXT_TYPE + COMMA_SEP +
            COLUMN_NAME_FIRST_NAME + TEXT_TYPE + COMMA_SEP +
            COLUMN_NAME_LAST_NAME + TEXT_TYPE + COMMA_SEP +
            COLUMN_NAME_EMAIL + TEXT_TYPE +
            " )";
    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
}
