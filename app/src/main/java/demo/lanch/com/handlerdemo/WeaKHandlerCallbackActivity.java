package demo.lanch.com.handlerdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * 使用Handler.Callback方法,避免造成内存泄露
 */
public class WeaKHandlerCallbackActivity extends AppCompatActivity {
    TextView textView;
    Handler handler;
    final int HANDLER_MSG = 0X00001;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handler_work);
        textView = findViewById(R.id.workText);
        handler = new Handler(new WeakReference<Handler.Callback>(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what) {
                    case HANDLER_MSG:
                        textView.setText(String.valueOf(msg.obj));
                        break;
                    default:
                        break;
                }
                return false;
            }
        }).get());
        sendMessage(handler, HANDLER_MSG, "Helloworld CallBack");

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
