package com.shid.covid19.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.shid.covid19.Model.Countries;
import com.shid.covid19.Model.TotalCases;

import java.util.List;

@Dao
public interface CoronaDAO {
    @Query("SELECT * FROM total_cases ")
    LiveData<TotalCases> loadTotalCases();

    @Query("SELECT * FROM countries ")
    LiveData<List<Countries>> loadCountries();

    @Query("SELECT count(*) FROM total_cases")
    int checkIfTableCaseEmpty();

    @Query("SELECT count(*) FROM countries")
    int checkIfTableCountriesEmpty();

    @Insert
    void insertTotalCases(TotalCases totalCases);

    @Insert
    void insertCountries(List<Countries> countries);


    @Query("SELECT * FROM countries WHERE country = :country ")
    Countries loadCountry(String country);

    @Query("DELETE FROM countries")
    public void deleteTableCountries();

    @Query("DELETE FROM total_cases")
    public void deleteTableTotalCases();

}
