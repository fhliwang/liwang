package com.fh.shop.api.httpclient;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;


/**
 * @类描述：
 * @作者        ：李旺
 * @邮箱        : 888888888@qq.com
 * @创建日期    ：2018年11月06日 12:34
 */
public class TestHttpClient1 {
    @Test
    public void  test(){
        /*打开浏览器*/
        CloseableHttpClient build = HttpClientBuilder.create().build();
        /*输入url*/
        HttpGet get = new HttpGet("https://www.cnblogs.com/Lxiaojiang/p/6236913.html");
        CloseableHttpResponse execute = null;
        /*发送请求*/
        try {
             execute = build.execute(get);
            /*获取数据*/
          String  s = EntityUtils.toString(execute.getEntity(), "utf-8");
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (execute==null){
                try {
                    execute.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                execute=null;
            }
            if (get==null){
                get.releaseConnection();
            }
            if (build==null){}
            try {
                build.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            build=null;
        }
    }
}
