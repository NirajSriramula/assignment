package com.bit.countries;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = country.class ,version = 1)
public abstract   class CountryDatabase extends RoomDatabase {
    private static CountryDatabase instance;
    public abstract countryDao countryDao();
    public static synchronized CountryDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),CountryDatabase.class,"Country_database").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}