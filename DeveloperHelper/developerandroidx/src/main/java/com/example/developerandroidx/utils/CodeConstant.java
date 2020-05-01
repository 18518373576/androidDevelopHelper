package com.example.developerandroidx.utils;

/**
 * Date: 2020/5/1 9:59
 * 参考:
 * 描述: 代码展示
 */
public class CodeConstant {
    public static String code_1 = "@Override\n" +
            "protected void initView() {\n" +
            "   actionBar = getSupportActionBar();\n" +
            "   //设置标题\n" +
            "   actionBar.setTitle(\"标题\");\n" +
            "   //设置副标题\n" +
            "   actionBar.setSubtitle(\"副标题\");\n" +
            "   //设置导航按钮 可见、可点击\n" +
            "   actionBar.setDisplayHomeAsUpEnabled(true);\n" +
            "   actionBar.setHomeButtonEnabled(true);\n" +
            "   //设置actionBar背景\n" +
            "   actionBar.setBackgroundDrawable(getResources().getDrawable(R.color.textColor));\n" +
            "   //设置左侧按钮图标\n" +
            "   actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher);\n" +
            "   //@Deprecated 添加tab选项\n" +
            "   actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);\n" +
            "   actionBar.addTab(actionBar.newTab().setText(\"TAB_1\").setTabListener(new OnTabListeer()));\n" +
            "   //设置自定义ActionBar\n" +
            "   //只有这样而且没有设置菜单选项设置才能让width填充屏幕\n" +
            "   actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);\n" +
            "   View view_custom_action_bar = LayoutInflater.from(this).inflate(R.layout.view_custom_action_bar, null);\n" +
            "   ActionBar.LayoutParams layoutParams =\n" +
            "       new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);\n" +
            "   actionBar.setCustomView(view_custom_action_bar, layoutParams);\n" +
            "   Toolbar parent = (Toolbar) view_custom_action_bar.getParent();\n" +
            "   parent.setContentInsetsAbsolute(0, 0);\n"+
            "}\n\n" +
            "/**\n" +
            "  * 复写：左侧导航按钮点击事件\n" +
            "  * 注意：如果复写了onOptionsItemSelected方法，则onSupportNavigateUp无用\n" +
            "  * @return\n" +
            "  */\n" +
            "@Override\n" +
            "public boolean onSupportNavigateUp() {\n" +
            "   finish();\n" +
            "   return super.onSupportNavigateUp();\n" +
            "}\n\n" +
            "/**\n" +
            "  * 添加菜单\n" +
            "  * @param menu\n" +
            "  * @return\n" +
            "  */\n" +
            "@Override\n" +
            "public boolean onCreateOptionsMenu(Menu menu) {\n" +
            "   getMenuInflater().inflate(R.menu.bottom_nav_menu, menu);\n" +
            "   return true;\n" +
            "}\n\n" +
            "/**\n" +
            "  * 添加菜单监听\n" +
            "  * @param item\n" +
            "  * @return\n" +
            "  */\n" +
            "@Override\n" +
            "public boolean onOptionsItemSelected(@NonNull MenuItem item) {\n" +
            "   switch (item.getItemId()) {\n" +
            "   //左侧导航按钮点击事件\n" +
            "   case android.R.id.home:\n" +
            "       finish();\n" +
            "       break;\n" +
            "   case R.id.navigation_android:\n" +
            "       showToast(getResources().getString(R.string.android));\n" +
            "       break;\n" +
            "   }\n" +
            "  return true;\n" +
            "}";
}
