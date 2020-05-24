package pl.sda;

public class QuestionBase {
    private Question[] questions;
    private int howManyQuestionSaved;

    public QuestionBase() {
        this.questions = new Question[100];
    }

    public int getQuestionCount() {
        return howManyQuestionSaved;
    }

    public void addQuestion(Question question) {
        questions[howManyQuestionSaved] = question;
        howManyQuestionSaved++;
    }

    public Question getQuestion(int chosenQuestion) {
        return questions[chosenQuestion];
    }
}
