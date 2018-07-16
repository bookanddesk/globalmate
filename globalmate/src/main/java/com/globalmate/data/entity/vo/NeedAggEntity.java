package com.globalmate.data.entity.vo;

import com.globalmate.data.entity.Need;

public class NeedAggEntity {

    private Need need;

    private AbstractNeed conceretNeed;

    public Need getNeed() {
        return need;
    }

    public void setNeed(Need need) {
        this.need = need;
    }

    public AbstractNeed getConceretNeed() {
        return conceretNeed;
    }

    public void setConceretNeed(AbstractNeed conceretNeed) {
        this.conceretNeed = conceretNeed;
    }
}
