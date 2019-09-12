package ru.otus.cources.model;

public class ExamData {

    private final String question;
    private final String answer;

    public ExamData(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
