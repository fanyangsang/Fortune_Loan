package com.fortuneloan.tenmileslotus.view.activitys

import android.app.ProgressDialog
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.blankj.utilcode.util.SPUtils
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.request.base.Request
import com.fortuneloan.tenmileslotus.R
import com.fortuneloan.tenmileslotus.adapter.HelpAdapter
import com.fortuneloan.tenmileslotus.base.BaseActivity
import com.fortuneloan.tenmileslotus.bean.HelpJsonBean
import com.fortuneloan.tenmileslotus.bean.HelpJsonBean.HelpBean
import com.fortuneloan.tenmileslotus.model.Api
import com.fortuneloan.tenmileslotus.utils.HttpHeader
import com.fortuneloan.tenmileslotus.utils.JsonCallBack
import kotlinx.android.synthetic.main.activity_help_center.*
import java.util.*

class HelpCenterActivity : BaseActivity() {

    private val helpCoreBeans: List<HelpBean> = ArrayList()
    private var helpAdapter: HelpAdapter? = null
    private var dialog: ProgressDialog? = null
    override fun getLayout(): Int {
        return R.layout.activity_help_center
    }

    override fun initView(savedInstanceState: Bundle?) {
        SPUtils.getInstance().put("isWeb", true)
        questionList
        rc_help!!.layoutManager = LinearLayoutManager(this)
        helpAdapter = HelpAdapter(helpCoreBeans)
        rc_help!!.adapter = helpAdapter
    }

    override fun initListener() {
        iv_back!!.setOnClickListener { finish() }
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
            dialog!!.setMessage("loading...")
        }
    }

    override fun onPause() {
        super.onPause()
        dialog = null
    }

    /**
     * 获取问答的列表
     */
    private val questionList: Unit
        private get() {
            val headers = HttpHeader.getHeader()
            OkGo.post<Any>(Api.QUESTION_ANSWER)
                    .headers(headers)
                    .execute(object : JsonCallBack() {
                        override fun onStart(request: Request<*, *>?) {
                            super.onStart(request)
                            if (!dialog!!.isShowing) {
                                dialog!!.show()
                            }
                        }

                        override fun onResponse(json: String) {
                            if (dialog!!.isShowing) {
                                dialog!!.dismiss()
                            }
                            val gson = Gson()
                            val helpJsonBean = gson.fromJson(json, HelpJsonBean::class.java)
                            if ("200" == helpJsonBean.code) {
                                helpAdapter!!.setNewData(helpJsonBean.help)
                            }
                        }

                        override fun onError(json: String) {
                            if (dialog!!.isShowing) {
                                dialog!!.dismiss()
                            }
                        }
                    })
        }
}