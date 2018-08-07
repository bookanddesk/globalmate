package com.globalmate.data.entity.vo;

import com.globalmate.data.entity.BaseEntity;

import java.util.List;

public abstract class AbstractNeed extends BaseEntity {

    public String getDeparture() {
        return null;
    }

    public String getDestination() {
        return null;
    }

    public String getDescription() {
        return null;
    }

    public abstract String getTag();

    public abstract List<String> getKeywords();

    public abstract String getTimeInfo();

}
