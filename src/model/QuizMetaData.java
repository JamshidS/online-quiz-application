package src.model;

import java.util.ArrayList;
import java.util.List;

public class QuizMetaData {
    private List<String> options = new ArrayList<>();
    private boolean correct;
    private String quiz;
    // String will change to Quiz (after Quiz class implemention done)

    // New variables will be defined based on relationships with other classes...

    public QuizMetaData() {
    }

    public QuizMetaData(List<String> options, boolean correct, String quiz) {
        this.options = options;
        this.correct = correct;
        this.quiz = quiz;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public String getQuiz() {
        return quiz;
    }

    public void setQuiz(String quiz) {
        this.quiz = quiz;
    }
}
