    package com.fortuneloan.tenmileslotus.view.activitys

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.text.*
import android.text.style.AbsoluteSizeSpan
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.Window
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.ToastUtils
import com.facebook.appevents.AppEventsConstants
import com.facebook.appevents.AppEventsLogger
import com.fortuneloan.tenmileslotus.R
import com.fortuneloan.tenmileslotus.base.BaseActivity
import com.fortuneloan.tenmileslotus.bean.JsonBean
import com.fortuneloan.tenmileslotus.bean.LoginBean
import com.fortuneloan.tenmileslotus.interfaces.OnCountDownTimerListener
import com.fortuneloan.tenmileslotus.model.Api
import com.fortuneloan.tenmileslotus.utils.CountDownTimerUtils90
import com.fortuneloan.tenmileslotus.utils.HttpHeader
import com.fortuneloan.tenmileslotus.utils.JsonCallBack
import com.fortuneloan.tenmileslotus.utils.Md5Utils
import com.fortuneloan.tenmileslotus.view.activitys.MainActivity
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.request.base.Request
import io.branch.referral.Branch
import io.branch.referral.util.BranchEvent
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {
    private var isSel = false
    private var dialog: ProgressDialog? = null
    private val userAgree: String? = null
    var spannableString: SpannableString? = null
    var spannableString1: SpannableString? = null
    var millisInFuture: Long = 60000
    private var instance: CountDownTimerUtils90? = null
    override fun getLayout(): Int {
        return R.layout.activity_login
    }

    override fun initView(savedInstanceState: Bundle?) {
        instance = CountDownTimerUtils90.getInstance(millisInFuture)
        instance!!.setOnCountDownTimerListener(object : OnCountDownTimerListener {
            override fun onTick(millisUntilFinished: Long) {
                millisInFuture = millisUntilFinished
                tv_code!!.isEnabled = false
                tv_code!!.text = (millisUntilFinished / 1000).toString() + "s"
            }

            override fun onFinish() {
                tv_code!!.isEnabled = true
                tv_code!!.text = "Send"
            }
        })

        //设置edittext的样式
        spannableString = SpannableString("Enter Mobile Number")
        spannableString1 = SpannableString("Enter OTP")
        val abs = AbsoluteSizeSpan(13, true)
        spannableString!!.setSpan(abs, 0, spannableString!!.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString1!!.setSpan(abs, 0, spannableString1!!.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        ed_mobile!!.hint = spannableString
        ed_code!!.hint = spannableString1
    }

    override fun onStop() {
        super.onStop()
        instance!!.stopTimer()
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

    override fun initListener() {
        tv_code!!.setOnClickListener { getCode() }


        iv_login!!.setOnClickListener { login() }


        tv_protocal!!.setOnClickListener {
            val intent = Intent(this@LoginActivity, WebActivity::class.java)
            intent.putExtra("url", Api.LOGIN_AGREE_MENT)
            intent.putExtra("title", "Privacy Agreement")
            intent.putExtra("notitle", true)
            startActivity(intent)
        }


        val drawableLeft = resources.getDrawable(
                R.drawable.icon_checked)
        val drawable = resources.getDrawable(
                R.drawable.icon_not_checked)
        isSel = true
        cb_agree!!.setOnClickListener {
            if (isSel) {
                cb_agree!!.setCompoundDrawablesWithIntrinsicBounds(drawable,
                        null, null, null)
            } else {
                cb_agree!!.setCompoundDrawablesWithIntrinsicBounds(drawableLeft,
                        null, null, null)
            }
            isSel = !isSel
        }

        ed_mobile.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                if (editable.length > 9) {
                    iv_login_checked.visibility = View.VISIBLE
                } else {
                    iv_login_checked.visibility = View.GONE
                }
            }
        })

        ed_mobile.setOnFocusChangeListener(OnFocusChangeListener { view, b ->
            if (b) {
                // 此处为得到焦点时的处理内容
                view_mobile.setBackgroundColor(resources.getColor(R.color.colorPurple))
            } else {
                // 此处为失去焦点时的处理内容
                view_mobile.setBackgroundColor(resources.getColor(R.color.colorCCC))
            }
        })

        ed_code.setOnFocusChangeListener(OnFocusChangeListener { view, b ->
            if (b) {
                // 此处为得到焦点时的处理内容
                view_otp.setBackgroundColor(resources.getColor(R.color.colorPurple))
            } else {
                // 此处为失去焦点时的处理内容
                view_otp.setBackgroundColor(resources.getColor(R.color.colorCCC))
            }
        })


    }

    override fun initData() {}

    /**
     * 获取验证码
     */
    private fun getCode() {
        if (TextUtils.isEmpty(ed_mobile!!.text.toString().trim { it <= ' ' })) {
            ToastUtils.showShort("Please enter correct mobile number!")
            return
        }
        if (ed_mobile!!.text.toString().length < 9) {
            ToastUtils.showShort("Please enter correct mobile number!")
            return
        }
        val deviceID = Settings.System.getString(this.contentResolver, Settings.System.ANDROID_ID)
        val num = ed_mobile!!.text.toString().trim { it <= ' ' }
        //将设备号存入缓存
        SPUtils.getInstance().put("deviceID", deviceID)
        SPUtils.getInstance().put("mobile", num)
        val str = "thunder2020"
        val str1 = Md5Utils.md5(str)
        val str2 = Md5Utils.md5(str1 + str)
        OkGo.post<Any>(Api.SEND)
                .headers("psd", str2)
                .params("mobile", num)
                .params("imei", deviceID)
                .execute(object : JsonCallBack() {
                    override fun onStart(request: Request<*, *>?) {
                        super.onStart(request)
                        tv_code!!.isEnabled = false
                        if (dialog != null && !dialog!!.isShowing) {
                            dialog!!.show()
                            dialog!!.setMessage("OTP sending...")
                        }
                    }

                    override fun onResponse(json: String) {
                        if (dialog != null && dialog!!.isShowing) {
                            dialog!!.dismiss()
                        }
                        val gson = Gson()
                        val jsonBean = gson.fromJson(json, JsonBean::class.java)
                        if ("200" == jsonBean.code) {
                            ToastUtils.showShort(jsonBean.msg)
                            instance!!.startTimer()
                        } else {
                            ToastUtils.showShort(jsonBean.msg)
                            tv_code!!.isEnabled = true
                        }
                    }

                    override fun onError(json: String) {}
                })
    }

    private fun login() {
        val headers = HttpHeader.getHeader()
        val phoneNum = ed_mobile!!.text.toString().trim { it <= ' ' }
        val imei = Settings.System.getString(this.contentResolver, Settings.System.ANDROID_ID)
        if (TextUtils.isEmpty(phoneNum)) {
//            mEditPhone.setError("请输入您的手机号");
//            mEditPhone.requestFocus();
            ToastUtils.showShort("Please enter correct mobile number")
            return
        }
        if (phoneNum.length < 9) {
            ToastUtils.showShort("Please enter correct mobile number")
            return
        }
        //        if (!ValidatePhoneUtil.validateMobileNumber(phoneNum)) {
//            mEditPhone.setError("请输入正确的手机号码"));
//            mEditPhone.requestFocus();
//            return;
//        }
        val smsCode = ed_code!!.text.toString().trim { it <= ' ' }
        if (TextUtils.isEmpty(smsCode)) {
//            mTvCode.setError("请输入验证码");
//            mTvCode.requestFocus();
            ToastUtils.showShort("Please enter OTP code")
            return
        }
        if (smsCode.length < 4 || smsCode.length > 6) {
            ToastUtils.showShort("Please enter correct OTP code")
            return
        }
        if (!isSel) {
            ToastUtils.showShort("Please agree the Terms and Conditions")
            return
        }
        OkGo.post<Any>(Api.LOGIN)
                .headers(headers)
                .params("mobile", phoneNum)
                .params("code", smsCode)
                .execute(object : JsonCallBack() {
                    override fun onStart(request: Request<*, *>?) {
                        super.onStart(request)
                        iv_login!!.isEnabled = false
                        if (dialog != null && !dialog!!.isShowing) {
                            dialog!!.show()
                        }
                    }

                    override fun onResponse(json: String) {
                        iv_login!!.isEnabled = true
                        if (dialog != null && dialog!!.isShowing) {
                            dialog!!.dismiss()
                        }
                        val gson = Gson()
                        val loginBean = gson.fromJson(json, LoginBean::class.java)
                        if ("200" == loginBean.code) {
                            //将token与uid存入缓存
                            SPUtils.getInstance().put("token", loginBean.data.token)
                            SPUtils.getInstance().put("uid", loginBean.data.uid)
                            //将已登录状态存入缓存
                            SPUtils.getInstance().put("isLogin", true)
                            Log.d("Login", "token为：" + loginBean.data.token)
                            Log.d("Login", "uid为：" + loginBean.data.uid)
                            //将认证的步骤存入缓存
                            SPUtils.getInstance().put("is_tip", loginBean.data.user_info.is_tip)
                            //将手机号存入缓存
                            SPUtils.getInstance().put("mobile", loginBean.data.user_info.mobile)
                            //根据字段去判断是否设置branch
                            //初始化头部请求
                            // HttpClient.getInstance().init(getApplication());
                            //设置branch
                            Branch.getInstance().setIdentity(loginBean.data.uid.toString() + "")
                            BranchEvent("user_login")
                                    .addCustomDataProperty("uid", loginBean.data.uid.toString() + "")
                                    .addCustomDataProperty("mobile", phoneNum)
                                    .setCustomerEventAlias("user_login")
                                    .logEvent(this@LoginActivity)
                            //设置facebook
                            if (1 == loginBean.data.user_info.is_new) {
                                val logger = AppEventsLogger.newLogger(this@LoginActivity)
                                //                                    logger.logEvent(AppEventsConstants.EVENT_NAME_COMPLETED_REGISTRATION);
                                val params = Bundle()
                                params.putString(AppEventsConstants.EVENT_PARAM_REGISTRATION_METHOD, "googleplay")
                                logger.logEvent(AppEventsConstants.EVENT_NAME_COMPLETED_REGISTRATION, params)
                            }
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                            // overridePendingTransition(R.anim.rotate_right, R.anim.rotate_left)
                            finish()
                        } else {
                            ToastUtils.showShort(loginBean.msg)
                        }
                    }

                    override fun onError(json: String) {
                        if (dialog != null && dialog!!.isShowing) {
                            dialog!!.dismiss()
                        }
                    }
                })
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) true else super.onKeyDown(keyCode, event)
    }
}
