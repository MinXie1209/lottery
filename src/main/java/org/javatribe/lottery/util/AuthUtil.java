package org.javatribe.lottery.util;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName AuthUtil
 * @Description TODO
 * @Author 江南小俊
 * @Date 2018/11/8 11:15
 * @Version 1.0.0
 **/
public class AuthUtil {
    public static final String APPID = "wx82e9c3f495fcaac4";
    public static final String APPSERCRET = "44a6d1cc59bef4e10d758839e05a0fc8";


    public static JSONObject httpGet(String url) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);
        CloseableHttpResponse response = null;
        JSONObject jsonObject = null;
        try {
            response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String str = EntityUtils.toString(entity, "UTF-8");
                jsonObject = JSONObject.parseObject(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                response.close();
                response = null;
            }
            if (httpget != null) {
                httpget.releaseConnection();
                httpget = null;
            }
            if (httpclient != null) {
                httpclient.close();
                httpclient = null;
            }
        }


        return jsonObject;
    }
}
