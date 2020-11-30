package com.fortuneloan.tenmileslotus.utils;

import android.os.CountDownTimer;

import com.fortuneloan.tenmileslotus.interfaces.OnCountDownTimerListener;

public class CountDownTimerUtils  {
    private static volatile CountDownTimerUtils instance = null;
    private OnCountDownTimerListener listener;
    private CountDownTimer timer;

    private CountDownTimerUtils() {
        //初始化倒计时
        initTimer();
    }

    public void setOnCountDownTimerListener(OnCountDownTimerListener listener) {
        this.listener = listener;
    }

    /**
     * 单例模式
     *
     * @return
     */
    public static CountDownTimerUtils getInstance() {
        // if already inited, no need to get lock everytime
        if (instance == null) {
            synchronized (CountDownTimerUtils.class) {
                if (instance == null) {
                    instance = new CountDownTimerUtils();
                }
            }
        }
        return instance;
    }

    private void initTimer() {
        timer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (listener != null) {
                    listener.onTick(millisUntilFinished);
                }
            }

            @Override
            public void onFinish() {
                if (listener != null) {
                    listener.onFinish();
                }
            }
        };
    }


    public void startTimer() {
        if (timer != null) {
            timer.start();
        }
    }

    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }
}
