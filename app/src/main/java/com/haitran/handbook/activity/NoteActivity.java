package com.haitran.handbook.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.haitran.handbook.R;
import com.haitran.handbook.adapter.NoteAdapter;
import com.haitran.handbook.manager.LoadDataManager;
import com.haitran.handbook.model.NoteModel;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.RealmResults;

/**
 * Created by Hai Tran on 3/22/2017.
 */

public class NoteActivity extends AppCompatActivity implements View.OnClickListener, NoteAdapter.OnClickItemNoteClock {
    public static final int KEY_CODE_DETAIL_EDIT = 200;
    private static final int KEY_CODE_REQUEST = 201;
    public static final String KEY_ID_DETAIL_ADD_EDIT = "KEY_ID_DETAIL_ADD_EDIT";
    @Bind(R.id.recycler_view_main)
    RecyclerView recyclerView;

    @Bind(R.id.floating_button)
    FloatingActionButton floatingActionButton;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.linear_normal)
    LinearLayout linearLayout;


    private RealmResults<NoteModel> arrayList;
    private NoteAdapter noteAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        ButterKnife.bind(this);
        initViews();
        initListener();
    }

    private void initListener() {
        floatingActionButton.setOnClickListener(this);
        noteAdapter.setOnClickItemAlarmClock(this);
    }

    private void initViews() {
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_arrow_back));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Ghi chú");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        arrayList = LoadDataManager.getInstance(this).getNoteModel();
        hideLinear(arrayList.size());
        noteAdapter = new NoteAdapter(arrayList, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(noteAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.floating_button:
                nextClass(KEY_ID_DETAIL_ADD_EDIT, System.currentTimeMillis(), KEY_CODE_REQUEST, "add");
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == KEY_CODE_DETAIL_EDIT) {
            if (resultCode == RESULT_OK) {
                updateList();
            }
        } else {
            if (resultCode == RESULT_OK) {
                updateList();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void updateList() {
        arrayList = LoadDataManager.getInstance(this).getNoteModel();
        noteAdapter.notifyDataSetChanged();
        hideLinear(arrayList.size());
    }

    public void hideLinear(int size) {
        if (size == 0) {
            linearLayout.setVisibility(View.VISIBLE);
        } else {
            linearLayout.setVisibility(View.GONE);
        }
    }

    public void nextClass(String keyIdDetail, long idValue, int keyForResult, String ofWho) {
        Intent intent = new Intent();
        intent.setClass(NoteActivity.this, EditAddNoteActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(keyIdDetail, idValue + "/" + ofWho);
        intent.putExtras(bundle);
        startActivityForResult(intent, keyForResult);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateList();
    }

    @Override
    public void onClickDetailEdit(NoteModel noteModel) {
        nextClass(KEY_ID_DETAIL_ADD_EDIT, noteModel.getId(), KEY_CODE_DETAIL_EDIT, "edit");
    }
}
