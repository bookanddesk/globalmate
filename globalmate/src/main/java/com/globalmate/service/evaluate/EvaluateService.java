package com.globalmate.service.evaluate;

import com.globalmate.data.dao.mapper.UEvaluationMapper;
import com.globalmate.data.entity.UEvaluation;
import com.globalmate.data.entity.User;
import com.globalmate.data.entity.po.GMEnums;
import com.globalmate.data.entity.vo.EvaluationAggEntity;
import com.globalmate.exception.DataNotFoundException;
import com.globalmate.service.common.ICreateService;
import com.globalmate.service.need.NeedService;
import com.globalmate.service.user.UserService;
import com.globalmate.uitl.CollectionUtils;
import com.globalmate.uitl.GMConstant;
import com.globalmate.uitl.IdGenerator;
import com.globalmate.uitl.StringUtils;
import com.google.common.primitives.Ints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author XingJiajun
 * @Date 2018/7/17 15:57
 * @Description
 */
@Service
public class EvaluateService implements IEvaluateService, ICreateService<UEvaluation, User> {

    @Autowired
    private UEvaluationMapper evaluationMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private NeedService needService;

    @Override
    public UEvaluation addEvaluation(User user, UEvaluation evaluation) {
        checkNotNull(evaluation);
        if (StringUtils.isBlank(evaluation.getId())) {
            evaluation.setId(IdGenerator.generateId());
        }
        if (StringUtils.isBlank(evaluation.getuEvaluatorId())) {
            evaluation.setuEvaluatorId(user.getId());
        }
        if (StringUtils.isBlank(evaluation.getuEvluatorName())) {
            evaluation.setuEvluatorName(user.getName());
        }
        if (StringUtils.isBlank(evaluation.getEvaExt1())) {
            evaluation.setEvaExt1(GMEnums.EvaluationType.PERSONAL.getValue());
        }
        evaluation.setCreateTime(Date.from(Instant.now()));
        int i = evaluationMapper.insert(evaluation);
        if (i > 0) {
            updateUserNiceValue(evaluation);
            return evaluationMapper.selectByPrimaryKey(evaluation.getId());
        }
        return null;
    }

    @Override
    public UEvaluation addSysEvaluation(User user, UEvaluation evaluation) {
        checkNotNull(evaluation);
        checkNotNull(evaluation.getContent());
        UEvaluation uEvaluation = create(user);
        uEvaluation.setContent(evaluation.getContent());
        uEvaluation.setuTargeterId(GMConstant.SYS_ID);
        uEvaluation.setuTargeterName(GMConstant.SYS_NAME);
        uEvaluation.setEvaExt1(GMEnums.EvaluationType.PLATFORM.getValue());
        int i = evaluationMapper.insert(uEvaluation);
        if (i > 0) {
            return evaluationMapper.selectByPrimaryKey(uEvaluation.getId());
        }
        return null;
    }

    @Override
    public UEvaluation updateEvaluation(User user, UEvaluation evaluation) {
        checkNotNull(evaluation);
        UEvaluation uEvaluation = getEvaluation(evaluation.getId());
        if (uEvaluation == null) {
            throw new DataNotFoundException("can't foud UEvaluation with id:" + evaluation.getId());
        }
        if (evaluation.getuEvaluatorId() == null) {
            evaluation.setuEvaluatorId(user.getId());
        }
        if (evaluation.getuEvluatorName() == null) {
            evaluation.setuEvluatorName(user.getName());
        }
        evaluation.setModifyTime(Date.from(Instant.now()));
        int i = evaluationMapper.updateByPrimaryKeySelective(evaluation);
        if (i > 0) {
            evaluation.setScore(String.valueOf(getEvaluationScore(evaluation) - getEvaluationScore(uEvaluation)));
            updateUserNiceValue(evaluation);
            return evaluationMapper.selectByPrimaryKey(evaluation.getId());
        }
        return null;
    }

    @Override
    public UEvaluation getEvaluation(String id) {
        if (StringUtils.isNoneBlank(id)) {
            return evaluationMapper.selectByPrimaryKey(id);
        }
        return null;
    }

    @Override
    public List<UEvaluation> listEvaluation(User user) {
        UEvaluation evaluation = new UEvaluation();
        if (user != null) {
            evaluation.setuEvaluatorId(user.getId());
        }
        return evaluationMapper.queryRecords(evaluation);
    }

    @Override
    public List<UEvaluation> listEvaluation(UEvaluation evaluation) {
        return evaluationMapper.queryRecords(Optional.ofNullable(evaluation).orElse(new UEvaluation()));
    }

    public List<EvaluationAggEntity> listEvaluationAgg(User user) {
        List<UEvaluation> uEvaluations = listEvaluation(user);
        if (CollectionUtils.isEmpty(uEvaluations)) {
            return null;
        }
        List<EvaluationAggEntity> entities = new ArrayList<>(uEvaluations.size());
        for (UEvaluation evaluation : uEvaluations) {
            if (filterEvaluation(evaluation)) {
                continue;
            }
            entities.add(buildEvaluationAgg(evaluation));
        }
        return entities;
    }

    private boolean filterEvaluation(UEvaluation evaluation) {
        return evaluation == null || StringUtils.isBlank(evaluation.getNeedId()) ||
                StringUtils.equals(evaluation.getuTargeterId(), GMConstant.SYS_ID);
    }

    private EvaluationAggEntity buildEvaluationAgg(UEvaluation evaluation) {
        return new EvaluationAggEntity().create(evaluation).setNeedAggEntity(needService.getNeedAgg(evaluation.getNeedId()));
    }

    @Override
    public void updateUserNiceValue(UEvaluation evaluation) {
        if (evaluation.getuTargeterId() == null) {
            return;
        }
        int evaluationScore = getEvaluationScore(evaluation);
        if (evaluationScore == 0) {
            return;
        }
        userService.updateNice(evaluation.getuTargeterId(), evaluationScore);
    }

    private int getEvaluationScore(UEvaluation evaluation) {
        if (evaluation == null || evaluation.getScore() == null) {
            return 0;
        }
        return Optional.ofNullable(Ints.tryParse(evaluation.getScore())).orElse(0);
    }

    @Override
    public UEvaluation create(User user) {
        UEvaluation evaluation = new UEvaluation();
        evaluation.setId(IdGenerator.generateId());
        evaluation.setCreateTime(Date.from(Instant.now()));
        if (user != null) {
            evaluation.setuEvaluatorId(user.getId());
            evaluation.setuEvluatorName(user.getName());
        }
        return evaluation;
    }
}
