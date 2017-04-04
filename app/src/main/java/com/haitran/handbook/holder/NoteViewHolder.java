package com.haitran.handbook.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.haitran.handbook.R;
import com.haitran.handbook.model.NoteModel;

/**
 * Created by diepnt on 2/16/2017.
 */

public class NoteViewHolder extends RecyclerView.ViewHolder {
    private TextView txtDay;
    private TextView txtName;
    private TextView txtContent;
    private ImageView imgClock;
    private Context context;

    public NoteViewHolder(View itemView) {
        super(itemView);
        initViews(itemView);
        this.setIsRecyclable(false);
    }

    private void initViews(View itemView) {
        context = itemView.getContext();
        imgClock = (ImageView) itemView.findViewById(R.id.img_clock);
        txtName = (TextView) itemView.findViewById(R.id.txt_name);
        txtDay = (TextView) itemView.findViewById(R.id.txt_date);
        txtContent = (TextView) itemView.findViewById(R.id.txt_content);
    }

    public void setUpViewHolder(NoteModel noteModel) {
        boolean isImage = noteModel.isNoteOrAlarm();
        if (isImage) {
            imgClock.setImageResource(R.drawable.ic_alarm);
        } else {
            imgClock.setImageResource(R.drawable.ic_note);
        }
        txtName.setText(noteModel.getName());
        txtDay.setText(noteModel.getDays()+"   "+noteModel.getTimer());
        txtContent.setText(noteModel.getContent());
    }

}
