package com.example.studente;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    EditText editText_username, editText_userpwd;
    TextView textView_tile;
    CheckBox checkBox_r;
    LinearLayout linearLayout_net_setting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }
    private void initView() {
        linearLayout_net_setting = (LinearLayout) findViewById(R.id.net_setting);
        editText_username = (EditText) findViewById(R.id.editText_username);
        editText_userpwd = (EditText) findViewById(R.id.editText_userpwd);
        textView_tile = (TextView) findViewById(R.id.textView_title);
        textView_tile.setText("登录页面");
        checkBox_r = (CheckBox) findViewById(R.id.checkBox);

        Button button_ok = (Button) findViewById(R.id.button_ok);
        button_ok.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View view) {
                System.out.println("button Click OK");
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }
}
