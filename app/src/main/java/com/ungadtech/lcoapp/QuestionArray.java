package com.ungadtech.lcoapp;

public class QuestionArray {
    private Question[] questions;

    public Question[] getQuestions ()
    {
        return questions;
    }

    public void setQuestions (Question[] questions)
    {
        this.questions = questions;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [questions = "+questions+"]";
    }
}
