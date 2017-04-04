package com.haitran.handbook.activity;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.haitran.handbook.R;
import com.haitran.handbook.broadcast.AlarmManagerBroadcastReceiver;
import com.haitran.handbook.manager.LoadDataManager;
import com.haitran.handbook.model.NoteModel;
import com.kyleduo.switchbutton.SwitchButton;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.sql.Time;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Hai Tran on 3/22/2017.
 */

public class EditAddNoteActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    @Bind(R.id.img_back)
    ImageView imgBack;

    @Bind(R.id.img_delete)
    ImageView imgDelete;

    @Bind(R.id.btn_done)
    Button btnDone;

    @Bind(R.id.txt_due_date)
    TextView txtDueDate;

    @Bind(R.id.edt_content)
    EditText edtContent;

    @Bind(R.id.edt_name_note)
    EditText edtNameNote;

    @Bind(R.id.switch_off_on)
    SwitchButton switchButton;

    @Bind(R.id.linear_date)
    LinearLayout linearDate;

    @Bind(R.id.liner_timer)
    LinearLayout linearTimer;

    @Bind(R.id.linear_container)
    LinearLayout linearContainer;


    @Bind(R.id.txt_time_number)
    TextView txtTimeNumber;


    private NoteModel noteModel;
    private long idModel = 0;
    private String name;
    private String content;
    private String timer;
    private String addOrEdit;
    private boolean switchOnOrOff;
    private String date;
    private int dayCurrent;
    private int monthCurrent;
    private int yearCurrent;
    private int hourCurrent;
    private int minuteCurrent;
    private AlarmManagerBroadcastReceiver alarmManagerBroadcastReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_note);
        ButterKnife.bind(this);
        getData();
        initViews();
        initListener();
    }

    private void getData() {
        String hello;
        hello = (String) getIntent().getExtras().get(NoteActivity.KEY_ID_DETAIL_ADD_EDIT);
        String[] split = hello.split("/");
        idModel = Long.parseLong(split[0]);
        addOrEdit = split[1];
        if (addOrEdit.equals("edit")) {
            if (idModel == 0) {
                return;
            }
            noteModel = LoadDataManager.getInstance(this).oneTimerModel(idModel);
        }
    }

    private void initViews() {
        switchOnOrOff = false;
        alarmManagerBroadcastReceiver = new AlarmManagerBroadcastReceiver();
        switchButton.setChecked(switchOnOrOff);
        setUpEdit();
    }

    public static String setTextTime(int hour, int minute) {
        String result;
        if (hour < 10 && minute >= 10) {
            result = "0" + hour + ":" + minute;
        } else if (minute < 10) {
            result = hour + ":0" + minute;
        } else if (hour < 10 && minute < 10) {
            result = "0" + hour + ":0" + minute;
        } else {
            result = hour + ":" + minute;
        }
        return result;
    }


    public void getContentName() {
        name = edtNameNote.getText().toString();
        content = edtContent.getText().toString();
    }

    public void handlingSwitchButton() {
        switchOnOrOff = switchButton.isChecked();
        if (switchOnOrOff) {
            linearContainer.setVisibility(View.VISIBLE);

        } else {
            linearContainer.setVisibility(View.INVISIBLE);
        }
    }

    public void setUpEdit() {
        if (addOrEdit.equals("edit")) {
            edtNameNote.setText(noteModel.getName());
            edtContent.setText(noteModel.getContent());
            if (noteModel.getTimer() != null && !noteModel.getTimer().equals("")) {
                String[] splitTime = noteModel.getTimer().split(":");
                hourCurrent = Integer.parseInt(splitTime[0].trim());
                minuteCurrent = Integer.parseInt(splitTime[1].trim());
            }
            if (noteModel.getDays() != null && !noteModel.getDays().equals("")) {
                String[] splitTime = noteModel.getDays().split("/");
                dayCurrent = Integer.parseInt(splitTime[0].trim());
                monthCurrent = Integer.parseInt(splitTime[1].trim());
                yearCurrent = Integer.parseInt(splitTime[2].trim());
            }
            txtTimeNumber.setText(noteModel.getTimer());
            switchOnOrOff = noteModel.isNoteOrAlarm();
            switchButton.setChecked(switchOnOrOff);
            txtDueDate.setText(noteModel.getDays());
            handlingSwitchButton();
        } else {
            handlingSwitchButton();
            hourCurrent = new Time(System.currentTimeMillis()).getHours();
            minuteCurrent = new Time(System.currentTimeMillis()).getMinutes();
            Calendar now = Calendar.getInstance();
            yearCurrent = now.get(Calendar.YEAR);
            monthCurrent = now.get(Calendar.MONTH);
            dayCurrent = now.get(Calendar.DAY_OF_MONTH);
            txtTimeNumber.setText(setTextTime(hourCurrent, minuteCurrent));
            txtDueDate.setText(dayCurrent + "/" + (monthCurrent + 1) + "/" + yearCurrent);
        }

    }

    public void handlingSelectDate() {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH);
        int day = now.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                EditAddNoteActivity.this,
                year,
                month,
                day + 1);

        dpd.setMinDate(now);
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    private void initListener() {
        linearDate.setOnClickListener(this);
        linearTimer.setOnClickListener(this);
        imgDelete.setOnClickListener(this);
        imgBack.setOnClickListener(this);
        btnDone.setOnClickListener(this);
        switchButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_date:
                handlingSelectDate();
                break;
            case R.id.img_delete:
                if (LoadDataManager.getInstance(this).getNoteModel() == null || LoadDataManager.getInstance(this).getNoteModel().size() == 0) {
                    return;
                }
                LoadDataManager.getInstance(this).deleteOneTimerModel(idModel);
                cancelTimer((int) idModel);
                Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.btn_done:
                handlingSave();
                break;
            case R.id.switch_off_on:
                handlingSwitchButton();
                break;
            case R.id.img_back:
                finish();
                break;
            case R.id.liner_timer:
                handlingShowDialogTime();
                break;
            default:
                break;
        }
    }

    public void handlingSave() {
        getContentName();
        if (name == null || name.equals("")) {
            name = "Không tên";
        }
        if (switchOnOrOff) {
            date = txtDueDate.getText().toString();
            timer = txtTimeNumber.getText().toString();
        } else {
            date = "";
            timer = "";
        }
        switchOnOrOff = switchButton.isChecked();
        if (content == null || content.equals("")) {
            Toast.makeText(this, "Bạn phải nhập nội dung", Toast.LENGTH_SHORT).show();
            return;
        } else {
            NoteModel noteModel = new NoteModel();
            noteModel.setId(idModel);
            noteModel.setName(name);
            noteModel.setContent(content);
            noteModel.setTimer(timer);
            noteModel.setNoteOrAlarm(switchOnOrOff);
            noteModel.setDays(date);
            if (addOrEdit.equals("edit")) {
                LoadDataManager.getInstance(this).updateTimerModel(noteModel);
                if (switchButton.isChecked()) {
                    startTimer(yearCurrent, monthCurrent, dayCurrent, hourCurrent, minuteCurrent, (int) idModel);
                }
            } else {
                LoadDataManager.getInstance(this).addTimerModel(noteModel);
                if (switchButton.isChecked()) {
                    startTimer(yearCurrent, monthCurrent, dayCurrent, hourCurrent, minuteCurrent, (int) idModel);
                }
            }
        }
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    public void startTimer(int year, int month, int day, int hour, int min, int id) {
        Context context = this.getApplicationContext();
        if (alarmManagerBroadcastReceiver != null) {
            alarmManagerBroadcastReceiver.setAlarm(context, year, month, day, id, hour, min, idModel);
        } else {
            Toast.makeText(context, "Alarm is null", Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelTimer(int idModel) {
        Context context = this.getApplicationContext();
        if (alarmManagerBroadcastReceiver != null) {
            alarmManagerBroadcastReceiver.cancelAlarm(context, idModel);
        } else {
            Toast.makeText(context, "Alarm is null", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        yearCurrent = year;
        monthCurrent = monthOfYear;
        dayCurrent = dayOfMonth;
        txtDueDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
    }

    private void handlingShowDialogTime() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                hourCurrent = hourOfDay;
                minuteCurrent = minute;
                txtTimeNumber.setText(setTextTime(hourCurrent, minuteCurrent));
            }
        }, hourCurrent, minuteCurrent, true);
        timePickerDialog.show();
    }

}
