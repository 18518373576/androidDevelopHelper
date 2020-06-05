package com.example.developerandroidx.utils;

/**
 * Date: 2020/5/1 9:59
 * 描述: 代码变量，定义了一些非静态get方法获取变量，本来是定义了一些静态变量的字符串，
 * 考虑到这些变量是不断变化的，不是需要整个生命周期的通用的静态变量，为了减少内存占用，
 * 使用单例和一些get方法获取，调用完成后既可以释放。
 */
public class CodeVariate {
    private CodeVariate() {
    }

    private static class CodeVariateInstance {
        public static final CodeVariate INSTANCE = new CodeVariate();
    }

    public static CodeVariate getInstance() {
        return CodeVariateInstance.INSTANCE;
    }

    public String getIssueNotes() {
        return "2020/05/22\n" +
                "\n问题描述:\n" +
                "刘海屏在切换到横屏后，刘海区域没有展示，是一条黑色的区域。\n" +
                "添加以下代码解决，可在application节点和activity节点设置。\n" +
                "<meta-data\n" +
                "   android:name=\"android.notch_support\"\n" +
                "   android:value=\"true\" />\n" +
                "设置完成后，刘海区域屏幕会显示theme里面设置的windowBackground颜色\n" +
                "\n2020/05/24\n" +
                "\n系统设置点击水波纹效果：\n" +
                "android:background=\"?android:attr/selectableItemBackground\"\n" +
                "android:background=\"?android:attr/selectableItemBackgroundBorderless\"\n" +
                "可设置为foreground前景色和background背景色\n" +
                "\n2020/05/28\n" +
                "Interpolator(插值器)，根据流失的时间因子计算得到属性因子";
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
                "单精度浮点型    float        4Byte(32bit)    1.4E-45~3.4E+38      0.0F\n" +
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
        return "操作符：\n" +
                "== 和 eqauls\n" +
                "==作用于基本类型时，判断基本类型变量的值是否相等，相等则返回true。作用于引用类型时，\n" +
                "则判断引用类型变量引用的地址是否相等。相等则返回true。\n" +
                "eqauls是Object超类中的方法，所以只能作用与引用数据类型。默认作用与==一样。\n" +
                "如果类重写了eqauls()方法，则按照重写的规则进行判断。如String类，重写了eqauls()方法，判断字符串是否一样";
    }

