package com.fortuneloan.tenmileslotus.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fortuneloan.tenmileslotus.R;
import com.fortuneloan.tenmileslotus.bean.NewProductBean;

import java.util.List;

public class ProductAdapter extends BaseQuickAdapter<NewProductBean.DataBean.ProductBean, BaseViewHolder> {

    public ProductAdapter(@Nullable List<NewProductBean.DataBean.ProductBean> data) {
        super(R.layout.item_product, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewProductBean.DataBean.ProductBean item) {
        Glide.with(mContext).load(item.getGoods_pic()).into((ImageView) helper.getView(R.id.iv_goods_image));
        helper.setText(R.id.tv_product_name,item.getGoods_name())
        .setText(R.id.tv_product_desc,item.getGoods_brief())
        .setText(R.id.tv_amount,"₹"+item.getAmount())
        .setText(R.id.tv_interest,item.getPeople_num());

    }
}
