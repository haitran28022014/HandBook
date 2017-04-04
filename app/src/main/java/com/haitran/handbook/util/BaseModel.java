package com.haitran.handbook.util;

import java.io.Serializable;

/**
 * Created by Hai Tran on 3/16/2017.
 */

public class BaseModel implements Serializable {
    private String id;
    private String imageName;
    private String title;
    private String detail;

    public BaseModel(String id, String imageName, String title, String detail) {
        this.id = id;
        this.imageName = imageName;
        this.title = title;
        this.detail = detail;
    }

    public BaseModel() {
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getId() {
        return id;
    }

    public String getImageName() {
        return imageName;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }
}
