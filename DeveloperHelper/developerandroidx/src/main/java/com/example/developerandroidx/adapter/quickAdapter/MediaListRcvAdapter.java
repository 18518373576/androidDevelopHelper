package com.example.developerandroidx.adapter.quickAdapter;

import android.graphics.Bitmap;
import android.util.Size;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.developerandroidx.R;
import com.example.developerandroidx.ui.android.contentProvider.provider.Media;
import com.example.developerandroidx.utils.PixelTransformForAppUtil;
import com.example.developerandroidx.utils.StringUtils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * 作者： zjf 2020/6/15 2:13 PM
 * 参考：
 * 描述：
 */
public class MediaListRcvAdapter extends BaseQuickAdapter<Media, BaseViewHolder> {


    public MediaListRcvAdapter(@Nullable List<Media> data) {
        super(R.layout.item_media_list, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, Media item) {
        holder.setText(R.id.tv_name, "名称:" + item.getName());
        holder.setText(R.id.tv_size, "大小:" + item.getSize());
        switch (item.getMediaType()) {
            case PIC:
                holder.setText(R.id.tv_duration, "时间:" +
                        StringUtils.getInstance().getFormatTime(item.getDuration(), "yy-MM-dd HH:mm:ss"));
                break;
            default:
                holder.setText(R.id.tv_duration, "时长:" + item.getDuration());
                break;
        }

        Glide
                .with(getContext())
                .load(item.getUri())
                .override(PixelTransformForAppUtil.dip2px(100), PixelTransformForAppUtil.dip2px(100))
                .centerCrop()
                .into((ImageView) holder.getView(R.id.iv_media_preview));
    }
}
