package com.example.calendarproject;

import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;
import static java.util.Calendar.getInstance;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.Calendar;

public class MonthPagerAdapter extends FragmentStateAdapter {
    private static int NUM_ITEMS=10; //기본 갯수
    int ItemCenter=5;
    Calendar now= getInstance();
    int y = now.get(YEAR);
    int m = now.get(MONTH);
//    MonthFragment2 weekC;

    public MonthPagerAdapter(MonthViewActivity fa) {
        super(fa);
//        weekC=new WeekCalendar(ItemCenter);
//        fa.getSupportActionBar().setTitle(toString(ItemCenter)); //처음 시작화면의 메뉴바 텍스트 설정
    }

    // 각 페이지를 나타내는 프래그먼트 반환
    @Override
    public Fragment createFragment(int position) {
//        weekC.makeCalendarData(position);
        m=m+(position-ItemCenter);
        MonthFragment2 fragment=new MonthFragment2(y,m);
        return fragment;
    }

    @Override
    public int getItemCount() { //프레그먼트 갯수를 정수의 최댓값만큼-사실상 3개가 재사용되는것 //이 값에 따라 갯수가 결정된다
        return 10;
    }
    //public int getRealPosition(int position) { return position; } //실제아이템포지션
    public String toString(int position){
        return toString(position);
    }
}
