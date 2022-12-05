package com.lkd.note.activity;

import static com.lkd.note.utils.StringUtil.getCurrentTimeFormat;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import com.lkd.note.R;
import com.lkd.note.manager.DataManager;
import com.lkd.note.manager.NoteDbOpenHelper;
import com.lkd.note.model.Note;
import com.lkd.note.utils.LUtil;
import com.lkd.note.utils.StringUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 添加界面
 */
public class addActivity extends AppCompatActivity {

     private EditText etTitle,etContent;
     private NoteDbOpenHelper mNoteDbOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        inittools();
    }

    /**
     * 初始化数据库的封装工具
     */
    private void inittools() {
        etContent = findViewById(R.id.et_content);
        etTitle = findViewById(R.id.et_title);
        mNoteDbOpenHelper = new NoteDbOpenHelper(this);
    }

    /**
     * 添加按钮设置的监听事件，在这里直接通过布局文件进行绑定
     * @param view
     */
    public void add(View view) {
        String title = etTitle.getText().toString();
        String content = etContent.getText().toString();
        if (TextUtils.isEmpty(title)){
            LUtil.showLongToast(this,"标题不可为空");
            return;
        }

         Note note = new Note();
         note.setTitle(title);
         note.setContent(content);
         note.setCreatedTime(getCurrentTimeFormat());
         long row = mNoteDbOpenHelper.insertData(note);
         if (row != -1){
             LUtil.showLongToast(this,"添加成功");
             this.finish();
         }else {
             LUtil.showLongToast(this,"添加失败");
         }
    }
}