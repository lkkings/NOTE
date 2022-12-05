package com.lkd.note.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lkd.note.R;
import com.lkd.note.manager.DataManager;
import com.lkd.note.model.User;
import com.lkd.note.utils.LUtil;
import com.lkd.note.utils.StringUtil;

/**
 * 注册界面
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    EditText username,password,phone;
    Button login;
    Button register;
    RadioGroup six;
    int sixv = -1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        initView();
    }
    private void init(){

    }

    /**
     * 实例化控件
     */
    private void initView() {
        username = findViewById(R.id.et_name);
        password = findViewById(R.id.et_password);
        phone = findViewById(R.id.et_phone);
        login = findViewById(R.id.btn_login);
        login.setOnClickListener(this);
        register = findViewById(R.id.btn_register);
        register.setOnClickListener(this);
        six = findViewById(R.id.rg_sex);
        six.setOnCheckedChangeListener(this);
    }

    /**
     * 该页面按钮的监听事件
     */
    @Override
    public void onClick(View view) {
       switch (view.getId()){
           //注册按钮触发
           case R.id.btn_register:
               String usernamev = username.getText().toString();
               String passwordv = password.getText().toString();
               String phonev = phone.getText().toString();
               if(StringUtil.isEmpty(usernamev) || StringUtil.isEmpty(passwordv) || StringUtil.isEmpty(phonev) || sixv == -1)
                   LUtil.showLongToast(this,"请完善信息");
               else {
                   User user = new User(usernamev,phonev,passwordv,sixv);
                   DataManager.getInstance().saveCurrentUser(user);
                   LUtil.showLongToast(this,"注册成功");
                   Intent intent = new Intent(this,HomeActivity.class);
                   startActivity(intent);
               }
               break;
           //登入按钮触发
           case R.id.btn_login:
               Intent intent = new Intent(this,LoginActivity.class);
               startActivity(intent);
               break;
       }
    }
    //单选按钮的监听事件，当选择状态发生改变时触发
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if(i == R.id.rb_man)
            sixv = 0;
        else
            sixv = 1;
    }
}
