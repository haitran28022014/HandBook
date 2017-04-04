package com.haitran.handbook.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by diepnt on 3/2/2017.
 */

public class TextViewSan extends TextView {

    public void setUpView(){
        Typeface tf = Typeface.createFromAsset(this.getContext().getAssets(),"font/RobotoSlab-Regular.ttf");
        this.setTypeface(tf,Typeface.NORMAL);
    }

    public TextViewSan(Context context) {
        super(context);
        setUpView();
    }

    public TextViewSan(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUpView();
    }

    public TextViewSan(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUpView();
    }
}
