package com.haitran.handbook.manager;

import android.content.Context;

import com.haitran.handbook.model.ThucMonModel;

import java.util.ArrayList;

/**
 * Created by Hai Tran on 3/15/2017.
 */

public class SaveListManager {
    public ArrayList<ThucMonModel> getThucPhamModels() {
        return thucPhamModels;
    }

    public void setThucPhamModels(ArrayList<ThucMonModel> thucPhamModels) {
        this.thucPhamModels = thucPhamModels;
    }

    public ArrayList<ThucMonModel> getMonAnModels() {
        return monAnModels;
    }

    public void setMonAnModels(ArrayList<ThucMonModel> monAnModels) {
        this.monAnModels = monAnModels;
    }

    public ArrayList<ThucMonModel> getNhomChatModels() {
        return nhomChatModels;
    }

    public void setNhomChatModels(ArrayList<ThucMonModel> nhomChatModels) {
        this.nhomChatModels = nhomChatModels;
    }

    public ArrayList<ThucMonModel> thucPhamModels;
    public ArrayList<ThucMonModel> monAnModels;
    public ArrayList<ThucMonModel> nhomChatModels;
    public static SaveListManager instance;
    public Context context;

    public SaveListManager(Context context) {
        this.context = context;
        thucPhamModels = new ArrayList<>();
        monAnModels = new ArrayList<>();
        nhomChatModels = new ArrayList<>();
    }

    public void clearAllList() {
        thucPhamModels.clear();
        monAnModels.clear();
        nhomChatModels.clear();
    }

    public static SaveListManager getInstance(Context context) {
        if (instance == null) {
            instance = new SaveListManager(context);
        }
        return instance;
    }


}
