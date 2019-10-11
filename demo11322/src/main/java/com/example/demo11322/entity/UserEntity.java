package com.example.demo11322.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Administrator
 * @date 2019-10-09 09:11
 */
public class UserEntity implements Serializable {
    private long id;
    private String guid;
    private String name;
    private String age;
    private Date createTime;

    public void setId(long id) {
        this.id = id;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getId() {
        return id;
    }

    public String getGuid() {
        return guid;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public Date getCreateTime() {
        return createTime;
    }
}
