package com.example.developerandroidx.ui.android.camera;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.utils.PixelTransformForAppUtil;

import butterknife.BindView;

public class CameraActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.ll_image)
    LinearLayout ll_image;

    private int llImageWidth = 0;
    private ImageView iv_first;
    private ImageView iv_two;
    private ImageView iv_three;

    @Override
    protected int bindLayout() {
        return R.layout.activity_camera;
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("发一个朋友圈");

        ll_image.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                llImageWidth = ll_image.getWidth();
                addView();
                ll_image.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    @SuppressLint("ResourceType")
    private void addView() {
        //margin值
        int margin = PixelTransformForAppUtil.dip2px(10);

        //计算添加的imageView的宽高，宽高保持一致
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((llImageWidth - margin * 2) / 3, (llImageWidth - margin * 2) / 3);

        //添加图片按钮
        iv_first = new ImageView(context);
        iv_first.setBackgroundColor(getResources().getColor(R.color.lightGrayColor));
        iv_first.setImageResource(R.mipmap.icon_add);
        int padding = PixelTransformForAppUtil.dip2px(30);
        iv_first.setPadding(padding, padding, padding, padding);
        iv_first.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.grayColor)));
        iv_first.setId(1001);
        iv_first.setOnClickListener(this);
        ll_image.addView(iv_first, 0, params);

        //添加的图片1
        iv_two = new ImageView(context);
        iv_two.setBackgroundColor(getResources().getColor(R.color.lightGrayColor));
        params.leftMargin = margin;
        params.rightMargin = margin;
        ll_image.addView(iv_two, 0, params);

        //添加图片2
        iv_three = new ImageView(context);
        iv_three.setBackgroundColor(getResources().getColor(R.color.lightGrayColor));
        params.leftMargin = 0;
        params.leftMargin = 0;
        ll_image.addView(iv_three, 0, params);

        iv_two.setVisibility(View.GONE);
        iv_three.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case 1001:
                DialogUtils.getInstance().showBottomMenu(context, new String[]{"相机", "图库"}, new DialogUtils.OnItemClickListener() {
                    @Override
                    public void onClick(String text, int index) {
                        switch (text) {
                            case "相机":
                                dispatchTakePictureIntent();
                                break;
                            case "图库":

                                break;
                        }
                    }
                });
                break;
        }
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    /**
     * 打开相机应用
     */
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //如果设备上没有可执行此action的应用返回null
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
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
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            iv_two.setImageBitmap(imageBitmap);
            iv_two.setVisibility(View.VISIBLE);
        }
    }
}