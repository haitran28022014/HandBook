package com.haitran.handbook.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haitran.handbook.R;
import com.haitran.handbook.activity.DetailThaikyActivity;
import com.haitran.handbook.adapter.PagerThaiKyAdapter;
import com.haitran.handbook.model.ThaiKyModel;
import com.haitran.handbook.util.BaseModel;
import com.haitran.handbook.view.TextViewCharter;
import com.haitran.handbook.view.TextViewSan;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Hai Tran on 3/13/2017.
 */

public class TongQuanFragment extends Fragment {
    private ThaiKyModel thaiKyModel;
    private int index;

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

    public static TongQuanFragment newInstance(Bundle args) {
        TongQuanFragment fragment = new TongQuanFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tongquan, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getData();
        getDataBase();
        initViews();
    }

    private void getDataBase() {
        thaiKyModel = ((DetailThaikyActivity) getActivity()).getThaiKyModel();
        setUpViews(thaiKyModel);
    }

    public void setUpViews(ThaiKyModel thaiKyModel) {
        BaseModel baseModel;
        baseModel = ((DetailThaikyActivity) getActivity()).getBaseModel();
        txtNameWeek.setText(baseModel.getTitle());
        txtDayOfWeek.setText(baseModel.getDetail());
        txtContentBaBau.setText(thaiKyModel.getBaBau());
        txtContentThaiNhi.setText(thaiKyModel.getThaiNhi());
        txtContentTamCaNguyet.setText(thaiKyModel.getTamCaNguyet());
        txtNguon.setText(thaiKyModel.getThamKhao());
    }

    private void initViews() {

    }

    private void getData() {
        Bundle arguments = getArguments();
        index = arguments.getInt(PagerThaiKyAdapter.POSITION, -1);
    }

}
