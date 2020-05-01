package com.example.developerandroidx.ui.widget.codeView;

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

    @Override
    protected int bindLayout() {
        return R.layout.activity_code_view;
    }

    @Override
    protected void initView() {
        actionBar.setTitle(R.string.code);
        cv_code_view.setTheme(CodeViewTheme.ANDROIDSTUDIO);
        cv_code_view.fillColor();
    }

    @Override
    protected void initData() {
        code = getIntent().getStringExtra(Constant.IntentParams.CODE);
        cv_code_view.showCode(code);
    }
}
