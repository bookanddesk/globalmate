package com.globalmate.data.entity;

public class UCredit {
    private String id;

    private String grade;

    private String name;

    private String privilege;

    private String busiext1;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege == null ? null : privilege.trim();
    }

    public String getBusiext1() {
        return busiext1;
    }

    public void setBusiext1(String busiext1) {
        this.busiext1 = busiext1 == null ? null : busiext1.trim();
    }
}