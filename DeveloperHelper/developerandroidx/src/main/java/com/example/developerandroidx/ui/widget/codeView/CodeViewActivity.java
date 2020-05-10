package com.example.developerandroidx.ui.widget.codeView;

import android.content.res.ColorStateList;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.utils.Constant;

import butterknife.BindView;
import thereisnospon.codeview.CodeView;
import thereisnospon.codeview.CodeViewTheme;

/**
 * 描述：代码展示界面
 * 引用：https://github.com/Thereisnospon/CodeView
 */
public class CodeViewActivity extends BaseActivity {

    private String code;

    @BindView(R.id.cv_code_view)
    CodeView cv_code_view;
    @BindView(R.id.tltle)
    View tltle;

    @Override
    protected int bindLayout() {
        return R.layout.activity_code_view;
    }

    @Override
    protected void initView() {
        cv_code_view.setTheme(CodeViewTheme.ANDROIDSTUDIO);
        cv_code_view.fillColor();
        tltle.setBackgroundResource(R.color.codeViewBackground);
        setTopBarTextLight();
        setTitle("Code");
    }

    @Override
    protected void initData() {
        code = getIntent().getStringExtra(Constant.IntentParams.INTENT_PARAM);
        cv_code_view.showCode(code);
    }
}
