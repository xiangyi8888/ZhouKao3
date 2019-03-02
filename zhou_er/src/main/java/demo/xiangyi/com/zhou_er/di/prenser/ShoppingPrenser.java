package demo.xiangyi.com.zhou_er.di.prenser;

import java.lang.ref.SoftReference;

import demo.xiangyi.com.zhou_er.bean.Bannerbean;
import demo.xiangyi.com.zhou_er.bean.Shoppinbean;
import demo.xiangyi.com.zhou_er.bean.Shoppinbean_two;
import demo.xiangyi.com.zhou_er.di.contraser.ShoppingContar;
import demo.xiangyi.com.zhou_er.di.model.ShoppingModel;


public class ShoppingPrenser implements ShoppingContar.ShoppingPrense<ShoppingContar.ShoppingView> {
    ShoppingContar.ShoppingView shoppingView;
    private SoftReference<ShoppingContar.ShoppingView> softReference;
    private ShoppingModel shoppingModel;

    @Override
    public void attachView(ShoppingContar.ShoppingView shoppingView) {
        this.shoppingView = shoppingView;
        softReference = new SoftReference<>(shoppingView);
        shoppingModel = new ShoppingModel();
    }

    @Override
    public void dataView(ShoppingContar.ShoppingView shoppingView) {
        softReference.clear();
    }

    @Override
    public void requesData() {
        shoppingModel.cantainData(new ShoppingContar.ShoppingModel.Callback() {
            @Override
            public void onCallback(Shoppinbean shoppinbean) {
                shoppingView.shataData(shoppinbean);
            }

            @Override
            public void onCallback(Shoppinbean_two shoppinbean) {
                shoppingView.shataData(shoppinbean);
            }

            @Override
            public void onCallback(Bannerbean bannerbean) {
                shoppingView.shataData(bannerbean);
            }
        });
    }
}
