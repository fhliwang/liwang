package com.fh.shop.api.util;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;

import java.io.File;
import java.util.Date;
import java.util.UUID;

/**
 * @类描述：
 * @作者        ：李旺
 * @邮箱        : 888888888@qq.com
 * @创建日期    ：2018年11月08日 14:01
 */
public class UploadCosUtil {
    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        String s = "D:\\59fe6d3ca07aecaf0603cd1b_640.jpg";
        UploadCosUtil.getCosImger(s);
    }
    /**
     * cos图片上传工具类
     * @return
     */
    public static String getCosImger(String url){
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials("AKIDPfiX8VzZGhvvgDrbR7ZnnY5NQDm6u2e1", "HJ7fye8OQIvJSM1RcXDeqoY4LNAj26vV");
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig中包含了设置region, https(默认http), 超时, 代理等set方法, 使用可参见源码或者接口文档FAQ中说明
        ClientConfig clientConfig = new ClientConfig(new Region("ap-shanghai"));
        // 3 生成cos客户端
        COSClient cosClient = new COSClient(cred, clientConfig);
        // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
        String bucketName = "cos-1257662465";
        // 简单文件上传, 最大支持 5 GB, 适用于小文件上传, 建议 20M以下的文件使用该接口
        // 大文件上传请参照 API 文档高级 API 上传
        File localFile = new File(url);
        // 指定要上传到 COS 上对象键
        // 对象键（Key）是对象在存储桶中的唯一标识。例如，在对象的访问域名 `bucket1-1250000000.cos.ap-guangzhou.myqcloud.com/doc1/pic1.jpg` 中，对象键为 doc1/pic1.jpg, 详情参考 [对象键](https://cloud.tencent.com/document/product/436/13324)
        String key =DateUtil.date2String(new Date(),DateUtil.YMD)+"/"+UUID.randomUUID().toString()+".jpg";
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
        String result = "https://cos-1257662465.cos.ap-shanghai.myqcloud.com/"+key;
        return  result;
    }
}
