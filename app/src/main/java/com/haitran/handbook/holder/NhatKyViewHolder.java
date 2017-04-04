package com.haitran.handbook.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.haitran.handbook.R;
import com.haitran.handbook.model.NhatKyModel;

/**
 * Created by diepnt on 2/16/2017.
 */

public class NhatKyViewHolder extends RecyclerView.ViewHolder {
    private TextView txtDay;
    private TextView txtContent;

    public NhatKyViewHolder(View itemView) {
        super(itemView);
        initViews(itemView);
        this.setIsRecyclable(false);
    }

    private void initViews(View itemView) {
        txtDay = (TextView) itemView.findViewById(R.id.txt_date);
        txtContent = (TextView) itemView.findViewById(R.id.txt_content);
    }

    public void setUpViewHolder(NhatKyModel nhatKyModel) {
        txtDay.setText(nhatKyModel.getDate());
        txtContent.setText(nhatKyModel.getContent());
    }

}
