package com.globalmate.data.entity.po;

import com.globalmate.uitl.StringUtils;

/**
 * @author XingJiajun
 * @Date 2018/7/10 15:38
 * @Description
 */
public class GMEnums {

    public enum NeedStatus {
        OPEN (1, "开放"),   //开放
        RUN  (2, "帮助中"),    //帮助中
        CLOSE(0, "关闭"),  //关闭

        EDIT  (3, "编辑中"),    //编辑中
        NEGOTIATE  (4, "洽谈中"),    //洽谈中
        EXECUTE  (5, "执行中"),    //执行中
        COMPLETED  (6, "已完成");    //已完成


        private int code;
        private String value;

        NeedStatus(int code, String value) {
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public int getCode() {
            return this.code;
        }

        public static NeedStatus transformCode(int code) {
            NeedStatus needStatus = null;
            for (NeedStatus status : NeedStatus.values()) {
                if (code == status.getCode()) {
                    needStatus = status;
                    break;
                }
            }
            return needStatus;
        }
    }

    public enum ServiceStatus {
        START(1, "start"),      //开始
        END  (0, "end"),        //结束
        STOP (-1, "stop");      //中止

        private int code;
        private String value;

        ServiceStatus(int code, String value) {
            this.code = code;
            this.value = value;
        }
        public String getValue() {
            return this.value;
        }
    }

    public enum AssistAction {
        //同意
        AGREE(1, "agree", "同意") {
            @Override
            public int getNeedStatus() {
                return NeedStatus.RUN.getCode();
            }
        },
        //拒绝
        REFUSE(-1, "refuse", "拒绝") {
            @Override
            public int getNeedStatus() {
                return NeedStatus.OPEN.getCode();
            }
        },
        //完成
        COMPLETE(0, "coplete", "完成") {
            @Override
           public int getNeedStatus() {
                return NeedStatus.CLOSE.getCode();
            }
        },
        //评价
        EVALUATION (2, "evaluation", "评价") {
            @Override
            public int getNeedStatus() {
                return NeedStatus.CLOSE.getCode();
            }
        };

        private int code;
        private String value;
        private String text;

        AssistAction(int code, String value, String text) {
            this.code = code;
            this.value = value;
            this.text = text;
        }
        public String getValue() {
            return this.value;
        }
        public String getText() {
            return this.text;
        }

        public abstract int getNeedStatus();

        public static AssistAction convertValue(String value) {
            AssistAction assistAction = null;
            for (AssistAction action : AssistAction.values()) {
                if (StringUtils.equalsIgnoreCase(action.getValue(), value)) {
                    assistAction = action;
                    break;
                }
            }
            return assistAction;
        }
    }

    public enum UserLinkType {
        ORGANIZATION(1, "organization");     //组织

        private int code;
        private String value;

        UserLinkType(int code, String value) {
            this.code = code;
            this.value = value;
        }
        public String getValue() {
            return this.value;
        }
    }

    public enum EvaluationType {
        PERSONAL(0, "personal"),    //对个人
        PLATFORM(1, "platform");     //对平台

        private int code;
        private String value;

        EvaluationType(int code, String value) {
            this.code = code;
            this.value = value;
        }
        public String getValue() {
            return this.value;
        }
    }

    public enum WXMsgType {
        SUBSCRIBE,    //关注
        MATCH;     //匹配
    }
    
    public enum UCertifyType {
    	
    	IDCARD,    //身份证
        PASSPORT,     //护照
        STUDENTID,    //学生证认证
        ALIPAYID;     //支付宝认证

    }
    
    public enum UCertifyEffectiveType {
    	
    	UNCHECKED("unchecked",0),    //未校验
    	PASS("pass",1),    //校验通过
    	NOTPASS("notpass",2);     //校验未通过

        private String code;
        private int value;

        UCertifyEffectiveType(String code, int value) {
            this.code = code;
            this.value = value;
        }
        public int getValue() {
            return this.value;
        }
    }

    /**
     * 用户关系类型
     */
    public enum UserRelationType {
        friend((short)0, "好友"),    //好友关系
        fans((short)1, "粉丝");     //粉丝关系

        private short code;
        private String value;

        UserRelationType(short code, String value) {
            this.code = code;
            this.value = value;
        }
        public String getValue() {
            return this.value;
        }
        public short getCode() {
            return this.code;
        }
    }

}
