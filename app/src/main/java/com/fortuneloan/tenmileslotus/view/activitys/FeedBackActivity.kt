package com.fortuneloan.tenmileslotus.view.activitys

import android.app.ProgressDialog
import android.os.Bundle
import android.view.Window
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.ToastUtils
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.request.base.Request
import com.fortuneloan.tenmileslotus.R
import com.fortuneloan.tenmileslotus.base.BaseActivity
import com.fortuneloan.tenmileslotus.bean.JsonBean
import com.fortuneloan.tenmileslotus.model.Api
import com.fortuneloan.tenmileslotus.utils.HttpHeader
import com.fortuneloan.tenmileslotus.utils.JsonCallBack
import kotlinx.android.synthetic.main.activity_feedback.*

class FeedBackActivity : BaseActivity() {
    private var dialog: ProgressDialog? = null
    override fun getLayout(): Int {
        return R.layout.activity_feedback
    }

    override fun initView(savedInstanceState: Bundle?) {
        SPUtils.getInstance().put("isWeb", true)
    }

    override fun initListener() {
        iv_back!!.setOnClickListener { finish() }
        tv_sumbit!!.setOnClickListener { submitQuestion(ed_opinion!!.text.toString()) }
    }

    override fun onResume() {
        super.onResume()
        //初始化dialog
        if (dialog == null) {
            dialog = ProgressDialog(this)
            dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog!!.setCanceledOnTouchOutside(false)
            dialog!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            dialog!!.setMessage("loading...")
        }
    }

    override fun onPause() {
        super.onPause()
        dialog = null
    }

    override fun initData() {}

    /**
     * 像后台提交问题反馈
     */
    private fun submitQuestion(text: String) {
        val headers = HttpHeader.getHeader()
        OkGo.post<Any>(Api.SUBMIT_QUESTION)
                .headers(headers)
                .params("content", text)
                .execute(object : JsonCallBack() {
                    override fun onStart(request: Request<*, *>?) {
                        super.onStart(request)
                        if (dialog != null && !dialog!!.isShowing) {
                            dialog!!.show()
                        }
                    }

                    override fun onResponse(json: String) {
                        if (dialog != null && dialog!!.isShowing) {
                            dialog!!.dismiss()
                        }
                        val gson1 = Gson()
                        val dataBean = gson1.fromJson(json, JsonBean::class.java)
                        if ("200" == dataBean.code) {
                            ToastUtils.showShort("Submit Success!")
                            finish()
                        }
                    }

                    override fun onError(json: String) {
                        if (dialog != null && dialog!!.isShowing) {
                            dialog!!.dismiss()
                        }
                    }
                })
    }
}