package com.fortuneloan.tenmileslotus.view.activitys

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.blankj.utilcode.util.SPUtils
import com.fortuneloan.tenmileslotus.R
import com.fortuneloan.tenmileslotus.adapter.ProductAdapter
import com.fortuneloan.tenmileslotus.base.BaseActivity
import com.fortuneloan.tenmileslotus.bean.NewProductBean
import com.fortuneloan.tenmileslotus.bean.NewProductBean.DataBean.ProductBean
import com.fortuneloan.tenmileslotus.model.Api
import com.fortuneloan.tenmileslotus.utils.HttpHeader
import com.fortuneloan.tenmileslotus.utils.JsonCallBack
import com.fortuneloan.tenmileslotus.view.activitys.MainActivity
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.request.base.Request
import kotlinx.android.synthetic.main.activity_pay_success.*
import kotlinx.android.synthetic.main.layout_title.*
import kotlinx.android.synthetic.main.layout_title.iv_back
import java.util.*

class PaySuccessActivity : BaseActivity() {

    private var dialog: ProgressDialog? = null
    var productAdapter: ProductAdapter? = null
    private val productBeans: List<ProductBean> = ArrayList()
    override fun getLayout(): Int {
        return R.layout.activity_pay_success
    }

    override fun initView(savedInstanceState: Bundle?) {
        SPUtils.getInstance().put("isPay", true)
        productList
        rc_product?.layoutManager = LinearLayoutManager(this)
        productAdapter = ProductAdapter(productBeans)
        rc_product?.adapter = productAdapter
    }

    override fun initListener() {
        tv_sumbit?.setOnClickListener {
            //进行网络请求，从后台控制步骤
            val intent = Intent(this@PaySuccessActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        iv_back?.setOnClickListener { finish() }
    }

    override fun initData() {}
    override fun onResume() {
        super.onResume()
        //初始化dialog
        if (dialog == null) {
            dialog = ProgressDialog(this)
            dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog!!.setCanceledOnTouchOutside(false)
            dialog!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            dialog!!.setMessage("Loading...")
        }
    }

    override fun onPause() {
        super.onPause()
        dialog = null
    }

    private val productList: Unit
        private get() {
            val headers = HttpHeader.getHeader()
            OkGo.post<Any>(Api.LOAN_URL)
                    .headers(headers)
                    .execute(object : JsonCallBack() {
                        override fun onStart(request: Request<*, *>?) {
                            super.onStart(request)
                            if (dialog != null && !dialog!!.isShowing) {
                                dialog?.show()
                            }
                        }
                        override fun onResponse(json: String) {
                            if (dialog != null && dialog!!.isShowing) {
                                dialog?.dismiss()
                            }
                            val gson = Gson()
                            val newProductBean = gson.fromJson(json, NewProductBean::class.java)
                            productAdapter?.setNewData(newProductBean.data.product)
                        }
                        override fun onError(json: String) {}
                    })
        }
}