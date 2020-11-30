package com.fortuneloan.tenmileslotus.view.activitys

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.TextView
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.ToastUtils
import com.fortuneloan.tenmileslotus.R
import com.fortuneloan.tenmileslotus.base.BaseActivity
import com.fortuneloan.tenmileslotus.bean.JsonBean
import com.fortuneloan.tenmileslotus.model.Api
import com.fortuneloan.tenmileslotus.utils.BottomDialog
import com.fortuneloan.tenmileslotus.utils.HttpHeader
import com.fortuneloan.tenmileslotus.utils.JsonCallBack
import com.fortuneloan.tenmileslotus.utils.WheelView
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.request.base.Request
import kotlinx.android.synthetic.main.activity_employment.*
import kotlinx.android.synthetic.main.layout_title.*
import java.util.*

class EmployActivity : BaseActivity() {
    private var dialog: ProgressDialog? = null
    private var bottomDialog: BottomDialog? = null
    private var value: String? = null

    var handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val str = msg.obj as String
            when (msg.what) {
                100 -> {
                    tv_career.text = str
                }
                200 -> {
                    tv_income.text = str
                }
                300 -> {
                    tv_work_ages.text = str
                }
            }
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_employment
    }

    override fun initView(savedInstanceState: Bundle?) {
        tv_titles.text = "Employment Info"

    }

    override fun initListener() {

        ll_career.setOnClickListener {
            val limits: MutableList<String> = ArrayList()
            limits.add("Full Time")
            limits.add("Part Time")
            limits.add("Free Lancer")
            limits.add("No Work")
            limits(limits, 100)
        }

        ll_income.setOnClickListener {
            val limits: MutableList<String> = ArrayList()
            limits.add("Less than 10000")
            limits.add("10,000~15,000")
            limits.add("15,000~20,000")
            limits.add("20,000~3,0000")
            limits.add("More than 30,000")
            limits(limits, 200)
        }

        ll_working_age.setOnClickListener {
            val limits: MutableList<String> = ArrayList()
            limits.add("Less than 1 years")
            limits.add("1-3 years")
            limits.add("3-5 years")
            limits.add("5-10 years")
            limits.add("More than 10 years")
            limits(limits, 300)
        }

        tv_submit.setOnClickListener { updateData() }

    }

    override fun initData() {
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

    /**
     * 提交就业信息的数据
     */
    private fun updateData(): Unit {
        if (TextUtils.isEmpty(tv_career.text.toString())) {
            ToastUtils.showShort("Please select your career")
            return
        }
        if (TextUtils.isEmpty(tv_income.text.toString())) {
            ToastUtils.showShort("Please select your income monthly")
            return
        }
        if (TextUtils.isEmpty(tv_work_ages.text.toString())) {
            ToastUtils.showShort("Please enter your bank name")
            return
        }
        val headers = HttpHeader.getHeader()
        OkGo.post<Any>(Api.SUBMIT_EMPLOY)
                .headers(headers)
                .params("basic_monthly_income", tv_income.text.toString())
                .params("occupation", tv_career.text.toString())
                .params("basic_total_employment", tv_work_ages.text.toString())
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
                        val gson = Gson()
                        val allDataBean = gson.fromJson(json, JsonBean::class.java)
                        if ("200" == allDataBean.code) {
                            ToastUtils.showShort("Submit Success")
                            SPUtils.getInstance().put("is_tip", 2)
                            //判断支付状态是否开启
                            if (SPUtils.getInstance().getBoolean("pay_switch", false)) {
                                val intent = Intent(this@EmployActivity, BankInfoActivity::class.java)
                                startActivity(intent)
                            } else {
                                SPUtils.getInstance().put("is_vip", true)
                                val intent = Intent(this@EmployActivity, MainActivity::class.java)
                                startActivity(intent)
                            }
                            finish()
                        } else {
                            ToastUtils.showShort(allDataBean.msg)
                        }
                    }

                    override fun onError(json: String) {
                        if (dialog != null && dialog!!.isShowing) {
                            dialog!!.dismiss()
                        }
                    }
                })
    }

    /**
     * 弹出选择框
     */
    private fun limits(limits: List<String>, what: Int) {
        if (bottomDialog != null && bottomDialog!!.isShowing()) {
            return
        }
        val outerView1 = LayoutInflater.from(this).inflate(R.layout.dialog_repayment, null)
        val wv1: WheelView = outerView1.findViewById(R.id.wv1)
        wv1.setItems(limits, 0)
        val tv_ok = outerView1.findViewById<TextView>(R.id.tv_ok)
        val tv_cancel = outerView1.findViewById<TextView>(R.id.tv_cancel)
        tv_ok.setOnClickListener {
            bottomDialog!!.dismiss()
            val nTimeLimit = wv1.selectedItem
            for (i in limits.indices) {
                if (limits[i] == nTimeLimit) {
                    value = limits[i]
                    break
                }
            }
            val message = Message()
            message.what = what
            message.obj = value
            handler.sendMessage(message)
        }
        tv_cancel.setOnClickListener { bottomDialog!!.dismiss() }
        bottomDialog = BottomDialog(this, R.style.ActionSheetDialogStyle)
        bottomDialog!!.setContentView(outerView1)
        bottomDialog!!.show()
    }


}