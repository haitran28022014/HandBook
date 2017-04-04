package com.haitran.handbook.model;

import io.realm.RealmObject;

/**
 * Created by Hai Tran on 3/24/2017.
 */

public class NhatKyModel extends RealmObject {
    private long id;
    private String content;
    private String date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
