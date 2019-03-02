package demo.xiangyi.com.zhou_er.di.contraser;

import demo.xiangyi.com.zhou_er.bean.Bannerbean;
import demo.xiangyi.com.zhou_er.bean.Shoppinbean;
import demo.xiangyi.com.zhou_er.bean.Shoppinbean_two;


public interface ShoppingContar {

    public interface ShoppingView{

        public void shataData(Shoppinbean shoppinbean);
        public void shataData(Shoppinbean_two shoppinbean);
        public void shataData(Bannerbean bannerbean);
    }

    public interface ShoppingPrense<ShoppingView>{

        public void attachView(ShoppingContar.ShoppingView shoppingView);

        public void dataView(ShoppingContar.ShoppingView shoppingView);

        public void requesData();
    }

    public interface ShoppingModel{

        public void cantainData(Callback callback);

        public interface Callback{
            public void onCallback(Shoppinbean shoppinbean);
            public void onCallback(Shoppinbean_two shoppinbean);
            public void onCallback(Bannerbean bannerbean);
        }
    }
}
