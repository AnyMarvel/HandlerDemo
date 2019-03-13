在使用Handler时如果直接使用匿名内部类的方式创建Handler对象ide会发出警告，提示内存泄漏风险。
这时可以通过创建继承Handler的静态内部类或使用弱引用来避免Handler对象持有外部类对象的强引用。
但是官方还提供了一个Handler.Callback接口。
关于这个接口官方文档只有这样一句描述：Callback interface you can use when instantiating a Handler to avoid having to implement your own subclass of Handler.
意思大概就是使用这个接口可以避免自己去写一个Handler的子类。
如果写成这样:
Handler handler = new Handler(
new Handler.Callback(
@Overridepublic
boolean handleMessage(Message msg)
{if
(msg.what ==1)
{textView.setText("Hello!");return true;   
}return false;));handler会持有匿名对象的引用，
匿名对象会持有外部类对象的引用，虽然ide不再警告但是内存泄漏问题并没有解决。
所以要在onDestroy方法中调用handler.removeCallbacksAndMessages(null); 来清空消息。
或者用弱引用：Handler handler = new Handler(
new WeakReference<Handler.Callbacks>(
new Handler.Callback(@overridepublic boolean handleMessage(Message msg) {
if (msg.what ==1){textView.setText("Hello!");return true;    }return false;)).get());这样在堆中匿名对象只有指向它的弱引用，gc回收可以回收它。

