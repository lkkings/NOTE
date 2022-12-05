package com.lkd.note.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lkd.note.R;
import com.lkd.note.manager.DataManager;
import com.lkd.note.manager.NoteDbOpenHelper;
import com.lkd.note.model.User;
import com.lkd.note.utils.LUtil;
import com.lkd.note.utils.StringUtil;

/**
 * 登入界面
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText username, password;
    Button login, register;
    CheckBox isRemember;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        initView();
        initData();
    }

    /**
     * 初始化数据，加载机制的账号密码
     */
    private void initData(){
        User user = DataManager.getInstance().getUserPass();
        if (user != null){
            username.setText(user.getPhone());
            password.setText(user.getPassword());
        }
    }

    /**
     * 实例化组件
     */
    private void initView() {
        username = (EditText) findViewById(R.id.edt_name);
        password = (EditText) findViewById(R.id.edt_psw);
        login = findViewById(R.id.btn_login);
        login.setOnClickListener(this);
        register = findViewById(R.id.btn_register);
        register.setOnClickListener(this);
        isRemember = findViewById(R.id.is_remember);
    }

    private void init() {

    }

    /**
     *这个界面所有按钮的监听事件
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //点击登入按钮触发
            case R.id.btn_login:
                String usernamev = username.getText().toString();
                String passwordv = password.getText().toString();
                if (StringUtil.isEmpty(usernamev) || StringUtil.isEmpty(passwordv)) {
                    LUtil.showLongToast(this,"账号密码不能为空");
                }
                else{
                    User user = DataManager.getInstance().getUser(usernamev);
                    if (user == null){
                        LUtil.showLongToast(this,"账号或密码错误");
                    }
                    else {
                        if(isRemember.isChecked()){
                            DataManager.getInstance().saveUserPass(user);
                        }
                        LUtil.showLongToast(this,"欢迎登入"+user.getNickname());
                        Intent intent = new Intent(this, HomeActivity.class);
                        startActivity(intent);
                    }
                }
                Toast.makeText(LoginActivity.this, "登入失败", Toast.LENGTH_SHORT).show();
                break;
            //点击注册按钮触发
            case R.id.btn_register:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
}
