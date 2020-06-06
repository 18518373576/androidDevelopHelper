package com.example.developerandroidx.ui.android.activity.transitionAnimation;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseViewModel;
import com.example.developerandroidx.model.TransitionAnimationItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2020/5/10 14:16
 * 参考:
 * 描述:
 */
public class TransitionAnimationViewModel extends BaseViewModel<List<TransitionAnimationItemBean>> {

    @Override
    protected void initData(@Nullable String... param) {
        setData(initData());
    }

    private List<TransitionAnimationItemBean> initData() {
        List<TransitionAnimationItemBean> list = new ArrayList<>();
        list.add(new TransitionAnimationItemBean(R.mipmap.bg_landscape, "这是标题1", "写下要发表的内容"));
        list.add(new TransitionAnimationItemBean(R.mipmap.bg_landscape, "这是标题2", "写下要发表的内容"));
        list.add(new TransitionAnimationItemBean(R.mipmap.bg_landscape, "这是标题3", "写下要发表的内容"));
        list.add(new TransitionAnimationItemBean(R.mipmap.bg_landscape, "这是标题4", "写下要发表的内容"));
        list.add(new TransitionAnimationItemBean(R.mipmap.bg_landscape, "这是标题5", "写下要发表的内容"));
        list.add(new TransitionAnimationItemBean(R.mipmap.bg_landscape, "这是标题6", "写下要发表的内容"));
        list.add(new TransitionAnimationItemBean(R.mipmap.bg_landscape, "这是标题7", "写下要发表的内容"));
        list.add(new TransitionAnimationItemBean(R.mipmap.bg_landscape, "这是标题8", "写下要发表的内容"));
        list.add(new TransitionAnimationItemBean(R.mipmap.bg_landscape, "这是标题9", "写下要发表的内容"));
        list.add(new TransitionAnimationItemBean(R.mipmap.bg_landscape, "这是标题10", "写下要发表的内容"));
        list.add(new TransitionAnimationItemBean(R.mipmap.bg_landscape, "这是标题11", "写下要发表的内容"));
        list.add(new TransitionAnimationItemBean(R.mipmap.bg_landscape, "这是标题12", "写下要发表的内容"));
        return list;
    }

    public LiveData<List<TransitionAnimationItemBean>> getAdapterList() {
        return getData();
    }

}
