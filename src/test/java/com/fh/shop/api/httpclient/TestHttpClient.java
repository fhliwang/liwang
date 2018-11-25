package com.fh.shop.api.httpclient;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.*;
import java.util.*;

/**
 * @类描述：
 * @作者        ：李旺
 * @邮箱        : 888888888@qq.com
 * @创建日期    ：2018年11月06日 11:48
 */
public class TestHttpClient {

    /**
     * 工具类测试接口
     */
    @Test
    public void sendTest2(){
        Map bodys = new HashMap();
        bodys.put("mobile","18853039289");
        bodys.put("templateid","9464419");
        bodys.put("codeLen","8");
        Map hends = new HashMap();
        String  appKey="803d0f61adbd1e8fbe59cd8c56c550a5";
        String nonce = RandomStringUtils.randomAlphanumeric(Integer.parseInt("8"));
        String curTime=new Date().getTime()+"";
        String  checkSum =  CheckSumBuilder.getCheckSum("b9af95abf538",nonce,curTime);
        hends.put("AppKey",appKey);
        hends.put("nonce",nonce);
        hends.put("CurTime",curTime);
        hends.put("CheckSum",checkSum);
        SendPostUtil.getSendPost("https://api.netease.im/sms/sendcode.action",bodys,hends);
    }


    /**
     * 使用httpClient调用网易云信接口
     */
    @Test
    public void sendTest(){
        /*创建client*/
        CloseableHttpClient client = HttpClientBuilder.create().build();
        /*请求类型*/
        HttpPost post = new HttpPost("https://api.netease.im/sms/sendcode.action");
        /*传递主体参数*/
        List<NameValuePair> pair = new ArrayList<NameValuePair>();
        /*手机号*/
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("mobile","18853039289");
        BasicNameValuePair basicNameValuePairs = new BasicNameValuePair("templateid","9464419");
        BasicNameValuePair basicNameValuePairss = new BasicNameValuePair("codeLen","8");
        pair.add(basicNameValuePair);
        pair.add(basicNameValuePairs);
        pair.add(basicNameValuePairss);
        try {
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(pair);
            post.setEntity(urlEncodedFormEntity);
          /*请求头信息*/
            String  appKey="803d0f61adbd1e8fbe59cd8c56c550a5";
            String nonce = RandomStringUtils.randomAlphanumeric(Integer.parseInt("8"));
            String curTime=new Date().getTime()+"";
            String  checkSum =  CheckSumBuilder.getCheckSum("b9af95abf538",nonce,curTime);
            post.addHeader("AppKey",appKey);
            post.addHeader("Nonce",nonce);
            post.addHeader("CurTime",curTime);
            post.addHeader("CheckSum",checkSum);
            CloseableHttpResponse execute = null;
                try {
                    /*执行*/
                    execute  = client.execute(post);
                    HttpEntity entity = execute.getEntity();
                    String s = EntityUtils.toString(entity, "utf-8");
                    System.out.println(s);
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(execute==null){
                        try {
                            execute.close();
                            execute=null;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                    if (post==null){
                        post.releaseConnection();
                    }
                    if (client==null){
                        try {
                            client.close();
                            client=null;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
    }
    /**
     * httpClient基本使用
     */
    @Test
    public void  test(){
        /*打开浏览器*/
        CloseableHttpClient build = HttpClientBuilder.create().build();
        /*输入url*/
        HttpGet get = new HttpGet("https://blog.csdn.net/edward_1996/article/details/77105055");
        /*敲回车*/
        CloseableHttpResponse response = null;
        String strFilename="D:\\java\\test.txt";
        try {
            response = build.execute(get);
            /*拿到数据*/
            String result = EntityUtils.toString(response.getEntity(),"utf-8");
                 // 创建文件对象
                File fileText = new File(strFilename);
                // 向文件写入对象写入信息
               FileWriter fileWriter = new FileWriter(fileText);
                // 写文件
               fileWriter.write(result);
                 // 关闭
                fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(response!=null){
                try {
                    response.close();
                    response=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            /*断开连接*/
            if (get!=null){
                get.releaseConnection();
            }
            if (build!=null){
                try {
                    build.close();
                    build=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
