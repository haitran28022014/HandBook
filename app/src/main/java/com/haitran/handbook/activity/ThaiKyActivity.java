package com.haitran.handbook.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.haitran.handbook.R;
import com.haitran.handbook.adapter.ThaiKyAdapter;
import com.haitran.handbook.callback.OnClickItemThaiKy;
import com.haitran.handbook.manager.DatabaseManager;
import com.haitran.handbook.manager.SaveListManager;
import com.haitran.handbook.util.BaseModel;
import com.haitran.handbook.util.Constant;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Hai Tran on 3/14/2017.
 */

public class ThaiKyActivity extends AppCompatActivity implements OnClickItemThaiKy {
    public static final String KEY_THAI_KY_MODEL = "KEY_THAI_KY_MODEL";

    @Bind(R.id.recycler_view_thai_ky)
    RecyclerView recyclerView;

    @Bind(R.id.tool_bar)
    Toolbar toolbar;

    private ArrayList<BaseModel> thaiKyModels;
    private ThaiKyAdapter thaiKyAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thaiky);
        ButterKnife.bind(this);
        initViews();
        initListener();
    }

    private void initViews() {
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_arrow_back));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(Constant.TITLE_THAI_KY);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        thaiKyModels = new ArrayList<>();
        thaiKyModels.addAll(DatabaseManager.getInstance(this).getArrayList());
        thaiKyAdapter = new ThaiKyAdapter(thaiKyModels, this);
        recyclerView.setAdapter(thaiKyAdapter);
    }

    private void initListener() {
        thaiKyAdapter.setOnClickItemThaiKy(this);
    }
    
    @Override
    public void onClickItem(BaseModel baseModel) {
        Intent intent = new Intent();
        SaveListManager.getInstance(this).clearAllList();
        intent.setClass(this, DetailThaikyActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_THAI_KY_MODEL, baseModel);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
