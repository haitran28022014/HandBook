package com.haitran.handbook.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haitran.handbook.R;
import com.haitran.handbook.adapter.ThucMonAdapter;
import com.haitran.handbook.asynctask.LoadAllMonAnAsyncTask;
import com.haitran.handbook.asynctask.LoadAllNhomChatAsyncTask;
import com.haitran.handbook.asynctask.LoadAllThucPhamAsyncTask;
import com.haitran.handbook.callback.OnClickItemThucMon;
import com.haitran.handbook.fragment.ThucMonFragment;
import com.haitran.handbook.model.ThucMonModel;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.haitran.handbook.fragment.ThucMonFragment.KEY_POSITION_THUC_MON;
import static com.haitran.handbook.fragment.ThucMonFragment.KEY_THUC_MON;
import static com.haitran.handbook.fragment.ThucMonFragment.NHOM_CHAT;
import static com.haitran.handbook.fragment.ThucMonFragment.THUC_PHAM;

/**
 * Created by Hai Tran on 3/19/2017.
 */

public class ThucMonActivity extends AppCompatActivity {
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    @Bind(R.id.loadingPanel)
    RelativeLayout loadingPanel;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private ArrayList<ThucMonModel> arrayList;
    private ThucMonAdapter thucMonAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thuc_mon);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        int position = getIntent().getIntExtra(HomeActivity.KEY_HOME_ACTIVITY, -1);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_arrow_back));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        arrayList = new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        switch (position) {
            case HomeActivity.DANH_SACH_THUC_PHAM:
                getSupportActionBar().setTitle("Danh sách thực phẩm");
                handlingGetAllThucPham();
                break;
            case HomeActivity.DANH_SACH_MON_AN:
                getSupportActionBar().setTitle("Danh sách món ăn");
                handlingGetAllMonAn();
                break;
            case HomeActivity.DANH_SACH_NHOM_CHAT:
                getSupportActionBar().setTitle("Danh sách nhóm chất");
                handlingGetAllNhomChat();
                break;
        }
    }

    public void handlingGetAllMonAn() {
        LoadAllMonAnAsyncTask loadAllMonAnAsyncTask = new LoadAllMonAnAsyncTask(new LoadAllMonAnAsyncTask.OnGetAllMonAn() {
            @Override
            public void onComplete(ArrayList<ThucMonModel> thucMonModels) {
                arrayList.addAll(thucMonModels);
                thucMonAdapter = new ThucMonAdapter(arrayList, ThucMonActivity.this, ThucMonFragment.MON_AN);
                thucMonAdapter.setOnClickItemThucMon(new OnClickItemThucMon() {
                    @Override
                    public void onClickThucMon(ThucMonModel thucMonModel, ImageView imageView, TextView textView) {
                        Intent intent = new Intent(ThucMonActivity.this, DetailThucMonActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt(KEY_POSITION_THUC_MON, ThucMonFragment.MON_AN);
                        bundle.putSerializable(KEY_THUC_MON, thucMonModel);
                        intent.putExtras(bundle);
                        Pair<View, String> p1 = Pair.create((View) imageView, getString(R.string.transition_string));
                        Pair<View, String> p2 = Pair.create((View) textView, getString(R.string.transition_name));
                        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(ThucMonActivity.this, p1, p2);
                        startActivity(intent, activityOptionsCompat.toBundle());
                    }
                });
                loadingPanel.setVisibility(View.GONE);
                recyclerView.setAdapter(thucMonAdapter);
            }
        }, this);
        loadAllMonAnAsyncTask.execute();
    }

    public void handlingGetAllThucPham() {
        LoadAllThucPhamAsyncTask loadThucPham = new LoadAllThucPhamAsyncTask(new LoadAllThucPhamAsyncTask.OnGetAllMonAn() {
            @Override
            public void onComplete(ArrayList<ThucMonModel> thucMonModels) {
                arrayList.addAll(thucMonModels);
                ThucMonAdapter thucMonAdapter = new ThucMonAdapter(arrayList, ThucMonActivity.this, THUC_PHAM);
                thucMonAdapter.setOnClickItemThucMon(new OnClickItemThucMon() {
                    @Override
                    public void onClickThucMon(ThucMonModel thucMonModel, ImageView imageView, TextView textView) {
                        Intent intent = new Intent(ThucMonActivity.this, DetailThucMonActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt(KEY_POSITION_THUC_MON, THUC_PHAM);
                        bundle.putSerializable(KEY_THUC_MON, thucMonModel);
                        intent.putExtras(bundle);
                        Pair<View, String> p1 = Pair.create((View) imageView, getString(R.string.transition_string));
                        Pair<View, String> p2 = Pair.create((View) textView, getString(R.string.transition_name));
                        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(ThucMonActivity.this, p1, p2);
                        startActivity(intent, activityOptionsCompat.toBundle());
                    }
                });
                loadingPanel.setVisibility(View.GONE);
                recyclerView.setAdapter(thucMonAdapter);
            }
        }, this);
        loadThucPham.execute();
    }

    public void handlingGetAllNhomChat() {
        LoadAllNhomChatAsyncTask loadThucPham = new LoadAllNhomChatAsyncTask(new LoadAllNhomChatAsyncTask.OnGetAllMonAn() {
            @Override
            public void onComplete(ArrayList<ThucMonModel> thucMonModels) {
                arrayList.addAll(thucMonModels);
                ThucMonAdapter thucMonAdapter = new ThucMonAdapter(
                        arrayList, ThucMonActivity.this, NHOM_CHAT);
                thucMonAdapter.setOnClickItemThucMon(new OnClickItemThucMon() {
                    @Override
                    public void onClickThucMon(ThucMonModel thucMonModel, ImageView imageView, TextView textView) {
                        Intent intent = new Intent(ThucMonActivity.this, DetailThucMonActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt(KEY_POSITION_THUC_MON, NHOM_CHAT);
                        bundle.putSerializable(KEY_THUC_MON, thucMonModel);
                        intent.putExtras(bundle);
                        Pair<View, String> p1 = Pair.create((View) imageView, getString(R.string.transition_string));
                        Pair<View, String> p2 = Pair.create((View) textView, getString(R.string.transition_name));
                        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(ThucMonActivity.this, p1, p2);
                        startActivity(intent, activityOptionsCompat.toBundle());
                    }
                });
                loadingPanel.setVisibility(View.GONE);
                recyclerView.setAdapter(thucMonAdapter);
            }
        }, this);
        loadThucPham.execute();
    }
}
