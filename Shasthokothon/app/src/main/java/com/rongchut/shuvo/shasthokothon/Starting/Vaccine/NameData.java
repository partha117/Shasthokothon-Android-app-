package com.rongchut.shuvo.shasthokothon.Starting.Vaccine;

/**
 * Created by ASUS on 02-Nov-16.
 */

public class NameData {
    private String name;
    private String id;

    public NameData(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public NameData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
