package com.globalmate.service.File;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.globalmate.uitl.GMConstant;
import org.apache.commons.codec.digest.HmacUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

/**
 * @author XingJiajun
 * @Date 2018/7/18 16:00
 * @Description
 */
@Service
public class FileService implements IFileService, InitializingBean {

    protected OSSClient ossClient;

    @Value("${bucket}")
    private String bucket;
    @Value("${endpoint}")
    private String endpoint;
    @Value("${accessKeyId}")
    private String accessKeyId;
    @Value("${accessKeySecret}")
    private String accessKeySecret;
    @Value("${host}")
    private String host;
    private long expireTime = 300;//单位：秒

    private static Logger logger = LoggerFactory.getLogger(FileService.class);


    public JSONObject getOSSPolicy(){
        JSONObject json = new JSONObject();

        try {
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);

            String dir = GMConstant.GM + "/";

            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");

            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = ossClient.calculatePostSignature(postPolicy);

            json.put("accessid", accessKeyId);
            json.put("policy", encodedPolicy);
            json.put("signature", postSignature);
            json.put("dir", dir);
            json.put("host", host);
            json.put("expire", String.valueOf(expireEndTime / 1000));
        } catch (Exception e) {
            logger.error("AliOSSClient-getOSSPolicy-error", e);
        }

        return json;
    }

    public String getOSSDownloadUrl(String ossName){
        JSONObject policy = getOSSPolicy();

        String valueToDigest = "GET" + "\n\n\n" + policy.getString("expire") + "\n" + "/" + bucket + "/" + ossName;
        byte[] digest = HmacUtils.hmacSha1(accessKeySecret, valueToDigest);
        String signature = BinaryUtil.toBase64String(digest);
        try {
            signature = URLEncoder.encode(signature, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
        }

        String url = host + "/" + ossName + "?OSSAccessKeyId=" + policy.getString("accessid") + "&Expires=" + policy.getString("expire") + "&Signature=" + signature;

        logger.info("OSS download url:" + url);

        return url;
    }

    @Override
    public void afterPropertiesSet() {
        ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }
}
