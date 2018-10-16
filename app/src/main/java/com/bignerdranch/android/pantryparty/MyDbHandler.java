package com.bignerdranch.android.pantryparty;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

public class MyDbHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userDB.db";
    private static final String TABLE_USERS = "users";

    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";

    public MyDbHandler(Context context, String name,
                       SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " +
                TABLE_USERS + "("
                + COLUMN_EMAIL + " TEXT,"
                + COLUMN_PASSWORD + " TEXT" + ")";
        db.execSQL(CREATE_USERS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public void addUser(User user) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_PASSWORD, user.getPassword());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public User findUser(String email) {
        String query = "Select * FROM " + TABLE_USERS + " WHERE " + COLUMN_EMAIL + " =  \"" + email + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        User user = new User();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            user.setEmail(cursor.getString(0));
            user.setPassword(cursor.getString(1));
            cursor.close();
        } else {
            user = null;
        }
        db.close();
        return user;
    }

    public boolean deleteUser(String email) {

        boolean result = false;

        String query = "Select * FROM " + TABLE_USERS + " WHERE " + COLUMN_EMAIL + " =  \"" + email + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        User user = new User();

        if (cursor.moveToFirst()) {
            user.setEmail(cursor.getString(0));
            db.delete(TABLE_USERS, COLUMN_EMAIL + " = ?",
                    new String[] { user.getEmail() });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
}
