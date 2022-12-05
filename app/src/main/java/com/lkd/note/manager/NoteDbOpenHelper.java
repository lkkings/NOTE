package com.lkd.note.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.lkd.note.model.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库的封装
 */
public class NoteDbOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "noteSQLite.db";
    private static final String TABLE_NAME_NOTE = "zhuweng";
    private static final String CREATE_TABLE_SQL = "create table " + TABLE_NAME_NOTE + "(id integer primary key autoincrement ,userId text,title text,content text,create_time text)";

    public NoteDbOpenHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_SQL);
        System.out.println("============================");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /**
     * 数据的插入
     */
    public long insertData(Note note) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        String userId = DataManager.getInstance().getCurrentUserPhone();
        values.put("userId",userId);
        values.put("title", note.getTitle());
        values.put("content", note.getContent());
        values.put("create_time", note.getCreatedTime());
        return db.insert(TABLE_NAME_NOTE, null, values);
    }

    /**
     * 数据的删除
     */
    public int deleteFromDbById(String id){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME_NOTE,"id = ?",new String[]{id});
    }

    /**
     * 数据的更新
     */
    public int updateData(Note note){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title",note.getTitle());
        values.put("content",note.getContent());
        values.put("create_time",note.getCreatedTime());
        return db.update(TABLE_NAME_NOTE,values,"id like ?",new String[]{note.getId()});
    }

    /**
     * 数据的查询
     */
    public List<Note> queryAllFromDb() {
        SQLiteDatabase db = getWritableDatabase();
        String phone = DataManager.getInstance().getCurrentUserPhone();
        List<Note> noteList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME_NOTE, null, null, null, null, null, null);
        if (cursor != null) {
            int id1 = cursor.getColumnIndex("id");
            int userId1 = cursor.getColumnIndex("userId");
            int title1 = cursor.getColumnIndex("title");
            int content1 = cursor.getColumnIndex("content");
            int createTime1 = cursor.getColumnIndex("create_time");
            while (cursor.moveToNext()) {
                String id = cursor.getString(id1);
                String userId = cursor.getString(userId1);
                String title = cursor.getString(title1);
                String content = cursor.getString(content1);
                String createTime = cursor.getString(createTime1);

                Note note = new Note();
                if (userId.equals(phone)){
                    note.setId(id);
                    note.setTitle(title);
                    note.setContent(content);
                    note.setCreatedTime(createTime);
                    noteList.add(note);
                }
            }
            cursor.close();
        }
        return noteList;
    }
}
