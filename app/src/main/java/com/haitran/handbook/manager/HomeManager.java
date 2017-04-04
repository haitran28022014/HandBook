package com.haitran.handbook.manager;

import com.haitran.handbook.util.BaseModel;

import java.util.ArrayList;

/**
 * Created by Hai Tran on 3/20/2017.
 */

public class HomeManager {
    private ArrayList<BaseModel> arrayList;

    public HomeManager() {
        arrayList = new ArrayList<>();
        arrayList.add(new BaseModel("", "foot", "Thai kỳ", ""));
        arrayList.add(new BaseModel("", "foot", "Danh sách thực phẩm", ""));
        arrayList.add(new BaseModel("", "foot", "Danh sách món ăn", ""));
        arrayList.add(new BaseModel("", "foot", "Danh sách nhóm chất", ""));
        arrayList.add(new BaseModel("", "foot", "Đặt tên cho bé", ""));
        arrayList.add(new BaseModel("", "foot", "Truyên", ""));
        arrayList.add(new BaseModel("", "foot", "Nhạc", ""));
    }

    public ArrayList<BaseModel> getArrayList() {
        return arrayList;
    }
}
