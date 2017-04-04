package com.haitran.handbook.activity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.haitran.handbook.R;
import com.haitran.handbook.adapter.PagerThaiKyAdapter;
import com.haitran.handbook.fragment.ThucMonFragment;
import com.haitran.handbook.fragment.TongQuanFragment;
import com.haitran.handbook.manager.DatabaseManager;
import com.haitran.handbook.model.ThaiKyModel;
import com.haitran.handbook.util.BaseModel;
import com.haitran.handbook.util.Constant;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.haitran.handbook.adapter.PagerThaiKyAdapter.POSITION;

/**
 * Created by Hai Tran on 3/9/2017.
 */

public class DetailThaikyActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.view_pager_detail)
    ViewPager viewPagerDetail;

    @Bind(R.id.tab_layout_detail)
    TabLayout tabLayoutDetail;

    @Bind(R.id.main_appbar)
    AppBarLayout appBarLayout;

    private PagerThaiKyAdapter pagerThaiKyAdapter;
    private ThaiKyModel thaiKyModel;
    private BaseModel baseModel;


    private ArrayList<Fragment> arrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_thaiky_detail);
        ButterKnife.bind(this);
        getData();
        initViews();
    }

    private void getData() {
        thaiKyModel = new ThaiKyModel();
        baseModel = new BaseModel();
        baseModel = (BaseModel) getIntent().getExtras().getSerializable(ThaiKyActivity.KEY_THAI_KY_MODEL);
        thaiKyModel = DatabaseManager.getInstance(this).getOneThaiKy(baseModel.getId());
    }

    private void initViews() {
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_arrow_back));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(thaiKyModel.getTen());
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        appBarLayout.setBackground(setBackgroundCover(thaiKyModel.getHinhAnh()));
        initFragment();
        pagerThaiKyAdapter = new PagerThaiKyAdapter(getSupportFragmentManager(), arrayList);
        viewPagerDetail.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutDetail));
        viewPagerDetail.setAdapter(pagerThaiKyAdapter);
        tabLayoutDetail.setupWithViewPager(viewPagerDetail);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset < -120) {
                    appBarLayout.setBackgroundColor(Color.parseColor("#8E3841"));
                } else {
                    appBarLayout.setBackground(setBackgroundCover(thaiKyModel.getHinhAnh()));
                }
            }
        });
    }

    public Drawable setBackgroundCover(String nameImage) {
        Drawable d = null;
        try {
            d = Drawable.createFromStream(getAssets().open(Constant.NAME_ASSET_THAI_KY + nameImage + Constant.JPG), null);
            appBarLayout.setBackground(d);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return d;
    }

    public ThaiKyModel getThaiKyModel() {
        return thaiKyModel;
    }

    public BaseModel getBaseModel() {
        return baseModel;
    }

    private void initFragment() {
        arrayList = new ArrayList<>();
        Bundle bundle = new Bundle();
        bundle.putInt(POSITION, 0);
        TongQuanFragment homeFragment = TongQuanFragment.newInstance(bundle);
        arrayList.add(homeFragment);

        Bundle bundle1 = new Bundle();
        bundle1.putInt(POSITION, 1);
        ThucMonFragment thucMonFragment = ThucMonFragment.newInstance(bundle1);
        arrayList.add(thucMonFragment);

        bundle1 = new Bundle();
        bundle1.putInt(POSITION, 2);
        ThucMonFragment thucMonFragment1 = ThucMonFragment.newInstance(bundle1);
        arrayList.add(thucMonFragment1);

        bundle1 = new Bundle();
        bundle1.putInt(POSITION, 3);
        ThucMonFragment thucMonFragment2 = ThucMonFragment.newInstance(bundle1);
        arrayList.add(thucMonFragment2);
    }

}
