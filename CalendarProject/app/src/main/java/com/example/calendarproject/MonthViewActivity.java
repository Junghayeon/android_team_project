package com.example.calendarproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class MonthViewActivity extends AppCompatActivity {
    //해당 함수에서 공유할 calendar변수 필요
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //기능 3. 이전 다음 버튼으로 다른 월의 달력을 표시
        //받은 인텐트가 있으면 그 내용으로 하고, 없으면 현재 날짜로 만듬
        Intent get = getIntent();
        if(get.getStringExtra("monthData")!=null){
            int m=Integer.parseInt(get.getStringExtra("monthData"));
            int y=Integer.parseInt(get.getStringExtra("yearData"));
                //전달 받은 날짜로 캘린더 객체 만들기
        } else {
            //현재 날짜로 캘린더 객체 만들기
        }

        TextView title = findViewById(R.id.title);
        //버튼 사이에 있는 tetview내용을 년도,월로 바꿔야한다


        //기능 4. 토스트 메세지

    }
    //기능 3. 이전 다음 버튼으로 다른 월의 달력을 표시
    //현재 액티비티 화면을 바꾸는 부분 && 현재 월 정보, 연도 정보를 전달하는 부분
    public void changeActivity(View view){
        Intent intent = new Intent(getApplicationContext(),
                MonthViewActivity.class);

        switch (view.getId()) {
            case R.id.prev:
                intent.putExtra("monthData",String.valueOf(calendar.month-1));
                break;
            case R.id.next:
                intent.putExtra("monthData",String.valueOf(calendar.month+1));
                break;
        }
        if (intent != null)
            finish();
            startActivity(intent);
    }
}