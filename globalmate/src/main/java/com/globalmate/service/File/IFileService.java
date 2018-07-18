package com.globalmate.service.File;

import com.alibaba.fastjson.JSONObject;

/**
 * @author XingJiajun
 * @Date 2018/7/18 16:01
 * @Description
 */
public interface IFileService {

    JSONObject getOSSPolicy();

    String getOSSDownloadUrl(String fileName);

}
