package com.fortuneloan.tenmileslotus.view.activitys;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.classic.common.MultipleStatusView;
import com.facebook.appevents.AppEventsLogger;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.fortuneloan.tenmileslotus.R;
import com.fortuneloan.tenmileslotus.adapter.MoneyAdapter;
import com.fortuneloan.tenmileslotus.bean.NewNoticeBean;
import com.fortuneloan.tenmileslotus.interfaces.OnCountDownTimerListener;
import com.fortuneloan.tenmileslotus.model.Api;
import com.fortuneloan.tenmileslotus.base.BaseActivity;
import com.fortuneloan.tenmileslotus.bean.JsonBean;
import com.fortuneloan.tenmileslotus.bean.NewPayDataBean;
import com.fortuneloan.tenmileslotus.bean.PayBean;
import com.fortuneloan.tenmileslotus.utils.CommonCallback;
import com.fortuneloan.tenmileslotus.utils.CountDownTimerUtils90;
import com.fortuneloan.tenmileslotus.utils.HttpHeader;
import com.fortuneloan.tenmileslotus.utils.JsonCallBack;

import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.branch.referral.util.BranchEvent;

public class PayActivity extends BaseActivity implements PaymentResultListener {
    @BindView(R.id.tv_titles)
    TextView tvTitles;
    @BindView(R.id.ll_submit)
    LinearLayout llSubmit;
    @BindView(R.id.cb_agree)
    TextView cbAgree;
    @BindView(R.id.tv_protocal)
    TextView tvProtocal;
    @BindView(R.id.ll_protocol)
    LinearLayout llProtocol;
    @BindView(R.id.rc_pay_money)
    RecyclerView rcPayMoney;
    @BindView(R.id.tv_pay_num)
    TextView tvPayNum;
    @BindView(R.id.tv_text8_2)
    TextView tvText8_2;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_timing)
    TextView tvTiming;
    @BindView(R.id.messsage_viewflipper)
    ViewFlipper viewFlipper;
    private String showAmountNum;

    private String payAmount; //支付的4个参数
    private String amount;
    private String orderId;
    private String day; //天数
    private boolean isSel;
    private boolean isRefre;
    private String userAgree;
    private String strHour;
    private String strMin;
    private String strSec;

    long millisInFuture;
    private CountDownTimerUtils90 instance;
    private ProgressDialog dialog;
    private MoneyAdapter moneyAdapter;
    private List<NewPayDataBean.LoanInfoBean> loanInfoBeans = new ArrayList<>();
    private String successText;
    private String failedText;
    private int paySize;
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100) {
                for (int i = 0; i < paySize; i++) {
                    LinearLayout ll = (LinearLayout) moneyAdapter.getViewByPosition(rcPayMoney, i, R.id.ll_pay_money);
                    TextView text1 = (TextView) moneyAdapter.getViewByPosition(rcPayMoney, i, R.id.tv_amount);
                    TextView text2 = (TextView) moneyAdapter.getViewByPosition(rcPayMoney, i, R.id.tv_interest);
                    TextView text3 = (TextView) moneyAdapter.getViewByPosition(rcPayMoney, i, R.id.tv_cycle);
                    TextView text4 = (TextView) moneyAdapter.getViewByPosition(rcPayMoney, i, R.id.tv_cycle_title);
                    TextView text5 = (TextView) moneyAdapter.getViewByPosition(rcPayMoney, i, R.id.tv_interest_title);
                    if (i != msg.arg1) {
                        if (ll != null) {
                            ll.setBackgroundResource(R.drawable.shape_pay_not_checked);
//                            text1.setTextColor(getResources().getColor(R.color.color989));
//                            text2.setTextColor(getResources().getColor(R.color.color989));
//                            text3.setTextColor(getResources().getColor(R.color.color989));
//                            text4.setTextColor(getResources().getColor(R.color.color989));
//                            text5.setTextColor(getResources().getColor(R.color.color989));
                        }

                    } else {
                        if (ll != null) {
                            ll.setBackgroundResource(R.drawable.bg_pay_checked);
//                            text1.setTextColor(getResources().getColor(R.color.colorWhite));
//                            text2.setTextColor(getResources().getColor(R.color.colorWhite));
//                            text3.setTextColor(getResources().getColor(R.color.colorWhite));
//                            text4.setTextColor(getResources().getColor(R.color.colorWhite));
//                            text5.setTextColor(getResources().getColor(R.color.colorWhite));
                        }
//                        text1.setTextColor(getResources().getColor(R.color.colorWhite));
//                        text1.setTextSize(20);
//                        text2.setVisibility(View.VISIBLE);
//                        text3.setVisibility(View.VISIBLE);
//                        text4.setVisibility(View.VISIBLE);
//                        text5.setVisibility(View.VISIBLE);
                        tvPayNum.setText(showAmountNum);
                    }
                }
            }
        }
    };

    @Override
    protected int getLayout() {
        return R.layout.activity_pay_new;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        isRefre = false;
        SPUtils.getInstance().put("isWeb", false);
        tvTitles.setText("Get Loan");
//        boolean isFromHome = getIntent().getBooleanExtra("fromHome", false);
//        if (isFromHome) {
//            SPUtils.getInstance().put("notPay", false);
//        }

        getPayData(false);
        LinearLayoutManager ms = new LinearLayoutManager(this);
//        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcPayMoney.setLayoutManager(ms);
        moneyAdapter = new MoneyAdapter(loanInfoBeans);
        rcPayMoney.setAdapter(moneyAdapter);
    }

    @Override
    protected void initListener() {

        tvProtocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PayActivity.this, WebActivity.class);
                intent.putExtra("url", Api.PAY_AGREEMENT);
                intent.putExtra("title", "Payment Agreement");
                intent.putExtra("notitle", true);
                startActivity(intent);
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // finish();
                quitDialog();
            }
        });

