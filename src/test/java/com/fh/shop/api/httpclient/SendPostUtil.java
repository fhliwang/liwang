package com.fh.shop.api.httpclient;

import com.qcloud.cos.utils.UrlEncoderUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @类描述：
 * @作者        ：李旺
 * @邮箱        : 888888888@qq.com
 * @创建日期    ：2018年11月06日 17:38
 */
public class SendPostUtil {


    /**
     *
     * @param url
     * @param bodys
     * @param heads
     * @return
     * getSendPost工具类
     */
    public static  String getSendPost(String url, Map<String,String> bodys,Map<String,String> heads){
        /*创建client*/
        CloseableHttpClient client = HttpClientBuilder.create().build();
        /*post请求*/
        HttpPost post = new HttpPost(url);
        /*map循环取值*/
        /*判断是否空*/
        if (bodys!=null && bodys.size()>0){
            Iterator<Map.Entry<String, String>> iterator = bodys.entrySet().iterator();
            List<NameValuePair> listpair = new ArrayList<NameValuePair>();
            /*循环map*/
            while (iterator.hasNext()){
                Map.Entry<String, String> next = iterator.next();
                String key = next.getKey();
                String value = next.getValue();
                listpair.add(new BasicNameValuePair(key,value));
                try {
                    UrlEncodedFormEntity urlEncodedFormEntity =new UrlEncodedFormEntity(listpair);
                    post.setEntity(urlEncodedFormEntity);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        /*头信息*/
        if (heads!=null && heads.size()>0){
            Iterator<Map.Entry<String, String>> iterator = heads.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<String, String> next = iterator.next();
                String key = next.getKey();
                String value = next.getValue();
                post.addHeader(key,value);
            }
        }
        String result="";
        CloseableHttpResponse response=null;
        /*执行*/
        try {
            response    = client.execute(post);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "utf-8");
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (response!=null){
                try {
                    response.close();
                    response=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if (post!=null){
                post.releaseConnection();
            }
            if (client!=null){
                try {
                    client.close();
                    client=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return  result;
    }
}
