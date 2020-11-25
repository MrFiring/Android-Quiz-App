package mrfiring.ru.bjd_tests;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static mrfiring.ru.bjd_tests.TestManager.mgr;

public class MainActivity extends AppCompatActivity {

    EditText edNumber;
    Button btnStart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edNumber = (EditText)findViewById(R.id.edNumber);
        btnStart = (Button)findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onStartClick();
            }
        });

    }

    private void onStartClick(){
            if(Integer.valueOf(edNumber.getText().toString()) > 60) {
                edNumber.setTextColor(Color.RED);
                return;
            }
            else{ edNumber.setTextColor(Color.BLACK);}




            mgr = new TestManager(this, Integer.valueOf(edNumber.getText().toString()));

    }

}
