package com.nkosi.buildingmanteinancefront.repository.slqlitedb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by nkosi on 2015/09/28.
 */
public final class FeedReaderContract {

    public FeedReaderContract() {
    }

    public static abstract class FeedUser implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME_USER_ID = "user_id";
        public static final String COLUMN_NAME_USER_NAME = "user_name";
        public static final String COLUMN_NAME_First_Name = "first_name";
        public static final String COLUMN_NAME_LAST_NAME = "last_name";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_PASSWORD = "password";

        private static final String TEXT_TYPE = " TEXT";
        private static final String COMMA_SEP = ",";
        private static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + FeedUser.TABLE_NAME + " (" +
                        FeedUser._ID + " INTEGER PRIMARY KEY," +
                        FeedUser.COLUMN_NAME_USER_ID + TEXT_TYPE + COMMA_SEP +
                        FeedUser.COLUMN_NAME_USER_NAME + TEXT_TYPE + COMMA_SEP +
                        FeedUser.COLUMN_NAME_First_Name + TEXT_TYPE + COMMA_SEP +
                        FeedUser.COLUMN_NAME_LAST_NAME + TEXT_TYPE + COMMA_SEP +
                        FeedUser.COLUMN_NAME_EMAIL + TEXT_TYPE + COMMA_SEP +
                        FeedUser.COLUMN_NAME_PASSWORD + TEXT_TYPE + COMMA_SEP +
                        " )";
        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + FeedUser.TABLE_NAME;

        public class FeedReaderDbHelper extends SQLiteOpenHelper {
            public static final int DATABASE_VERSION = 1;
            public static final String DATABASE_NAME = "FeedReader.db";

            public FeedReaderDbHelper(Context context) {
                super(context, DATABASE_NAME, null, DATABASE_VERSION);
            }

            @Override
            public void onCreate(SQLiteDatabase db) {
                db.execSQL(SQL_CREATE_ENTRIES);

            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL(SQL_DELETE_ENTRIES);
                onCreate(db);
            }

            public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                onUpgrade(db, oldVersion, newVersion);
            }
        }
    }
}
