package com.example.ylb.common.util;


import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

/*
* 发送短信验证
* */
public class SendMsgUtils {

    private static  final String UID = "yangyunbin";//用户名
    private static  final String KEY = "55EFF78D2442AA07BE643088B8251CC2";//密钥

    public static void sendCode(String phone,String msgCode){

        try {
            HttpClient client = new HttpClient();
            PostMethod post = new PostMethod("https://utf8api.smschinese.cn/");
            post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");//在头文件中设置转码
            NameValuePair[] data ={ new NameValuePair(
                    "Uid", UID),
                    new NameValuePair("Key", KEY),
                    new NameValuePair("smsMob",phone),
                    new NameValuePair("smsText",msgCode)};
            post.setRequestBody(data);

            client.executeMethod(post);
            Header[] headers = post.getResponseHeaders();
            int statusCode = post.getStatusCode();
            System.out.println("statusCode:"+statusCode); //HTTP状态码
            for(Header h : headers){
                System.out.println(h.toString());
            }
            String result = new String(post.getResponseBodyAsString().getBytes("utf-8"));
            System.out.println(result);  //打印返回消息状态

            post.releaseConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


    public static void main(String[] args) {
        //sendCode("18069361256","验证码为：9999");
    }
}
