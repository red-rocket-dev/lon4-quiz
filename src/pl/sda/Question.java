package pl.sda;

public class Question {
    private String text;
    private String[] answers;
    private int correctAnswerIndex;

    public Question() {
        this.answers = new String[4];
    }

    public String getText() {
        return text;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCorrectAnswerIndex(int correctAnswerIndex) {
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }
}
