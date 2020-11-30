package com.fortuneloan.tenmileslotus.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.Window;

import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.fortuneloan.tenmileslotus.bean.JsonBean;

public abstract class CommonCallback<T> extends AbsCallback<T> {
    //    private Dialog dialog;
    private ProgressDialog dialog;

    public CommonCallback(Context activity) {
        initDialog(activity);
    }

    public CommonCallback() {
    }

    private void initDialog(Context activity) {
        if (dialog == null) {
            dialog = new ProgressDialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("loading...");
        }

//             dialog = new Dialog(activity, R.style.dialog_style);
//            //设置是否允许Dialog可以被点击取消,也会阻止Back键
//            dialog.setCancelable(false);
//            //获取Dialog窗体的根容器
//            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            ViewGroup root = (ViewGroup) dialog.getWindow().getDecorView().findViewById(android.R.id.content);
//            //设置窗口大小为屏幕大小
//            WindowManager wm = (WindowManager) activity.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
//            Point screenSize = new Point();
//            wm.getDefaultDisplay().getSize(screenSize);
//            root.setLayoutParams(new LinearLayout.LayoutParams(screenSize.x, screenSize.y));
//            //获取自定义布局,并设置给Dialog
//            View view = inflater.inflate(R.layout.dialog_layout, root, false);
//            dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//            dialog.show();
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    @Override
    public void onFinish() {
        super.onFinish();
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }

    @Override
    public void onSuccess(Response<T> response) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
        JsonBean body = (JsonBean) response.body();
        if (body != null) {
            if (("403").equals(body.getCode())) {
                ToastUtils.showShort(body.getMsg());
                return;
            }
        }
    }

    @Override
    public void onError(Response<T> response) {
        super.onError(response);
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
        ToastUtils.showShort(response.message());
    }
}