//        tvBankNum.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(PayActivity.this,BankActivity.class);
//                intent.putExtra("isUpdate",true);
//                startActivity(intent);
//            }
//        });

        llSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSel) {
                    pay();
                    // payDialog();
                } else {
                    ToastUtils.showShort("Please agree to the payment agreement！");
                    return;
                }
            }
        });

        final Drawable drawableLeft = getResources().getDrawable(
                R.drawable.icon_checked);
        final Drawable drawable = getResources().getDrawable(
                R.drawable.icon_not_checked);
        isSel = true;
        cbAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSel) {
                    cbAgree.setCompoundDrawablesWithIntrinsicBounds(drawable,
                            null, null, null);
                } else {
                    cbAgree.setCompoundDrawablesWithIntrinsicBounds(drawableLeft,
                            null, null, null);
                }
                isSel = !isSel;
            }
        });
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
        if (dialog == null) {
            dialog = new ProgressDialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("loading...");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        dialog = null;
    }

    @Override
    protected void onStop() {
        super.onStop();
        instance.stopTimer();
    }

    @Override
    protected void initData() {
    }

    /**
     * 请求支付接口的基本数据
     */
    private void getPayData(boolean isRefre) {
        HttpHeaders headers = HttpHeader.getHeader();
        OkGo.post(Api.NEW_PAY_DATA)
                .headers(headers)
                .execute(new JsonCallBack() {

                    @Override
                    public void onStart(Request request) {
                        super.onStart(request);
                        if (!isRefre) {
                            if (dialog != null && !dialog.isShowing()) {
                                dialog.show();
                            }
                        }
                    }

                    @Override
                    public void onResponse(String json) {
                        if (dialog != null && dialog.isShowing()) {
                            dialog.dismiss();
                        }
                        Gson gson = new Gson();
                        NewPayDataBean newPayDataBean = gson.fromJson(json, NewPayDataBean.class);
                        if ("200".equals(newPayDataBean.getCode())) {
                            getTips();
                            moneyAdapter.setNewData(newPayDataBean.getLoan_info());
                            tvText8_2.setText(newPayDataBean.getCopy().replace("\n\n", "\n") + "");
                            paySize = newPayDataBean.getLoan_info().size();
                            //默认选中第一组数据
                            showAmountNum = "₹" + newPayDataBean.getLoan_info().get(0).getAmount();
                            Message message = new Message();
                            message.what = 100;
                            message.arg1 = 0;
                            handler.sendMessage(message);
                            payAmount = newPayDataBean.getLoan_info().get(0).getAmount();
                            amount = newPayDataBean.getLoan_info().get(0).getLoan_amount();
                            day = newPayDataBean.getLoan_info().get(0).getLoan_time();

                            moneyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    showAmountNum = "₹" + newPayDataBean.getLoan_info().get(position).getAmount();
                                    //借款金额
                                    payAmount = newPayDataBean.getLoan_info().get(position).getAmount();
                                    amount = newPayDataBean.getLoan_info().get(position).getLoan_amount();
                                    day = newPayDataBean.getLoan_info().get(position).getLoan_time();
                                    Message message = new Message();
                                    message.what = 100;
                                    message.arg1 = position;
                                    handler.sendMessage(message);
                                }
                            });
                        } else {
                            ToastUtils.showShort(newPayDataBean.getMsg());
                        }
                    }

                    @Override
                    public void onError(String json) {
                        if (dialog != null && dialog.isShowing()) {
                            dialog.dismiss();
                        }
                    }
                });
    }


    @Override
    public void onPaymentSuccess(String s) {
        HttpHeaders headers = HttpHeader.getHeader();
        OkGo.<JsonBean>post(Api.PAY_RESULT)
                .headers(headers)
                .params("order_number", orderId)
                .params("pay_channel", "razorpay")
                .params("pay_msg", "SUCCESS")
                .params("pay_RZToken", s)
                .params("pay_result", "SUCCESS")//pay_result   支付结果    1 是成功   2 不
                .execute(new CommonCallback<JsonBean>(PayActivity.this) {
                    @Override
                    public void onSuccess(Response<JsonBean> response) {
                        super.onSuccess(response);
                        //配置branch
                        new BranchEvent("PaySuccess")
                                .addCustomDataProperty("uid", SPUtils.getInstance().getInt("uid") + "")
                                .addCustomDataProperty("orderid", orderId)
                                .addCustomDataProperty("mobile", SPUtils.getInstance().getString("mobile"))
                                .addCustomDataProperty("amount", amount + "")
                                .addCustomDataProperty("exorderid", "default")
                                .setCustomerEventAlias("PaySuccess")
                                .logEvent(PayActivity.this);
                        //配置facebook
                        Log.d("PayActivity", "买点执行了");
                        AppEventsLogger logger = AppEventsLogger.newLogger(PayActivity.this);
                        Bundle bundle = new Bundle();
                        bundle.putString("orderid", orderId);
                        bundle.putString("channel", "razorpay");
                        String newAmount = amount.substring(1, amount.length());
                        logger.logPurchase(BigDecimal.valueOf(Integer.parseInt(newAmount)), Currency.getInstance("INR"), bundle);
                        SPUtils.getInstance().put("isPay", true);
                        SPUtils.getInstance().put("notPay", false);
                        SPUtils.getInstance().put("is_vip", true);
                        //creatDialog();
//                        step();
//                        //进行网络请求，从后台控制步骤
//                        Intent intent = new Intent(NewPayActivity.this, MainActivity.class);
//                        startActivity(intent);
//                        NewPayActivity.this.finish();
                        Intent intent = new Intent(PayActivity.this, PaySuccessActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public JsonBean convertResponse(okhttp3.Response response) throws Throwable {
                        return JSON.parseObject(response.body().string(), JsonBean.class);
                    }
                });
    }

    @Override
    public void onPaymentError(int i, String s) {
        HttpHeaders headers = HttpHeader.getHeader();
        OkGo.<JsonBean>post(Api.PAY_RESULT)
                .headers(headers)
                .params("order_number", orderId)
                .params("pay_channel", "razorpay")
                .params("pay_msg", "CANCELL")
                .params("pay_RZToken", s)
                .params("pay_result", "CANCELL")//pay_result   支付结果    1 是成功   2 不
                .execute(new CommonCallback<JsonBean>(PayActivity.this) {
                    @Override
                    public void onSuccess(Response<JsonBean> response) {
                        super.onSuccess(response);
                        ToastUtils.showShort(s);
                       // creatDialog1();
                        //                       SPUtils.getInstance().put("notPay", true);
                        Intent intent = new Intent(PayActivity.this, PayFailedActivity.class);
                        startActivity(intent);

                    }

                    @Override
                    public JsonBean convertResponse(okhttp3.Response response) throws Throwable {
                        return JSON.parseObject(response.body().string(), JsonBean.class);
                    }
                });

    }

    /**
     * 支付
     */
    //支付功能
    private void pay() {
        HttpHeaders headers = HttpHeader.getHeader();
        OkGo.post(Api.GET_PAY_DATA)
                .headers(headers)
                .params("orderLoanType", 1 + "")
                .params("orderLoanAmount", amount.substring(1, amount.length())) //借款金额
                .params("orderAmount", payAmount) //支付的金额
                .params("orderLoanCycle", day)
                .execute(new JsonCallBack() {

                    @Override
                    public void onStart(Request request) {
                        super.onStart(request);
                        llSubmit.setEnabled(false);
                        if (dialog != null && !dialog.isShowing()) {
                            dialog.show();
                        }

                    }

                    @Override
                    public void onResponse(String json) {
                        llSubmit.setEnabled(true);
                        if (dialog != null && dialog.isShowing()) {
                            dialog.dismiss();
                        }
                        Gson gson = new Gson();
                        PayBean payBean = gson.fromJson(json, PayBean.class);
                        if ("200".equals(payBean.getCode())) {

                            Checkout checkout = new Checkout();
                            checkout.setKeyID(payBean.getOrderData().getAppId());
                            orderId = payBean.getOrderData().getOrderNumber();

                            /**
                             * Set your logo here
                             */
                            checkout.setImage(R.drawable.ic_logo);

                            /**
                             * Reference to current activity
                             */
//                                final Activity activity = this;
                            /**
                             * Pass your payment options to the Razorpay Checkout as a JSONObject
                             */
                            try {
                                JSONObject options = new JSONObject();
                                options.put("name", payBean.getOrderData().getName());
                                options.put("description", payBean.getOrderData().getDescription());
                                options.put("image", payBean.getOrderData().getImage());
                                options.put("order_id", payBean.getOrderData().getOrderNumber());
                                options.put("currency", payBean.getOrderData().getOrderCurrency());

                                /**
                                 * Amount is always passed in currency subunits
                                 * Eg: "500" = INR 5.00
                                 */

                                options.put("amount", payBean.getOrderData().getOrderAmount());
                                checkout.open(PayActivity.this, options);
                            } catch (Exception e) {
                                Log.e("StepPayActivity", "Error in starting Razorpay Checkout", e);
                            }
                        } else {
                            ToastUtils.showShort(payBean.getMsg());
                            Log.d("PayActivity", "信息为：" + payBean.getMsg());
                        }

                    }

                    @Override
                    public void onError(String json) {
                        if (dialog != null && dialog.isShowing()) {
                            dialog.dismiss();
                        }
                    }
                });
    }

    /**
     * 支付成功所对应的弹窗
     */

    private void creatDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(PayActivity.this);
        View v = LayoutInflater.from(PayActivity.this).inflate(R.layout.dialog_pay_result, null, false);
        ViewHolder viewHolder = new ViewHolder(v);
        builder.setView(v);
        final AlertDialog dialog = builder.create();
        final Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0));
        dialog.setCancelable(false);
        dialog.show();
        viewHolder.rlDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                //前往代超页面
                SPUtils.getInstance().put("isPay", true);
                //进行网络请求，从后台控制步骤
                Intent intent = new Intent(PayActivity.this, MainActivity.class);
                startActivity(intent);
                PayActivity.this.finish();
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text1)
        TextView tvText8;
        @BindView(R.id.rl_dialog)
        RelativeLayout rlDialog;


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    /**
     * 支付失败所对应的弹窗
     */

    private void creatDialog1() {
        AlertDialog.Builder builder = new AlertDialog.Builder(PayActivity.this);
        View v = LayoutInflater.from(PayActivity.this).inflate(R.layout.dialog_pay_result_1, null, false);
        ViewHolder1 viewHolder1 = new ViewHolder1(v);
        builder.setView(v);
        final AlertDialog dialog = builder.create();
        final Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0));
        dialog.setCancelable(false);
        dialog.show();
        viewHolder1.rlDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                //前往代超页面
            }
        });
    }


    class ViewHolder1 extends RecyclerView.ViewHolder {

        @BindView(R.id.text1)
        TextView tvText9;
        @BindView(R.id.rl_dialog)
        RelativeLayout rlDialog;

        ViewHolder1(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    /**
     * 返回按钮对应的弹窗
     */
    private void quitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Valid for 90 minutes, do you really want to give up the loan？");
        builder.setPositiveButton("Give up", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setNeutralButton("Cancel", null);
        final AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * 监听物理返回键
     *
     * @param keyCode
     * @param event
     * @return
     */

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            quitDialog();
            return true; //将事件拦截，避免直接退出
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 设置文字滚动
     */

    /**
     * 文字滚动
     */
    private void getTips() {
        HttpHeaders headers = HttpHeader.getHeader();
        OkGo.post(Api.TEXT_ROLL)
                .headers(headers)
                .execute(new JsonCallBack() {
                    @Override
                    public void onResponse(String json) {
                        Gson gson = new Gson();
                        NewNoticeBean newNoticeBean = gson.fromJson(json, NewNoticeBean.class);
                        // 设置viewFlipper
                        List<String> data = new ArrayList<>();
                        Animation anim_in_left = AnimationUtils.loadAnimation(PayActivity.this, R.anim.slide_in_left);//左进
                        Animation anim_out_right = AnimationUtils.loadAnimation(PayActivity.this, R.anim.slide_out_right);//右出
                        viewFlipper.setInAnimation(anim_in_left);//添加进入动画效果
                        viewFlipper.setOutAnimation(anim_out_right);//添加退出动画效果

                        for (int i = 0; i < newNoticeBean.getData().size(); i++) {
                            data.add(newNoticeBean.getData().get(i));
                        }
//
                        for (int i = 0; i < data.size(); i++) {//遍历图片资源
                            TextView textView = new TextView(PayActivity.this);//创建ImageView对象
                            textView.setText(data.get(i));//将遍历的图片保存在ImageView中
                            textView.setTextSize(13);
                            textView.setTextColor(getResources().getColor(R.color.colorTheme));
                            viewFlipper.addView(textView);//  //加载图片
                        }
                    }

                    @Override
                    public void onError(String json) {

                    }
                });

    }


}
