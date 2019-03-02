package demo.xiangyi.com.zhou_er;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView text;
    int i=4;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            i--;
            if(i<0){
                Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                startActivity(intent);
                finish();
                return;
            }
            text.setText("还剩"+i+"S");
            handler.sendEmptyMessageDelayed(100,1000);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.text);
        handler.sendEmptyMessageDelayed(1000,1000);
    }
}
