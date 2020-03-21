package com.shid.covid19.ui.notifications;

public class Information {

    private String question;
    private String answer;

    public Information(String question, String answer) {
        this.question = question;

        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }


}
