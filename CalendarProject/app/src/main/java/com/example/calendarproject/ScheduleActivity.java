package com.example.calendarproject;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ScheduleActivity extends AppCompatActivity {

//    final static String TAG="SQLITEDBTEST";
//
//    EditText mId;
//    EditText mMemo;
//    EditText mPlace;
//
//    private DBHelper mDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

//        mId = (EditText)findViewById(R.id.title);
//        mMemo = (EditText)findViewById(R.id.memo);
//        mPlace = (EditText)findViewById(R.id.place);
//
//        mDbHelper = new DBHelper(this);
//
//        Button button = (Button)findViewById(R.id.saveBtn);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                insertRecord();
//                viewAllToTextView();
////                viewAllToListView();
//            }
//        });
//
//        Button button1 = (Button)findViewById(R.id.deleteBtn);
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                deleteRecord();
//                viewAllToTextView();
////                viewAllToListView();
//            }
//        });
//
//        Button button2 = (Button)findViewById(R.id.saveBtn);
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                updateRecord();
//                viewAllToTextView();
////                viewAllToListView();
//            }
//        });
//
////        Button button3 = (Button)findViewById(R.id.veiwall);
////        button3.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                viewAllToTextView();
////                //   viewAllToListView();
////
////            }
////        });
////        viewAllToTextView();

    }

//    private void viewAllToTextView() {
//        TextView result = (TextView)findViewById(R.id.result);
//
//        Cursor cursor = mDbHelper.getAllUsersBySQL();
//
//        StringBuffer buffer = new StringBuffer();
//        while (cursor.moveToNext()) {
//            buffer.append(cursor.getInt(0)+" \t");
//            buffer.append(cursor.getString(1)+" \t");
//            buffer.append(cursor.getString(2)+"\n");
//        }
//        result.setText(buffer);
//        Log.d(TAG, "!!!!!!:"+buffer);
//    }
//
////    private void viewAllToListView() {
////
////        Cursor cursor = mDbHelper.getAllUsersByMethod();
////
////        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(),
////                R.layout.item, cursor, new String[]{
////                UserContract.Users._ID,
////                UserContract.Users.KEY_NAME,
////                UserContract.Users.KEY_PHONE},
////                new int[]{R.id._id, R.id.name, R.id.phone}, 0);
////
////        ListView lv = (ListView)findViewById(R.id.listview);
////        lv.setAdapter(adapter);
////
////        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////            @Override
////            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
////                Adapter adapter = adapterView.getAdapter();
////
////                mId.setText(((Cursor)adapter.getItem(i)).getString(0));
////                mMemo.setText(((Cursor)adapter.getItem(i)).getString(1));
////                mPhone.setText(((Cursor)adapter.getItem(i)).getString(2));
////            }
////        });
////        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
////    }
//
//    private void updateRecord() {
//        TextView _id = (TextView)findViewById(R.id._id);
//        TextView name = (TextView)findViewById(R.id.edit_name);
//        TextView phone = (TextView)findViewById(R.id.edit_phone);
//
//        mDbHelper.updateUserBySQL(_id.getText().toString(),name.getText().toString(),phone.getText().toString());
////        long nOfRows = mDbHelper.updateUserByMethod(_id.getText().toString(),
////                name.getText().toString(),
////                phone.getText().toString());
////        if (nOfRows >0)
////            Toast.makeText(this,"Record Updated", Toast.LENGTH_SHORT).show();
////        else
////            Toast.makeText(this,"No Record Updated", Toast.LENGTH_SHORT).show();
//    }
//
//    private void deleteRecord() {
//        TextView _id = (TextView)findViewById(R.id._id);
//
//        mDbHelper.deleteUserBySQL(_id.getText().toString());
////        long nOfRows = mDbHelper.deleteUserByMethod(_id.getText().toString());
////        if (nOfRows >0)
////            Toast.makeText(this,"Record Deleted", Toast.LENGTH_SHORT).show();
////        else
////            Toast.makeText(this,"No Record Deleted", Toast.LENGTH_SHORT).show();
//    }
//
//    private void insertRecord() {
//        TextView title = (TextView)findViewById(R.id.edit_name);
//        TextView memo = (TextView)findViewById(R.id.edit_phone);
//
//        mDbHelper.insertUserBySQL(title.getText().toString(),memo.getText().toString());
////        long nOfRows = mDbHelper.insertUserByMethod(name.getText().toString(),phone.getText().toString());
////        if (nOfRows >0)
////            Toast.makeText(this,nOfRows+" Record Inserted", Toast.LENGTH_SHORT).show();
////        else
////            Toast.makeText(this,"No Record Inserted", Toast.LENGTH_SHORT).show();
//    }

}