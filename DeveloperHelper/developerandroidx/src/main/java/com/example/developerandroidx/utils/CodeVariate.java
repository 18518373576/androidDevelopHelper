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
                "   //获取actionBar\n" +
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
        return "参考资料：《java面向对象编程(第二版)》\n" +
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
                "int a=Integer.valueOf(s,2);\n\n" +
                "基本类型和引用类型的区别：\n" +
                "对于基本类型的变量，java虚拟机会为其分配数据类型实际占用的内存大小，而对于引用类型\n" +
                "变量，它仅仅是一个指向堆区中某个实例的指针。\n\n" +
                "关键字new创建对象，步骤：\n" +
                "1、为对象分配内存空间，将对象的实例变量自动初始化为其变量类型的默认值\n" +
                "2、如果实例变量在声明时被显示初始化，就把初始化值赋给实例变量\n" +
                "3、调用构造方法\n" +
                "4、返回对象的引用\n\n" +
                "变量：\n" +
                "成员变量：在类中声明，作用域是整个类\n" +
                "局部变量：位于栈区，在方法或方法的代码块中声明，作用域为声明变量的方法或代码块\n" +
                "实例变量和静态变量：\n" +
                "   成员变量有两种，被static修饰的变量叫静态变量，没有被static修饰的叫实例变量。\n" +
                "   类的静态变量在内存中只有一个，被类的所有实例共享，位于方法区，其声明周期取决于类的生命周期，\n" +
                "类加载的时候被创建并分配内存，类卸载的时候被销毁并撤销内存\n" +
                "   类的每个实例都会有实例变量，位于堆区，每创建一个类的实例，虚拟机就会为实例变量分配一次内存，\n" +
                "实例变量的生命周期取决于实例的生命周期。创建实例的时候被创建并分配内存，销毁实例的时候被销毁\n" +
                "并撤销内存\n" +
                "局部变量的生命周期：\n" +
                "   虚拟机中的某个线程调用一个方法时，会为这个方法中的局部变量分配内存。\n" +
                "结束调用一个方法时，会结束这个方法中局部变量的生命周期";
    }

    //java/操作符
    public String getCode_4() {
        return "";
    }

    //android/性能优化
    public String getCode_3() {
        return "性能优化：\n" +
                "java：\n" +
                "   1.在定义变量时，选用那种数据类型，要考录到实际和性能需求。例如月份的取值是1~12的整数，\n" +
                "因此可以把月份定义为：byte month，此时java虚拟机只需要为month分配一个字节的内存，如果\n" +
                "定义为long，尽管是可行的，但是会占用更多的内存。\n" +
                "   2.根据变量的作用域和生命周期，合理的声明变量，尽量使变量的作用域最小化。\n" +
                "\nandroid：\n" +
                "   1.定义布局文件，尽量减少控件的嵌套。\n" +
                "   2.在activity或fragment执行的线程操作，或绑定例如：eventBus，butterknife，在onDestory\n" +
                "要终止线程和解绑。";
    }
}