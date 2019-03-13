package demo.lanch.com.handlerdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.lang.ref.WeakReference;


/**
 * 标准Handler使用方法
 * 解决内容泄露问题
 * 新建handler对象使用WeakReference封装对象,保证不会造成内存泄露
 */
public class WeakHandlerActivity extends AppCompatActivity {
    TextView textView;
    Handler handler;
    final int HANDLER_MSG = 0X00001;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handler_work);
        textView = findViewById(R.id.workText);
        handler = new MyHandler(this);
        sendMessage(handler, HANDLER_MSG, "HelloWorld");
    }

    class MyHandler extends Handler {
        WeakReference<Activity> weakReference;

        public MyHandler(Activity activity) {
            weakReference = new WeakReference<Activity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            final Activity activity = weakReference.get();
            if (activity != null) {
                switch (msg.what) {
                    case HANDLER_MSG:
                        textView.setText(String.valueOf(msg.obj));
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * 发送handler message消息
     *
     * @param mHandler
     * @param what
     * @param object
     */
    private void sendMessage(final Handler mHandler, int what, Object object) {
        Message message = mHandler.obtainMessage();
        message.what = what;
        message.obj = object;
        mHandler.sendMessage(message);
    }

}
