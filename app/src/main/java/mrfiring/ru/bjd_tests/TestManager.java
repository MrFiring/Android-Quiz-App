package mrfiring.ru.bjd_tests;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * Created by MrFiring on 29.12.2016.
 */



public class TestManager {
    private int questionsCount = 0;
    private ArrayList<Question> questionMap;
    private ArrayList<Question> randQuestions;
    private int rightAnswers = 0;
    private int curQuestion = 0;

    public static TestManager mgr;



    public TestManager(MainActivity main, int questionsCount){
            this.questionMap = get_questions_from_resource(main);
            this.questionsCount = questionsCount;


            start_test(main, get_random_questions());
    }

    public ArrayList<Question> getRandQuestions() {
        return randQuestions;

    }

    public void setRightAnswers(int rightAnswers) {
        this.rightAnswers = rightAnswers;
    }

    public int getRightAnswers() {
        return rightAnswers;
    }

    public int getCurQuestion() {
        return curQuestion;
    }

    public void setCurQuestion(int curQuestion) {
        this.curQuestion = curQuestion;
    }

    private  void start_test(MainActivity main, ArrayList<Question> q){

            this.randQuestions = q;
            Intent intent = new Intent(main, Tests_Activity.class);
            Question question = q.get(curQuestion);

            intent.putExtra("Question", question.getQues());
            intent.putExtra("RightNum", question.getRightNum());
             intent.putStringArrayListExtra("Answers", question.getAnswers());

           // this.setCurQuestion(1);
            main.startActivity(intent);


    }

    private ArrayList<Question> get_random_questions(){
        ArrayList<Question> qList = new ArrayList<>();
        Random rand = new Random();
        ArrayList<Integer> tempRands = new ArrayList<>();

        for(int i = 0; i < questionsCount; i++)
        {

            int j = rand.nextInt(this.questionMap.size());
            if(!tempRands.contains(j))
            {
                tempRands.add(j);
                qList.add(questionMap.get(j));
            }
            else {
                while (tempRands.contains(j))
                    j = rand.nextInt(this.questionMap.size());
                tempRands.add(j);
                qList.add(questionMap.get(j));
            }
        }

        return qList;
    }
    private ArrayList<Question> get_questions_from_resource(Context context){
        ArrayList<Question> q = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.raw.data)));


            String text = "";
            String buf = "";
            while((buf = br.readLine()) != null)
                text += buf;
            br.close();

            String[] blocks = text.split(Pattern.quote("#"));

            for(int i = 0; i < blocks.length; i++)
            {
                    String[] sBlock = blocks[i].split(Pattern.quote("@|@"));
                    Question question = new Question(sBlock[0]) ;
                    if(sBlock.length >= 4)
                    for(int j = 1; j < sBlock.length; j++){
                        String ans = sBlock[j];
                        if(ans.startsWith("+")) {
                            ans = ans.substring(1);
                            question.setRightNum(j);
                        }

                        question.addAnswer(ans);

                    }
                q.add(question);

            }



            Log.d("TAGG", "CONTAINS:" + questionMap.size());

        }catch(Exception ex){
                Log.getStackTraceString(ex);
        }

        return  q;
    }

}
