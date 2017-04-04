package com.haitran.handbook.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haitran.handbook.R;
import com.haitran.handbook.model.NoteModel;

public class NoteView extends LinearLayout {
    public void setOnClickClose(OnClickClose onClickClose) {
        this.onClickClose = onClickClose;
    }

    private OnClickClose onClickClose;
    private TextView txtClose;
    private TextView txtName;
    private TextView txtContent;
    private View view;

    public NoteView(Context context, NoteModel noteModel) {
        super(context);
        view = LayoutInflater.from(context).inflate(R.layout.view_show_note, this);
        txtClose = (TextView) findViewById(R.id.txt_close);
        txtName = (TextView) findViewById(R.id.txt_name);
        txtContent = (TextView) findViewById(R.id.txt_content);
        txtName.setText(noteModel.getName());
        txtContent.setText(noteModel.getContent());
        txtClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickClose.onClose();
            }
        });
    }

    public interface OnClickClose {
        void onClose();
    }
}
