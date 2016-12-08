package com.stevenscheffelaar.simple_security;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import java.util.ArrayList;

/**
 * Created by Steven on 11/27/2016.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "LightManager";

    private static final String TABLE_LIGHTS = "lights";

    private static final String KEY_LIGHT_NAME = "name";
    private static final String KEY_LIGHT_NUM = "id";
    private static final String KEY_ON_CODE = "on_code";
    private static final String KEY_OFF_CODE = "off_code";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void  onCreate(SQLiteDatabase db) {
        String CREATE_LIGHT_TABLE = "CREATE TABLE " + TABLE_LIGHTS + "(" + KEY_LIGHT_NUM +
                " INTEGER PRIMARY KEY, " + KEY_ON_CODE + " TEXT, " + KEY_OFF_CODE + " TEXT," +
                KEY_LIGHT_NAME +  " TEXT)";
        db.execSQL(CREATE_LIGHT_TABLE);
    }

    @Override
    public void  onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIGHTS);
        onCreate(db);
    }

    public void addLight(Light light) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_LIGHT_NUM, light.getLightNum());
        values.put(KEY_ON_CODE, light.getOnCode());
        values.put(KEY_OFF_CODE, light.getOffCode());
        values.put(KEY_LIGHT_NAME, light.getLightName());

        db.insert(TABLE_LIGHTS, null, values);
        db.close(); // Closing database connection
    }

    public Light getLight(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_LIGHTS, new String[] { KEY_LIGHT_NUM,
                        KEY_ON_CODE, KEY_OFF_CODE, KEY_LIGHT_NAME }, KEY_LIGHT_NUM + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Light light = new Light(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));

        return light;
    }

    public ArrayList<Light> getAllLights() {
        ArrayList<Light> lightList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_LIGHTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Light light = new Light();
                light.setLightNum(Integer.parseInt(cursor.getString(0)));
                light.setOnCode(cursor.getString(1));
                light.setOffCode(cursor.getString(2));
                light.setLightName(cursor.getString(3));
                // Adding contact to list
                lightList.add(light);
            } while (cursor.moveToNext());
        }

        return lightList;
    }

    public int getLightCount() {
        String countQuery = "SELECT * FROM " + TABLE_LIGHTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        // return count
        return count;
    }

    public int updateLight(Light light) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ON_CODE, light.getOnCode());
        values.put(KEY_OFF_CODE, light.getOffCode());
        values.put(KEY_LIGHT_NAME, light.getLightName());

        // updating row
        return db.update(TABLE_LIGHTS, values, KEY_LIGHT_NUM + " = ?",
                new String[] { String.valueOf(light.getLightNum()) });
    }

    public void deleteLight(Light light) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LIGHTS, KEY_LIGHT_NUM + " = ?",
                new String[] { String.valueOf(light.getLightNum()) });
        db.close();
    }
}
