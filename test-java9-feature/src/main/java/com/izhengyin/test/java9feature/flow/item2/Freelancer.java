package com.izhengyin.test.java9feature.flow.item2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2020-08-08 14:46
 */
public class Freelancer extends Employee {

    private int fid;

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public Freelancer(int id, int fid, String name) {
        super(id, name);
        this.fid = fid;
    }

    @Override
    public String toString() {
        return "[id="+super.getId()+",name="+super.getName()+",fid="+fid+"]";
    }
}