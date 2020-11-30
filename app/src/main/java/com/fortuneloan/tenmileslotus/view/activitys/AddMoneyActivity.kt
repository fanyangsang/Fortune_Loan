package com.fortuneloan.tenmileslotus.view.activitys

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.ToastUtils
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.request.base.Request
import com.fortuneloan.tenmileslotus.R
import com.fortuneloan.tenmileslotus.base.BaseActivity
import com.fortuneloan.tenmileslotus.bean.AddMoneyBean
import com.fortuneloan.tenmileslotus.model.Api
import com.fortuneloan.tenmileslotus.utils.HttpHeader
import com.fortuneloan.tenmileslotus.utils.JsonCallBack
import kotlinx.android.synthetic.main.activity_add_money.*
import kotlinx.android.synthetic.main.layout_title.*

class AddMoneyActivity : BaseActivity() {
    private var dialog: ProgressDialog? = null

    override fun getLayout(): Int {
        return R.layout.activity_add_money
    }

    override fun initView(savedInstanceState: Bundle?) {
        tv_titles!!.text = "Increase quota"
    }

    override fun initListener() {

        iv_face_button!!.setOnClickListener {
            val intent = Intent(this, FaceActivity::class.java)
            startActivity(intent)
        }

        iv_base_button!!.setOnClickListener {

            val tip = SPUtils.getInstance().getInt("is_tip", 0)

            if (tip ==0){
                ToastUtils.showShort("Please pass the face verification first!")
                return@setOnClickListener
            }

            val intent = Intent(this, BaseInfoActivity::class.java)
            startActivity(intent)
        }

        iv_bank_button!!.setOnClickListener {
            val tip = SPUtils.getInstance().getInt("is_tip", 0)
            if (tip ==0){
                ToastUtils.showShort("Please pass the face verification first!")
                return@setOnClickListener
            }else if (tip ==1){
                ToastUtils.showShort("Please complete your base information first!")
                return@setOnClickListener
            }

            val intent = Intent(this, BankInfoActivity::class.java)
            startActivity(intent)
        }
        iv_back!!.setOnClickListener { finish() }

        tv_draw_money!!.setOnClickListener {
            when (SPUtils.getInstance().getInt("is_tip", 0)) {
                0 -> {
                    val intent = Intent(this, FaceActivity::class.java)
                    startActivity(intent)
                }
                1 -> {
                    val intent = Intent(this, BaseInfoActivity::class.java)
                    startActivity(intent)
                }
                2 -> {
                    val intent = Intent(this, BankInfoActivity::class.java)
                    startActivity(intent)

                }
                3 -> {
                    if (SPUtils.getInstance().getBoolean("pay_switch", false)) {
                        val intent = Intent(this, AuthenticationActivity::class.java)
                        startActivity(intent)
                    } else {
                        SPUtils.getInstance().put("is_vip", true)
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }

                }
                4 -> {
                    if (SPUtils.getInstance().getBoolean("pay_switch", false)) {
                        val intent = Intent(this, WalletActivity::class.java)
                        startActivity(intent)
                    } else {
                        SPUtils.getInstance().put("is_vip", true)
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }

                }
            }

        }
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
        getMoney()


    }

    override fun onPause() {
        super.onPause()
        dialog = null
    }

    /**
     * 获取金额等数据
     */
    private fun getMoney() {
        val headers = HttpHeader.getHeader()
        OkGo.post<Any>(Api.ADD_MONEY)
                .headers(headers)
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
                        val dataBean = gson1.fromJson(json, AddMoneyBean::class.java)
                        if ("200" == dataBean.code) {
                            tv_may_quota!!.text = dataBean.data?.may_quota
                            val face = dataBean.data?.faceAuth_add.toString()
//                            val basic = dataBean.data?.basicInfo_add.toString()
//                            val bank = dataBean.data?.bank_add.toString()

                            val tip = SPUtils.getInstance().getInt("is_tip", 0)
                            when (tip) {
                                0 -> {
                                    //将第一次的额度存入缓存，审核页面将会用到
                                    createDialog(tv_may_quota.text.toString())
                                    SPUtils.getInstance().getString("min_quota", tv_may_quota.text.toString())
                                }
                                1 -> {
                                    iv_face_button!!.visibility = View.GONE
                                    tv_face_button!!.visibility = View.VISIBLE
                                    tv_face_button!!.text = "up to\n"+dataBean.data?.faceAuth_add
                                }
                                2 -> {
                                    iv_face_button!!.visibility = View.GONE
                                    tv_face_button!!.visibility = View.VISIBLE
                                    iv_base_button!!.visibility = View.GONE
                                    tv_basic_button!!.visibility = View.VISIBLE
                                    tv_face_button!!.text = "up to\n"+dataBean.data?.faceAuth_add
                                    tv_basic_button!!.text = "up to\n"+dataBean.data?.basicInfo_add
                                }
                                3 -> {
                                    iv_face_button!!.visibility = View.GONE
                                    tv_face_button!!.visibility = View.VISIBLE
                                    iv_base_button!!.visibility = View.GONE
                                    tv_basic_button!!.visibility = View.VISIBLE
                                    iv_bank_button!!.visibility = View.GONE
                                    tv_bank_button!!.visibility = View.VISIBLE
                                    tv_face_button!!.text = "up to\n"+dataBean.data?.faceAuth_add
                                    tv_basic_button!!.text = "up to\n"+dataBean.data?.basicInfo_add
                                    tv_bank_button!!.text = "up to\n"+dataBean.data?.bank_add
                                }
                            }
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
     * 首次进入，显示可借金额的弹窗
     */
    private fun createDialog( text: String) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Congratulations!\nYou get loan amount of "+text+".")
        builder.setNeutralButton("confirm", null)
        val dialog = builder.create()
        dialog.show()
    }





}