package com.app.shresta.shrestaapp.activity.handler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.app.shresta.shrestaapp.activity.aesalgorithm.AESHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "shresta_app";

    private static final String TABLE_USER_REG = "user_registration";
    private static final String ID = "id";
    private static final String USER_FULL_NAME = "full_name";
    private static final String USER_MOBILE_NUMBER = "mobile_number";
    private static final String USER_EMAIL = "email";
    private static final String USER_USERNAME = "email";
    private static final String USER_PASSWORD = "password";

    private static final String TABLE_ADD_KEYVALUES = "addkeyvalues";
    private static final String KEY_ID = "keyid";
    private static final String KEY = "key";
    private static final String KEY_VALUE = "key_value";

    String DecryptedKeyValueStr;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Category table create query
        String CREATE_USER_REG_TABLE = "CREATE TABLE " + TABLE_USER_REG + "("
                + ID + " INTEGER PRIMARY KEY," + USER_FULL_NAME + " TEXT," + USER_MOBILE_NUMBER + " TEXT," + USER_EMAIL + " TEXT," +
                USER_PASSWORD + " TEXT)";

        String CREATE_KEY_VALUES_TABLE = "CREATE TABLE " + TABLE_ADD_KEYVALUES + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"  + KEY + " TEXT," + KEY_VALUE + " TEXT)";

        db.execSQL(CREATE_USER_REG_TABLE);
        db.execSQL(CREATE_KEY_VALUES_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_REG);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADD_KEYVALUES);
        onCreate(db);
    }

    //**************************** INTETRTING VALUES IN TO AIRPORT TABLE******************************//

    public void docReg(String full_name, String mobile_number, String email, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_FULL_NAME, full_name);
        values.put(USER_MOBILE_NUMBER, mobile_number);
        values.put(USER_EMAIL, email);
        values.put(USER_USERNAME, username);
        values.put(USER_PASSWORD, password);

        // Inserting Row
        db.insert(TABLE_USER_REG, null, values);
        db.close(); // Closing database connection
    }


    public List<String> docLogin(String mobile_number) {

        List<String> docs = new ArrayList<String>();
        String selectQuery = "SELECT * FROM " + TABLE_USER_REG + " WHERE mobile_number =" + mobile_number ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {

                docs.add(cursor.getString(1));
                System.out.println("cursor.getString(1) : "  + cursor.getString(1));
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        return docs;
    }

    public void addKeyValues(String key, String key_value){
        System.out.println("key==  ; " + key);
        System.out.println("key value == ; " + key_value);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY, key);
        values.put(KEY_VALUE, key_value);

        // Inserting Row
        db.insert(TABLE_ADD_KEYVALUES, null, values);
        db.close(); // Closing database connection
    }

    public List<String> getValuesList() {

        List<String> keyValues = new ArrayList<String>();
        String selectQuery = "SELECT * FROM " + TABLE_ADD_KEYVALUES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                String keyVaueStr=cursor.getString(2);
                System.out.println("===Database handler keyValueStr : " + keyVaueStr);
                try{
                    DecryptedKeyValueStr = AESHelper.decrypt("AES Algo", keyVaueStr);
                    Log.i("DecryptedKeyValueStry: ", DecryptedKeyValueStr);
                }catch (Exception e){
                    e.printStackTrace();
                }
                keyValues.add(cursor.getString(0) + "***" + cursor.getString(1) + "***" +keyVaueStr);
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        return keyValues;
    }
}
