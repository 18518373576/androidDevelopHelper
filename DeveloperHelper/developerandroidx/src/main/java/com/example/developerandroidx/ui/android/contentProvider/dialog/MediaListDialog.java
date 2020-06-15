package com.example.developerandroidx.ui.android.contentProvider.dialog;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.developerandroidx.R;
import com.example.developerandroidx.adapter.quickAdapter.MediaListRcvAdapter;
import com.example.developerandroidx.projectInterface.FunctionDialogInterface;
import com.example.developerandroidx.ui.android.contentProvider.provider.Media;
import com.example.developerandroidx.utils.DialogUtils;
import com.kongzue.dialog.v3.FullScreenDialog;

import java.util.List;

/**
 * 作者： zjf 2020/6/15 1:57 PM
 * 参考：
 * 描述：
 */
public class MediaListDialog implements FunctionDialogInterface {
    private List<Media> mediaList;

    public MediaListDialog(List<Media> mediaList) {
        this.mediaList = mediaList;
    }

    @Override
    public void show(Context context) {
        DialogUtils.getInstance().showFullScreenDialog(context, R.layout.dialog_content_provider, new DialogUtils.OnFullScreenDialogBindView() {
            @Override
            public void onBind(FullScreenDialog dialog, View rootView) {
                RecyclerView rcv_media_list = rootView.findViewById(R.id.rcv_media_list);
                rcv_media_list.setLayoutManager(new LinearLayoutManager(context));
                rcv_media_list.setAdapter(new MediaListRcvAdapter(mediaList));
            }
        });
    }
}
