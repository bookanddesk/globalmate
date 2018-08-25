package com.globalmate.service.evaluate;

import com.globalmate.data.entity.UEvaluation;
import com.globalmate.data.entity.User;

import java.util.List;

/**
 * @author XingJiajun
 * @Date 2018/7/17 15:55
 * @Description
 */
public interface IEvaluateService {
    /**
     * 添加评论
     * @param user 评论者
     * @param evaluation
     * @return
     */
    UEvaluation addEvaluation(User user, UEvaluation evaluation);

    /**
     * 添加针对平台的意见
     * @param user
     * @param evaluation
     * @return
     */
    UEvaluation addSysEvaluation(User user, UEvaluation evaluation);

    UEvaluation updateEvaluation(User user, UEvaluation evaluation);

    UEvaluation getEvaluation(String id);


    /**
     * 获取发出的全部评价
     * @param user
     * @return
     */
    List<UEvaluation> listEvaluation(User user);

    List<UEvaluation> listAcquired(User user);

    List<UEvaluation> listEvaluation(UEvaluation evaluation);

    /**
     * 更新帮助者好人值
     * @param evaluation
     */
    void updateUserNiceValue(UEvaluation evaluation);
}
