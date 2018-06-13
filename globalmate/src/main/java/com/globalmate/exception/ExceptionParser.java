package com.globalmate.exception;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.MyBatisSystemException;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class ExceptionParser {

    public static String getExcepitonMsg(Throwable throwable){
        if(throwable == null){
            return null;
        }
        String message = throwable.getMessage();
        if(throwable instanceof NullPointerException){
            if(StringUtils.isBlank(message)){
                message = "空指针：" + getStackTraceMsg(getFirstStackTraceElement(throwable), false);
            }
        }else if(throwable instanceof IllegalArgumentException){
            message = "参数错误：" + message;
        }else if(throwable instanceof MyBatisSystemException){
            message = "数据库错误：" + message;
        }else if(throwable instanceof JedisConnectionException){
            message = "redis缓存错误：" + message;
        }else if(throwable instanceof RuntimeException) {
            if(StringUtils.isBlank(message)){
                message = "运行时错误！";
            }
        }else {
            if(StringUtils.isBlank(message)){
                message = "未知错误：" + getStackTraceMsg(getFirstStackTraceElement(throwable), false);
            }
        }
        return message;
    }

    private static StackTraceElement getFirstStackTraceElement(Throwable throwable){
        StackTraceElement stackTraceElement = null;
        if(throwable != null){
            StackTraceElement[] stackTrace = throwable.getStackTrace();
            if(stackTrace != null && stackTrace.length > 0){
                stackTraceElement = stackTrace[0];
            }
        }
        return stackTraceElement;
    }

    private static String getStackTraceMsg(StackTraceElement stackTraceElement, boolean confuse){
        String result = null;
        if(stackTraceElement != null){
            String className = stackTraceElement.getClassName(),
                    classSimpleName = className.substring(className.lastIndexOf(".")+1),
                    methodName = stackTraceElement.getMethodName();
            if(confuse){
                String classSimpleNameUppercase = new String();
                for(int i = 0; i < classSimpleName.length(); i ++){
                    char c = classSimpleName.charAt(i);
                    if(Character.isUpperCase(c)){
                        classSimpleNameUppercase += c;
                    }
                }
                if(classSimpleNameUppercase.length() > 0){
                    classSimpleName = classSimpleNameUppercase;
                }
                String methodNameUppercase = methodName.substring(0, 1);
                for(int i = 1; i < methodName.length(); i ++){
                    char c = methodName.charAt(i);
                    if(Character.isUpperCase(c)){
                        methodNameUppercase += c;
                    }
                }
                methodName = methodNameUppercase;
            }
            result = classSimpleName+"."+methodName+":"+stackTraceElement.getLineNumber();
        }
        return result;
    }
}
