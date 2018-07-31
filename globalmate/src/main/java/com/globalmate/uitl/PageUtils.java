package com.globalmate.uitl;

import com.github.pagehelper.PageHelper;

/**
 * @author XingJiajun
 * @Date 2018/7/26 21:06
 * @Description
 */
public class PageUtils {


    public static void startPage(Integer pageNumber, Integer pageSize,
                                 boolean count) {
        if (pageNumber == null || pageNumber <= 0) {
            pageNumber = DataStatus.PAGE_NUM_DEFAULT.getValue();
        }
        if (pageSize == null || pageSize < 0) {
            pageSize = DataStatus.PAGE_SIZE_DEFAULT.getValue();
        }
        if (pageSize == 0) {
            PageHelper.startPage(pageNumber, pageSize, count, null, true);
        } else {
            PageHelper.startPage(pageNumber, pageSize, count);
        }
    }

    public enum DataStatus {
        TEMPLATE_START_DATA_STATUS("启用", 1),
        TEMPLATE_STOP_DATA_STATUS("停用", 0),
        //数据状态，删除／未删除
        RECORD_STATUS_NORMAL("未删除",0),RECORD_STATUS_DELETE("已删除",1),
        PAGE_NUM_DEFAULT("默认页数",1),
        PAGE_SIZE_DEFAULT("默认每页记录数",10),
        ;
        private String name;
        private Integer value;
        private boolean ifHasPage;

        DataStatus(String name, Integer value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }

        public boolean getIsIfHasPage() {
            return ifHasPage;
        }

        public void setIfHasPage(boolean ifHasPage) {
            this.ifHasPage = ifHasPage;
        }
    }

}
