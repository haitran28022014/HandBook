package com.haitran.handbook.callback;

import android.widget.ImageView;
import android.widget.TextView;

import com.haitran.handbook.model.ThucMonModel;

/**
 * Created by Hai Tran on 3/16/2017.
 */

public interface OnClickItemThucMon {
    void onClickThucMon(ThucMonModel thucMonModel, ImageView imageView, TextView textView);
}
