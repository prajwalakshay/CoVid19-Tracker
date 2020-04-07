package com.shid.covid19.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.shid.covid19.R;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    GridLayout mainGrid;
    Information information;
    String question;
    String answer;


    private List<Information> list = new ArrayList<>();
//    private String[] questions = getResources().getStringArray(R.array.questions);

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);
               // ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        list = notificationsViewModel.getInformationList();

        mainGrid = root.findViewById(R.id.mainGrid);

        //Set Event
        setSingleEvent(mainGrid);
        return root;
    }




    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {

                //You can see , all child item is CardView , so we just cast object to CardView


                final int finalI = i;
            Log.d("TAG" ,"value of i : "+ i);


            CardView cardView = (CardView) mainGrid.getChildAt(i);
            int finalI1 = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                            information = list.get(finalI1);
                    question = information.getQuestion();
                    answer = information.getAnswer();
                    Intent intent = new Intent(getContext(), InfoActivity.class);
                    intent.putExtra("question", question);
                    intent.putExtra("answer", answer);
                    startActivity(intent);

                }
            });
        }
    }
}