    //android/activity
    public String getCode_5() {
        return "Activity的启动模式：\n" +
                "<standard>\n" +
                "默认模式,不设置启动模式即默认为standard，每次启动都会创建一个新的activity实例。\n" +
                "以栈的形式存储实例，后创建的实例，在栈的顶部。如果以这种方式启动的Activity被跨进程调用，\n" +
                "在5.0之前新启动的Activity实例会放入发送Intent的Task的栈的顶部，尽管它们属于不同的程序，\n" +
                "5.0之后，会创建一个新的Task，新启动的Activity就会放入刚创建的Task中。\n" +
                "<singleTop>\n" +
                "单顶模式，如果当前activity存在于栈顶，则无法被创建新的实例，而是调用onNewIntent()方法\n" +
                "可以解决，按钮重复点击，创建多个activity的问题。\n" +
                "<singleTask>\n" +
                "单任务模式，如果一个任务栈已经存在一个singleTask模式的activity，启动此activity会清空此activity\n" +
                "任务栈上面的activity,调用onNewIntent()方法。\n" +
                "<singleInstance>\n" +
                "单实例模式，单独存在一个任务栈中，整个手机系统内只会存在一个singleInstance模式的activity，所有的应用共用，例如：来电界面。\n" +
                "\n隐式启动：\n" +
                "例：Intent intent = new Intent(\"com.example.developerandroidx.ActionIntentActivity\");\n" +
                "   startActivity(intent);\n" +
                "\n以下内容参考:\n" +
                "链接：https://www.jianshu.com/p/12c6253f1851 作者：menteelin 来源：简书\n\n" +
                "<action的匹配规则>\n" +
                "action在Intent-filter可以设置多条\n" +
                "intent中必须指定action否则匹配失败且intent中action最多只有一条\n" +
                "intent中的action和intent-filter中的action必须完全一样时（包括大小写）才算匹配成功\n" +
                "intent中的action只要与intent-filter其中的一条匹配成功即可\n" +
                "<category的匹配规则>\n" +
                "category在intent-filter中可以有多条\n" +
                "category在intent中也可以有多条\n" +
                "intent中所有的category都可以在intent-filter中找到一样的（包括大小写）才算匹配成功\n" +
                "通过intent启动Activity的时候如果没有添加category会自动添加android.intent.category.DEFAULT，\n" +
                "如果intent-filter中没有添加android.intent.category.DEFAULT则会匹配失败\n" +
                "<data的匹配规则>\n" +
                "在说data的匹配规则之前我们先来说说data的语法\n" +
                "<data \n" +
                "  android:host=\"string\"\n" +
                "  android:mimeType=\"string\"\n" +
                "  android:path=\"string\"\n" +
                "  android:pathPattern=\"string\"\n" +
                "  android:pathPrefix=\"string\"\n" +
                "  android:port=\"string\"\n" +
                "  android:scheme=\"string\"/>\n" +
                "举个栗子：\n" +
                "scheme://host:port/path|pathPrefix|pathPattern\n" +
                "jrmf://jrmf360.com:8888/first\n" +
                "scheme：主机的协议部分，如jrmf\n" +
                "host：主机部分，如jrmf360.com\n" +
                "port： 端口号，如8888\n" +
                "path：路径，如first\n" +
                "pathPrefix：指定了部分路径，它会跟Intent对象中的路径初始部分匹配，如first\n" +
                "pathPattern：指定的路径可以进行正则匹配，如first\n" +
                "mimeType：处理的数据类型，如image/*\n" +
                "\n" +
                "intent-filter中可以设置多个data\n" +
                "intent中只能设置一个data\n" +
                "intent-filter中指定了data，intent中就要指定其中的一个data\n" +
                "setType会覆盖setData，setData会覆盖setType，因此需要使用setDataAndType方法来设置data和mimeType\n\n" +
                "其他注意：\n" +
                "android.intent.action.MAIN ：程序最先启动的Activity可以给多个Activity设置\n" +
                "android.intent.category.LAUNCHER：应用程序是否显示在桌面，可以给多个Activity配置\n" +
                "android.intent.action.MAIN和android.intent.category.LAUNCHER同时设置会在launcher显示一个应用图标，\n" +
                "单独设置android.intent.category.LAUNCHER不会出现图标，且一个应用程序最少要有一对。也可以设置多对，\n" +
                "这样会在系统桌面出现过个应用程序图标。\n";
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

    //android/animaiton
    public String getCode_6() {
        return "参考：\n" +
                "链接：https://www.jianshu.com/p/16e0d4e92bb2 作者：Power_android 来源：简书\n\n" +
                "平移动画 < translate >     TranslateAnimation     移动View\n" +
                "缩放动画 < scale >         ScaleAnimation         方法或缩小View\n" +
                "旋转动画 < rotate >        RotateAnimation        旋转view\n" +
                "透明动画 < alpha >         AlphaAnimation         改变View的透明度\n" +
                "Animation属性:\n" +
                "android:duration            setDuration(long)               动画持续时间，毫秒为单位\n" +
                "android:ShareInterpolator   setInterpolator(Interpolator)   设定插值器（指定的动画效果，譬如回弹等）\n" +
                "android:fillAfter           setFillAfter(boolean)           控件动画结束时是否保持动画最后的状态\n" +
                "android:fillBefore          setFillBefore(boolean)          控件动画结束时是否还原到开始动画前的状态\n" +
                "android:repeatMode          setRepeatMode(int)              重复类型有两个值，reverse表示倒序回放，restart表示从头播放\n" +
                "android:startOffset         setStartOffset(long)            调用start函数之后等待开始运行的时间，单位为毫秒\n" +
                "插值器:\n" +
                "AccelerateDecelerateInterpolator   @android:anim/accelerate_decelerate_interpolator    动画始末速率较慢，中间加速\n" +
                "AccelerateInterpolator             @android:anim/accelerate_interpolator               动画开始速率较慢，之后慢慢加速\n" +
                "AnticipateInterpolator             @android:anim/anticipate_interpolator               开始的时候从后向前甩\n" +
                "AnticipateOvershootInterpolator    @android:anim/anticipate_overshoot_interpolator     类似上面AnticipateInterpolator\n" +
                "BounceInterpolator                 @android:anim/bounce_interpolator                   动画结束时弹起\n" +
                "CycleInterpolator                  @android:anim/cycle_interpolator                    循环播放速率改变为正弦曲线\n" +
                "DecelerateInterpolator             @android:anim/decelerate_interpolator               动画开始快然后慢\n" +
                "LinearInterpolator                 @android:anim/linear_interpolator                   动画匀速改变\n" +
                "OvershootInterpolator              @android:anim/overshoot_interpolator                向前弹出一定值之后回到原来位置\n";
    }

    public String getCode_7() {

        return "创建基本通知：\n" +
                "NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)\n" +
                "         .setSmallIcon(R.drawable.notification_icon)\n" +
                "         .setContentTitle(textTitle)\n" +
                "         .setContentText(textContent)\n" +
                "         .setPriority(NotificationCompat.PRIORITY_DEFAULT);\n" +
                "\n创建展示更多内容的通知：\n" +
                "NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)\n" +
                "         .setSmallIcon(R.drawable.notification_icon)\n" +
                "         .setContentTitle(\"My notification\")\n" +
                "         .setContentText(\"Much longer text that cannot fit one line...\")\n" +
                "         .setStyle(new NotificationCompat.BigTextStyle()\n" +
                "                 .bigText(\"Much longer text that cannot fit one line...\"))\n" +
                "         .setPriority(NotificationCompat.PRIORITY_DEFAULT);\n" +
                "\n创建通知渠道：\n" +
                "private void createNotificationChannel() {\n" +
                "     // Create the NotificationChannel, but only on API 26+ because\n" +
                "     // the NotificationChannel class is new and not in the support library\n" +
                "     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {\n" +
                "         CharSequence name = getString(R.string.channel_name);\n" +
                "         String description = getString(R.string.channel_description);\n" +
                "         int importance = NotificationManager.IMPORTANCE_DEFAULT;\n" +
                "         NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);\n" +
                "         channel.setDescription(description);\n" +
                "         // Register the channel with the system; you can't change the importance\n" +
                "         // or other notification behaviors after this\n" +
                "         NotificationManager notificationManager = getSystemService(NotificationManager.class);\n" +
                "         notificationManager.createNotificationChannel(channel);\n" +
                "     }\n" +
                " }\n" +
                "\n设置通知的点按操作：\n" +
                "// Create an explicit intent for an Activity in your app\n" +
                "Intent intent = new Intent(this, AlertDetails.class);\n" +
                "intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);\n" +
                "PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);\n" +
                "\n" +
                "NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)\n" +
                "        .setSmallIcon(R.drawable.notification_icon)\n" +
                "        .setContentTitle(\"My notification\")\n" +
                "        .setContentText(\"Hello World!\")\n" +
                "        .setPriority(NotificationCompat.PRIORITY_DEFAULT)\n" +
                "        // Set the intent that will fire when the user taps the notification\n" +
                "        .setContentIntent(pendingIntent)\n" +
                "        .setAutoCancel(true);\n" +
                "\n显示通知：\n" +
                "NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);\n" +
                "// notificationId is a unique int for each notification that you must define\n" +
                "notificationManager.notify(notificationId, builder.build());\n" +
                "\n添加操作按钮：\n" +
                "Intent snoozeIntent = new Intent(this, MyBroadcastReceiver.class);\n" +
                "snoozeIntent.setAction(ACTION_SNOOZE);\n" +
                "snoozeIntent.putExtra(EXTRA_NOTIFICATION_ID, 0);\n" +
                "PendingIntent snoozePendingIntent =\n" +
                "        PendingIntent.getBroadcast(this, 0, snoozeIntent, 0);\n" +
                "\n" +
                "NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)\n" +
                "        .setSmallIcon(R.drawable.notification_icon)\n" +
                "        .setContentTitle(\"My notification\")\n" +
                "        .setContentText(\"Hello World!\")\n" +
                "        .setPriority(NotificationCompat.PRIORITY_DEFAULT)\n" +
                "        .setContentIntent(pendingIntent)\n" +
                "        .addAction(R.drawable.ic_snooze, getString(R.string.snooze),\n" +
                "                snoozePendingIntent);\n" +
                "添加直接回复操作：\n" +
                "// Key for the string that's delivered in the action's intent.\n" +
                "private static final String KEY_TEXT_REPLY = \"key_text_reply\";\n" +
                "\n" +
                "String replyLabel = getResources().getString(R.string.reply_label);\n" +
                "RemoteInput remoteInput = new RemoteInput.Builder(KEY_TEXT_REPLY)\n" +
                "        .setLabel(replyLabel)\n" +
                "        .build();\n\n" +
                "// Build a PendingIntent for the reply action to trigger.\n" +
                "PendingIntent replyPendingIntent =\n" +
                "        PendingIntent.getBroadcast(getApplicationContext(),\n" +
                "                conversation.getConversationId(),\n" +
                "                getMessageReplyIntent(conversation.getConversationId()),\n" +
                "                PendingIntent.FLAG_UPDATE_CURRENT);\n\n" +
                "// Create the reply action and add the remote input.\n" +
                "NotificationCompat.Action action =\n" +
                "        new NotificationCompat.Action.Builder(R.drawable.ic_reply_icon,\n" +
                "                getString(R.string.label), replyPendingIntent)\n" +
                "                .addRemoteInput(remoteInput)\n" +
                "                .build();\n\n" +
                "// Build the notification and add the action.\n" +
                "Notification newMessageNotification = new Notification.Builder(context, CHANNEL_ID)\n" +
                "        .setSmallIcon(R.drawable.ic_message)\n" +
                "        .setContentTitle(getString(R.string.title))\n" +
                "        .setContentText(getString(R.string.content))\n" +
                "        .addAction(action)\n" +
                "        .build();\n" +
                "\n" +
                "// Issue the notification.\n" +
                "NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);\n" +
                "notificationManager.notify(notificationId, newMessageNotification);\n\n" +
                "private CharSequence getMessageText(Intent intent) {\n" +
                "   Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);\n" +
                "   if (remoteInput != null) {\n" +
                "       return remoteInput.getCharSequence(KEY_TEXT_REPLY);\n" +
                "   }\n" +
                "   return null;\n" +
                "}\n" +
                "\n添加进度条：\n" +
                "NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);\n" +
                "NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);\n" +
                "builder.setContentTitle(\"Picture Download\")\n" +
                "        .setContentText(\"Download in progress\")\n" +
                "        .setSmallIcon(R.drawable.ic_notification)\n" +
                "        .setPriority(NotificationCompat.PRIORITY_LOW);\n" +
                "\n" +
                "// Issue the initial notification with zero progress\n" +
                "int PROGRESS_MAX = 100;\n" +
                "int PROGRESS_CURRENT = 0;\n" +
                "builder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false);\n" +
                "notificationManager.notify(notificationId, builder.build());\n" +
                "\n" +
                "// Do the job here that tracks the progress.\n" +
                "// Usually, this should be in a\n" +
                "// worker thread\n" +
                "// To show progress, update PROGRESS_CURRENT and update the notification with:\n" +
                "// builder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false);\n" +
                "// notificationManager.notify(notificationId, builder.build());\n" +
                "\n" +
                "// When done, update the notification one more time to remove the progress bar\n" +
                "builder.setContentText(\"Download complete\")\n" +
                "        .setProgress(0,0,false);\n" +
                "notificationManager.notify(notificationId, builder.build());\n" +
                "\n广播两种发送方式的区别。\n" +
                "方式1:\n" +
                "现象描述:此种广播发送方式,广播接收者必须在清单文件中声明,不在清单文件中声明的动态注册的广播接收者无法接收到,\n" +
                "因为我在通知栏发送的广播是以这种方式发送的,所以一直很困惑二次传递的问题,后来改用<方式2>发送广播,所有的问题解决了.\n" +
                "因为是静态注册所以,这里实现的 OnReceivedListener listener没有被赋值,消息需要再经过<方式2>的发送方式才能被动态注册的AppBroadcastReceiver接收到.\n" +
                "Intent intent = new Intent(context, AppBroadcastReceiver.class);\n" +
                "intent.setAction(Constant.BroadcastAction.TEST);\n" +
                "intent.putExtra(\"sendBroadcast\", \"收到一条广播，还附加了一条消息\");\n" +
                "sendBroadcast(intent);\n" +
                "方式2:\n" +
                "这种方式发送的广播,广播接收者不需要在清单文件中声明\n" +
                "sendBroadcast(new Intent(Constant.BroadcastAction.TEST).putExtra(\"sendBroadcast\", \"收到一条广播，还附加了一条消息\"));\n";
    }
}
