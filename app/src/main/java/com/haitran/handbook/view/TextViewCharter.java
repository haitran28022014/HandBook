package com.haitran.handbook.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by diepnt on 3/2/2017.
 */

public class TextViewCharter extends TextView {

    public void setUpView(){
        Typeface tf = Typeface.createFromAsset(this.getContext().getAssets(),"font/RobotoSlab-Light.ttf");
        this.setTypeface(tf,Typeface.NORMAL);
    }

    public TextViewCharter(Context context) {
        super(context);
        setUpView();
    }

    public TextViewCharter(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUpView();
    }

    public TextViewCharter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUpView();
    }
}
