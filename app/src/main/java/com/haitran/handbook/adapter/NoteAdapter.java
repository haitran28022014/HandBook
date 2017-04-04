package com.haitran.handbook.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haitran.handbook.R;
import com.haitran.handbook.holder.NoteViewHolder;
import com.haitran.handbook.model.NoteModel;

import io.realm.RealmResults;

/**
 * Created by diepnt on 2/16/2017.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {
    private RealmResults<NoteModel> arrayList;
    private LayoutInflater inflater;
    private Context context;
    private OnClickItemNoteClock onClickItemNoteClock;

    public NoteAdapter(RealmResults<NoteModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        inflater = inflater.from(context);
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, final int position) {
        holder.setUpViewHolder(arrayList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItemNoteClock.onClickDetailEdit(arrayList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void setOnClickItemAlarmClock(OnClickItemNoteClock onClickItemNoteClock) {
        this.onClickItemNoteClock = onClickItemNoteClock;
    }

    public interface OnClickItemNoteClock {
        void onClickDetailEdit(NoteModel noteModel);
    }

}
