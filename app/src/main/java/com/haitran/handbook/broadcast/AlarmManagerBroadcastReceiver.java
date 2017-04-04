package com.haitran.handbook.broadcast;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.PowerManager;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Toast;

import com.haitran.handbook.manager.LoadDataManager;
import com.haitran.handbook.model.NoteModel;
import com.haitran.handbook.view.NoteView;

import java.util.Calendar;

import static android.content.Context.WINDOW_SERVICE;

/**
 * Created by Hai Tran on 3/23/2017.
 */

public class AlarmManagerBroadcastReceiver extends BroadcastReceiver {
    private NoteView noteView;
    private WindowManager windowManager;
    private Context context;
    private NoteModel noteModel;
    private WindowManager.LayoutParams params;

    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager pm = (PowerManager) context
                .getSystemService(Context.POWER_SERVICE);
        this.context = context;
        windowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
        noteModel = LoadDataManager.getInstance(context).oneTimerModel( intent.getLongExtra("ID_MODEL", -1));
        showNoteView();
        Log.e("hai", "nhay di ma");
        Toast.makeText(context, "Nhay vao ngay do roi do", Toast.LENGTH_SHORT).show();
    }

    private void showNoteView() {
        noteView = new NoteView(context, noteModel);
        params = new WindowManager.LayoutParams();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.CENTER;
        params.type = WindowManager.LayoutParams.TYPE_PHONE;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        params.format = PixelFormat.TRANSPARENT;
        windowManager.addView(noteView, params);
        noteView.setOnClickClose(new NoteView.OnClickClose() {
            @Override
            public void onClose() {
                windowManager.removeView(noteView);
            }
        });
    }

    public void setAlarm(Context con,int year,int month,int day, int id, int hour, int min,long value) {
        AlarmManager am = (AlarmManager) con
                .getSystemService(Context.ALARM_SERVICE);
        Calendar cal = Calendar.getInstance();
        cal.set(year,month, day, hour, min);
        Log.e("Test",cal.get(Calendar.YEAR)+"/"+cal.get(Calendar.MONTH)+"/"+cal.get(Calendar.DAY_OF_MONTH));
        Log.e("hahaha",year+"/"+month+"/"+day);
        Intent intent = new Intent(con, AlarmManagerBroadcastReceiver.class);
        intent.putExtra("ID_MODEL", value);
        PendingIntent sender = PendingIntent.getBroadcast(con, id, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), sender);
    }

    public void cancelAlarm(Context context, int id) {
        Intent intent = new Intent(context,
                AlarmManagerBroadcastReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, id,
                intent, 0);
        AlarmManager alarmManager = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }

}
