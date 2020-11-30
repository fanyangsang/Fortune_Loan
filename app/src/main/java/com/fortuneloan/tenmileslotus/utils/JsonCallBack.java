package com.fortuneloan.tenmileslotus.utils;

import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.request.base.Request;

import java.io.IOException;

import okhttp3.Response;
import okhttp3.ResponseBody;

public abstract class JsonCallBack implements Callback {
    @Override
    public void onStart(Request request) {
    }

    @Override
    public void onSuccess(com.lzy.okgo.model.Response response) {
        if (response == null) {
            return;
        }
        if (response.getRawResponse() == null) {
            return;
        }

        ResponseBody body = response.getRawResponse().body();
        if (body == null) {
            return;
        }
        try {
            String string = body.string();
            onResponse(string);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onCacheSuccess(com.lzy.okgo.model.Response response) {

    }

    @Override
    public void onError(com.lzy.okgo.model.Response response) {
        if (response != null) {
            Response rawResponse = response.getRawResponse();
            if (rawResponse != null) {
                ResponseBody body = response.getRawResponse().body();
                if (body != null) {
                    try {
                        String string = body.string();
                        onError(string);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void onFinish() {

    }

    @Override
    public void uploadProgress(Progress progress) {

    }

    @Override
    public void downloadProgress(Progress progress) {

    }

    @Override
    public Object convertResponse(Response response) throws Throwable {
        return null;
    }

    public abstract void onResponse(String json);

    public abstract void onError(String json);
}
