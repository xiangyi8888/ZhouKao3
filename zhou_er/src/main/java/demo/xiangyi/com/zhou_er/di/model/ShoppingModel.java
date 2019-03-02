package demo.xiangyi.com.zhou_er.di.model;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import demo.xiangyi.com.zhou_er.Contain;
import demo.xiangyi.com.zhou_er.bean.Bannerbean;
import demo.xiangyi.com.zhou_er.bean.Shoppinbean;
import demo.xiangyi.com.zhou_er.bean.Shoppinbean_two;
import demo.xiangyi.com.zhou_er.di.contraser.ShoppingContar;


public class ShoppingModel implements ShoppingContar.ShoppingModel {
    @Override
    public void cantainData(final Callback callback) {

        OkGo.<String> get(Contain.URI).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String responseData = response.body().toString();
                Gson gson = new Gson();
                Shoppinbean shoppinbean = gson.fromJson(responseData, Shoppinbean.class);
                callback.onCallback(shoppinbean);
            }
        });

        OkGo.<String> get(Contain.RX).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String responseData = response.body().toString();
                Gson gson = new Gson();
                Shoppinbean_two shoppinbean = gson.fromJson(responseData, Shoppinbean_two.class);
                callback.onCallback(shoppinbean);
            }
        });

        OkGo.<String> get(Contain.BANNER).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String responseData = response.body().toString();
                Gson gson = new Gson();
                Bannerbean bannerbean = gson.fromJson(responseData, Bannerbean.class);
                callback.onCallback(bannerbean);
            }
        });
    }
}
