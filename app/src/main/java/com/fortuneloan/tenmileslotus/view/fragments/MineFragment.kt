package com.fortuneloan.tenmileslotus.view.fragments

import android.app.AlertDialog
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.ToastUtils
import com.fortuneloan.tenmileslotus.R
import com.fortuneloan.tenmileslotus.base.BaseFragment
import com.fortuneloan.tenmileslotus.view.activitys.*
import kotlinx.android.synthetic.main.dialog_email.*
import kotlinx.android.synthetic.main.fragment_mine.*

class MineFragment : BaseFragment() {

    override fun getLayout(): Int {
        return R.layout.fragment_mine
    }

    override fun initView(view: View) {
    }

    override fun initListener(view: View) {

        rl_about_us!!.setOnClickListener {
            val intent = Intent(context, AboutActivity::class.java)
            startActivity(intent)
        }

        rl_feedback!!.setOnClickListener {
            val intent1 = Intent(context, FeedBackActivity::class.java)
            startActivity(intent1)
        }

        rl_help_center!!.setOnClickListener {
            val intent2 = Intent(context, HelpCenterActivity::class.java)
            startActivity(intent2)
        }

        rl_service_email!!.setOnClickListener {
            creatDialog1("Service Email", SPUtils.getInstance().getString("service_email"))
        }

        tv_bank_card!!.setOnClickListener {

            val nextStep = SPUtils.getInstance().getInt("is_tip", 0)
            when {
                nextStep < 2 -> {
                    newStep
                }
                nextStep == 2 -> {
                    val intent3 = Intent(context, BankInfoActivity::class.java)
                    startActivity(intent3)
                }

                nextStep == 3 -> {
                   newStep
                }

                nextStep >3 -> {
                    //修改绑卡信息，可直接绕过审核
                    val intent4 = Intent(context, BankInfoActivity::class.java)
                    intent4.putExtra("isUpdate", true)
                    startActivity(intent4)
                }
            }

        }

        tv_wallet!!.setOnClickListener {newStep }

        rl_exit.setOnClickListener { quitDialog() }

        tv_credit.setOnClickListener {
            val nextStep = SPUtils.getInstance().getInt("is_tip", 0)
            when{
                nextStep <= 3 ->{
                    ToastUtils.showShort("Please complete the information authentication first!")
                }

                nextStep > 3 ->{
                    val intent = Intent(context,CreditActivity::class.java)
                    startActivity(intent)
                }
            }

        }

    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            if (SPUtils.getInstance().getBoolean("pay_switch", false)) {
                tv_wallet!!.visibility = View.VISIBLE
                tv_bank_card!!.visibility = View.VISIBLE
            } else {
                tv_wallet!!.visibility = View.GONE
                tv_bank_card!!.visibility = View.GONE
            }

            if (!TextUtils.isEmpty(SPUtils.getInstance().getString("mobile"))) {
                tv_mobile!!.text = SPUtils.getInstance().getString("mobile")
            }
        }

    }

    override fun initData() {}

    /**
     * 点击弹出email地址的弹窗
     */
    private fun creatDialog1(title: String, text: String) {
        val builder = AlertDialog.Builder(context)
        val v = LayoutInflater.from(context).inflate(R.layout.dialog_email, null, false)
        val viewHolder1 = ViewHolder(v)
        builder.setView(v)
        val dialog = builder.create()
        dialog.show()
        viewHolder1.tvSend!!.text = "" + title
        viewHolder1.tvEmail!!.text = "" + text
        viewHolder1.tvEmail!!.setOnClickListener { dialog.dismiss() }
    }

    internal inner class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        var tvEmail: TextView? = null
        var tvSend: TextView? = null

        init {
            tvEmail = view?.findViewById(R.id.tv_email) as TextView
            tvSend = view?.findViewById(R.id.tv_send) as TextView
        }
    }

    /**
     * 退出登录的弹窗
     */
    private fun creatDialog2() {
        val builder = AlertDialog.Builder(context)
        val v = LayoutInflater.from(context).inflate(R.layout.dialog_quit_confirm, null, false)
        val viewHolder2 = ViewHolder2(v)
        builder.setView(v)
        val dialog = builder.create()
        val window = dialog.window
        window!!.setBackgroundDrawable(ColorDrawable(0))
        dialog.show()
        viewHolder2.llQuit!!.setOnClickListener { //将登录状态设为未登录
            SPUtils.getInstance().put("isLogin", false)
            SPUtils.getInstance().put("is_vip", false)
            SPUtils.getInstance().put("notPay", false)
            SPUtils.getInstance().getString("name", "")
            SPUtils.getInstance().put("is_tip", 0)
            //缓存银行信息
            SPUtils.getInstance().put("bank_name", "")
            //缓存银行卡信息
            SPUtils.getInstance().put("bank_card_number", "")
            val intent2 = Intent(context, LoginActivity::class.java)
            startActivity(intent2)
        }
        viewHolder2.llCancel!!.setOnClickListener { dialog.dismiss() }
    }

    internal inner class ViewHolder2(view: View?) : RecyclerView.ViewHolder(view!!) {
        @JvmField
        @BindView(R.id.ll_quit)
        var llQuit: LinearLayout? = null

        @JvmField
        @BindView(R.id.ll_cancel)
        var llCancel: LinearLayout? = null

        init {
            ButterKnife.bind(this, view!!)
        }
    }

    /**
     * 新版获取应该进行哪一步的操作
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
     * 返回按钮对应的弹窗
     */
    private fun quitDialog() {
        val builder = AlertDialog.Builder(context)
        builder.setMessage("Confirm to quit?")
        builder.setPositiveButton("Quit") { dialogInterface, i -> //将登录状态设为未登录
            SPUtils.getInstance().put("isLogin", false)
            SPUtils.getInstance().put("is_vip", false)
            SPUtils.getInstance().put("notPay", false)
            SPUtils.getInstance().getString("name", "")
            SPUtils.getInstance().put("is_tip", 0)
            //缓存银行信息
            SPUtils.getInstance().put("bank_name", "")
            //缓存银行卡信息
            SPUtils.getInstance().put("bank_card_number", "")
            SPUtils.getInstance().put("pay_switch", false)
            val intent2 = Intent(context, LoginActivity::class.java)
            startActivity(intent2)
        }
        builder.setNeutralButton("Cancel", null)
        val dialog = builder.create()
        dialog.show()
    }
}