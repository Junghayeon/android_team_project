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
import java.util.Calendar;

public class MonthViewActivity extends AppCompatActivity {
    //해당 함수에서 공유할 calendar변수 필요
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //기능 2.년/월 달력 표시하는 MonthViewActivity 개발
        //- MonthViewActivity 시작시, 액티비티로 전달된 년도과 월 정보가 있으면,
        //주어진 년도와 월에 해당하는 달력을 만들어서 표시하고,
        //없으면, Calendar 클래스를 활용하여 현재 날짜 정보를 얻어와서, 현재 년도의 현재 월 달력을 표시

        //데이터 원본 준비
        String[] items = {"item1",""};

        //어댑터 준비 (배열 객체 이용, simple_list_item_1 리솟 사용
        ArrayAdapter<String> adapt
                =new ArrayAdapter<String>(
                context: this,
                android.R.layout.simple_list_item_1,
                items);

        //id를 바탕으로 화면 레이아웃에 정의된 GridView 객체 로딩
        GridView gridView = (GridView) findViewById(R.id.myGrid) ;

        //어댑터를 GridView 객체에 연결
        gridView.setAdapter(adapt);

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


        //기능 4. Toast 메세지
        // 항목 클릭 이벤트 처리


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