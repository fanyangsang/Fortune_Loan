package com.fortuneloan.tenmileslotus;

import android.app.Application;
import android.view.Gravity;

import com.blankj.utilcode.util.ToastUtils;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.AppEventsLogger;

import io.branch.referral.Branch;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Branch.enableLogging();
        // Branch object initialization
        Branch.getAutoInstance(this);
        //HttpClient.getInstance().init(this);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        FacebookSdk.setIsDebugEnabled(true);
        FacebookSdk.addLoggingBehavior(LoggingBehavior.APP_EVENTS);
        //设置toast居中显示
        ToastUtils.setGravity(Gravity.CENTER,0,0);
    }
}
