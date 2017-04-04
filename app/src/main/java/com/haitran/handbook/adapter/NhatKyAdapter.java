package com.haitran.handbook.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haitran.handbook.R;
import com.haitran.handbook.holder.NhatKyViewHolder;
import com.haitran.handbook.model.NhatKyModel;

import io.realm.RealmResults;

/**
 * Created by diepnt on 2/16/2017.
 */

public class NhatKyAdapter extends RecyclerView.Adapter<NhatKyViewHolder> {
    private RealmResults<NhatKyModel> arrayList;
    private LayoutInflater inflater;
    private Context context;
    private OnClickItemNhatKy onClickItemNhatKy;

    public NhatKyAdapter(RealmResults<NhatKyModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        inflater = inflater.from(context);
    }

    @Override
    public NhatKyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_nhat_ky, parent, false);
        return new NhatKyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NhatKyViewHolder holder, final int position) {
        holder.setUpViewHolder(arrayList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItemNhatKy.onClickDetailEdit(arrayList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void setOnClickItemNhatKy(OnClickItemNhatKy onClickItemNhatKy) {
        this.onClickItemNhatKy = onClickItemNhatKy;
    }

    public interface OnClickItemNhatKy {
        void onClickDetailEdit(NhatKyModel nhatKyModel);
    }

}
