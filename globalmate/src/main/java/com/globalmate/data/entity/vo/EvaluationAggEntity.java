package com.globalmate.data.entity.vo;

import com.globalmate.data.entity.UEvaluation;
import com.globalmate.service.common.ICreateService;

/**
 * @author XingJiajun
 * @Date 2018/7/17 16:58
 * @Description
 */
public class EvaluationAggEntity implements ICreateService<EvaluationAggEntity, UEvaluation> {
    private UEvaluation evaluation;
    private NeedAggEntity needAggEntity;

    public UEvaluation getEvaluation() {
        return evaluation;
    }

    public EvaluationAggEntity setEvaluation(UEvaluation evaluation) {
        this.evaluation = evaluation;
        return this;
    }

    public NeedAggEntity getNeedAggEntity() {
        return needAggEntity;
    }

    public EvaluationAggEntity setNeedAggEntity(NeedAggEntity needAggEntity) {
        this.needAggEntity = needAggEntity;
        return this;
    }

    @Override
    public EvaluationAggEntity create(UEvaluation evaluation) {
        return this.setEvaluation(evaluation);
    }
}
