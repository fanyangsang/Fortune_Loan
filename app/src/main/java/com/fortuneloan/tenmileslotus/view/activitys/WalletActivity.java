package com.fortuneloan.tenmileslotus.view.activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.request.base.Request;
import com.fortuneloan.tenmileslotus.R;
import com.fortuneloan.tenmileslotus.adapter.WalletBillAdapter;
import com.fortuneloan.tenmileslotus.base.BaseActivity;
import com.fortuneloan.tenmileslotus.bean.JsonBean;
import com.fortuneloan.tenmileslotus.bean.NewNoticeBean;
import com.fortuneloan.tenmileslotus.bean.WalletBean;
import com.fortuneloan.tenmileslotus.bean.WalletBillBean;
import com.fortuneloan.tenmileslotus.model.Api;
import com.fortuneloan.tenmileslotus.utils.HttpHeader;
import com.fortuneloan.tenmileslotus.utils.JsonCallBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class WalletActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_titles)
    TextView tvTitles;
    @BindView(R.id.tv_sumbit)
    TextView tvSubmit;
    @BindView(R.id.tv_available_credit)
    TextView tvAvailable;
    @BindView(R.id.tv_detailed)
    TextView tvDetailed;
    @BindView(R.id.tv_interest)
    TextView tvInterest;
    @BindView(R.id.tv_may_quota)
    TextView tvMayQuota;
    @BindView(R.id.iv_no_data)
    ImageView ivNoData;
    @BindView(R.id.tv_no_data)
    TextView tvNoData;
    @BindView(R.id.rc_wallet)
    RecyclerView rcWallet;
    @BindView(R.id.tv_bank_num)
    TextView tvBankNum;
    @BindView(R.id.rl_bank_card_open)
    RelativeLayout rlBankOpen;
    @BindView(R.id.tv_score)
    TextView tvScore;
    private ProgressDialog dialog;
    private WalletBillAdapter walletBillAdapter;
    private List<WalletBillBean.OrderBean> orderBeans = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.activity_wallet;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        getWalletData();
        tvTitles.setText("Wallet");
        rcWallet.setLayoutManager(new LinearLayoutManager(this));
        walletBillAdapter = new WalletBillAdapter(orderBeans);
        rcWallet.setAdapter(walletBillAdapter);
    }

    @Override
    protected void initListener() {

        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitData();
            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvBankNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //修改绑卡信息，可直接绕过审核
                Intent intent4 = new Intent(WalletActivity.this, BankInfoActivity.class);
                intent4.putExtra("isUpdate", true);
                startActivity(intent4);
            }
        });


    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        //初始化dialog
        if (dialog == null) {
            dialog = new ProgressDialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("Loading...");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        dialog = null;
    }

    /**
     * 请求钱包页面的数据
     */
    private void getWalletData() {
        HttpHeaders headers = HttpHeader.getHeader();
        OkGo.post(Api.WALLET_DATA)
                .headers(headers)
                .execute(new JsonCallBack() {
                    @Override
                    public void onStart(Request request) {
                        super.onStart(request);
                        if (dialog != null && !dialog.isShowing()) {
                            dialog.show();
                        }
                    }

                    @Override
                    public void onResponse(String json) {
                        getAccount();
                        Gson gson = new Gson();
                        WalletBean walletBean = gson.fromJson(json, WalletBean.class);
                        if ("200".equals(walletBean.getCode())) {
                            tvAvailable.setText("₹"+SPUtils.getInstance().getString("may_quota"));
                            tvDetailed.setText(walletBean.getTransfered() + "");
                            tvInterest.setText(walletBean.getTotalInterest() + "");
                            tvScore.setText(SPUtils.getInstance().getString("creditScore"));
                            // tvMayQuota.setText("₹" + walletBean.getOption().getMay_quota());
                            tvMayQuota.setText("₹"+SPUtils.getInstance().getString("may_quota"));
                            tvBankNum.setText(walletBean.getBank_card() + "");
                            //将银行卡账号存入缓存
                            SPUtils.getInstance().put("bank_card", walletBean.getBank_card());
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
     * 获取账单的信息
     */
    private void getAccount() {
        HttpHeaders headers = HttpHeader.getHeader();
        OkGo.post(Api.WALLET_BILL)
                .headers(headers)
                .execute(new JsonCallBack() {
                    @Override
                    public void onStart(Request request) {
                        super.onStart(request);
                    }

                    @Override
                    public void onResponse(String json) {
                        if (dialog != null && dialog.isShowing()) {
                            dialog.dismiss();
                        }
                        Gson gson = new Gson();
                        WalletBillBean walletBillBean = gson.fromJson(json, WalletBillBean.class);
                        if ("200".equals(walletBillBean.getCode())) {
                            if (walletBillBean.getOrder().size() > 0) {
                                ivNoData.setVisibility(View.GONE);
                                tvNoData.setVisibility(View.GONE);
                                rcWallet.setVisibility(View.VISIBLE);
                                walletBillAdapter.setNewData(walletBillBean.getOrder());
                            } else {

                                ivNoData.setVisibility(View.VISIBLE);
                                tvNoData.setVisibility(View.VISIBLE);
                                rcWallet.setVisibility(View.GONE);

                            }
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
     * 提交数据
     */
    private void submitData() {
        HttpHeaders headers = HttpHeader.getHeader();
        OkGo.post(Api.SUBMIT_WALLET)
                .headers(headers)
                .execute(new JsonCallBack() {
                    @Override
                    public void onStart(Request request) {
                        super.onStart(request);
                        if (dialog != null && !dialog.isShowing()) {
                            dialog.show();
                        }
                    }

                    @Override
                    public void onResponse(String json) {
                        if (dialog != null && dialog.isShowing()) {
                            dialog.dismiss();
                        }
                        Gson gson = new Gson();
                        JsonBean allDataBean = gson.fromJson(json, JsonBean.class);
                        if ("200".equals(allDataBean.getCode())) {
                            Intent intent = new Intent(WalletActivity.this, PayActivity.class);
                            startActivity(intent);
                            finish();
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


}
