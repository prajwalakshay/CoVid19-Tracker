package com.shid.covid19.Api;

import com.shid.covid19.Model.Countries;
import com.shid.covid19.Model.TotalCases;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("all")
    Call<TotalCases> getTotalCases(



    );

    @GET("countries")
    Call<List<Countries>> getAllCountries(





    );

    @GET("countries")
    Call<Countries> getAllCountries(


            @Query("sort") String sort


    );

    @GET("countries/{country-name}")
    Call<Countries> getCountry(


            @Path("country-name") String country_name


    );
}
