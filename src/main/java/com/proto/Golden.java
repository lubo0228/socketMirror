package com.proto;

import com.core.Serializer;

/**
 * Created by L-DELL on 2018/8/14.
 */
public class Golden extends Serializer {

    private long num;

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    @Override
    protected void read() {
        this.num = readLong();
    }

    @Override
    protected void write() {
        writeLong(num);
    }
}
