package com.example.calendarproject;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class WeekDBHelper extends SQLiteOpenHelper {
    final static String TAG = "SQLiteDBTest";

    public WeekDBHelper(Context context) {
        super(context, WeekContract.DB_NAME, null, WeekContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, getClass().getName() + ".onCreate()");
        db.execSQL(WeekContract.Weeks.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.i(TAG, getClass().getName() + ".onUpgrade()");
        db.execSQL(WeekContract.Weeks.DELETE_TABLE);
        onCreate(db);
    }
    public void insertCalBySQL(String title, String start, String end, String address, String memo, String cal, String hour){
        try {
            String sql = String.format(
                    "INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s, %s) VALUES (NULL, '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                    WeekContract.Weeks.TABLE_NAME,
                    WeekContract.Weeks._ID,
                    WeekContract.Weeks.KEY_TITLE,
                    WeekContract.Weeks.KEY_START,
                    WeekContract.Weeks.KEY_END,
                    WeekContract.Weeks.KEY_ADDRESS,
                    WeekContract.Weeks.KEY_MEMO,
                    WeekContract.Weeks.KEY_CAL,
                    WeekContract.Weeks.KEY_HOUR,
                    title,start,end,address,memo,cal,hour);

            getWritableDatabase().execSQL(sql);
            Log.d("kuku", "insertUserBySQL: ");
        }catch (SQLException e) {
            Log.e(TAG,"Error in inserting recodes");
            Log.d("kuku", "insertUserBySQL: x");
        }
    }
    public Cursor getAllCalsBySQL() {
        String sql = "Select * FROM " + WeekContract.Weeks.TABLE_NAME;
        return getReadableDatabase().rawQuery(sql,null);
    }
    public Cursor getCalBySQL(String info) {
        String sql = String.format("Select * FROM %s WHERE %s = '%s'",
                WeekContract.Weeks.TABLE_NAME,
                WeekContract.Weeks.KEY_CAL,info);
        return getReadableDatabase().rawQuery(sql,null);
    }
    public Cursor getCalHourBySQL(String info,String hour) { //그 날의 세부일정 받아올 때
        String sql = String.format("Select * FROM %s WHERE %s = '%s' AND %s = '%s'",
                WeekContract.Weeks.TABLE_NAME,
                WeekContract.Weeks.KEY_CAL,info,
                WeekContract.Weeks.KEY_HOUR,hour);
        return getReadableDatabase().rawQuery(sql,null);
    }
    public void deleteCalBySQL(String info,String hour) {
        String sql = String.format (
                "DELETE FROM %s WHERE %s = '%s' AND %s = '%s'",
                WeekContract.Weeks.TABLE_NAME,
                WeekContract.Weeks.KEY_CAL, info,
                WeekContract.Weeks.KEY_HOUR,hour);
        getWritableDatabase().execSQL(sql);
    }
    public void updateCalBySQL(String title, String start, String end, String address, String memo, String info,String hour){
        try {
            String sql = String.format(
                    "UPDATE  %s SET %s = '%s',%s = '%s',%s = '%s',%s = '%s',%s = '%s' WHERE %s = '%s' AND %s = '%s'",
                    WeekContract.Weeks.TABLE_NAME,
                    WeekContract.Weeks.KEY_TITLE,title,
                    WeekContract.Weeks.KEY_START,start,
                    WeekContract.Weeks.KEY_END,end,
                    WeekContract.Weeks.KEY_ADDRESS,address,
                    WeekContract.Weeks.KEY_MEMO,memo,
                    WeekContract.Weeks.KEY_CAL,info,
                    WeekContract.Weeks.KEY_HOUR,hour);

            getWritableDatabase().execSQL(sql);
            Log.d(TAG, "updateCalBySQL: ");
        }catch (SQLException e) {
            Log.e(TAG,"Error in inserting recodes");
        }
    }
}
