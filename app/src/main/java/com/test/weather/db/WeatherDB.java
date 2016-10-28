package com.test.weather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.test.weather.model.City;
import com.test.weather.model.District;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LT on 2016/10/19.
 */

public class WeatherDB {

    public static final String DB_NAME = "weather";

    public static final int VERSION = 1;

    private static WeatherDB weatherDB;

    private SQLiteDatabase db;

    private WeatherDB(Context context){
        WeatherOpenHelper dbHelper = new WeatherOpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    public synchronized static WeatherDB getInstance(Context context){
        if(weatherDB == null){
            weatherDB = new WeatherDB(context);
        }
        return weatherDB;
    }
    public void saveCity(City city){
        if(city != null){
            ContentValues values = new ContentValues();
            values.put("city_name", city.getCityName());
            values.put("city_code", city.getCityCode());
            db.insert("City", null, values);
        }
    }

    public List<City> loadCities(){
        List<City> list = new ArrayList<City>();
        Cursor cursor = db
                .query("City", null, null, null, null, null, null);
        if(cursor.moveToFirst()){
            do {
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName((cursor.getString(cursor
                .getColumnIndex("city_name"))));
                city.setCityCode(cursor.getString(cursor
                .getColumnIndex("city_code")));
                list.add(city);
        } while (cursor.moveToNext());
        }return list;
    }

    public void saveDistrict(District district){
        if(district != null){
            ContentValues values = new ContentValues();
            values.put("district_name", district.getDistrictName());
            values.put("district_code", district.getDistrictCode());
            values.put("city_id", district.getCityId());
            db.insert("District", null, values);
        }
    }

    public List<District> loadDistricts(int cityId){
        List<District> list = new ArrayList<District>();
        Cursor cursor = db.query("District", null, "city_id = ?",
                new String[]{String.valueOf(cityId)}, null, null, null);
        if(cursor.moveToFirst()){
            do {
                District district = new District();
                district.setId(cursor.getInt((cursor.getColumnIndex("id"))));
                district.setDistrictName(cursor.getString((cursor
                        .getColumnIndex("district_name"))));
                district.setDistrictCode(cursor.getString(cursor
                        .getColumnIndex("district_code")));
                district.setCityId(cityId);
                list.add(district);
            } while (cursor.moveToNext());
        }
        return list;
    }
}
