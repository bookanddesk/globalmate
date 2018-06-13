package com.globalmate.common;

import com.globalmate.data.entity.po.JsonResult;
import com.globalmate.exception.ExceptionParser;
import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class JsonResultAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Around("execution(* com.globalmate.controller..*.*(..))")
    public Object jsonResultAround(ProceedingJoinPoint proceedingJoinPoint) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        long beginTime = System.nanoTime();
        Object result = null;
        try{
            result = proceedingJoinPoint.proceed();
        }catch (Throwable throwable){
            logger.error("JsonResult Controller error:", throwable);
            result = JsonResult.fail(ExceptionParser.getExcepitonMsg(throwable));
        }finally {
            if(result != null && result instanceof JsonResult){
                long costTime = (System.nanoTime() - beginTime) / 1000000L;
                JsonResult jsonResult = (JsonResult) result;
                jsonResult.setCostTime(costTime);
            }
        }
        return result;
    }

}
