package com.fortuneloan.tenmileslotus.view.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.Window;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.request.base.Request;
import com.fortuneloan.tenmileslotus.R;
import com.fortuneloan.tenmileslotus.adapter.ProductAdapter;
import com.fortuneloan.tenmileslotus.base.BaseFragment;
import com.fortuneloan.tenmileslotus.bean.JsonBean;
import com.fortuneloan.tenmileslotus.bean.NewProductBean;
import com.fortuneloan.tenmileslotus.model.Api;
import com.fortuneloan.tenmileslotus.utils.HttpHeader;
import com.fortuneloan.tenmileslotus.utils.JsonCallBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ProductFragment extends BaseFragment {

    @BindView(R.id.rc_product)
    RecyclerView rcProduct;
    private ProgressDialog dialog;
    ProductAdapter productAdapter;
    private boolean isVip;
    private List<NewProductBean.DataBean.ProductBean> productBeans = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.fragment_product;
    }

    @Override
    protected void initView(View view) {
        isVip = SPUtils.getInstance().getBoolean("is_vip", false);
        getProductList();
        rcProduct.setLayoutManager(new LinearLayoutManager(getContext()));
        productAdapter = new ProductAdapter(productBeans);
        rcProduct.setAdapter(productAdapter);
    }

    @Override
    protected void initListener(View view) {
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        //初始化dialog
        if (dialog == null) {
            dialog = new ProgressDialog(getContext());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("loading...");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        dialog = null;
    }

    private void getProductList() {
        HttpHeaders headers = HttpHeader.getHeader();
        OkGo.post(Api.LOAN_URL)
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
                        NewProductBean newProductBean = gson.fromJson(json, NewProductBean.class);
                        productAdapter.setNewData(newProductBean.getData().getProduct());
                        productAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                                Intent intent = new Intent(getContext(), WebActivity.class);
//                                intent.putExtra("url", newProductBean.getData().getProduct().get(position).getGoods_url());
//                                intent.putExtra("title", newProductBean.getData().getProduct().get(position).getGoods_name());
//                                startActivity(intent);
                                OkGo.post(Api.SEND_UV)
                                        .headers(headers)
                                        .params("extend_id", newProductBean.getData().getProduct().get(position).getProduct_id())
                                        .params("type", 1)
                                        .execute(new JsonCallBack() {
                                            @Override
                                            public void onResponse(String json) {
                                                Gson gson1 = new Gson();
                                                JsonBean dataBean = gson1.fromJson(json, JsonBean.class);
                                                if ("200".equals(dataBean.getCode())) {
                                                    Log.d("Pro", "执行了");
                                                    //跳转战外
                                                    Intent intent2 = new Intent();
                                                    intent2.setData(Uri.parse(newProductBean.getData().getProduct().get(position).getGoods_url()));
                                                    intent2.setAction(Intent.ACTION_VIEW);
                                                    startActivity(intent2);
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
                        });
                    }

                    @Override
                    public void onError(String json) {
                    }
                });
    }



}
