package com.fortuneloan.tenmileslotus.view.activitys;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.classic.common.MultipleStatusView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.request.base.Request;
import com.fortuneloan.tenmileslotus.R;
import com.fortuneloan.tenmileslotus.bean.JsonBean;
import com.fortuneloan.tenmileslotus.model.Api;
import com.fortuneloan.tenmileslotus.base.BaseActivity;
import com.fortuneloan.tenmileslotus.utils.HttpHeader;
import com.fortuneloan.tenmileslotus.utils.JsonCallBack;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import butterknife.BindView;

public class FaceActivity extends BaseActivity {
    @BindView(R.id.tv_sumbit)
    TextView tvSubmit;
    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_titles)
    TextView tvTitles;
    @BindView(R.id.face_bg)
    RelativeLayout faceBg;
    @BindView(R.id.tv_tips)
    TextView tvTips;
    private ProgressDialog dialog;
    private int isClick = 0;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 999) {
               // step();
                submitData();
            }
        }
    };

    //安卓调用相机，实现拍照的功能
    @Override
    protected int getLayout() {
        return R.layout.activity_camera;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        tvTitles.setText("Face Verification");
    }
    @Override
    protected void onResume() {
        super.onResume();
        //初始化dialog
        if (dialog == null) {
            dialog = new ProgressDialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("Please wait for 3-5 seconds...");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        dialog = null;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initListener() {

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // finish();
                quitDialog();
            }
        });

       //点击照片，进行人脸识别
        faceBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(FaceActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); // 系统常量， 启动相机的关键
                    startActivityForResult(openCameraIntent, 200); // 参数常量为自定义的request code, 在取返回结果时有用
                } else {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 0);
                }
            }
        });

        //点击按钮，进行下一步的操作
        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isClick ==1) {
                   // creatDialog();
                    if (dialog != null && !dialog.isShowing()) {
                        dialog.show();
                    }
                    //直接进行step操作
                    Message message = new Message();
                    message.what = 999;
                    handler.sendMessageDelayed(message,3000);
                }else {
                    if (ActivityCompat.checkSelfPermission(FaceActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); // 系统常量， 启动相机的关键
                        startActivityForResult(openCameraIntent, 200); // 参数常量为自定义的request code, 在取返回结果时有用
                    } else {
                        requestPermissions(new String[]{Manifest.permission.CAMERA}, 0);
                    }
                }
            }
        });


    }

    @Override
    protected void initData() {

    }

    /**
     * 申请系统权限的回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean hasAllGranted = true;
        int pos = 0;
        //判断是否拒绝  拒绝后要怎么处理 以及取消再次提示的处理
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                hasAllGranted = false;
                pos = i;
                break;
            }
        }
        if (hasAllGranted) {
            //执行调用摄像头的操作
            Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); // 系统常量， 启动相机的关键
            startActivityForResult(openCameraIntent, 200); // 参数常量为自定义的request code, 在取返回结果时有用
        } else {
            //继续申请系统权限
            return;
        }
    }

    /**
     * 假装调取人脸识别，然后将拍的照片展示在控件上
     *
     * @param requestCode
     * @param resultCode
     * @param intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case 200:
                // 此处写“如何获取图片”...
                if (intent == null) return;
                if (null == intent.getExtras()) return;
                Bitmap bm = (Bitmap) intent.getExtras().get("data");
                isClick = 1;
                ivImage.setImageBitmap(bm);
                ivImage.setScaleType(ImageView.ScaleType.FIT_XY);
                tvSubmit.setText("SUBMIT");
        }
    }


    /**
     * 提交数据
     */
    private void submitData(){
        HttpHeaders headers = HttpHeader.getHeader();
        OkGo.post(Api.SUBMIT_FACE)
                .headers(headers)
                .execute(new JsonCallBack() {
                    @Override
                    public void onStart(Request request) {
                        super.onStart(request);
                    }

                    @Override
                    public void onResponse(String json) {
                        if (dialog != null && dialog.isShowing()) {
                            dialog.dismiss();
                        }
                        Gson gson = new Gson();
                        JsonBean allDataBean = gson.fromJson(json,JsonBean.class);
                        if ("200".equals(allDataBean.getCode())){
                            SPUtils.getInstance().put("is_tip",1);
                            finish();
                        }
                    }
                    @Override
                    public void onError(String json) {
                        if (dialog != null && dialog.isShowing()) {
                            dialog.dismiss();
                        }

                    }
                });
    }

    /**
     * 返回按钮对应的弹窗
     */
    private void quitDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("A few simple steps left, do you really want to give up？");
        builder.setPositiveButton("Give up", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setNeutralButton("Cancel",null);
        final AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * 监听物理返回键
     * @param keyCode
     * @param event
     * @return
     */

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            quitDialog();
            return true; //将事件拦截，避免直接退出
        }
        return super.onKeyDown(keyCode, event);
    }

}
