package com.ungadtech.lcoapp;

public class Question {
    private String Answer;

    private String question;

    public String getAnswer ()
    {
        return Answer;
    }

    public void setAnswer (String Answer)
    {
        this.Answer = Answer;
    }

    public String getQuestion ()
    {
        return question;
    }

    public void setQuestion (String question)
    {
        this.question = question;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Answer = "+Answer+", question = "+question+"]";
    }
}
