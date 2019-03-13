package demo.lanch.com.handlerdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        findViewById(R.id.workdemo).setOnClickListener(this);
        findViewById(R.id.weakdemo).setOnClickListener(this);
        findViewById(R.id.weakcallbackdemo).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.workdemo:
                Intent intent = new Intent(MainActivity.this, HandlerWorkDemo.class);
                startActivity(intent);
                break;
            case R.id.weakdemo:
                Intent intent1 = new Intent(MainActivity.this, WeakHandlerActivity.class);
                startActivity(intent1);
                break;
            case R.id.weakcallbackdemo:
                Intent intent2 = new Intent(MainActivity.this, WeaKHandlerCallbackActivity.class);
                startActivity(intent2);
                break;
            default:
                break;
        }
    }
}
