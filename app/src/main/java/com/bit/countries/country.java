package com.bit.countries;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "country_table")
public class country {

    public int getId() {
        return id;
    }


    @PrimaryKey(autoGenerate = true)
    private int id;

    String name;
    String capital;
    String flagurl;
    String region;
    String subregion;
    String population;
    String languages;
    String borders;


    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public String getFlagurl() {
        return flagurl;
    }

    public String getRegion() {
        return region;
    }

    public String getSubregion() {
        return subregion;
    }

    public String getPopulation() {
        return population;
    }

    public String getLanguages() {
        return languages;
    }

    public String getBorders() {
        return borders;
    }

    public country(String name, String capital, String flagurl, String region, String subregion, String population, String languages, String borders) {
        this.name = name;
        this.capital = capital;
        this.flagurl = flagurl;
        this.region = region;
        this.subregion = subregion;
        this.population = population;
        this.languages = languages;
        this.borders = borders;
    }
}
