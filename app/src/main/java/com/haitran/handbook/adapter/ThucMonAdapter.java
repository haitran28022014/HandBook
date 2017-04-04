package com.haitran.handbook.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haitran.handbook.R;
import com.haitran.handbook.callback.OnClickItemThucMon;
import com.haitran.handbook.holder.ThucMonViewHolder;
import com.haitran.handbook.model.ThucMonModel;

import java.util.ArrayList;

/**
 * Created by Hai Tran on 1/10/2017.
 */

public class ThucMonAdapter extends RecyclerView.Adapter<ThucMonViewHolder> {
    private LayoutInflater inflater;
    private ArrayList<ThucMonModel> arrayList;
    private int index;
    private OnClickItemThucMon onClickItemThucMon;

    public ThucMonAdapter(ArrayList<ThucMonModel> arrayList, Context context, int index) {
        this.arrayList = arrayList;
        inflater = inflater.from(context);
        this.index = index;
    }

    @Override
    public ThucMonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_thaiky_grid, parent, false);
        return new ThucMonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ThucMonViewHolder holder, final int position) {
        holder.setUpHolder(arrayList.get(position), index);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickItemThucMon.onClickThucMon(arrayList.get(position), holder.getImageThaiKy(), holder.getNameWeek());
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void setOnClickItemThucMon(OnClickItemThucMon onClickItemThucMon) {
        this.onClickItemThucMon = onClickItemThucMon;
    }

}
