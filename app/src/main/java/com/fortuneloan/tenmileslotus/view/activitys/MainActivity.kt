package com.fortuneloan.tenmileslotus.view.activitys

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.widget.FrameLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import butterknife.BindView
import com.blankj.utilcode.util.SPUtils
import com.fortuneloan.tenmileslotus.R
import com.fortuneloan.tenmileslotus.base.BaseActivity
import com.fortuneloan.tenmileslotus.view.activitys.MainActivity
import com.fortuneloan.tenmileslotus.view.fragments.HomeFragment
import com.fortuneloan.tenmileslotus.view.fragments.MineFragment
import com.fortuneloan.tenmileslotus.view.fragments.ProductFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : BaseActivity() {

    var rbMainMine: RadioButton? = null
    var fragmentManager: FragmentManager? = null
    var mHomeFragment: Fragment? = null
    var mMineFragment: Fragment? = null
    private val fragments: MutableList<Fragment> = ArrayList()

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun initView(savedInstanceState: Bundle?) {
        fragmentManager = supportFragmentManager
        val isVip = SPUtils.getInstance().getBoolean("is_vip", false)
        if (isVip) {
            initNewFragment()
        } else {
            initFragment()
        }
    }

    override fun initListener() {
        rg_main!!.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.main_home -> onTab(0)
                R.id.main_mine -> {
                    val isLogin = SPUtils.getInstance().getBoolean("isLogin", false)
                    if (!isLogin) {
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                    } else {
                        onTab(1)
                    }
                }
            }
        }
    }

    override fun initData() {

    }

    //贷超页面的首页
    private fun initNewFragment() {
        rg_main!!.check(R.id.main_home) //默认选中首页
        mHomeFragment = ProductFragment()
        if (mMineFragment == null) {
            mMineFragment = MineFragment()
        }
        fragments.clear()
        fragments.add(mHomeFragment!!)
        fragments.add(mMineFragment!!)

        //将fragment文件放入FrameLayout中
        fragmentManager!!.beginTransaction()
                .add(R.id.fl_main, fragments[0])
                .add(R.id.fl_main, fragments[1])
                .hide(fragments[1])
                .commit()
    }

    //默认页面的首页
    private fun initFragment() {
        rg_main!!.check(R.id.main_home) //默认选中首页
        mHomeFragment = HomeFragment()
        if (mMineFragment == null) {
            mMineFragment = MineFragment()
        }
        fragments.clear()
        fragments.add(mHomeFragment!!)
        fragments.add(mMineFragment!!)

        //将fragment文件放入FrameLayout中
        fragmentManager!!.beginTransaction()
                .add(R.id.fl_main, fragments[0])
                .add(R.id.fl_main, fragments[1])
                .hide(fragments[1])
                .commit()
    }

    /**
     * 切换tab
     */
    private fun onTab(position: Int) {
        fragmentManager!!.beginTransaction()
                .show(fragments[position])
                .hide(fragments[(position + 1) % 2])
                .commit()
    }

    companion object {
        fun forward(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    /**
     * 禁用返回键
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) true else super.onKeyDown(keyCode, event)
    }

}