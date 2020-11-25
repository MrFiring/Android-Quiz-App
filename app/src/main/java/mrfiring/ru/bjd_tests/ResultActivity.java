package mrfiring.ru.bjd_tests;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class ResultActivity extends AppCompatActivity {

    TextView tvRight, tvInvalid, tvResult;
    Button toGeneral;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvRight = (TextView)findViewById(R.id.tvRight);
        tvInvalid = (TextView)findViewById(R.id.tvInvalid);
        tvResult = (TextView)findViewById(R.id.tvResult);

        tvRight.setText(Html.fromHtml("<html> " + this.getResources().getString(R.string.rigts) + "<b>" + String.valueOf(TestManager.mgr.getRightAnswers()) +"</html>"));
        tvInvalid.setText(Html.fromHtml("<html> " + this.getResources().getString(R.string.invalids) + "<b>" + String.valueOf(TestManager.mgr.getRandQuestions().size() - TestManager.mgr.getRightAnswers()) +"</html>"));
        float res = ((100*TestManager.mgr.getRightAnswers())/ (TestManager.mgr.getRandQuestions().size())* 0.05f);
        String formattedDouble = new DecimalFormat("#0.00").format(res);
        tvResult.setText(formattedDouble);


        toGeneral = (Button)findViewById(R.id.btnToGeneral);
        toGeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBtnToGeneralClick();
            }
        });



    }

    @Override
    public void onBackPressed() {

    }

    private void onBtnToGeneralClick(){
        Intent  i = new Intent(this, MainActivity.class);
        startActivity(i);

    }

}
