package com.example.studente;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studente.util.GetValue;
import com.example.studente.util.UsrDateBase;
import com.example.studente.util.Util;


public class LoginActivity extends AppCompatActivity {

    EditText editText_username, editText_userpwd;
    TextView textView_tile;
    CheckBox checkBox_r;
    LinearLayout linearLayout_net_setting;
    private Button regiest_user;
    private EditText regiestUser;
    private Button regiestAction;
    private Button cancelAction;
    private EditText regiestFtpwd;
    private EditText regiestSdpwd;
    private UsrDateBase usrDateBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //数据库操作
        usrDateBase = new UsrDateBase(LoginActivity.this);
        usrDateBase.openDB("Student",1);
        initView();
    }
    private void initView() {
        linearLayout_net_setting = (LinearLayout) findViewById(R.id.net_setting);
        editText_username = (EditText) findViewById(R.id.editText_username);
        editText_userpwd = (EditText) findViewById(R.id.editText_userpwd);
        regiest_user = (Button)findViewById(R.id.textView3);
        textView_tile = (TextView) findViewById(R.id.textView_title);
        textView_tile.setText("登录页面");
        checkBox_r = (CheckBox) findViewById(R.id.checkBox);
        GetValue value = Util.loadSetting(LoginActivity.this,"login.txt","name","pwd");
        editText_username.setText(value.getValue1());
        editText_userpwd.setText(value.getValue2());

        Button button_ok = (Button) findViewById(R.id.button_ok);
        button_ok.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View view) {
                String login_name = editText_username.getText().toString().trim();
                String login_pwd = editText_userpwd.getText().toString().trim();

                boolean flag = usrDateBase.query(login_name,login_pwd);

                if (flag){
                    if (checkBox_r.isChecked()){
                        Util.setStting(LoginActivity.this,"login.txt","name",login_name,"pwd",login_pwd);
                    }
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("user_name",login_name);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this,"用户名或错误",Toast.LENGTH_LONG).show();
                }
            }
        });

        regiest_user.setOnClickListener(new View.OnClickListener() {

            private String pwd2;
            private String pwd1;
            private String name;

            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(LoginActivity.this);
                dialog.setTitle("注册界面");
                dialog.getWindow().setContentView(R.layout.regiest_layout);
                //设置不可点击别的地方取消界面
                dialog.setCancelable(false);
                dialog.show();
                //获取控件,记得加上 dialog.getWindow().
                regiestFtpwd = (EditText) dialog.getWindow().findViewById(R.id.regiest_ftpwd);
                regiestSdpwd = (EditText) dialog.getWindow().findViewById(R.id.regiest_sdpwd);
                regiestUser = (EditText)dialog.getWindow(). findViewById(R.id.regiest_user);
                regiestAction = (Button) dialog.getWindow().findViewById(R.id.regiest_action);
                cancelAction = (Button)dialog.getWindow(). findViewById(R.id.cancel_action);

                regiestAction.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        //这三个放到点击里面
                        name = regiestUser.getText().toString().trim();
                        pwd1 = regiestFtpwd.getText().toString().trim();
                        pwd2 = regiestSdpwd.getText().toString().trim();
                        if (pwd2.equals(pwd1)){
                            //创建数据库
                            long result = usrDateBase.insert_user(name, pwd1);
                            if (result>0){
                                Toast.makeText(LoginActivity.this,"注册成功",Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                            }else {
                                Toast.makeText(LoginActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(LoginActivity.this,"两次输入的密码不正确",Toast.LENGTH_LONG).show();
                        }
                    }
                });
                cancelAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

            }
        });

    }
}
