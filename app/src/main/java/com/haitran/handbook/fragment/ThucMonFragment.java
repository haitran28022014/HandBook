package com.haitran.handbook.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haitran.handbook.R;
import com.haitran.handbook.activity.DetailThaikyActivity;
import com.haitran.handbook.activity.DetailThucMonActivity;
import com.haitran.handbook.adapter.PagerThaiKyAdapter;
import com.haitran.handbook.adapter.ThucMonAdapter;
import com.haitran.handbook.asynctask.LoadMonAnIdAsyncTask;
import com.haitran.handbook.asynctask.LoadNhomChatIdAsyncTask;
import com.haitran.handbook.asynctask.LoadThucPhamIdAsyncTask;
import com.haitran.handbook.callback.OnClickItemThucMon;
import com.haitran.handbook.manager.SaveListManager;
import com.haitran.handbook.model.ThaiKyModel;
import com.haitran.handbook.model.ThucMonModel;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Hai Tran on 3/13/2017.
 */

public class ThucMonFragment extends Fragment {
    public static final int THUC_PHAM = 1;
    public static final int MON_AN = 2;
    public static final int NHOM_CHAT = 3;
    public static final String KEY_THUC_MON = "KEY_THUC_MON";
    public static final String KEY_POSITION_THUC_MON = "KEY_POSITION_THUC_MON";

    @Bind(R.id.recycler_view_thuc_pham)
    RecyclerView recyclerView;

    @Bind(R.id.loadingPanel)
    RelativeLayout progressBar;
    
    private ThaiKyModel thaiKyModel;
    public static int index;

