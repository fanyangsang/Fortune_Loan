package com.fortuneloan.tenmileslotus.view.activitys

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.widget.ImageView
import butterknife.BindView
import com.blankj.utilcode.util.SPUtils
import com.fortuneloan.tenmileslotus.R
import com.fortuneloan.tenmileslotus.base.BaseActivity
import com.fortuneloan.tenmileslotus.utils.ImgLoader
import io.branch.referral.Branch
import io.branch.referral.Branch.BranchReferralInitListener
import kotlinx.android.synthetic.main.activity_launcher.*

class LauncherActivity : BaseActivity() {

    override fun getLayout(): Int {
        return R.layout.activity_launcher
    }

    override fun initView(savedInstanceState: Bundle?) {
        ImgLoader.display(this, R.drawable.screen, cover)
        val imei = Settings.System.getString(this.contentResolver, Settings.System.ANDROID_ID)
        SPUtils.getInstance().put("imei", imei)
    }

    override fun initListener() { }
    override fun initData() { }
    override fun onResume() {
        super.onResume()
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000) //android里的延时操作
    }

    public override fun onStart() {
        super.onStart()
        Branch.sessionBuilder(this).withCallback(branchReferralInitListener).withData(if (intent != null) intent.data else null).init()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        // if activity is in foreground (or in backstack but partially visible) launching the same
        // activity will skip onStart, handle this case with reInitSession
        Branch.sessionBuilder(this).withCallback(branchReferralInitListener).reInit()
    }

    private val branchReferralInitListener = BranchReferralInitListener { referringParams, error -> }
}