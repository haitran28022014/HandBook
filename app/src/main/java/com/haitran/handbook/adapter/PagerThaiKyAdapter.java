package com.haitran.handbook.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Hai Tran on 3/22/2017.
 */

public class PagerThaiKyAdapter extends FragmentPagerAdapter {
    public static final String[] COLUMN_NAME = {"TỔNG QUAN", "THỰC PHẨM", "MÓN ĂN","NHÓM CHẤT"};
    public static final String POSITION = "POSITION";
    private final ArrayList<Fragment> arrayList;

    public PagerThaiKyAdapter(FragmentManager fm, ArrayList<Fragment> arrayList) {
        super(fm);
        this.arrayList = arrayList;
    }

    @Override
    public Fragment getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return COLUMN_NAME[position];
    }
}
