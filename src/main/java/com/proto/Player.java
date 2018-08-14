package com.proto;

import com.core.Serializer;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by L-DELL on 2018/8/14.
 */
public class Player extends Serializer {

    private Integer id;
    private String name;
    private List<Integer> skills = new LinkedList<>();
    private Golden golden = new Golden();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getSkills() {
        return skills;
    }

    public void setSkills(List<Integer> skills) {
        this.skills = skills;
    }

    public Golden getGolden() {
        return golden;
    }

    public void setGolden(Golden golden) {
        this.golden = golden;
    }

    @Override
    protected void read() {
        this.id = readInt();
        this.name = readString();
        this.skills = readList(Integer.class);
        this.golden = read(Golden.class);
    }

    @Override
    protected void write() {
        writeInt(id);
        writeString(name);
        writeList(skills);
        writeObject(golden);
    }
}
