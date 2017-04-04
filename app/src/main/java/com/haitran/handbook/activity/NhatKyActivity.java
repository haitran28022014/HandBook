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
import com.haitran.handbook.adapter.NhatKyAdapter;
import com.haitran.handbook.manager.LoadDataNhatKyManager;
import com.haitran.handbook.model.NhatKyModel;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.RealmResults;

/**
 * Created by Hai Tran on 3/24/2017.
 */

public class NhatKyActivity extends AppCompatActivity implements View.OnClickListener, NhatKyAdapter.OnClickItemNhatKy {
    public static final int KEY_CODE_REQUEST_NHAT_KY = 300;
    public static final int KEY_CODE_DETAIL_EDIT_NHAT_KY = 301;
    public static final String KEY_ID_DETAIL_ADD_EDIT_NHAT_KY = "KEY_ID_DETAIL_ADD_EDIT_NHAT_KY";
    @Bind(R.id.recycler_view_main)
    RecyclerView recyclerView;

    @Bind(R.id.floating_button)
    FloatingActionButton floatingActionButton;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.linear_normal)
    LinearLayout linearLayout;


    private RealmResults<NhatKyModel> arrayList;
    private NhatKyAdapter nhatKyAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhat_ky);
        ButterKnife.bind(this);
        initViews();
        initListener();
    }

    private void initListener() {
        floatingActionButton.setOnClickListener(this);
        nhatKyAdapter.setOnClickItemNhatKy(this);
    }

    private void initViews() {
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_arrow_back));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Nhật ký");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        arrayList = LoadDataNhatKyManager.getInstance(this).getNhatKyModel();
        hideLinear(arrayList.size());
        nhatKyAdapter = new NhatKyAdapter(arrayList, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(nhatKyAdapter);
    }

    public void hideLinear(int size) {
        if (size == 0) {
            linearLayout.setVisibility(View.VISIBLE);
        } else {
            linearLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.floating_button:
                nextClass(KEY_ID_DETAIL_ADD_EDIT_NHAT_KY, System.currentTimeMillis(), KEY_CODE_REQUEST_NHAT_KY, "add");
                break;
        }
    }

    public void nextClass(String keyIdDetail, long idValue, int keyForResult, String ofWho) {
        Intent intent = new Intent();
        intent.setClass(NhatKyActivity.this, EditAddNhatKyActivity.class);
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

    public void updateList() {
        arrayList = LoadDataNhatKyManager.getInstance(this).getNhatKyModel();
        nhatKyAdapter.notifyDataSetChanged();
        hideLinear(arrayList.size());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == KEY_CODE_DETAIL_EDIT_NHAT_KY) {
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

    @Override
    public void onClickDetailEdit(NhatKyModel nhatKyModel) {
        nextClass(KEY_ID_DETAIL_ADD_EDIT_NHAT_KY, nhatKyModel.getId(), KEY_CODE_DETAIL_EDIT_NHAT_KY, "edit");
    }
}
