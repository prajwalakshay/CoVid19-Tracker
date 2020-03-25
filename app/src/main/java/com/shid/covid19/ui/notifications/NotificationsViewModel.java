package com.shid.covid19.ui.notifications;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.shid.covid19.R;

import java.util.ArrayList;
import java.util.List;

public class NotificationsViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;
    List<Information> informationList = new ArrayList<>();



    public NotificationsViewModel(@NonNull Application application) {
        super(application);
        Information information1 = new Information(getApplication().getString(R.string.question1),
                getApplication().getString(R.string.answer1));
        Information information2 = new Information(getApplication().getString(R.string.question2),
                getApplication().getString(R.string.answer2));
        Information information3 = new Information(getApplication().getString(R.string.question3),
                getApplication().getString(R.string.answer3));
        Information information4 = new Information(getApplication().getString(R.string.question4),
                getApplication().getString(R.string.answer4));
        Information information5 = new Information(getApplication().getString(R.string.question5),
                getApplication().getString(R.string.answer5));
        Information information6 = new Information(getApplication().getString(R.string.question6),
                getApplication().getString(R.string.answer6));
        Information information7 = new Information(getApplication().getString(R.string.question7),
                getApplication().getString(R.string.answer7));
        Information information8 = new Information(getApplication().getString(R.string.question8),
                getApplication().getString(R.string.answer8));
        Information information9 = new Information(getApplication().getString(R.string.question9),
                getApplication().getString(R.string.answer9));
        Information information10 = new Information(getApplication().getString(R.string.question10),
                getApplication().getString(R.string.answer10));
        Information information11 = new Information(getApplication().getString(R.string.question11),
                getApplication().getString(R.string.answer11));
        Information information12 = new Information(getApplication().getString(R.string.question12),
                getApplication().getString(R.string.answer12));
        /*
        Information information13 = new Information(getApplication().getString(R.string.question1),
                getApplication().getString(R.string.answer1));
        Information information14 = new Information(getApplication().getString(R.string.question1),
                getApplication().getString(R.string.answer1));

         */

        informationList.add(information1);
        informationList.add(information2);
        informationList.add(information3);
        informationList.add(information4);
        informationList.add(information5);
        informationList.add(information6);
        informationList.add(information7);
        informationList.add(information8);
        informationList.add(information9);
        informationList.add(information10);
        informationList.add(information11);
        informationList.add(information12);
        /*
        informationList.add(information13);
        informationList.add(information14);

         */
    }

    public List<Information>getInformationList(){
        return informationList;
    }

    public LiveData<String> getText() {
        return mText;
    }
}