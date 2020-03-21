package com.shid.covid19.ui.dashboard;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;


import com.shid.covid19.Model.Countries;
import com.shid.covid19.R;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.MyViewHolder> {

    private List<Countries> countries;
    private Context context;
    private OnItemClickListener onItemClickListener;


    public CountryAdapter( Context context) {
       // this.countries = countries;
        this.context = context;

        this.countries = new ArrayList<>();
        this.countries.addAll(DashboardFragment.countriesList);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holders, int position) {
        final MyViewHolder holder = holders;

        Countries country = countries.get(position);

        holder.recovered.setText("Nombre de personne rétabli: " + country.getRecovered());
        holder.country.setText(country.getCountry());
        holder.today_deaths.setText("Personne(s) décédés aujourd'hui: " + country.getTodayDeaths());
        holder.number_cases.setText("Nombre de cas avérés: " + country.getCases());
        holder.today_cases.setText("Cas avérés aujour'hui: " + country.getTodayCases());
        holder.deaths.setText("Nombre de personne Décédé: " + country.getDeaths());


    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * When data changes, this method updates the list of clipEntries
     * and notifies the adapter to use the new values on it
     */
    public void setClips(List<Countries> mCountries) {
        countries = mCountries;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase();

        //countries.clear();
        //countries = DashboardFragment.countriesList;
        DashboardFragment.countriesList.clear();
        if (charText.length() == 0) {
            DashboardFragment.countriesList.addAll(countries);
           // countries.addAll(DashboardFragment.countriesList);
        } else {
            for (Countries wp : countries) {
                if (wp.getCountry().toLowerCase().contains(charText)) {
                    DashboardFragment.countriesList.add(wp);
                    //countries.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder  {

        TextView country, number_cases, today_cases, deaths, today_deaths, recovered;
        OnItemClickListener onItemClickListener;

        public MyViewHolder(View itemView) {

            super(itemView);


            country = itemView.findViewById(R.id.textView_country);
            number_cases = itemView.findViewById(R.id.number_of_cases);
            today_cases = itemView.findViewById(R.id.today_cases);
            deaths = itemView.findViewById(R.id.number_of_deaths);
            today_deaths = itemView.findViewById(R.id.todays_deaths);
            recovered = itemView.findViewById(R.id.number_of_recovered);


            this.onItemClickListener = onItemClickListener;

        }




    }
}
