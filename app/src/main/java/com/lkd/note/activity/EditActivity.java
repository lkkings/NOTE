package com.lkd.note.activity;

import static com.lkd.note.utils.StringUtil.getCurrentTimeFormat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import com.lkd.note.R;
import com.lkd.note.manager.NoteDbOpenHelper;
import com.lkd.note.model.Note;
import com.lkd.note.utils.LUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *编辑界面
 */
public class EditActivity extends AppCompatActivity {

    private Note note;
    private int cur = 0;
    private EditText etTitle,etContent;
    private NoteDbOpenHelper mNoteDbOpenHelper;
    private List<Note> noteList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        mNoteDbOpenHelper = new NoteDbOpenHelper(this);
        etTitle = findViewById(R.id.et_title);
        etContent = findViewById(R.id.et_content);
        initData();
    }

    /**
     * 初始化数据
      */
    private void initData() {
        Intent intent = getIntent();
        note = (Note) intent.getSerializableExtra("note");
        if (note != null){
            etTitle.setText(note.getTitle());
            etContent.setText(note.getContent());
            noteList = mNoteDbOpenHelper.queryAllFromDb();
            for (Note no: noteList){
                if (no.getId().equals(note.getId()))
                    break;
                cur ++;
            }
        }

    }

    /**
     * 保存按钮的监听事件，这里通过布局文件进行绑定
     */
    public void save(View view) {
        String title = etTitle.getText().toString();
        String content = etContent.getText().toString();
        if (TextUtils.isEmpty(title)){
            LUtil.showLongToast(this,"标题不得为空");
            return;
        }
        note.setTitle(title);
        note.setContent(content);
        note.setCreatedTime(getCurrentTimeFormat());
        long rowId = mNoteDbOpenHelper.updateData(note);
        if (rowId != -1){
            LUtil.showLongToast(this,"修改成功");
            this.finish();
        }else {
            LUtil.showLongToast(this,"修改失败");
        }
    }
    public void last(View view) {
        if (cur == 0){
            LUtil.showLongToast(this, "已经是第一条了");
            return;
        }
        cur --;
        Note note =  noteList.get(cur);
        if (note != null) {
            etTitle.setText(note.getTitle());
            etContent.setText(note.getContent());
        }
    }
    public void next(View view) {
        if (cur == noteList.size()-1){
            LUtil.showLongToast(this, "已经是第一条了");
            return;
        }
        cur ++;
        Note note =  noteList.get(cur);
        if (note != null) {
            etTitle.setText(note.getTitle());
            etContent.setText(note.getContent());
        }
    }

}