package com.haitran.handbook.model;

import io.realm.RealmObject;

/**
 * Created by Hai Tran on 3/22/2017.
 */

public class NoteModel extends RealmObject {
    private long id;
    private boolean noteOrAlarm;
    private String name;
    private String timer;
    private String content;
    private String days;
    
    public long getId() {
        return id;
    }

    public String getTimer() {
        return timer;
    }

    public void setTimer(String timer) {
        this.timer = timer;
    }

    public boolean isNoteOrAlarm() {
        return noteOrAlarm;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public String getDays() {
        return days;
    }
    

    public void setId(long id) {
        this.id = id;
    }

    public void setNoteOrAlarm(boolean noteOrAlarm) {
        this.noteOrAlarm = noteOrAlarm;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDays(String days) {
        this.days = days;
    }

}
