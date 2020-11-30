package com.fortuneloan.tenmileslotus.view.fragments

import android.app.ProgressDialog
import android.content.Intent
import android.view.View
import android.view.Window
import com.blankj.utilcode.util.SPUtils
import com.fortuneloan.tenmileslotus.R
import com.fortuneloan.tenmileslotus.base.BaseFragment
import com.fortuneloan.tenmileslotus.bean.NewHomeBean
import com.fortuneloan.tenmileslotus.bean.TouristDataBean
import com.fortuneloan.tenmileslotus.model.Api
import com.fortuneloan.tenmileslotus.utils.HttpHeader
import com.fortuneloan.tenmileslotus.utils.JsonCallBack
import com.fortuneloan.tenmileslotus.view.activitys.*
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.request.base.Request
import io.branch.referral.PrefHelper
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {

    private var dialog: ProgressDialog? = null
    override fun getLayout(): Int {
        return R.layout.fragment_home
    }

    override fun initView(view: View?) {
        val sessionid = PrefHelper.getInstance(context).sessionID
        val identityid = PrefHelper.getInstance(context).identityID
        SPUtils.getInstance().put("sessionid", sessionid)
        SPUtils.getInstance().put("identityid", identityid)

    }

    override fun initListener(view: View?) {
        tv_loan_now!!.setOnClickListener {
            val isLogin = SPUtils.getInstance().getBoolean("isLogin", false)
            if (!isLogin) {
                val intent = Intent(context, LoginActivity::class.java)
                startActivity(intent)
            } else {
                newStep
            }
        }
        sr_fresh!!.setColorSchemeResources(R.color.colorTheme)
        sr_fresh!!.setOnRefreshListener { //判断是否登录，若登录则用uid和token去请求数据，若未登录则不请求数据
            val isLogin = SPUtils.getInstance().getBoolean("isLogin", false)
            if (isLogin) {
                getLeastestData(true)
            } else {
                getTouristData(true)
            }
        }
    }

    override fun initData() {}
    override fun onResume() {
        super.onResume()
        //初始化dialog
        if (dialog == null) {
            dialog = ProgressDialog(context)
            dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog!!.setCanceledOnTouchOutside(false)
            dialog!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            dialog!!.setMessage("loading...")

            //判断是否登录，若登录则用uid和token去请求数据，若未登录则不请求数据
            val isLogin = SPUtils.getInstance().getBoolean("isLogin", false)
            if (isLogin) {
                getLeastestData(false)
            } else {
                getTouristData(false)
            }

        }
    }

    override fun onPause() {
        super.onPause()
        dialog = null
    }

    /**
     * 只要有步骤没走完，就一定跳到增加金额的界面
     *
     * @param
     */
    private val newStep: Unit
        private get() {
            val nextStep = SPUtils.getInstance().getInt("is_tip", 0)
            when (nextStep) {
                0 -> {
                    val intent = Intent(context, BaseInfoActivity::class.java)
                    startActivity(intent)
                }
                1 -> {
                    val intent2 = Intent(context, EmployActivity::class.java)
                    startActivity(intent2)
                }

                2 -> {
                    if (SPUtils.getInstance().getBoolean("pay_switch", false)) {
                        val intent = Intent(context, BankInfoActivity::class.java)
                        startActivity(intent)
                    } else {
                        SPUtils.getInstance().put("is_vip", true)
                        val intent = Intent(context, MainActivity::class.java)
                        startActivity(intent)
                    }
                }

                3 -> {
                    if (SPUtils.getInstance().getBoolean("pay_switch", false)) {
                        val intent = Intent(context, AuthenticationActivity::class.java)
                        startActivity(intent)
                    } else {
                        SPUtils.getInstance().put("is_vip", true)
                        val intent = Intent(context, MainActivity::class.java)
                        startActivity(intent)
                    }
                }

                4 -> {
                    if (SPUtils.getInstance().getBoolean("pay_switch", false)) {
                        val intent = Intent(context, WalletActivity::class.java)
                        startActivity(intent)
                    } else {
                        SPUtils.getInstance().put("is_vip", true)
                        val intent = Intent(context, MainActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
        }

    /**
     * 在403中获取游客的数据
     */
    private fun getTouristData(isRefresh: Boolean) {
        OkGo.post<Any>(Api.HOME_DATA)
                .headers("uid", "")
                .headers("token", "")
                .headers("imei", SPUtils.getInstance().getString("imei"))
                .headers("isapk", "1")
                .execute(object : JsonCallBack() {
                    override fun onStart(request: Request<*, *>?) {
                        super.onStart(request)
                        if (!isRefresh) {
                            if (dialog != null && !dialog!!.isShowing) {
                                dialog!!.show()
                            }
                        }
                    }

                    override fun onResponse(json: String) {
                        if (isRefresh) {
                            sr_fresh!!.isRefreshing = false
                        }
                        if (dialog != null && dialog!!.isShowing) {
                            dialog!!.dismiss()
                        }
                        tv_this_amount.visibility = View.VISIBLE
                        val gson = Gson()
                        val touristDataBean = gson.fromJson(json, TouristDataBean::class.java)
                        tv_max_amount.text = touristDataBean.data.can_quota.quota_value
                    }

                    override fun onError(json: String) {
//                        if (dialog != null && dialog.isShowing()) {
//                            dialog.dismiss();
//                        }
                    }
                })
    }

    /**
     * 新的首页数据的接口，下一版再开始用
     */
    private fun getLeastestData(isRefresh: Boolean) {
        val headers = HttpHeader.getHeader()
        OkGo.post<Any>(Api.HOME_DATA)
                .headers(headers)
                .execute(object : JsonCallBack() {
                    override fun onStart(request: Request<*, *>?) {
                        super.onStart(request)
                        if (!isRefresh) {
                            if (dialog != null && !dialog!!.isShowing) {
                                dialog!!.show()
                            }
                        }
                    }

                    override fun onResponse(json: String) {
                        if (isRefresh) {
                            sr_fresh!!.isRefreshing = false
                        }
                        if (dialog != null && dialog!!.isShowing) {
                            dialog!!.dismiss()
                        }
                        tv_this_amount.visibility = View.VISIBLE
                        val gson = Gson()
                        val newHomeBean = gson.fromJson(json, NewHomeBean::class.java)
                        if ("200" == newHomeBean.code) {
                            tv_max_amount.text = newHomeBean.range
                            if (1 == newHomeBean.switchX) {
                                SPUtils.getInstance().put("pay_switch", true) //支付开关的状态
                            }
                            SPUtils.getInstance().put("service_email", newHomeBean.email) //邮箱存入缓存
                            SPUtils.getInstance().put("may_quota", newHomeBean.loan.may_quota) //最大额度存入缓存

                            //设置进度
                            val nextStep = SPUtils.getInstance().getInt("is_tip", 0)
                            when (nextStep) {
                                0 -> {
                                }
                                1 -> {
                                    iv_home_checked_1.setImageResource(R.drawable.icon_checked)
                                    tv_personal.setTextColor(resources.getColor(R.color.colorTheme))
                                    rl_score.background = resources.getDrawable(R.drawable.pic_progress_2)
                                }

                                2 -> {
                                    iv_home_checked_1.setImageResource(R.drawable.icon_checked)
                                    iv_home_checked_2.setImageResource(R.drawable.icon_checked)
                                    tv_personal.setTextColor(resources.getColor(R.color.colorTheme))
                                    tv_employ.setTextColor(resources.getColor(R.color.colorTheme))
                                    rl_score.background = resources.getDrawable(R.drawable.pic_progress_3)
                                }

                                3 -> {
                                    iv_home_checked_1.setImageResource(R.drawable.icon_checked)
                                    iv_home_checked_2.setImageResource(R.drawable.icon_checked)
                                    iv_home_checked_3.setImageResource(R.drawable.icon_checked)
                                    tv_personal.setTextColor(resources.getColor(R.color.colorTheme))
                                    tv_employ.setTextColor(resources.getColor(R.color.colorTheme))
                                    tv_bank.setTextColor(resources.getColor(R.color.colorTheme))
                                    rl_score.background = resources.getDrawable(R.drawable.pic_progress_4)
                                }

                                4 -> {
                                    iv_home_checked_1.setImageResource(R.drawable.icon_checked)
                                    iv_home_checked_2.setImageResource(R.drawable.icon_checked)
                                    iv_home_checked_3.setImageResource(R.drawable.icon_checked)
                                    tv_personal.setTextColor(resources.getColor(R.color.colorTheme))
                                    tv_employ.setTextColor(resources.getColor(R.color.colorTheme))
                                    tv_bank.setTextColor(resources.getColor(R.color.colorTheme))
                                    rl_score.background = resources.getDrawable(R.drawable.pic_progress_5)
                                    tv_score.text = SPUtils.getInstance().getString("creditScore")
                                }
                            }


                        }
                    }

                    override fun onError(json: String) {}
                })
    }
}