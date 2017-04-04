package com.haitran.handbook.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.haitran.handbook.R;
import com.haitran.handbook.util.BaseModel;
import com.haitran.handbook.util.HelpUtil;
import com.haitran.handbook.view.TextViewSan;

/**
 * Created by Hai Tran on 3/15/2017.
 */

public class ItemDrawerViewHolder extends RecyclerView.ViewHolder {
    private ImageView imgIcon;
    private TextViewSan name;

    public ItemDrawerViewHolder(View itemView) {
        super(itemView);
        imgIcon = (ImageView) itemView.findViewById(R.id.img_icon);
        name = (TextViewSan) itemView.findViewById(R.id.txt_name);
    }

    public void setUpHolder(BaseModel baseModel) {
        Glide.with(itemView.getContext())
                .load(HelpUtil.getImageId(baseModel.getImageName(), itemView.getContext()))
                .override(60,60).into(imgIcon);
        name.setText(baseModel.getTitle());
    }

}
