package com.haitran.handbook.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.haitran.handbook.R;
import com.haitran.handbook.manager.LoadDataNhatKyManager;
import com.haitran.handbook.model.NhatKyModel;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Hai Tran on 3/22/2017.
 */

public class EditAddNhatKyActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.img_back)
    ImageView imgBack;

    @Bind(R.id.img_delete)
    ImageView imgDelete;

    @Bind(R.id.btn_done)
    Button btnDone;

    @Bind(R.id.edt_content)
    EditText edtContent;

    private int year, month, day, hour, min;
    private String content;
    private String date;
    private NhatKyModel nhatKyModel;
    private long idModel;
    private String editOrAdd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_nhat_ky);
        ButterKnife.bind(this);
        getData();
        initViews();
        initListener();
    }

    private void initListener() {
        imgBack.setOnClickListener(this);
        btnDone.setOnClickListener(this);
        imgDelete.setOnClickListener(this);
    }

    private void getData() {
        String hello;
        hello = (String) getIntent().getExtras().get(NhatKyActivity.KEY_ID_DETAIL_ADD_EDIT_NHAT_KY);
        String[] split = hello.split("/");
        idModel = Long.parseLong(split[0]);
        editOrAdd = split[1];
        if (editOrAdd.equals("edit")) {
            if (idModel == 0) {
                return;
            }
            nhatKyModel = LoadDataNhatKyManager.getInstance(this).oneNhatKyModel(idModel);
        }
    }

    private void initViews() {
        if(editOrAdd.equals("edit")) {
            edtContent.setText(nhatKyModel.getContent());
        }
    }

    public void getDate() {
        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);
        hour = cal.get(Calendar.HOUR);
        min = cal.get(Calendar.MINUTE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_delete:
                if (LoadDataNhatKyManager.getInstance(this).getNhatKyModel() == null || LoadDataNhatKyManager.getInstance(this).getNhatKyModel().size() == 0) {
                    return;
                }
                LoadDataNhatKyManager.getInstance(this).deleteOneNhatKyModel(idModel);
                Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.btn_done:
                handlingSave();
                break;
            case R.id.img_back:
                finish();
                break;
            default:
                break;
        }
    }

    private void handlingSave() {
        getDate();
        content = edtContent.getText().toString();
        if (content == null || content.equals("")) {
            Toast.makeText(this, "Bạn hãy nhập nội dung", Toast.LENGTH_SHORT).show();
        } else {
            NhatKyModel nhatKyModel = new NhatKyModel();
            nhatKyModel.setId(idModel);
            nhatKyModel.setContent(content);
            nhatKyModel.setDate(day + "-" + month + "-" + year + "   " + hour + ":" + min);
            if (editOrAdd.equals("edit")) {
                LoadDataNhatKyManager.getInstance(this).updateNhatKyModel(nhatKyModel);
            } else {
                LoadDataNhatKyManager.getInstance(this).addNhatKyModel(nhatKyModel);
            }
        }
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
