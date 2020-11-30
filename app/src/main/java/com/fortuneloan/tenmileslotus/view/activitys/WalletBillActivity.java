package com.fortuneloan.tenmileslotus.view.activitys;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.request.base.Request;
import com.fortuneloan.tenmileslotus.R;
import com.fortuneloan.tenmileslotus.adapter.WalletBillAdapter;
import com.fortuneloan.tenmileslotus.base.BaseActivity;
import com.fortuneloan.tenmileslotus.bean.WalletBillBean;
import com.fortuneloan.tenmileslotus.model.Api;
import com.fortuneloan.tenmileslotus.utils.HttpHeader;
import com.fortuneloan.tenmileslotus.utils.JsonCallBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class WalletBillActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_titles)
    TextView tvTitles;
    @BindView(R.id.rc_wallet_bill)
    RecyclerView rcWallet;

    private ProgressDialog dialog;
    private WalletBillAdapter walletBillAdapter;
    private List<WalletBillBean.OrderBean> orderBeans = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.activity_wallet_bill;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        getAccount();
        rcWallet.setLayoutManager(new LinearLayoutManager(this));
        walletBillAdapter = new WalletBillAdapter(orderBeans);
        rcWallet.setAdapter(walletBillAdapter);
    }

    @Override
    protected void initListener() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvTitles.setText("My Bill");
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
                        WalletBillBean walletBillBean = gson.fromJson(json, WalletBillBean.class);
                        if ("200".equals(walletBillBean.getCode())) {
                            walletBillAdapter.setNewData(walletBillBean.getOrder());
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
