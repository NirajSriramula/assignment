package com.bit.countries;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface countryDao {
    @Insert
    void insert(country country);

    @Query("delete from country_table")
    void DeleteAll();

    @Query("SELECT * FROM country_table")
    List<country> getAll();
}
