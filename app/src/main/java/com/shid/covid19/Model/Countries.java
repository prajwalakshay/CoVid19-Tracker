package com.shid.covid19.Model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "countries")
public class Countries {
    @PrimaryKey(autoGenerate = true)
    private int countryId;

    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("cases")
    @Expose
    private int cases;

    @SerializedName("todayCases")
    @Expose
    private int todayCases;

    @SerializedName("deaths")
    @Expose
    private int deaths;

    @SerializedName("todayDeaths")
    @Expose
    private int todayDeaths;

    @SerializedName("recovered")
    @Expose
    private int recovered;

    @Ignore
    public Countries(String country, int cases, int todayCases, int deaths, int todayDeaths, int recovered) {
        this.country = country;
        this.cases = cases;
        this.todayCases = todayCases;
        this.deaths = deaths;
        this.todayDeaths = todayDeaths;
        this.recovered = recovered;
    }

    public Countries(int countryId, String country, int cases, int todayCases, int deaths, int todayDeaths, int recovered) {
        this.countryId = countryId;
        this.country = country;
        this.cases = cases;
        this.todayCases = todayCases;
        this.deaths = deaths;
        this.todayDeaths = todayDeaths;
        this.recovered = recovered;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public int getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(int todayCases) {
        this.todayCases = todayCases;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getTodayDeaths() {
        return todayDeaths;
    }

    public void setTodayDeaths(int todayDeaths) {
        this.todayDeaths = todayDeaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }
}
