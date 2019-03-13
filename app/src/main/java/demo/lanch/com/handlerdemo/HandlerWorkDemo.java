package demo.lanch.com.handlerdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.widget.TextView;

public class HandlerWorkDemo extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handler_work);

        textView = findViewById(R.id.workText);
        Thread workerThread = new Thread(new SampleTask(new MyHandler()));
        workerThread.start();
    }

    public void appendText(String msg) {
        textView.setText(textView.getText() + "\n" + msg);
    }

    class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String result = msg.getData().getString("message");
            // 更新UI
            appendText(result);

        }
    }

    private class SampleTask implements Runnable {
        private Handler handler;

        public SampleTask(MyHandler myHandler) {
            this.handler = myHandler;
        }

        @Override
        public void run() {
            try {  // 模拟执行某项任务，下载等
                Thread.sleep(5000);
                // 任务完成后通知activity更新UI
                Message msg = prepareMessage("task completed!");
                // message将被添加到主线程的MQ中
                handler.sendMessage(msg);
            } catch (InterruptedException e) {
                Log.d("SampleTask", "interrupted!");
            }

        }

        private Message prepareMessage(String str) {
            Message result = handler.obtainMessage();
            Bundle data = new Bundle();
            data.putString("message", str);
            result.setData(data);
            return result;
        }
    }
}