    public static ThucMonFragment newInstance(Bundle args) {
        ThucMonFragment fragment = new ThucMonFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thuc_mon, container, false);
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
    }


    private void initViews() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        switch (index) {
            case THUC_PHAM:
                if (SaveListManager.getInstance(getContext()).getThucPhamModels() == null
                        || SaveListManager.getInstance(getContext()).getThucPhamModels().size() == 0) {
                    handlingThucPham();
                } else {
                    if (getContext() != null)
                        setUpThucPham();
                }
                break;
            case MON_AN:
                if (SaveListManager.getInstance(getContext()).getMonAnModels() == null
                        || SaveListManager.getInstance(getContext()).getMonAnModels().size() == 0) {
                    handlingMonAn();
                } else {
                    if (getContext() != null)
                        setUpMonAn();
                }
                break;
            case NHOM_CHAT:
                if (SaveListManager.getInstance(getContext()).getNhomChatModels() == null
                        || SaveListManager.getInstance(getContext()).getNhomChatModels().size() == 0) {
                    handlingNhomChat();
                } else {
                    if (getContext() != null)
                        setUpNhomChat();
                }
                break;
            default:

                break;
        }
    }

    private void handlingThucPham() {
        String arrayThucPham = thaiKyModel.getThucPham();
        String[] a = arrayThucPham.split(",");
        LoadThucPhamIdAsyncTask loadThucPhamIdAsyncTask = new LoadThucPhamIdAsyncTask(new LoadThucPhamIdAsyncTask.OnGetAllMonAn() {
            @Override
            public void onComplete(ArrayList<ThucMonModel> thucMonModels) {
                SaveListManager.getInstance(getContext()).setThucPhamModels(thucMonModels);
                setUpThucPham();
            }
        }, a, getContext());
        loadThucPhamIdAsyncTask.execute();
    }

    private void handlingMonAn() {
        String arrayThucPham = thaiKyModel.getMonAn();
        String[] a = arrayThucPham.split(",");
        LoadMonAnIdAsyncTask loadMonAnIdAsyncTask = new LoadMonAnIdAsyncTask(new LoadMonAnIdAsyncTask.OnGetAllMonAn() {
            @Override
            public void onComplete(ArrayList<ThucMonModel> thucMonModels) {
                SaveListManager.getInstance(getContext()).setMonAnModels(thucMonModels);
                setUpMonAn();
            }
        }, a, getContext());
        loadMonAnIdAsyncTask.execute();
    }

    private void handlingNhomChat() {
        String arrayThucPham = thaiKyModel.getChat();
        String[] a = arrayThucPham.split(",");
        LoadNhomChatIdAsyncTask loadNhomChatIdAsyncTask = new LoadNhomChatIdAsyncTask(new LoadNhomChatIdAsyncTask.OnGetAllMonAn() {
            @Override
            public void onComplete(ArrayList<ThucMonModel> thucMonModels) {
                SaveListManager.getInstance(getContext()).setNhomChatModels(thucMonModels);
                setUpNhomChat();
            }
        }, a, getContext());
        loadNhomChatIdAsyncTask.execute();
    }


    public void setUpThucPham() {
        ThucMonAdapter thucMonAdapter = new ThucMonAdapter(SaveListManager.getInstance(getContext()).getThucPhamModels(), getContext(), THUC_PHAM);
        thucMonAdapter.setOnClickItemThucMon(new OnClickItemThucMon() {
            @Override
            public void onClickThucMon(ThucMonModel thucMonModel, ImageView imageView, TextView textView) {
                Intent intent = new Intent(getContext(), DetailThucMonActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(KEY_POSITION_THUC_MON, THUC_PHAM);
                bundle.putSerializable(KEY_THUC_MON, thucMonModel);
                intent.putExtras(bundle);
                Pair<View, String> p1 = Pair.create((View) imageView, getString(R.string.transition_string));
                Pair<View, String> p2 = Pair.create((View) textView, getString(R.string.transition_name));
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), p1, p2);
                startActivity(intent, activityOptionsCompat.toBundle());
            }
        });
        progressBar.setVisibility(View.GONE);
        recyclerView.setAdapter(thucMonAdapter);
    }

    public void setUpMonAn() {
        ThucMonAdapter thucMonAdapter1 = new ThucMonAdapter(SaveListManager.getInstance(getContext()).getMonAnModels(), getContext(), MON_AN);
        thucMonAdapter1.setOnClickItemThucMon(new OnClickItemThucMon() {
            @Override
            public void onClickThucMon(ThucMonModel thucMonModel, ImageView imageView, TextView textView) {
                Intent intent = new Intent(getContext(), DetailThucMonActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(KEY_POSITION_THUC_MON, MON_AN);
                bundle.putSerializable(KEY_THUC_MON, thucMonModel);
                intent.putExtras(bundle);
                Pair<View, String> p1 = Pair.create((View) imageView, getString(R.string.transition_string));
                Pair<View, String> p2 = Pair.create((View) textView, getString(R.string.transition_name));
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), p1, p2);
                startActivity(intent, activityOptionsCompat.toBundle());

            }
        });
        progressBar.setVisibility(View.GONE);
        recyclerView.setAdapter(thucMonAdapter1);
    }

    public void setUpNhomChat() {
        ThucMonAdapter thucMonAdapter2 = new ThucMonAdapter(SaveListManager.getInstance(getContext()).getNhomChatModels(), getContext(), NHOM_CHAT);
        thucMonAdapter2.setOnClickItemThucMon(new OnClickItemThucMon() {
            @Override
            public void onClickThucMon(ThucMonModel thucMonModel, ImageView imageView, TextView textView) {
                Intent intent = new Intent(getContext(), DetailThucMonActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(KEY_POSITION_THUC_MON, NHOM_CHAT);
                bundle.putSerializable(KEY_THUC_MON, thucMonModel);
                intent.putExtras(bundle);
                Pair<View, String> p1 = Pair.create((View) imageView, getString(R.string.transition_string));
                Pair<View, String> p2 = Pair.create((View) textView, getString(R.string.transition_name));
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), p1, p2);
                startActivity(intent, activityOptionsCompat.toBundle());

            }
        });
        progressBar.setVisibility(View.GONE);
        recyclerView.setAdapter(thucMonAdapter2);
    }

    private void getData() {
        Bundle arguments = getArguments();
        index = arguments.getInt(PagerThaiKyAdapter.POSITION, -1);
    }

}
