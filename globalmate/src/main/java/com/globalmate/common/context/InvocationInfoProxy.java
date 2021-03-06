package com.globalmate.common.context;

public class InvocationInfoProxy {

    private static InvocationInfoProxy infoProxy = new InvocationInfoProxy();

    private final static ThreadLocal<InvocationInfo> INFO_THREAD_LOCAL = new ThreadLocal<>();


    private InvocationInfoProxy(){}

    public static InvocationInfoProxy getInstance() {
        return infoProxy;
    }

    public static InvocationInfo getInvocationInfo() {
        InvocationInfo invocationInfo = INFO_THREAD_LOCAL.get();
        if (invocationInfo == null) {
            invocationInfo = new InvocationInfo();
            setInvocationInfo(invocationInfo);
        }
        return invocationInfo;
    }

    public static void setInvocationInfo(InvocationInfo invocationInfo) {
        INFO_THREAD_LOCAL.set(invocationInfo);
    }

    public static void setUserId(String userId) {
        getInvocationInfo().setUserId(userId);
    }

    public static String getUserId() {
        return getInvocationInfo().getUserId();
    }

    public static String getLocalStr() {
        InvocationInfo invocationInfo = getInvocationInfo();
        if (invocationInfo != null) {
            return invocationInfo.getLocalStr();
        }else {
            return null;
        }
    }

    public static void setLocalStr(String localStr) {
        InvocationInfo invocationInfo = getInvocationInfo();
        if (invocationInfo != null) {
            invocationInfo.setLocalStr(localStr);
        }else {
            invocationInfo = new InvocationInfo();
            setInvocationInfo(invocationInfo);
            invocationInfo.setLocalStr(localStr);
        }
    }

    public static void reset() {
        if (getInvocationInfo() != null) {
            setInvocationInfo(null);
        }
    }

}
