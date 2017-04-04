package com.haitran.handbook.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.github.lzyzsd.circleprogress.ArcProgress;
import com.haitran.handbook.R;
import com.haitran.handbook.adapter.ItemDrawerAdapter;
import com.haitran.handbook.manager.DatabaseManager;
import com.haitran.handbook.manager.HomeManager;
import com.haitran.handbook.manager.SharePreManager;
import com.haitran.handbook.model.ThaiKyModel;
import com.haitran.handbook.util.BaseModel;
import com.haitran.handbook.util.HelpUtil;
import com.haitran.handbook.view.TextViewCharter;
import com.haitran.handbook.view.TextViewSan;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Hai Tran on 3/20/2017.
 */

public class HomeActivity extends AppCompatActivity implements ItemDrawerAdapter.OnClickItemDrawer, View.OnClickListener {
    public static final int THAI_KY = 0;
    public static final int DANH_SACH_THUC_PHAM = 1;
    public static final int DANH_SACH_MON_AN = 2;
    public static final int DANH_SACH_NHOM_CHAT = 3;
    public static final int TRUYEN = 4;
    public static final String KEY_HOME_ACTIVITY = "KEY_HOME_ACTIVITY";
    @Bind(R.id.tool_bar)
    Toolbar toolbar;

    @Bind(R.id.recyclerViewDrawer)
    RecyclerView recyclerView;

    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Bind(R.id.txt_tuoi_thai)
    TextViewSan txtTuoiThai;

    @Bind(R.id.txt_du_sinh)
    TextViewSan txtDuSinh;

    @Bind(R.id.txt_con_lai)
    TextViewSan txtConLai;

    @Bind(R.id.arc_progress)
    ArcProgress arcProgress;

    @Bind(R.id.linear_ghi_chu)
    LinearLayout linearGhiChu;

    @Bind(R.id.linear_nhat_ky)
    LinearLayout linearNhatKy;

    @Bind(R.id.linear_thai_ky)
    LinearLayout linearThaiKy;

    @Bind(R.id.img_cover)
    ImageView imgCover;


    @Bind(R.id.txt_content_ba_bau)
    TextViewCharter txtContentBaBau;

    @Bind(R.id.txt_content_thai_nhi)
    TextViewCharter txtContentThaiNhi;

    @Bind(R.id.txt_tam_ca_nguyet)
    TextViewCharter txtContentTamCaNguyet;

    @Bind(R.id.txt_nguon_tham_khao)
    TextViewCharter txtNguon;

    @Bind(R.id.txt_name)
    TextViewSan txtNameWeek;

    @Bind(R.id.txt_day)
    TextViewCharter txtDayOfWeek;

    private ItemDrawerAdapter itemDrawerAdapter;
    private HomeManager homeManager;
    private ArrayList<BaseModel> baseModels;
    private ArrayList<BaseModel> thaiKyModels;
    private String tuoiThai;
    private String fromDateToDate;
    private String idModel;
    private ThaiKyModel thaiKyModel;
    private int tuanThu;
    private String duSinh;
    private String ngayConLai;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initViews();
        initListener();
    }

    private void initListener() {
        itemDrawerAdapter.setOnClickItemDrawer(this);
        linearThaiKy.setOnClickListener(this);
        linearNhatKy.setOnClickListener(this);
        linearGhiChu.setOnClickListener(this);
    }

    private void initViews() {
        Glide.with(this).load(R.drawable.img_pregnant).override(600, 400).into(imgCover);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_menu_white_24dp));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        tuoiThai = SharePreManager.getInstance(this).getDatePeriod();
        duSinh = SharePreManager.getInstance(this).getDueDate();
        ngayConLai = HelpUtil.tinhNgayConLai(duSinh);
        txtTuoiThai.setText("Tuổi thai:  " + HelpUtil.tinhTuoiThai(tuoiThai));
        tuanThu = HelpUtil.tuanThuMay(tuoiThai);
        txtDuSinh.setText("Dự sinh:  " + duSinh);
        txtConLai.setText("Còn lại:  " + ngayConLai);
        txtNameWeek.setText("Tuần thứ " + tuanThu);
        arcProgress.setProgress(HelpUtil.tinhPhanTram(tuoiThai));
        baseModels = new ArrayList<>();
        thaiKyModels = new ArrayList<>();
        thaiKyModels.addAll(DatabaseManager.getInstance(this).getAllThaiKy());
        for (int i = 0; i < thaiKyModels.size(); i++) {
            if (("Tuần " + tuanThu).equals(thaiKyModels.get(i).getTitle())) {
                fromDateToDate = thaiKyModels.get(i).getDetail();
                idModel = thaiKyModels.get(i).getId();
                break;
            }
        }
        txtDayOfWeek.setText(fromDateToDate);
        thaiKyModel = DatabaseManager.getInstance(this).getOneThaiKy(idModel);
        setUpViews();
        homeManager = new HomeManager();
        baseModels.addAll(homeManager.getArrayList());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        itemDrawerAdapter = new ItemDrawerAdapter(baseModels, this);
        recyclerView.setAdapter(itemDrawerAdapter);
    }

    public void setUpViews() {
        txtContentBaBau.setText(thaiKyModel.getBaBau());
        txtContentThaiNhi.setText(thaiKyModel.getThaiNhi());
        txtContentTamCaNguyet.setText(thaiKyModel.getTamCaNguyet());
        txtNguon.setText(thaiKyModel.getThamKhao());
    }


    @Override
    public void onClickItemDrawer(int position) {
        Intent intent1 = new Intent(this, ThucMonActivity.class);
        switch (position) { 
            case THAI_KY:
                Intent intent = new Intent(this, ThaiKyActivity.class);
                startActivity(intent);
                break;
            case DANH_SACH_THUC_PHAM:
                intent1.putExtra(KEY_HOME_ACTIVITY, DANH_SACH_THUC_PHAM);
                startActivity(intent1);
                break;
            case DANH_SACH_MON_AN:
                intent1.putExtra(KEY_HOME_ACTIVITY, DANH_SACH_MON_AN);
                startActivity(intent1);
                break;
            case DANH_SACH_NHOM_CHAT:
                intent1.putExtra(KEY_HOME_ACTIVITY, DANH_SACH_NHOM_CHAT);
                startActivity(intent1);
                break;
            case TRUYEN:
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_thai_ky:
                Intent intent = new Intent(this, ThaiKyActivity.class);
                startActivity(intent);
                break;
            case R.id.linear_ghi_chu:
                intent = new Intent(this, NoteActivity.class);
                startActivity(intent);
                break;
            case R.id.linear_nhat_ky:
                intent = new Intent(this, NhatKyActivity.class);
                startActivity(intent);
                break;
        }
    }
}
