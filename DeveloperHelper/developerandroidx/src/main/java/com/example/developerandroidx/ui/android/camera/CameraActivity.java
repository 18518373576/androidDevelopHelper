package com.example.developerandroidx.ui.android.camera;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.ui.android.activity.transitionAnimation.TransitionToActivity;
import com.example.developerandroidx.ui.android.service.service.TestIntentService;
import com.example.developerandroidx.ui.widget.webView.TechnologyWebviewActivity;
import com.example.developerandroidx.utils.Constant;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.utils.LogUtils;
import com.example.developerandroidx.utils.PixelTransformForAppUtil;
import com.example.developerandroidx.utils.RouteUtil;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;

/**
 * 功能点调用系统相机
 * 使用FileProvider获取文件Uri
 * 文档：https://developer.android.google.cn/training/camera/photobasics
 */

public class CameraActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.ll_image)
    LinearLayout ll_image;

    private int llImageWidth = 0;
    private ImageView iv_first;
    //相机照片保存的位置
    private String imagePath;
    //打开相机的请求码
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    //打开图库请求码
    private static final int REQUEST_IMAGE_GALLERY = 2;
    private Transition transition;
    private LinearLayout.LayoutParams params;
    private int margin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        //默认使用淡入淡出动画，配合sharedView使用
        transition = TransitionInflater.from(this).inflateTransition(R.transition.fade);
        //退出时使用
        getWindow().setExitTransition(transition);
        //第一次进入时使用
        getWindow().setEnterTransition(transition);
        //再次进入时使用
        getWindow().setReenterTransition(transition);

        getWindow().setReturnTransition(transition);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_camera;
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("发个朋友圈");
        iv_right.setVisibility(View.VISIBLE);
        iv_right.setOnClickListener(this);

        //获取ll_image的宽度，用于计算添加的ImageView的宽高
        ll_image.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                llImageWidth = ll_image.getWidth();
                addView();
                ll_image.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    /**
     * 添加ImageView
     */
    @SuppressLint("ResourceType")
    private void addView() {
        //margin值
        margin = PixelTransformForAppUtil.dip2px(10);
        //计算添加的imageView的宽高，宽高保持一致
        params = new LinearLayout.LayoutParams((llImageWidth - margin * 2) / 3, (llImageWidth - margin * 2) / 3);

        //添加图片按钮
        iv_first = new ImageView(context);
        iv_first.setBackgroundColor(getResources().getColor(R.color.lightGrayColor));
        iv_first.setImageResource(R.mipmap.icon_add);
        int padding = PixelTransformForAppUtil.dip2px(40);
        iv_first.setPadding(padding, padding, padding, padding);
        iv_first.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.grayColor)));
        iv_first.setId(1001);
        iv_first.setOnClickListener(this);
        ll_image.addView(iv_first, 0, params);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case 1001://点击添加图片
                DialogUtils.getInstance().showBottomMenu(context, new String[]{"相机", "图库"}, new DialogUtils.OnItemClickListener() {
                    @Override
                    public void onClick(String text, int index) {
                        switch (text) {
                            case "相机":
                                dispatchTakePictureIntent();
                                break;
                            case "图库":
                                chooseGalleryPicture();
                                break;
                        }
                    }
                });
                break;
            case R.id.iv_right:
                RouteUtil.goTo(context, RouteUtil.getDestination(TechnologyWebviewActivity.class), "https://developer.android.google.cn/guide/topics/media/camera");
                break;
        }
    }

    /**
     * 选择图库图片
     */
    private void chooseGalleryPicture() {
        Intent choosePicIntent = new Intent(Intent.ACTION_PICK);
        choosePicIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(choosePicIntent, REQUEST_IMAGE_GALLERY);
    }

    /**
     * 打开相机应用
     */
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //如果设备上没有可执行此action的应用返回null
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (photoFile != null) {
                Uri photoUri = FileProvider.getUriForFile(context, "com.example.developerandroidx", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    /**
     * 创建照片文件
     *
     * @return
     */
    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        imagePath = image.getAbsolutePath();
        LogUtils.e("图片创建：", imagePath);
        return image;
    }

    /**
     * 相机回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            Uri uri = null;
            //都转换为uri
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                //拍照结果返回，在打开相机界面的时候已经保存了完整的图片路径，把路径转换为uri
                uri = Uri.fromFile(new File(imagePath));
            } else if (requestCode == REQUEST_IMAGE_GALLERY) {
                //从图库返回结果，图片的uri，转换为绝对路径
                uri = data.getData();
            }

            //添加的图片
            ImageView iv_add_view = new ImageView(context);
            iv_add_view.setTag(uri);
            params.rightMargin = margin;
            ll_image.addView(iv_add_view, 0, params);

            //Glide使用：https://www.jianshu.com/p/791ee473a89b
            Glide.with(context).load(uri).override(PixelTransformForAppUtil.getDiaplayWidth(), PixelTransformForAppUtil.getDiaplayWidth()).centerCrop().into(iv_add_view);
            iv_add_view.setVisibility(View.VISIBLE);
            //拿到图片点击放大，这里使用了activity共享元素动画，同一个activity可以使用scene场景过渡，或者属性动画，这里不作实现
            iv_add_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, EnlargeToActivity.class);
                    intent.putExtra(Constant.IntentParams.INTENT_PARAM, (Uri) iv_add_view.getTag());
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(CameraActivity.this, iv_add_view, "sharedView").toBundle());
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        //开启IntentService清理照片缓存
        TestIntentService.startActionClearPic(context);
        super.onDestroy();
    }
}