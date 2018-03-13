package com.example.studente.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/3/9.
 */

public class UsrDateBase {
    private Context context;
    private String tableSql;
    private String db_name;
    private SQLiteDatabase sqLiteDatabase;
    private MySQLiteOpenHelper mySQLiteOpenHelper;

    public UsrDateBase(Context context) {
        this.context = context;
    }

    class MySQLiteOpenHelper extends SQLiteOpenHelper {

        public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String table_sql = "create table user("+"name varchar ,"+" pwd varchar"+")";
            sqLiteDatabase.execSQL(table_sql);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("drop table if exists user");
            onCreate(sqLiteDatabase);
        }
    }

    public void openDB(String db_name, int version) {
        mySQLiteOpenHelper = new MySQLiteOpenHelper(context, db_name, null, version);
        sqLiteDatabase = mySQLiteOpenHelper.getReadableDatabase();
    }

    public long insert_user(String name, String pwd) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("pwd", pwd);
        long result = sqLiteDatabase.insert("user", null, contentValues);
        return result;

    }

    public int update_pwd(String name1, String old_pwd, String new_pwd) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("pwd", new_pwd);
        int result = sqLiteDatabase.update("user", contentValues, "name = ? and pwd = ?", new String[]{name1, old_pwd});
        sqLiteDatabase.close();
        return result;
    }

    public boolean query(String name, String pwd) {
        Cursor cursor = sqLiteDatabase.query("user", null, "name=? and pwd=?", new String[]{name,pwd}, null, null, null);
        if (cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }
}
