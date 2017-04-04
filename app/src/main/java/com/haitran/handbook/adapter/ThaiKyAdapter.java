package com.haitran.handbook.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haitran.handbook.R;
import com.haitran.handbook.callback.OnClickItemThaiKy;
import com.haitran.handbook.holder.ThaiKyViewHolder;
import com.haitran.handbook.util.BaseModel;

import java.util.ArrayList;

/**
 * Created by Hai Tran on 1/10/2017.
 */

public class ThaiKyAdapter extends RecyclerView.Adapter<ThaiKyViewHolder> {
    private LayoutInflater inflater;
    private ArrayList<BaseModel> arrayList;

    public void setOnClickItemThaiKy(OnClickItemThaiKy onClickItemThaiKy) {
        this.onClickItemThaiKy = onClickItemThaiKy;
    }

    private OnClickItemThaiKy onClickItemThaiKy;

    public ThaiKyAdapter(ArrayList<BaseModel> arrayList, Context context) {
        this.arrayList = arrayList;
        inflater = inflater.from(context);
    }

    @Override
    public ThaiKyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_list, parent, false);
        return new ThaiKyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ThaiKyViewHolder holder, final int position) {
        holder.setUpHolder(arrayList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickItemThaiKy.onClickItem(arrayList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

}
