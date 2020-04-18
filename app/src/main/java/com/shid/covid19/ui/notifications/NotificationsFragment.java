package com.shid.covid19.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.orhanobut.dialogplus.ViewHolder;
import com.shid.covid19.R;
import com.shid.covid19.ui.SymptomActivity;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {


    NotificationsViewModel notificationsViewModel;
    Button btn_symptoms;
    CardView cardView, cardView2, cardView3, cardView4, cardView5, cardView6, cardView7, cardView8, cardView9, cardView10;
    DialogPlus dialogPlus;
    LinearLayout linearLayout;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);
        // ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.activity_information, container, false);
        setUI(root);
        btn_symptoms = root.findViewById(R.id.consultButton);
        btn_symptoms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SymptomActivity.class);
                startActivity(intent);
            }
        });


        clickEvents();


        return root;
    }

    private void clickEvents() {
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getString(R.string.question1), getString(R.string.answer1));
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getString(R.string.question3), getString(R.string.answer3));
            }
        });

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getString(R.string.question10), getString(R.string.answer10));
            }
        });

        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getString(R.string.q1), getString(R.string.a1));
            }
        });

        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getString(R.string.q2), getString(R.string.a2));
            }
        });

        cardView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getString(R.string.q3), getString(R.string.a3));
            }
        });

        cardView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getString(R.string.q4), getString(R.string.a4));
            }
        });

        cardView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getString(R.string.q5), getString(R.string.a5));
            }
        });

        cardView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getString(R.string.q6), getString(R.string.a6));
            }
        });

        cardView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getString(R.string.q7), getString(R.string.a7));
            }
        });
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        openDialogWash(getString(R.string.wash));
            }
        });
    }

    private void setUI(View root) {
        linearLayout = root.findViewById(R.id.washView);
        cardView = root.findViewById(R.id.cardQ1);
        cardView2 = root.findViewById(R.id.cardQ2);
        cardView3 = root.findViewById(R.id.cardQ3);
        cardView4 = root.findViewById(R.id.cardQ4);
        cardView5 = root.findViewById(R.id.cardQ5);
        cardView6 = root.findViewById(R.id.cardQ6);
        cardView7 = root.findViewById(R.id.cardQ7);
        cardView8 = root.findViewById(R.id.cardQ8);
        cardView9 = root.findViewById(R.id.cardQ9);
        cardView10 = root.findViewById(R.id.cardQ10);
    }

    private void openDialogWash(String header){
        dialogPlus = DialogPlus.newDialog(getContext())
                .setContentHolder(new ViewHolder(R.layout.layout_dialog_wash))
                .setHeader(R.layout.layout_header)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(DialogPlus dialog, Object item, View view, int position) {
                    }
                })
                .setExpanded(true)  // This will enable the expand feature, (similar to android L share dialog)
                .setGravity(Gravity.CENTER)
                .setCancelable(true)
                .create();

        TextView title = (TextView)dialogPlus.findViewById(R.id.title_question);
        title.setText(header);
        dialogPlus.show();
    }

    private void openDialog(String title, String answer) {


        dialogPlus = DialogPlus.newDialog(getContext())
                .setContentHolder(new ViewHolder(R.layout.layout_dialog))
                .setHeader(R.layout.layout_header)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(DialogPlus dialog, Object item, View view, int position) {
                    }
                })
                .setExpanded(true)  // This will enable the expand feature, (similar to android L share dialog)
                .setGravity(Gravity.CENTER)
                .setCancelable(true)
                .create();
        TextView title_question = (TextView) dialogPlus.findViewById(R.id.title_question);
        TextView answer_question = (TextView) dialogPlus.findViewById(R.id.answer);

        title_question.setText(title);
        answer_question.setText(answer);

        dialogPlus.show();
    }


}
