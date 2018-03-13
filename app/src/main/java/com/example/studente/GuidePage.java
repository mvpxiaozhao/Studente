package com.example.studente;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.studente.util.GetValue;
import com.example.studente.util.Util;

public class GuidePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_page);

        //获取第几次加载
        GetValue getValue = Util.loadSetting(GuidePage.this,"guideText.txt","number","");
        //获取值
        String number = getValue.getValue1();
        Log.v("number",number);
        if (number.equals("1")){
            Intent intent = new Intent(GuidePage.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }else {
            Util.setStting(GuidePage.this,"guideText.txt","number","1","","");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(GuidePage.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            },3000);
        }


    }
}
