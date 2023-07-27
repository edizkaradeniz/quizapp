package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ZorResultActivity extends AppCompatActivity {

    DataHelper dataHelper;
    TextView olanpuan,eniyi,tv;
    Button anasayf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zor_result);

        dataHelper = new DataHelper(this);

        olanpuan = (TextView) findViewById(R.id.yapilanpuan);
        eniyi = (TextView) findViewById(R.id.eniyiskor);
        tv = (TextView) findViewById(R.id.tv_user);
        anasayf = (Button) findViewById(R.id.home);

        int puan = dataHelper.receiveDataInt("PUAN Zor",0);
        int eni = dataHelper.receiveDataInt("En İyi Zor",0);

        olanpuan = (TextView) findViewById(R.id.yapilanpuan);
        eniyi = (TextView) findViewById(R.id.eniyiskor);
        tv.setText("Bir dahaki sefer için iyi şanslar, "+dataHelper.receiveDataString("İsim","Kullanıcı"));
        olanpuan.setText(""+puan);

        if (puan>eni){
            eni = puan;
            dataHelper.saveDataInt("En iyi Zor",eni);

        }
        eniyi.setText(""+eni);
        anasayf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ZorResultActivity.this,MainActivity.class));
                finish();
            }
        });

    }
}

