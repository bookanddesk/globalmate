package com.globalmate.data.entity;

public class BaseTypeEntity {
    private String id;
    private String needId;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

}