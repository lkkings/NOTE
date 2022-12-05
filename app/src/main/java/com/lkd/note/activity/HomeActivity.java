package com.lkd.note.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lkd.note.R;
import com.lkd.note.adapter.MyAdapter;
import com.lkd.note.manager.DataManager;
import com.lkd.note.manager.NoteDbOpenHelper;
import com.lkd.note.model.Note;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 主页面
 */
public class HomeActivity extends AppCompatActivity{
    private RecyclerView mRecyclerView;
    private FloatingActionButton mBtnAdd;
    private List<Note> mNotes;
    private MyAdapter mMyAdapter;
    private NoteDbOpenHelper mNoteDbOpenHelper;

    public HomeActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();
        initData();
        initEvent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshDataFromDB();
    }

    /**
     * 更新列表的数据
     */
    private void refreshDataFromDB() {
        mNotes = getDataFromDB();
        mMyAdapter.refreshData(mNotes);
    }

    /**
     * 给列表设置适配器
     */
    private void initEvent() {
        mMyAdapter = new MyAdapter(this,mNotes);
        mRecyclerView.setAdapter(mMyAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    /**
     * 初始化列表数据
     */
    private void initData() {
        mNotes = new ArrayList<>();
        mNoteDbOpenHelper = new NoteDbOpenHelper(this);
    }

    /**
     * 重数据库中查询所有的事
     * @return
     */
    private List<Note> getDataFromDB() {
        return mNoteDbOpenHelper.queryAllFromDb();
    }

    /**
     * 初始化列表的容器
     */
    private void initView() {
        mRecyclerView = findViewById(R.id.rlv);
    }

    /**
     * 悬浮按钮的监听事件，这里通过布局文件绑定
     */
    public void add(View view) {
        Intent intent = new Intent(this,addActivity.class);
        startActivity(intent);
    }

}