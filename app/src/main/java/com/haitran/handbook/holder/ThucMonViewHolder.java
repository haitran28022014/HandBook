package com.haitran.handbook.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.haitran.handbook.R;
import com.haitran.handbook.model.ThucMonModel;
import com.haitran.handbook.util.Constant;
import com.haitran.handbook.view.TextViewSan;

/**
 * Created by Hai Tran on 3/15/2017.
 */

public class ThucMonViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageThaiKy;
    private TextViewSan nameWeek;
    private TextView dayOfWeek;

    public ThucMonViewHolder(View itemView) {
        super(itemView);
        imageThaiKy = (ImageView) itemView.findViewById(R.id.img_thaiky);
        nameWeek = (TextViewSan) itemView.findViewById(R.id.txt_name_week);
        dayOfWeek = (TextView) itemView.findViewById(R.id.txt_day_of_week);
    }

    public void setUpHolder(ThucMonModel thucMonModel, int position) {
        if (position == 1) {
            Glide.with(imageThaiKy.getContext())
                    .load(Constant.LINK_THUC_PHAM_IMAGE + thucMonModel.getHinhAnh() + Constant.JPG)
                    .override(350, 350)
                    .into(imageThaiKy);
        } else if (position==2){
            Glide.with(imageThaiKy.getContext())
                    .load(Constant.LINK_MON_AN_IMAGE + thucMonModel.getHinhAnh() + Constant.JPG)
                    .override(350, 350)
                    .into(imageThaiKy);
        }else {
            Glide.with(imageThaiKy.getContext())
                    .load(Constant.LINK_NHOM_CHAT + thucMonModel.getHinhAnh() + Constant.JPG)
                    .override(350, 350)
                    .into(imageThaiKy);
        }
        nameWeek.setText(thucMonModel.getTenMonAn());
        dayOfWeek.setText(thucMonModel.getSoLuoc());
    }

    public TextViewSan getNameWeek() {
        return nameWeek;
    }

    public TextView getDayOfWeek() {
        return dayOfWeek;
    }

    public ImageView getImageThaiKy() {
        return imageThaiKy;
    }
}
