package com.haitran.handbook.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.haitran.handbook.R;
import com.haitran.handbook.manager.SharePreManager;
import com.haitran.handbook.util.HelpUtil;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, View.OnClickListener {
    @Bind(R.id.txt_last_period)
    TextView txtLastPeriod;

    @Bind(R.id.txt_due_date)
    TextView txtDueDate;

    @Bind(R.id.txt_congratulation)
    TextView txtCongratulation;

    @Bind(R.id.btn_skip_ok)
    Button btnSkipOk;
    public static final int FIRST = 0;
    public static final int LAST_PERIOD = 1;
    public static final int DUE_DATE = 2;
    private int setUpDate;
    SimpleDateFormat sdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String check = SharePreManager.getInstance(this).getDatePeriod();
        if (!check.equals("null")) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            MainActivity.this.finish();
            return;
        }
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
        initListener();
    }

    private void initViews() {
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        txtCongratulation.setVisibility(View.GONE);
    }

    private void initListener() {
        txtLastPeriod.setOnClickListener(this);
        txtDueDate.setOnClickListener(this);
        btnSkipOk.setOnClickListener(this);
    }

    private void handlingShowDialogTime(int position) {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH);
        int day = now.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                MainActivity.this,
                year,
                month,
                day);
        switch (position) {
            case FIRST:
                now.add(Calendar.DAY_OF_YEAR, -280);
                dpd.setMinDate(now);
                Calendar c = Calendar.getInstance();
                c.add(Calendar.DAY_OF_YEAR, -7);
                dpd.setMaxDate(c);
                dpd.show(getFragmentManager(), "Datepickerdialog");
                break;
            case LAST_PERIOD:
                now.add(Calendar.DAY_OF_YEAR, -280);
                dpd.setMinDate(now);
                c = Calendar.getInstance();
                c.add(Calendar.DAY_OF_YEAR, -7);
                dpd.setMaxDate(c);
                dpd.show(getFragmentManager(), "Datepickerdialog");
                break;
            case DUE_DATE:
                now.add(Calendar.DAY_OF_YEAR, 7);
                dpd.setMinDate(now);
                c = Calendar.getInstance();
                c.add(Calendar.DAY_OF_YEAR, 280);
                dpd.setMaxDate(c);
                dpd.show(getFragmentManager(), "Datepickerdialog");
                break;
            default:
                break;
        }

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String result = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
        txtLastPeriod.setTextColor(Color.BLACK);
        txtDueDate.setTextColor(Color.BLACK);
        String week = null;
        if (setUpDate == LAST_PERIOD || setUpDate == FIRST) {
            txtLastPeriod.setText(result);
            Calendar dueC = HelpUtil.getOneDate(result, 280);
            week = HelpUtil.tinhTuoiThai(result);
            txtDueDate.setText(sdf.format(dueC.getTime()));
        } else if (setUpDate == DUE_DATE) {
            txtDueDate.setText(result);
            Calendar lastDate = HelpUtil.getOneDate(result, -280);
            String last = sdf.format(lastDate.getTime());
            txtLastPeriod.setText(last);
            week = HelpUtil.tinhTuoiThai(last);
        }
        txtCongratulation.setVisibility(View.VISIBLE);
        txtCongratulation.setText("Xin chúc mừng! Bạn đang mang thai " + week);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_last_period:
                setUpDate = LAST_PERIOD;
                handlingShowDialogTime(LAST_PERIOD);
                break;
            case R.id.txt_due_date:
                setUpDate = DUE_DATE;
                handlingShowDialogTime(DUE_DATE);
                break;
            case R.id.btn_skip_ok:
                SharePreManager.getInstance(this).putDatePeriod(txtLastPeriod.getText().toString());
                SharePreManager.getInstance(this).putDueDate(txtDueDate.getText().toString());
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                MainActivity.this.finish();
                break;
            default:
                break;
        }
    }
}
