package com.fortuneloan.tenmileslotus.utils;

import com.blankj.utilcode.util.SPUtils;
import com.lzy.okgo.model.HttpHeaders;

public class HttpHeader {
    public static HttpHeaders getHeader(){
        HttpHeaders headers = new HttpHeaders();
        long time = System.currentTimeMillis();
        String sign = "uid=" + SPUtils.getInstance().getInt("uid")  + "+" + "token=" + SPUtils.getInstance().getString("token") + "+" + "time=" + time + "" + "gaifghfidasshfks";
        String md5 = Md5Utils.md5(sign);
        headers.put("uid", SPUtils.getInstance().getInt("uid") + "");
        headers.put("token", SPUtils.getInstance().getString("token"));
        headers.put("Accept-Language","zh-CN");
        headers.put("Connection" ,"keep-alive");
        headers.put("time" , time+"");
        headers.put("model" ,android.os.Build.MODEL);
        headers.put("sign", md5);
        headers.put("imei",SPUtils.getInstance().getString("imei"));
        headers.put("sessionId", SPUtils.getInstance().getString("sessionid"));
        headers.put("identityId",SPUtils.getInstance().getString("identityid"));
        headers.put("isapk","1");
        return headers;
    }
}
