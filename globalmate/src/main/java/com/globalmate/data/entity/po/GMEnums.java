package com.globalmate.data.entity.po;

/**
 * @author XingJiajun
 * @Date 2018/7/10 15:38
 * @Description
 */
public class GMEnums {

    public enum NeedStatus {
        OPEN (1, "open"),   //开放
        RUN  (2, "run"),    //帮助中
        CLOSE(0, "close");  //关闭

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
        AGREE(1, "agree") {
            @Override
            public int getNeedStatus() {
                return NeedStatus.RUN.getCode();
            }
        },
        //拒绝
        REFUSE(-1, "refuse") {
            @Override
            public int getNeedStatus() {
                return NeedStatus.OPEN.getCode();
            }
        },
        //完成
        COMPLETE(0, "coplete") {
            @Override
           public int getNeedStatus() {
                return NeedStatus.CLOSE.getCode();
            }
        },
        //评价
        EVALUATION (2, "evaluation") {
            @Override
            public int getNeedStatus() {
                return NeedStatus.CLOSE.getCode();
            }
        };

        private int code;
        private String value;

        AssistAction(int code, String value) {
            this.code = code;
            this.value = value;
        }
        public String getValue() {
            return this.value;
        }

        public abstract int getNeedStatus();

    }

}