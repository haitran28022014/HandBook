package com.haitran.handbook.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Hai Tran on 2/19/2017.
 */

public class SharePreManager {
    private static final String KEY_SAVE_LAST_PERIOD = "KEY_SAVE_LAST_PERIOD";
    private static final String KEY_SAVE_DUE_DATE = "KEY_SAVE_DUE_DATE";
    public static SharePreManager instance;
    public Context context;

    public SharePreManager(Context context) {
        this.context = context;
    }

    public static SharePreManager getInstance(Context context) {
        if (instance == null) {
            instance = new SharePreManager(context);
        }
        return instance;
    }

    public void putDatePeriod(String value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_SAVE_LAST_PERIOD, value);
        editor.commit();
    }

    public String getDatePeriod() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String name = sharedPreferences.getString(KEY_SAVE_LAST_PERIOD, "null");
        return name;
    }


    public void putDueDate(String value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_SAVE_DUE_DATE, value);
        editor.apply();
    }

    public String getDueDate() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String name = sharedPreferences.getString(KEY_SAVE_DUE_DATE, "null");
        return name;
    }

}
