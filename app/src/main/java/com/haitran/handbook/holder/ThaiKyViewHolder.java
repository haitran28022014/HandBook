package com.haitran.handbook.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.haitran.handbook.R;
import com.haitran.handbook.util.BaseModel;
import com.haitran.handbook.util.Constant;
import com.haitran.handbook.view.TextViewCharter;
import com.haitran.handbook.view.TextViewSan;

/**
 * Created by Hai Tran on 3/15/2017.
 */

public class ThaiKyViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageThaiKy;
    private TextViewSan nameWeek;
    private TextView dayOfWeek;

    public ThaiKyViewHolder(View itemView) {
        super(itemView);
        imageThaiKy = (ImageView) itemView.findViewById(R.id.img_thaiky);
        nameWeek = (TextViewSan) itemView.findViewById(R.id.txt_name_week);
        dayOfWeek = (TextViewCharter) itemView.findViewById(R.id.txt_day_of_week);
    }

    public void setUpHolder(BaseModel baseModel) {
        Glide.with(itemView.getContext())
                .load(Constant.PATH_ASSETS + baseModel.getImageName() + Constant.JPG)
                .override(400, 300)
                .into(imageThaiKy);
        nameWeek.setText(baseModel.getTitle());
        dayOfWeek.setText(baseModel.getDetail());
    }

}
