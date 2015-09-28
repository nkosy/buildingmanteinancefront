package com.nkosi.buildingmanteinancefront.repository.slqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.nkosi.buildingmanteinancefront.repository.slqlitedb.model.User;

/**
 * Created by nkosi on 2015/09/28.
 */
public class MyDBHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "buildingManager.db";
    public static final String TABLE_NAME = "users";
    public static final String COLUMN_NAME_USER_ID = "user_id";
    public static final String COLUMN_NAME_USER_NAME = "user_name";
    public static final String COLUMN_NAME_PASSWORD = "password";
    public static final String COLUMN_NAME_First_Name = "first_name";
    public static final String COLUMN_NAME_LAST_NAME = "last_name";
    public static final String COLUMN_NAME_EMAIL = "email";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " +TABLE_NAME;


    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME , factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_NAME_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COLUMN_NAME_USER_NAME + TEXT_TYPE + COMMA_SEP +
                        COLUMN_NAME_PASSWORD + TEXT_TYPE + COMMA_SEP +
                        COLUMN_NAME_First_Name + TEXT_TYPE + COMMA_SEP +
                        COLUMN_NAME_LAST_NAME + TEXT_TYPE + COMMA_SEP +
                        COLUMN_NAME_EMAIL + TEXT_TYPE +
                        " )";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TABLE);
        onCreate(db);
    }

    public void addNewUser(User user)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_USER_NAME, user.getUser_name());
        values.put(COLUMN_NAME_PASSWORD, user.getPassword());
        values.put(COLUMN_NAME_First_Name, user.getFirst_name());
        values.put(COLUMN_NAME_LAST_NAME, user.getLast_name());
        values.put(COLUMN_NAME_EMAIL, user.getEmail());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void deleteUser(String user_name){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME_USER_NAME + "=\"" + user_name + "\";" );
    }

}
