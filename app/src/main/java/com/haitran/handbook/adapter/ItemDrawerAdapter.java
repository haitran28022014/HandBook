package com.haitran.handbook.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haitran.handbook.R;
import com.haitran.handbook.holder.ItemDrawerViewHolder;
import com.haitran.handbook.util.BaseModel;

import java.util.ArrayList;

/**
 * Created by Hai Tran on 1/10/2017.
 */

public class ItemDrawerAdapter extends RecyclerView.Adapter<ItemDrawerViewHolder> {
    private LayoutInflater inflater;
    private ArrayList<BaseModel> arrayList;

    public void setOnClickItemDrawer(OnClickItemDrawer onClickItemDrawer) {
        this.onClickItemDrawer = onClickItemDrawer;
    }

    private OnClickItemDrawer onClickItemDrawer;

    public ItemDrawerAdapter(ArrayList<BaseModel> arrayList, Context context) {
        this.arrayList = arrayList;
        inflater = inflater.from(context);
    }

    @Override
    public ItemDrawerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_home_drawer, parent, false);
        return new ItemDrawerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemDrawerViewHolder holder, final int position) {
        holder.setUpHolder(arrayList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickItemDrawer.onClickItemDrawer(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public interface OnClickItemDrawer{
        void onClickItemDrawer(int position);
    }
}
