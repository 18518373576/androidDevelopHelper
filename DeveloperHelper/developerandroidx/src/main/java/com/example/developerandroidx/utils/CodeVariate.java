package com.example.developerandroidx.utils;

/**
 * Date: 2020/5/1 9:59
 * 描述: 代码变量，定义了一些非静态get方法获取变量，本来是定义了一些静态变量的字符串，
 * 考虑到这些变量是不断变化的，不是需要整个生命周期的通用的静态变量，为了减少内存占用，
 * 使用单例和一些get方法获取，调用完成后既可以释放。
 */
public class CodeVariate {
    private static CodeVariate codeVariate = null;

    private CodeVariate() {
    }

    public static CodeVariate getInstance() {
        if (codeVariate == null) {
            codeVariate = new CodeVariate();
        }
        return codeVariate;
    }

    //widget/actionBar
    public String getCode_1() {
        return "@Override\n" +
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
                "   parent.setContentInsetsAbsolute(0, 0);\n" +
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

    //java/数据类型
    public String getCode_2() {
        return "参考书籍：《java面向对象编程(第二版)》\n" +
                "参考链接：https://blog.csdn.net/xushiyu1996818/java/article/details/83269526\n" +
                "基本类型：\n" +
                "数据类型       关键字         内存字节数       取值范围              默认值\n" +
                "布尔型         boolean      1Byte(8bit)     true/false           false\n" +
                "字节型         byte         1Byte(8bit)     -128~127             0\n" +
                "字符型         char         2Byte(16bit)    0~2^16-1            '\\u0000'\n" +
                "短整型         short        2Byte(16bit)    -2^15~2^15-1         0\n" +
                "整型           int          4Byte(32bit)    -2^31~2^31-1         0\n" +
                "长整型         long         8Byte(64bit)    -2^63~2^63-1         0\n" +
                "单精度浮点型    float        8Byte(32bit)    1.4E-45~3.4E+38      0.0F\n" +
                "双精度浮点型    double       8Byte(64bit)    4.9E-324~1.7977E+308 0.0D\n\n" +
                "引用类型：\n" +
                "类类型、接口类型、数组类型\n\n" +
                "boolean类型：\n" +
                "当java编译器把java源代码编译为字节码时，会用int或byte表示boolean，在java虚拟机中，用整数0来\n" +
                "表示false，用任意一个非0整数来表示true。但是，在java源程序中不允许把‘整数’或‘null’赋值给boolean\n" +
                "类型的变量。\n" +
                "byte、short、int、long类型\n" +
                "这些都是整数类型，并且都有符号。\n" +
                "负数表示：\n" +
                "首先我们要对原码、反码和补码有个了解：\n" +
                "1、所谓原码就是二进制定点表示法，即最高位为符号位，“0”表示正，“1”表示负，其余位表示数值的大小。\n" +
                "2、反码表示法规定：正数的反码与其原码相同；负数的反码是对其原码逐位取反，但符号位除外。\n" +
                "原码10010= 反码11101 （10010，1为符号码，故为负）\n" +
                "(11101) 二进制= -13 十进制\n" +
                "3、补码表示法规定：正数的补码与其原码相同；负数的补码是在其反码的末位加1。\n" +
                "举一例，我们来看整数-1在计算机中如何表示。\n" +
                "假设这也是一个int类型，那么：\n" +
                "1、先取1的原码：00000000 00000000 00000000 00000001\n" +
                "2、得反码：     11111111 11111111 11111111 11111110\n" +
                "3、得补码：     11111111 11111111 11111111 11111111\n" +
                "十六进制表示：  0xFFFFFFFF\n" +
                "所以Java中Integer.toBinaryString(-5)结果为11111111111111111111111111111011. Integer是32位(bit)的。\n" +
                "负数转二进制规律：\n" +
                "1、取负数的绝对值的原码\n" +
                "2、计算原码的反码\n" +
                "3、对反码加一，获取补码\n" +
                "例：-3转为二进制\n" +
                "3\n" +
                "0011\n" +
                "1100\n" +
                "1101\n" +
                "二进制负数（开头为1）转为十进制负数\n" +
                "1、 对二进制减一\n" +
                "2、对此数取反码\n" +
                "3、得到此数的十进制，例：\n" +
                "1101\n" +
                "1100\n" +
                "0011\n" +
                "3\n" +
                "java 二进制与十进制互相转化\n" +
                "int num=3;\n" +
                "//十进制转二进制\n" +
                "String s=Integer.toBinaryString(num);\n" +
                "//二进制转十进制\n" +
                "int a=Integer.valueOf(s,2);\n";
    }

    //android/性能优化
    public String getCode_3() {
        return "性能优化：\n" +
                "   1.在定义变量时，选用那种数据类型，要考录到实际和性能需求。例如月份的取值是1~12的整数，\n" +
                "因此可以把月份定义为：byte month，此时java虚拟机只需要为month分配一个字节的内存，如果\n" +
                "定义为long，尽管是可行的，但是会占用更多的内存。";
    }
}
