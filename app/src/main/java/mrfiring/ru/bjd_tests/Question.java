package mrfiring.ru.bjd_tests;

import java.util.ArrayList;

/**
 * Created by Admin on 29.12.2016.
 */

public class Question{
    private String ques;
    private int rightNum = 0;
    private ArrayList<String> answers;

    public Question() {
        ques = null;
        answers = null;
    }

    public Question(String ques, int rightNum, ArrayList<String> answers) {
        this.ques = ques;
        this.rightNum = rightNum;
        this.answers = answers;
    }

    public Question(String ques) {
        this.ques = ques;
        this.answers = new ArrayList<>();
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public void addAnswer(String answ){
        answers.add(answ);

    }

    public void setRightNum(int rightNum) {
        this.rightNum = rightNum;
    }

    public String getQues() {
        return ques;
    }

    public int getRightNum() {
        return rightNum;
    }
}