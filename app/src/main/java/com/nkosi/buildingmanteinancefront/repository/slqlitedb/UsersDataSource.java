package com.nkosi.buildingmanteinancefront.repository.slqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nkosi.buildingmanteinancefront.repository.slqlitedb.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NkosikhonaS on 9/30/2015.
 */
public class UsersDataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_NAME_USER_ID, MySQLiteHelper.COLUMN_NAME_FIRST_NAME,
            MySQLiteHelper.COLUMN_NAME_LAST_NAME, MySQLiteHelper.COLUMN_NAME_EMAIL,
            MySQLiteHelper.COLUMN_NAME_USER_NAME, MySQLiteHelper.COLUMN_NAME_PASSWORD };

    public UsersDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public String createUser(User user){

        String outcome = "";
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_NAME_USER_NAME, user.getUser_name());
        values.put(MySQLiteHelper.COLUMN_NAME_PASSWORD, user.getPassword());
        values.put(MySQLiteHelper.COLUMN_NAME_FIRST_NAME, user.getFirst_name());
        values.put(MySQLiteHelper.COLUMN_NAME_LAST_NAME, user.getLast_name());
        values.put(MySQLiteHelper.COLUMN_NAME_EMAIL, user.getEmail());

        try{
            long insertId = database.insert(MySQLiteHelper.TABLE_NAME, null,
                    values);
            Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME,
                    allColumns, MySQLiteHelper.COLUMN_NAME_USER_ID + " = " + insertId, null,
                    null, null, null);

            cursor.moveToFirst();
            User newUser = cursorToUser(cursor);
            cursor.close();
            outcome = "Success";
        }catch (Exception ex){
           outcome = ex.getMessage();
        }

        return outcome;
    }

    public User cursorToUser(Cursor cursor) {
        User user = new User();
        user.setUser_id(cursor.getLong(0));
        user.setFirst_name(cursor.getString(1));
        user.setLast_name(cursor.getString(2));
        user.setEmail(cursor.getString(3));
        user.setUser_name(cursor.getString(4));
        user.setPassword(cursor.getString(5));
        return user;
    }

    public String findUser(String userName){

        String result = null;

        String[] args={userName};
        Cursor cursor = database.rawQuery("select " + MySQLiteHelper.COLUMN_NAME_USER_NAME +
                " from " + MySQLiteHelper.TABLE_NAME + " where " + MySQLiteHelper.COLUMN_NAME_USER_NAME +
                " = ?", args);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            User user = cursorToUser(cursor);
            if(user.getUser_name().trim() == userName.trim())
                result =  user.getUser_name();
            cursor.moveToNext();
            result +=  user.getUser_name() + "\n";
        }

        return result;
    }

    public List<User> getAllUsers() {
        List<User> comments = new ArrayList<User>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            User user = cursorToUser(cursor);
            comments.add(user);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }
}
