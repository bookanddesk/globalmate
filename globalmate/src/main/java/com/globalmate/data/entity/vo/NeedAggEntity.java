package com.globalmate.data.entity.vo;

import com.globalmate.data.entity.Need;

public class NeedAggEntity {

    private Need need;

    private AbstractNeed abstractNeed;

    public Need getNeed() {
        return need;
    }

    public void setNeed(Need need) {
        this.need = need;
    }

    public AbstractNeed getAbstractNeed() {
        return abstractNeed;
    }

    public void setAbstractNeed(AbstractNeed abstractNeed) {
        this.abstractNeed = abstractNeed;
    }
}
