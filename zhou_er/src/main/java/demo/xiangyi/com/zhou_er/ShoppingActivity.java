package demo.xiangyi.com.zhou_er;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.greenrobot.eventbus.Subscribe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import demo.xiangyi.com.zhou_er.adapter.Goods1Adapter;
import demo.xiangyi.com.zhou_er.beant.Booss;
import demo.xiangyi.com.zhou_er.beant.DataBean;

public class ShoppingActivity extends AppCompatActivity {

    @BindView(R.id.text_one)
    TextView textOne;
    @BindView(R.id.text_two)
    TextView textTwo;
    @BindView(R.id.draw)
    DrawerLayout draw;
    @BindView(R.id.goodsRecy)
    RecyclerView goodsRecy;
    @BindView(R.id.mcb)
    CheckBox mcb;
    @BindView(R.id.sssum)
    TextView sssum;
    @BindView(R.id.c)
    RelativeLayout c;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            abc((String) msg.obj);
        }

    };
    private Goods1Adapter goodsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        ButterKnife.bind(this);


        myJson();
    }


    private void myJson() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                InputStreamReader inputStreamReader;
                try {
                    inputStreamReader = new InputStreamReader(ShoppingActivity.this.getAssets().open("cart.json"), "UTF-8");
                    BufferedReader bufferedReader = new BufferedReader(
                            inputStreamReader);
                    String line;
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    Message message = handler.obtainMessage();
                    message.obj = stringBuilder.toString();
                    handler.sendMessage(message);
                    inputStreamReader.close();
                    bufferedReader.close();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    @Subscribe(sticky = true)

    private void abc(String s) {
        Gson gson = new Gson();
        Booss boos = gson.fromJson(s, Booss.class);
        List<DataBean> data = boos.getData();
        goodsAdapter = new Goods1Adapter(ShoppingActivity.this);
        goodsRecy.setLayoutManager(new GridLayoutManager(ShoppingActivity.this,1));
        goodsRecy.setAdapter(goodsAdapter);
        goodsAdapter.add(data);
    }
}
