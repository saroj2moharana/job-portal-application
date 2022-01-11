package com.skm.model;

import com.skm.constants.ERole;

/**
 * Created by Saroj on 11/01/22
 **/
public class Role {
    private Integer id;
    private ERole name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }
}
