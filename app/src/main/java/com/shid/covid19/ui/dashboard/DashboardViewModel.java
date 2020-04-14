package com.shid.covid19.ui.dashboard;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.shid.covid19.Api.ApiClient;
import com.shid.covid19.Api.ApiInterface;
import com.shid.covid19.Database.AppDatabase;
import com.shid.covid19.Model.Countries;
import com.shid.covid19.Model.TotalCases;
import com.shid.covid19.Utils.AppExecutor;
import com.shid.covid19.Utils.Constant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;
    private LiveData<List<Countries>> countriesListLiveData;
    private List<Countries> ecowasCountriesList;
    //private MutableLiveData<List<Countries>> listOfCountries;
    //private List<Countries> list;
    private LiveData<Countries> countryLiveData;
    public static String country_chosen;
    public static String status;
    public static String errorCode;
    private int error_response;
    AppDatabase database;

    public DashboardViewModel(Application application) {
        super(application);

        AppExecutor appExecutor = AppExecutor.getInstance();
        appExecutor.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                database = AppDatabase.getInstance(application);
                //listOfCountries = new MutableLiveData<>();
                getListOfCountries();
                countriesListLiveData = database.coronaDAO().loadCountries();
                ecowasCountriesList = database.coronaDAO().loadEcowasCountries();
            }
        });


    }

    public List<Countries> getEcowasCountriesList(){
        return ecowasCountriesList;
    }

    public LiveData<List<Countries>> getCountries() {
        AppExecutor appExecutor = AppExecutor.getInstance();
        appExecutor.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                if (database.coronaDAO().checkIfTableCountriesEmpty() <= 0) {
                    status = Constant.ERROR;
                }
            }
        });
        return countriesListLiveData;
    }

    public void getListOfCountries() {

        status = "";
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Countries>> callTotal = apiInterface.getAllCountries();
        callTotal.enqueue(new Callback<List<Countries>>() {
            @Override
            public void onResponse(Call<List<Countries>> call, Response<List<Countries>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    status = Constant.SUCCESS;
                    //list = response.body();
                    // listOfCountries.postValue(response.body());
                    AppExecutor appExecutor = AppExecutor.getInstance();
                    appExecutor.diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            if (database.coronaDAO().checkIfTableCountriesEmpty() <= 0) {
                                database.coronaDAO().insertCountries(response.body());
                            } else {
                                database.coronaDAO().deleteTableCountries();
                                database.coronaDAO().insertCountries(response.body());
                            }

                        }
                    });
                } else {
                    status = Constant.ERROR;
                    error_response = response.code();
                    switch (response.code()) {
                        case 404:
                            errorCode = "404 not found";
                            break;
                        case 500:
                            errorCode = "500 server broken";
                            break;
                        default:
                            errorCode = "unknown error";
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Countries>> call, Throwable t) {
                AppExecutor appExecutor = AppExecutor.getInstance();
                appExecutor.diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (database.coronaDAO().checkIfTableCaseEmpty() <= 0) {
                            status = Constant.FAILURE;
                        }
                    }
                });

            }
        });


    }

    public LiveData<String> getText() {
        return mText;
    }
}