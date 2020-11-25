package mrfiring.ru.bjd_tests;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Tests_Activity extends AppCompatActivity {
    private int selectedIndex = -1;

    private  Button btnAnswer, btnNext, btnAns1, btnAns2, btnAns3, btnAns4, btnAns5;
    private TextView tvQuestion;
    private int  stdColor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests_);

        Intent data = getIntent();
        String queStr = data.getStringExtra("Question");
        ArrayList<String> ans = data.getStringArrayListExtra("Answers");


        boolean isSuperscriptQuestion = false;
        if(queStr.equals("Прийнятний рівень ризику у світовій практиці становить:"))
        {
            isSuperscriptQuestion = true;
            ans.clear();
            ans.add("1/1000");
            ans.add("10");//^6
            ans.add("10");//^-4
            ans.add("10");//^-6
            ans.add("1/100");


        }


        boolean isSmallScreen = false;
        Display dp = getWindowManager().getDefaultDisplay();
        int width = dp.getWidth();
        int height = dp.getHeight();
        if(width <= 480 || height <= 800 )
            isSmallScreen = true;

        tvQuestion = (TextView)findViewById(R.id.tvQuestion);
        tvQuestion.setText(queStr);


        btnAnswer = (Button)findViewById(R.id.btnAnswer);

        btnAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBtnAnswerClick();
            }
        });

        btnAns1 = (Button)findViewById(R.id.btnAns1);
        btnAns1.setText(Html.fromHtml("<html>"+ans.get(0) +"</html>"));
        if(isSmallScreen)
            btnAns1.setTextSize(10.f);
        stdColor = btnAns1.getCurrentTextColor();
        btnAns1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    onAns1Click();
                colorControl();
            }
        });
        btnAns2 = (Button)findViewById(R.id.btnAns2);
        if(isSuperscriptQuestion)
            btnAns2.setText(Html.fromHtml("<html>" + ans.get(1) + "<sup>6</sup>" + "</html>"));
        else
            btnAns2.setText(Html.fromHtml("<html>"+ans.get(1) +"</html>"));
        if(isSmallScreen)
            btnAns2.setTextSize(10.f);
        btnAns2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAns2Click();
                colorControl();
            }
        });
        btnAns3 = (Button)findViewById(R.id.btnAns3);
        if(isSuperscriptQuestion)
            btnAns3.setText(Html.fromHtml("<html>"+ans.get(2) +  "<sup>-4</sup>" + "</html>"));
        else
            btnAns3.setText(Html.fromHtml("<html>"+ans.get(2) + "</html>"));

        if(isSmallScreen)
            btnAns3.setTextSize(10.f);
        btnAns3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAns3Click();
                colorControl();
            }
        });
        btnAns4 = (Button)findViewById(R.id.btnAns4);
        if(isSmallScreen)
            btnAns4.setTextSize(10.f);
        if(ans.size() >= 4) {

            if(isSuperscriptQuestion)
                btnAns4.setText(Html.fromHtml("<html>" + ans.get(3) + "<sup>-6</sup>" + "</html>"));
            else
                btnAns4.setText(Html.fromHtml("<html>" + ans.get(3) + "</html>"));
            btnAns4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    onAns4Click();
                    colorControl();
                }
            });
        }
        else
        btnAns4.setVisibility(Button.GONE);


        btnAns5 = (Button)findViewById(R.id.btnAns5);
        if(isSmallScreen)
            btnAns5.setTextSize(10.f);
        if(ans.size() == 5) {
            btnAns5.setVisibility(Button.VISIBLE);
            btnAns5.setText(Html.fromHtml("<html>"+ans.get(4) +"</html>"));
            btnAns5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onAns5Click();
                    colorControl();
                }
            });
        }








    }

    @Override
    public void onBackPressed() {
        //do nothing
    }

    private void colorControl(){
            switch(selectedIndex){
                case 1:
                    btnAns1.setTextColor(getResources().getColor(R.color.colorPrimary));
                    btnAns2.setTextColor(stdColor);
                    btnAns3.setTextColor(stdColor);
                    btnAns4.setTextColor(stdColor);
                    btnAns5.setTextColor(stdColor);
                    break;
                case 2:
                    btnAns2.setTextColor(getResources().getColor(R.color.colorPrimary));
                    btnAns1.setTextColor(stdColor);
                    btnAns3.setTextColor(stdColor);
                    btnAns4.setTextColor(stdColor);
                    btnAns5.setTextColor(stdColor);
                    break;
                case 3:
                    btnAns3.setTextColor(getResources().getColor(R.color.colorPrimary));
                    btnAns2.setTextColor(stdColor);
                    btnAns1.setTextColor(stdColor);
                    btnAns4.setTextColor(stdColor);
                    btnAns5.setTextColor(stdColor);
                    break;
                case 4:
                    btnAns4.setTextColor(getResources().getColor(R.color.colorPrimary));
                    btnAns2.setTextColor(stdColor);
                    btnAns3.setTextColor(stdColor);
                    btnAns1.setTextColor(stdColor);
                    btnAns5.setTextColor(stdColor);
                    break;
                case 5:
                    btnAns5.setTextColor(getResources().getColor(R.color.colorPrimary));
                    btnAns2.setTextColor(stdColor);
                    btnAns3.setTextColor(stdColor);
                    btnAns1.setTextColor(stdColor);
                    btnAns4.setTextColor(stdColor);
                    break;
                default:
                    break;

            }

    }



    private void onBtnAnswerClick(){
            if(selectedIndex == -1)
                return;

            if(selectedIndex == getIntent().getIntExtra("RightNum", -1))
            {
                TestManager.mgr.setRightAnswers(TestManager.mgr.getRightAnswers() + 1);

               NextActivity();
            }
        else
                NextActivity();

    }

    private void NextActivity(){
        if(TestManager.mgr.getCurQuestion()+1 == TestManager.mgr.getRandQuestions().size())
        {
            Intent in = new Intent(this, ResultActivity.class);
            startActivity(in);
        }
        else {
            Intent in = new Intent(this, Tests_Activity.class);
            TestManager.mgr.setCurQuestion(TestManager.mgr.getCurQuestion()+1);
            Question q = TestManager.mgr.getRandQuestions().get(TestManager.mgr.getCurQuestion());

            in.putExtra("Question", q.getQues());
            in.putExtra("RightNum", q.getRightNum());
            in.putStringArrayListExtra("Answers", q.getAnswers());

            startActivity(in);


        }

    }


    private void onAns1Click(){selectedIndex = 1;}
    private void onAns2Click(){ selectedIndex = 2;}
    private void onAns3Click(){ selectedIndex = 3;}
    private void onAns4Click(){ selectedIndex = 4;}
    private void onAns5Click(){selectedIndex = 5;}
}
