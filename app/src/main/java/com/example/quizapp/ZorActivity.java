package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ZorActivity extends AppCompatActivity {

    DataHelper dataHelper;
    TextView sorularr,puann,oyun_adi,gecsayi;
    ImageButton dogrux,yanlisx,homex;

    RelativeLayout gec;
    int gecc;

    Random r = new Random();
    int n;
    int points=0;
    int SKIP_NUMBER=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zor);

        dataHelper = new DataHelper(this);
        gecsayi= (TextView) findViewById(R.id.gecsayi);
        sorularr= (TextView) findViewById(R.id.sorular);
        oyun_adi= (TextView) findViewById(R.id.oyun_adi);
        puann= (TextView) findViewById(R.id.puan);
        dogrux = (ImageButton) findViewById(R.id.dogrux);
        yanlisx= (ImageButton) findViewById(R.id.yanlisx);
        homex= (ImageButton) findViewById(R.id.anasayfayagit);
        gec = (RelativeLayout) findViewById(R.id.gec);

        homex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ZorActivity.this,MainActivity.class));
                finish();
            }
        });

        gecsayi.setText(""+dataHelper.receiveDataInt("Geç",SKIP_NUMBER));
        oyun_adi.setText(dataHelper.receiveDataString("İsim","Kullanıcı"));

        final String[] arrayA = {getString(R.string.z1),getString(R.string.z2),getString(R.string.z3),getString(R.string.z4),getString(R.string.z5),
                getString(R.string.z6),getString(R.string.z7),getString(R.string.z8),getString(R.string.z9),getString(R.string.z10)};

        final Boolean[] arrayB = {false,true,true,false,true,true,true,false,true,false};

        final ArrayList<String> sorular= new ArrayList<String>(Arrays.asList(arrayA));
        final ArrayList<Boolean>cevaplar = new ArrayList<Boolean>(Arrays.asList(arrayB));

        n = r.nextInt(sorular.size());
        sorularr.setText(sorular.get(n));

        gec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gecsayi.setText(""+dataHelper.receiveDataInt("Geç",SKIP_NUMBER));
                gecc = dataHelper.receiveDataInt("Geç",SKIP_NUMBER);
                if (dataHelper.receiveDataInt("Geç",SKIP_NUMBER)==0){
                    Toast.makeText(ZorActivity.this,"0 geçme hakkınız var",Toast.LENGTH_SHORT).show();
                }else{
                    gecc--;
                    sorular.remove(n);
                    cevaplar.remove(n);
                    if (sorular.size()==0){
                        result();
                    }else{
                        n = r.nextInt(sorular.size());
                        sorularr.setText(sorular.get(n));
                        dataHelper.saveDataInt("Geç",gecc);
                    }
                }
            }
        });

        dogrux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cevaplar.get(n)) {
                    points++;
                    sorular.remove(n);
                    cevaplar.remove(n);
                    puann.setText("Skor:"+points);
                    if (sorular.size()==0){
                        result();
                    }else{
                        n=r.nextInt(sorular.size());
                        sorularr.setText(sorular.get(n));
                    }
                }else{
                    result();
                }
            }
        });

        yanlisx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!cevaplar.get(n)) {
                    points++;
                    sorular.remove(n);
                    cevaplar.remove(n);
                    puann.setText("Skor:"+points);
                    if (sorular.size()==0){
                        result();
                    }else{
                        n=r.nextInt(sorular.size());
                        sorularr.setText(sorular.get(n));
                    }
                }else{
                    result();
                }
            }
        });
    }

    private void result() {
        dataHelper.saveDataInt("PUAN Zor",points);
        startActivity(new Intent(ZorActivity.this,ZorResultActivity.class));
        finish();
    }
}
