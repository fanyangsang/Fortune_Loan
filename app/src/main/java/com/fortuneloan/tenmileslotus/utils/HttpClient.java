package com.fortuneloan.tenmileslotus.utils;

import android.app.Application;
import android.util.Log;

import com.blankj.utilcode.util.SPUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.MemoryCookieStore;
import com.lzy.okgo.model.HttpHeaders;

import java.util.concurrent.TimeUnit;

import io.branch.referral.PrefHelper;
import okhttp3.OkHttpClient;

public class HttpClient {


    private static final int TIMEOUT = 10000;
    private static HttpClient sInstance;
    private OkHttpClient mOkHttpClient;
    private String mLanguage;//语言
    private String mUrl = "http://192.168.8.114/api/";

    public static HttpClient getInstance() {
        if (sInstance == null) {
            synchronized (HttpClient.class) {
                if (sInstance == null) {
                    sInstance = new HttpClient();
                }
            }
        }
        return sInstance;
    }

    public void init(Application applicationContext) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS);
        builder.readTimeout(TIMEOUT, TimeUnit.MILLISECONDS);
        builder.writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS);
        //使用内存保持cookie，app退出后，cookie消失
        builder.cookieJar(new CookieJarImpl(new MemoryCookieStore()));
//        //使用sp保持cookie，如果cookie不过期，则一直有效
//        builder.cookieJar(new CookieJarImpl(new SPCookieStore(CommonAppContext.sInstance)));
//        //使用数据库保持cookie，如果cookie不过期，则一直有效
//        builder.cookieJar(new CookieJarImpl(new DBCookieStore(CommonAppContext.sInstance)));
        builder.retryOnConnectionFailure(true);
//        Dispatcher dispatcher = new Dispatcher();
//        dispatcher.setMaxRequests(20000);
//        dispatcher.setMaxRequestsPerHost(10000);
//        builder.dispatcher(dispatcher);
        mOkHttpClient = builder.build();
        String sessionid = PrefHelper.getInstance(applicationContext).getSessionID();
        String identityid = PrefHelper.getInstance(applicationContext).getIdentityID();
        HttpHeaders headers = new HttpHeaders();
        headers.put("Accept-Language", "zh-CN");
        headers.put("Connection", "keep-alive");
        headers.put("sessionId", sessionid);
        headers.put("identityId", identityid);
        headers.put("uid", SPUtils.getInstance().getInt("uid") + "");
        headers.put("token", SPUtils.getInstance().getString("token"));
        long time = System.currentTimeMillis();
        headers.put("time", time+ "");
        String sign = "uid=" + SPUtils.getInstance().getInt("uid")  + "+" + "token=" + SPUtils.getInstance().getString("token") + "+" + "time=" + time + "" + "gaifghfidasshfks";
        String md5 = Md5Utils.md5(sign);
        headers.put("sign", md5);
        headers.put("model", android.os.Build.MODEL);
        OkGo.getInstance().init(applicationContext)
                .setOkHttpClient(mOkHttpClient)
                .setCacheMode(CacheMode.NO_CACHE)
                .addCommonHeaders(headers)
                .setRetryCount(1);
    }


    public void cancel(String tag) {
        OkGo.cancelTag(mOkHttpClient, tag);
    }

    public void setLanguage(String language) {
        mLanguage = language;
    }
}
