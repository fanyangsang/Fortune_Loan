package com.fortuneloan.tenmileslotus.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fortuneloan.tenmileslotus.R;
import com.fortuneloan.tenmileslotus.bean.NewPayDataBean;

import java.util.List;

public class MoneyAdapter extends BaseQuickAdapter<NewPayDataBean.LoanInfoBean, BaseViewHolder> {
    public MoneyAdapter(@Nullable List<NewPayDataBean.LoanInfoBean> data) {
        super(R.layout.item_pay_money, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewPayDataBean.LoanInfoBean item) {
        helper.setText(R.id.tv_amount, "Borrow :\t" + item.getLoan_amount())
                .setText(R.id.tv_interest, "\t₹" + item.getAmount())
                .setText(R.id.tv_cycle, "\t" + item.getLoan_time());
    }
}
