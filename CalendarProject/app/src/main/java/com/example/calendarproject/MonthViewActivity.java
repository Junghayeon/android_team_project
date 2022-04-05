package com.example.calendarproject;

import static java.util.Calendar.*;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MonthViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //기능 2. 현재 날짜 받아와서 GridView에 날짜 뿌려주기
        ArrayList<String> daysList = new ArrayList<String>();

        Calendar now = getInstance();
        Calendar cal = getInstance();

        int curYear = now.get(YEAR);
        int curMonth = now.get(MONTH)+1;
        int curDay = now.get(DAY_OF_MONTH);
        int lastDate = now.getActualMaximum(DATE);
        cal.set(curYear, curMonth, 1); //DAY_OF_MONTH를 1로 설정 (월의 첫날)
        int startDay = cal.get(DAY_OF_WEEK); //그 주의 요일 반환 (일:1 ~ 토:7)

        //daysList에 날짜 채워넣기
        for(int i=1; i<=lastDate+startDay; i++) {
            //달의 첫일(1일)의 요일보다 작을 시 공백 채워넣기
            if(i<startDay) {
                daysList.add("");
            }
            else {
                daysList.add(String.valueOf(i-startDay+1));
            }
        }

        //어댑터 준비 (배열 객체 이용, simple_list_item_1 리소스 사용
        ArrayAdapter<String> adapt
                = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                daysList);

        // id를 바탕으로 화면 레이아웃에 정의된 GridView 객체 로딩
        GridView gridview = (GridView) findViewById(R.id.gridview);
        // 어댑터를 GridView 객체에 연결
        gridview.setAdapter(adapt);


        //기능 4. Toast 메세지
        // 항목 선택 이벤트 처리
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(MonthViewActivity.this,
                        curYear+"." +curMonth+ "."+curDay,
                        Toast.LENGTH_SHORT).show();
            }
        });

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



    }
    //기능 3. 이전 다음 버튼으로 다른 월의 달력을 표시
    //현재 액티비티 화면을 바꾸는 부분 && 현재 월 정보, 연도 정보를 전달하는 부분
    public void changeActivity(View view){
        Intent intent = new Intent(getApplicationContext(),
                MonthViewActivity.class);

        switch (view.getId()) {
            case R.id.prev:
                intent.putExtra("monthData",String.valueOf(MONTH-1));
                break;
            case R.id.next:
                intent.putExtra("monthData",String.valueOf(MONTH+1));
                break;
        }
        if (intent != null)
            finish();
            startActivity(intent);
    }


}