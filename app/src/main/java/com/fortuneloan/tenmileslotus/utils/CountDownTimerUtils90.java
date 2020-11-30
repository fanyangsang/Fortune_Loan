package com.fortuneloan.tenmileslotus.utils;

import android.os.CountDownTimer;

import com.fortuneloan.tenmileslotus.interfaces.*;

public class CountDownTimerUtils90{
    /**
     * 倒计时90分钟
     */
    private static volatile CountDownTimerUtils90 instance = null;
    private OnCountDownTimerListener    listener;
    private CountDownTimer timer;

    private CountDownTimerUtils90(long millisInFuture, long countDownInterval) {
        //初始化倒计时
        initTimer(millisInFuture, countDownInterval);
    }


    public void setOnCountDownTimerListener(OnCountDownTimerListener listener) {
        this.listener = listener;
    }

    /**
     * 单例模式
     *
     * @return
     */
    public static CountDownTimerUtils90 getInstance(long millisInFuture) {
        // if already inited, no need to get lock everytime
//        if (instance == null) {
//            synchronized (CountDownTimerUtils90.class) {
//                if (instance == null) {
                    instance = new CountDownTimerUtils90(millisInFuture, 1000);
//                }
//            }
//        }
        return instance;
    }

    private void initTimer(long millisInFuture, long countDownInterval) {
        timer = new CountDownTimer(millisInFuture,countDownInterval) {
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
