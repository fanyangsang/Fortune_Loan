package com.fortuneloan.tenmileslotus.view.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.fortuneloan.tenmileslotus.R;
import com.fortuneloan.tenmileslotus.base.BaseActivity;
import com.fortuneloan.tenmileslotus.interfaces.OnCountDownTimerListener;
import com.fortuneloan.tenmileslotus.utils.CountDownTimerUtils90;

import butterknife.BindView;

public class PayFailedActivity extends BaseActivity{
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_sumbit)
    TextView tvSumbit;
    @BindView(R.id.tv_timing)
    TextView tvTiming;
    private String strHour;
    private String strMin;
    private String strSec;
    long millisInFuture;
    private CountDownTimerUtils90 instance;

    @Override
    protected int getLayout() {
        return R.layout.activity_pay_failed;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
    }

    @Override
    protected void initListener() {

        tvSumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //前往代超页面
                //返回支付页面
                Intent intent = new Intent(PayFailedActivity.this, PayActivity.class);
                startActivity(intent);
                finish();
            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();

        //如果缓存不为空且大于0，则用缓存的时间作为倒计时的时间，否则用1个半小时作为倒计时的时间
        long leftTime = SPUtils.getInstance().getLong("millisInFuture");
        if (leftTime > 1000) {
            millisInFuture = leftTime;
        } else {
            millisInFuture = 5400000;
        }
        instance = CountDownTimerUtils90.getInstance(millisInFuture);
        instance.setOnCountDownTimerListener(new OnCountDownTimerListener() {
            @Override
            public void onTick(long millisUntilFinished) {
                millisInFuture = millisUntilFinished;
                //将每时每刻的倒计时都存入缓存
                SPUtils.getInstance().put("millisInFuture", millisUntilFinished);
                long hour = millisUntilFinished / (1000 * 60 * 60);
                long min = (millisUntilFinished - hour * (1000 * 60 * 60)) / (1000 * 60);
                long sec = (millisUntilFinished - hour * (1000 * 60 * 60) - min * (1000 * 60)) / 1000;
                if (hour >= 0 && hour < 10) {
                    strHour = "0" + hour;
                }
                if (hour >= 10) {
                    strHour = hour + "";
                }
                if (min >= 0 && min < 10) {
                    strMin = "0" + min;
                }
                if (min >= 10) {
                    strMin = min + "";
                }
                if (sec >= 0 && sec < 10) {
                    strSec = "0" + sec;
                }
                if (sec >= 10) {
                    strSec = sec + "";
                }

                tvTiming.setText(strHour + ":\t" + strMin + ":\t" + strSec);
            }

            @Override
            public void onFinish() {
                tvTiming.setText("no time left");
            }
        });
        instance.startTimer();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        instance.stopTimer();
    }
}