package com.globalmate.data.entity;

public class UTag {
    private String id;

    private String name;

    private String type;

    private String busiext1;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getBusiext1() {
        return busiext1;
    }

    public void setBusiext1(String busiext1) {
        this.busiext1 = busiext1 == null ? null : busiext1.trim();
    }
}