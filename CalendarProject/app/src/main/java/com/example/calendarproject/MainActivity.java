package com.example.calendarproject;

import androidx.appcompat.app.AppCompatActivity;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements MonthFragment.OnDetailSelectListener {
    private static final int MAIN_ACTIVITY_REQUEST_CODE = 0;
    private static final TextView NULL = null;
    ViewPager2 vp; //페이징을 위한 변수
    WeekPagerAdapter WFA;
    MonthPagerAdapter MFA;
    private WeekDBHelper mDbHelper;
    int CurrentPage;
    String selectYMD = null;
    String selectH=null;
    int selectPos = 0;
    boolean menu_month=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new WeekDBHelper(this);
        FloatingActionButton fab = findViewById(R.id.fab);
        Dialog calDialog=new Dialog(MainActivity.this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, DetailCalendarActivity.class);

                if (selectYMD == null)
                    onDetailSelect(0, "0", 0); //앱 시작후 아이템 클릭이 없어 선택 날짜를 모르는 경우
                i.putExtra("cal", selectYMD);//
                i.putExtra("hour", selectH);//
                //월이 걸쳐진부분 오류있음 6월1일인데 5월로 표기
                //calDialog.show(); 다이어로그 실행하는 코드 미완성임
                //출처: https://jhshjs.tistory.com/59 [독학하는 1인 개발자:티스토리]
                startActivityForResult(i, MAIN_ACTIVITY_REQUEST_CODE);
            }
        });

        calDialog.setContentView(R.layout.cal_dialog);

        vp = findViewById(R.id.vpPager); //xml파일의 vpPager가져오기
        //일단 처음 시작할때 월간달력이 보이도록 설정해두었음
        setMonthPager(vp);//액티비티 시작시 weeKFragmentAdater와 연결함(아래 함수있음)
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("kuku", "sss: " + selectPos);
        // 결과를 반환하는 액티비티가 FIRST_ACTIVITY_REQUEST_CODE 요청코드로 시작된 경우가 아니거나
        // 결과 데이터가 빈 경우라면, 메소드 수행을 바로 반환함.
        if (requestCode != MAIN_ACTIVITY_REQUEST_CODE || data == null)
            return;

        //월간인지 주간인지
        if (menu_month) {
            GridView g = findViewById(R.id.MONTH_monthgrid);
            if (data.getStringExtra("CalTitle") != null) {
                String result = data.getStringExtra("CalTitle");
                Log.d("kuku", "onActivityResult: " + result);
                //TextView child = (TextView) g.getChildAt(selectPos);
                LinearLayout childL = (LinearLayout) g.getChildAt(selectPos);
                TextView childC = childL.findViewById(R.id.month_item_cal1);
                if(childC!=null)  //가끔 프로그램 상의 문제로 getChildAt오류가 있는데 막기 위한것
                    childC.setText(result);
            } else {
//                TextView child = (TextView) g.getChildAt(selectPos);
//                if(child!=null)
//                    child.setText(selectPos);
            }
        }else{
            GridView g = findViewById(R.id.WEEK_daygrid);
            if (data.getStringExtra("CalTitle") != null) {
                String result = data.getStringExtra("CalTitle");
                Log.d("kuku", "onActivityResult: " + result);
                TextView child = (TextView) g.getChildAt(selectPos);
                if(child!=null)  //가끔 프로그램 상의 문제로 getChildAt오류가 있는데 막기 위한것
                    child.setText(result);
            } else {
                TextView child = (TextView) g.getChildAt(selectPos);
                if(child!=null)
                    child.setText(" ");
            }
        }
    }

    //메뉴바를 동적 추가하는 부분
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //메뉴바의 아이템이 클릭되었을때-이 부분에서 월별/주별 달력 전환함
    public boolean onOptionsItemSelected(MenuItem item) {
        //클릭한 메뉴 item id를 읽어옴
        switch (item.getItemId()) {
            //02. 월간 달력 프레그먼트
            case R.id.action_monthActivity:
                setMonthPager(vp);
                return true;

            //03. 주간 달력 프레그먼트
            case R.id.action_weekActivity:
                setWeekPager(vp);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //월간 페이지 어댑터 설정 함수
    private void setMonthPager(ViewPager2 vp) {
        ViewPager2 monthPager = vp;
        //출처: https://itpangpang.tistory.com/266 [ITPangPang]
        MFA = new MonthPagerAdapter(this);
        monthPager.setAdapter(MFA);

        monthPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                //메뉴바의 글자를 바꾸는 부분(연도-월 표시 변경)
                MainActivity.this.getSupportActionBar().setTitle(MFA.toString(position));
                //출처: 액션바 텍스트 바꾸기 https://m.blog.naver.com/dhdnjswnd/221665442594
            }
        });

        TextView blank = this.findViewById(R.id.dates_text);

        LinearLayout.LayoutParams pp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
        blank.setLayoutParams(pp);
        monthPager.setCurrentItem(MFA.ItemCenter);
        menu_month = true;
    }

    //주간 페이지 어댑터 설정 함수
    private void setWeekPager(ViewPager2 vp) {
        ViewPager2 weekPager = vp;
        WFA = new WeekPagerAdapter(this);
        weekPager.setAdapter(WFA);
        //페이지 변경 이벤트 리스너
        weekPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                //메뉴바의 글자를 바꾸는 부분(연도-월 표시 변경)
                MainActivity.this.getSupportActionBar().setTitle(WFA.toString(position));
                //출처: 액션바 텍스트 바꾸기 https://m.blog.naver.com/dhdnjswnd/221665442594


            }
        });
        TextView blank = this.findViewById(R.id.dates_text);
        LinearLayout.LayoutParams pp = new LinearLayout.LayoutParams(getResources().getDimensionPixelSize(R.dimen.size_50dp), LinearLayout.LayoutParams.WRAP_CONTENT);
        blank.setLayoutParams(pp);
        //https://realjune.tistory.com/11 dimen의 dp값을 px값으로 간단하게 변환해주는 코드
        //https://ckbcorp.tistory.com/1197 setWidth가 안될때 param으로 동적 변환
        weekPager.setCurrentItem(WFA.ItemCenter); //중간 페이지에서 시작
        Log.d("kuku", "setWeekPager: "+WFA.ItemCenter+ " "+weekPager.getCurrentItem());
        menu_month = false;
    }

    @Override
    public void onDetailSelect(int position, String day, int time) { //연월일시를 선택에 따라 갱신하는 부분
        Log.d("kuku", "sss: " + selectPos);
        if (menu_month) {
            selectYMD = MFA.toString(vp.getCurrentItem()) + day + "일 " ;
        } else {
            selectYMD = WFA.weekC.getYMD(vp.getCurrentItem(), position);//.toString(vp.getCurrentItem())+day+"일 "+time+"시";
        }
        selectPos =position;
        selectH=time+"시";
    }
    public void showDialog(){

    }
};