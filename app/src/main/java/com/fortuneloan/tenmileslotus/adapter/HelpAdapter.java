package com.fortuneloan.tenmileslotus.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fortuneloan.tenmileslotus.R;
import com.fortuneloan.tenmileslotus.bean.HelpJsonBean;

import java.util.List;

import androidx.annotation.Nullable;

public class HelpAdapter extends BaseQuickAdapter<HelpJsonBean.HelpBean, BaseViewHolder> {
    public HelpAdapter(@Nullable List<HelpJsonBean.HelpBean> data) {
        super(R.layout.item_help_center, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, HelpJsonBean.HelpBean item) {
        helper.setText(R.id.tv_question,item.getProblem())
                .setText(R.id.tv_answer,item.getAnswer());
    }
}
