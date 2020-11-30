package com.fortuneloan.tenmileslotus.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fortuneloan.tenmileslotus.R;
import com.fortuneloan.tenmileslotus.bean.WalletBillBean;

import java.util.List;

public class WalletBillAdapter extends BaseQuickAdapter<WalletBillBean.OrderBean, BaseViewHolder> {
    public WalletBillAdapter(@Nullable List<WalletBillBean.OrderBean> data) {
        super(R.layout.item_wallet, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, WalletBillBean.OrderBean item) {

        int length = item.getCreate_time().length();
        helper.setText(R.id.tv_amount, item.getOrder_loan_amount() + "")
                .setText(R.id.tv_time, item.getCreate_time().substring(5, length - 8))
                .setText(R.id.tv_text6_2, "Please wait for cash withdraw");
    }
}
