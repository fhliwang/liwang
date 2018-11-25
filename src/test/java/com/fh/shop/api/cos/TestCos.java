package com.fh.shop.api.cos;


import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;

import java.io.File;

/**
 * @类描述：
 * @作者        ：李旺
 * @邮箱        : 888888888@qq.com
 * @创建日期    ：2018年11月06日 18:50
 */
public class TestCos {

    /**
     * 腾讯cos接口
     */
    public static void main(String[] args) {
        COSCredentials cred = new BasicCOSCredentials("AKIDPfiX8VzZGhvvgDrbR7ZnnY5NQDm6u2e1", "HJ7fye8OQIvJSM1RcXDeqoY4LNAj26vV");
        ClientConfig clientConfig = new ClientConfig(new Region("ap-shanghai"));
        COSClient cosClient = new COSClient(cred, clientConfig);
        String bucketName = "cos-1257662465";
        File localFile = new File("D:\\1f178a82b9014a902df55272a1773912b21bee32.jpg");
        String key = "99999999.jpg";
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
        // 关闭客户端(关闭后台线程)
        cosClient.shutdown();
    }
}
