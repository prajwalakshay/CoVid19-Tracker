package com.shid.covid19.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.shid.covid19.Database.AppDatabase;
import com.shid.covid19.Model.TotalCases;
import com.shid.covid19.R;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.shid.covid19.Utils.Constant.ERROR;
import static com.shid.covid19.Utils.Constant.FAILURE;
import static com.shid.covid19.Utils.Constant.SUCCESS;

public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private HomeViewModel homeViewModel;
    private TextView numberOfDeaths, numberOfCases, numberOfRecovered;
    private TextView textView_deaths, textView_cases, textView_recovered;
    private ImageView imageView_recovered, imageView_cases, imageView_deaths;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RelativeLayout errorLayout;
    private ImageView errorImage;
    private TextView errorTitle, errorMessage;
    private Button btnRetry;
    private Toolbar toolbar;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        numberOfCases = root.findViewById(R.id.number_of_cases);
        numberOfDeaths = root.findViewById(R.id.number_of_deaths);
        numberOfRecovered = root.findViewById(R.id.number_of_recovered);

        textView_deaths = root.findViewById(R.id.textView_deaths);
        textView_cases = root.findViewById(R.id.textView_cases);
        textView_recovered = root.findViewById(R.id.textView_recovered);

        imageView_cases = root.findViewById(R.id.imageView);
        imageView_deaths = root.findViewById(R.id.imageDeath);
        imageView_recovered = root.findViewById(R.id.imageRecovered);

        swipeRefreshLayout = root.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);

        errorLayout = root.findViewById(R.id.errorLayout);
        errorImage = root.findViewById(R.id.errorImage);
        errorTitle = root.findViewById(R.id.errorTitle);
        errorMessage = root.findViewById(R.id.errorMessage);
        btnRetry = root.findViewById(R.id.btnRetry);

        toolbar = root.findViewById(R.id.toolbar);
        homeViewModel.getLocal().observe(getViewLifecycleOwner(), new Observer<TotalCases>() {
            @Override
            public void onChanged(TotalCases totalCases) {
                setUiBasedOnStatus(HomeViewModel.status);
                if (totalCases != null) {
                    numberOfCases.setText("" + totalCases.getCases());
                    numberOfDeaths.setText("" + totalCases.getDeaths());
                    numberOfRecovered.setText("" + totalCases.getRecovered());
                }
            }
        });
/*
        homeViewModel.getTotalCases().observe(getViewLifecycleOwner(), new Observer<TotalCases>() {
            @Override
            public void onChanged(TotalCases totalCases) {
                setUiBasedOnStatus(HomeViewModel.status);
                if (totalCases != null){
                    numberOfCases.setText("" + totalCases.getCases());
                    numberOfDeaths.setText("" + totalCases.getDeaths());
                    numberOfRecovered.setText("" + totalCases.getRecovered());
                }




            }
        });

 */

        return root;
    }

    private void setUiBasedOnStatus(String value) {
        switch (value) {
            case SUCCESS:
                Log.d(TAG, "error is : " + HomeViewModel.errorCode);
                Log.d(TAG, "status is: " + HomeViewModel.status);
                swipeRefreshLayout.setRefreshing(false);
                HomeViewModel.status = "";
                break;
            case FAILURE:
                Log.d(TAG, "error is : " + HomeViewModel.errorCode);
                Log.d(TAG, "status is: " + HomeViewModel.status);
                swipeRefreshLayout.setRefreshing(false);
                HomeViewModel.status = "";
                showErrorMessage(
                        R.drawable.oops,
                        "Oops..",
                        "Pas de réseau, Réessayez\n" +
                                HomeViewModel.errorCode);
                break;
            case ERROR:
                Log.d(TAG, "error is : " + HomeViewModel.errorCode);
                Log.d(TAG, "status is: " + HomeViewModel.status);

                HomeViewModel.status = "";
                swipeRefreshLayout.setRefreshing(false);
                showErrorMessage(
                        R.drawable.no_result,
                        "Aucun Résultat",
                        "Réessayez!\n" +
                                HomeViewModel.errorCode);
                break;
            default:
                HomeViewModel.status = "";
                break;

        }
    }
private void hideMainLayout(){
    imageView_recovered.setVisibility(View.GONE);
    imageView_deaths.setVisibility(View.GONE);
    imageView_cases.setVisibility(View.GONE);
    textView_recovered.setVisibility(View.GONE);
    textView_cases.setVisibility(View.GONE);
    textView_deaths.setVisibility(View.GONE);
    numberOfRecovered.setVisibility(View.GONE);
    numberOfDeaths.setVisibility(View.GONE);
    numberOfCases.setVisibility(View.GONE);
}

private void revealMainLayout(){
    imageView_recovered.setVisibility(View.VISIBLE);
    imageView_deaths.setVisibility(View.VISIBLE);
    imageView_cases.setVisibility(View.VISIBLE);
    textView_deaths.setVisibility(View.VISIBLE);
    textView_cases.setVisibility(View.VISIBLE);
    textView_recovered.setVisibility(View.VISIBLE);
    numberOfCases.setVisibility(View.VISIBLE);
    numberOfDeaths.setVisibility(View.VISIBLE);
    numberOfRecovered.setVisibility(View.VISIBLE);
}
    private void showErrorMessage(int imageView, String title, String message) {
        Log.d("TAG", "showError is called");
        if (errorLayout.getVisibility() == View.GONE) {
            errorLayout.setVisibility(View.VISIBLE);
            hideMainLayout();
        }

        errorImage.setImageResource(imageView);
        errorTitle.setText(title);
        errorMessage.setText(message);

        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartApp();
                errorLayout.setVisibility(View.GONE);
                revealMainLayout();

            }
        });

    }

    @Override
    public void onRefresh() {
        restartApp();
    }

    private void restartApp() {
        getActivity().recreate();
    }
}
