package com.haitran.handbook.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.haitran.handbook.R;
import com.haitran.handbook.fragment.ThucMonFragment;
import com.haitran.handbook.model.ThucMonModel;
import com.haitran.handbook.util.Constant;
import com.haitran.handbook.view.TextViewCharter;
import com.haitran.handbook.view.TextViewSan;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Createtd by Hai Tran on 3/16/2017.
 */

public class DetailThucMonActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.txt_loai_luong)
    TextViewSan txtLoaiLuong;

    @Bind(R.id.che_bien)
    TextViewSan cheBien;

    @Bind(R.id.nguyen_lieu)
    TextViewSan nguyenLieu;

    @Bind(R.id.card_che_bien)
    CardView cardCheBien;

    @Bind(R.id.card_nguyen_lieu)
    CardView cardNguyenLieu;

    @Bind(R.id.card_nguon_tham_khao)
    CardView cardNguonThamKhao;

    @Bind(R.id.txt_luong_chat)
    TextViewCharter txtLuongChat;

    @Bind(R.id.txt_so_luoc)
    TextViewCharter txtSoLuoc;

    @Bind(R.id.txt_content_yes_no)
    TextViewCharter txtContentYesNo;

    @Bind(R.id.txt_nguyen_lieu)
    TextViewCharter txtNguyenLieu;

    @Bind(R.id.txt_che_bien)
    TextViewCharter txtCheBien;

    @Bind(R.id.txt_nguon_tham_khao)
    TextViewCharter txtNguonThamKhao;

    @Bind(R.id.img_thaiky)
    ImageView imageView;

    @Bind(R.id.toolbar_title)
    TextViewSan toolbarTilte;

    private int positionThucMon;
    private ThucMonModel thucMonModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setEnterTransition(new Explode());
        getWindow().setExitTransition(new Explode());
        setContentView(R.layout.activity_detail_thuc_mon);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_arrow_back));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        thucMonModel = new ThucMonModel();
        thucMonModel = (ThucMonModel) getIntent().getExtras().getSerializable(ThucMonFragment.KEY_THUC_MON);
        positionThucMon = getIntent().getExtras().getInt(ThucMonFragment.KEY_POSITION_THUC_MON);
        toolbarTilte.setText(thucMonModel.getTenMonAn());
        switch (positionThucMon) {
            case ThucMonFragment.THUC_PHAM:
                handlingThucPham();
                break;
            case ThucMonFragment.MON_AN:
                handlingMonAn();
                break;
            case ThucMonFragment.NHOM_CHAT:
                handlingNhomChat();
                break;
            default:
                break;
        }
        txtContentYesNo.setText(thucMonModel.getNenDung());
        txtSoLuoc.setText(thucMonModel.getSoLuoc());
    }

    public void handlingThucPham() {
        Glide.with(this)
                .load(Constant.LINK_THUC_PHAM_IMAGE + thucMonModel.getHinhAnh() + Constant.JPG)
                .into(imageView);
        txtLoaiLuong.setText("Lượng chất");
        cardCheBien.setVisibility(View.GONE);
        cardNguyenLieu.setVisibility(View.GONE);
        cardNguonThamKhao.setVisibility(View.GONE);
        txtLuongChat.setText(thucMonModel.getLuongChat());
    }

    public void handlingMonAn() {
        Glide.with(this)
                .load(Constant.LINK_MON_AN_IMAGE + thucMonModel.getHinhAnh() + Constant.JPG)
                .into(imageView);
        txtLoaiLuong.setText("Loại");
        cardCheBien.setVisibility(View.VISIBLE);
        cardNguyenLieu.setVisibility(View.VISIBLE);
        cardNguonThamKhao.setVisibility(View.VISIBLE);
        txtLuongChat.setText(thucMonModel.getLoai());
        txtNguyenLieu.setText(thucMonModel.getNguyenLieu());
        txtCheBien.setText(thucMonModel.getCheBien());
        txtNguonThamKhao.setText(thucMonModel.getThamKhao());
    }

    public void handlingNhomChat() {
        Glide.with(this)
                .load(Constant.LINK_NHOM_CHAT + thucMonModel.getHinhAnh() + Constant.JPG)
                .into(imageView);
        txtLoaiLuong.setText("Cơ sở");
        txtLuongChat.setText(thucMonModel.getLuongChat());
        txtNguonThamKhao.setText(thucMonModel.getThamKhao());
        nguyenLieu.setText("Dưỡng Chất");
        txtNguyenLieu.setText(thucMonModel.getLoai());
        cheBien.setText("Lưu ý");
        txtCheBien.setText(thucMonModel.getLuuY());
        txtNguonThamKhao.setText(thucMonModel.getThamKhao());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
