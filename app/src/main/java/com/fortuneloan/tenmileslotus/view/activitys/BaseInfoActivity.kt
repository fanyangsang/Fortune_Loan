package com.fortuneloan.tenmileslotus.view.activitys

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.text.TextUtils
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.Window
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import butterknife.BindView
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
import kotlinx.android.synthetic.main.activity_base_info.*
import kotlinx.android.synthetic.main.layout_title.*
import java.util.*

class BaseInfoActivity : BaseActivity() {

    private var value: String? = null
    private var dialog: ProgressDialog? = null
    private var bottomDialog: BottomDialog? = null
    var handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val str = msg.obj as String
            when (msg.what) {
                300 -> {
                    tv_married.text = str
                }
                400 -> {
                    tv_education.text = str
                }
                500 -> {
                    tv_gender.text = str
                }
            }
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_base_info
    }

    override fun initView(savedInstanceState: Bundle?) {
        tv_titles.text = "Basic Info"
    }

    override fun initListener() {
        iv_back!!.setOnClickListener { // finish();
            quitDialog()
        }
        tv_submit.setOnClickListener { submitData() }
        ll_married.setOnClickListener {
            val limits: MutableList<String> = ArrayList()
            limits.add("Single")
            limits.add("Married")
            limits.add("Divorced")
            limits.add("Widowed")
            limits(limits, 300)
        }
        ll_education.setOnClickListener {
            val limits: MutableList<String> = ArrayList()
            limits.add("University or above")
            limits.add("University")
            limits.add("High school")
            limits.add("Below high school")
            limits(limits, 400)
        }
        ll_gender.setOnClickListener {
            val limits: MutableList<String> = ArrayList()
            limits.add("Male")
            limits.add("Female")
            limits(limits, 500)
        }
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

    /**
     * 提交数据
     */
    private fun submitData() {
        if (TextUtils.isEmpty(tv_name.text.toString())) {
            ToastUtils.showShort("Please enter your name")
            return
        }
        if (TextUtils.isEmpty(tv_gender.text.toString())) {
            ToastUtils.showShort("Please select your gender")
            return
        }
        if (TextUtils.isEmpty(tv_birthday.text.toString())) {
            ToastUtils.showShort("Please enter your age")
            return
        }
        if (TextUtils.isEmpty(tv_married.text.toString())) {
            ToastUtils.showShort("Please select your marital status")
            return
        }
        if (TextUtils.isEmpty(tv_email!!.text.toString())) {
            ToastUtils.showShort("Please enter your email address")
            return
        }
        if (TextUtils.isEmpty(tv_education.text.toString())) {
            ToastUtils.showShort("Please enter your education information")
            return
        }

        //将姓名缓存起来
        SPUtils.getInstance().put("name", tv_name!!.text.toString())
        val headers = HttpHeader.getHeader()
        OkGo.post<Any>(Api.SUBMIT_BASIC)
                .headers(headers)
                .params("Name", tv_name.text.toString())
                .params("Gender", tv_gender.text.toString())
                .params("Age", tv_birthday.text.toString())
                .params("Marriage", tv_married.text.toString())
                .params("Education", tv_education.text.toString())
                .params("Email", tv_email.text.toString())
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
                            SPUtils.getInstance().put("is_tip", 1)
                            val intent = Intent(this@BaseInfoActivity, EmployActivity::class.java)
                            startActivity(intent)
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
        if (bottomDialog != null && bottomDialog!!.isShowing) {
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

    /**
     * 返回按钮对应的弹窗
     */
    private fun quitDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("A few simple steps left, do you really want to give up？")
        builder.setPositiveButton("Give up") { dialogInterface, i -> finish() }
        builder.setNeutralButton("Cancel", null)
        val dialog = builder.create()
        dialog.show()
    }

    /**
     * 监听物理返回键
     * @param keyCode
     * @param event
     * @return
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            quitDialog()
            return true //将事件拦截，避免直接退出
        }
        return super.onKeyDown(keyCode, event)
    }
}