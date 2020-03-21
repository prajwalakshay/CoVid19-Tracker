package com.shid.covid19.ui.notifications;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.shid.covid19.R;

public class InfoActivity extends AppCompatActivity {
    TextView question, answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        question = findViewById(R.id.question);
        answer = findViewById(R.id.answer);

        {
            String question1 = getIntent().getStringExtra("question");
            question.setText(question1);

            String info = getIntent().getStringExtra("answer");
            answer.setText(info);
        }
    }
}
