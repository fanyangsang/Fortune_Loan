package com.fortuneloan.tenmileslotus.view.activitys

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.KeyEvent
import android.view.Window
import android.widget.EditText
import android.widget.ImageView
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
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.request.base.Request
import kotlinx.android.synthetic.main.activity_bank.*
import kotlinx.android.synthetic.main.layout_title.*

class BankInfoActivity : BaseActivity() {

    private var dialog: ProgressDialog? = null
    private var isUpdate = false
    override fun getLayout(): Int {
        return R.layout.activity_bank
    }

    override fun initView(savedInstanceState: Bundle?) {
        tv_titles.text = "Bank Card Info"
        if (!TextUtils.isEmpty(SPUtils.getInstance().getString("name"))) {
            tv_name!!.setText(SPUtils.getInstance().getString("name"))
        }
        if (!TextUtils.isEmpty(SPUtils.getInstance().getString("bank_name"))) {
            tv_bank_name!!.setText(SPUtils.getInstance().getString("bank_name"))
        }
        if (!TextUtils.isEmpty(SPUtils.getInstance().getString("bank_card_number"))) {
            tv_birthday!!.setText(SPUtils.getInstance().getString("bank_card_number"))
        }
        isUpdate = intent.getBooleanExtra("isUpdate", false)
    }

    override fun initListener() {
        iv_back!!.setOnClickListener {
            if (isUpdate) {
                finish()
            } else {
                quitDialog()
            }
        }
        tv_submit!!.setOnClickListener {
            if (isUpdate) {
                updateData()
            } else {
                submitData()
            }
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
        if (TextUtils.isEmpty(tv_birthday.text.toString())) {
            ToastUtils.showShort("Please enter your account number")
        }
        if (TextUtils.isEmpty(tv_name.text.toString())) {
            ToastUtils.showShort("Please enter your name")
        }
        if (TextUtils.isEmpty(tv_bank_name.text.toString())) {
            ToastUtils.showShort("Please enter your bank name")
        }
        //缓存姓名
        SPUtils.getInstance().put("name", tv_name!!.text.toString())
        //缓存银行信息
        SPUtils.getInstance().put("bank_name", tv_bank_name!!.text.toString())
        //缓存银行卡信息
        SPUtils.getInstance().put("bank_card_number", tv_birthday!!.text.toString())
        val headers = HttpHeader.getHeader()
        OkGo.post<Any>(Api.SUBMIT_BANK)
                .headers(headers)
                .params("bank_card_number", tv_birthday.text.toString())
                .params("bank_name", tv_bank_name.text.toString())
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
                            SPUtils.getInstance().put("is_tip", 3)
                            val intent = Intent(this@BankInfoActivity, AuthenticationActivity::class.java)
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
     * 提交修改的信息
     */
    private fun updateData() {
        if (TextUtils.isEmpty(tv_birthday.text.toString())) {
            ToastUtils.showShort("Please enter your account number")
        }
        if (TextUtils.isEmpty(tv_name.text.toString())) {
            ToastUtils.showShort("Please enter your name")
        }
        if (TextUtils.isEmpty(tv_bank_name.text.toString())) {
            ToastUtils.showShort("Please enter your bank name")
        }
        //缓存姓名
        SPUtils.getInstance().put("name", tv_name!!.text.toString())
        //缓存银行信息
        SPUtils.getInstance().put("bank_name", tv_bank_name!!.text.toString())
        //缓存银行卡信息
        SPUtils.getInstance().put("bank_card_number", tv_birthday!!.text.toString())
        val headers = HttpHeader.getHeader()
        OkGo.post<Any>(Api.SUBMIT_BANK)
                .headers(headers)
                .params("bank_card_number", tv_birthday.text.toString())
                .params("bank_name", tv_bank_name.text.toString())
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
                            SPUtils.getInstance().put("is_tip", 4)
                            val intent = Intent(this@BankInfoActivity, WalletActivity::class.java)
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
     *
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