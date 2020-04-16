package com.shid.covid19.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.shid.covid19.R;
import com.shid.covid19.ui.SymptomActivity;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {


    NotificationsViewModel notificationsViewModel;
    Button btn_symptoms, btn_info;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);
        // ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.activity_information, container, false);
        btn_symptoms = root.findViewById(R.id.consultButton);
        btn_symptoms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SymptomActivity.class);
                startActivity(intent);
            }
        });
        btn_info = root.findViewById(R.id.consultButton2);
        btn_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),InfoActivity.class);
                startActivity(intent);
            }
        });


        return root;
    }

    private void backPress(){

    }


}
