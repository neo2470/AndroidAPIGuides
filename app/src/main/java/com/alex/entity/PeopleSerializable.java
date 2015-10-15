package com.alex.entity;

import java.io.Serializable;

/**
 * Created by alex on 15-10-15.
 */
public class PeopleSerializable implements Serializable{

    public PeopleSerializable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
