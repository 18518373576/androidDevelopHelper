package com.example.developerandroidx.ui.android.contentProvider.provider;

import android.net.Uri;

/**
 * 作者： zjf 2020/6/15 1:24 PM
 * 参考：
 * 描述：视频文件信息
 */
public class Media {

    public enum MediaType {
        VIDEO, PIC, MUSIC
    }

    private final Uri uri;
    private final String name;
    private final long duration;
    private final int size;

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    private MediaType mediaType;

    public Uri getUri() {
        return uri;
    }

    public String getName() {
        return name;
    }

    public long getDuration() {
        return duration;
    }

    public int getSize() {
        return size;
    }

    public Media(Uri uri, String name, long duration, int size, MediaType mediaType) {
        this.uri = uri;
        this.name = name;
        this.duration = duration;
        this.size = size;
        this.mediaType = mediaType;
    }


}
