package com.example.demo.model;

public class Operation {
    private String question;
    private double answer;

    public Operation(String question, double answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public double getAnswer() {
        return answer;
    }
}

