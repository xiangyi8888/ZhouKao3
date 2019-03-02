package demo.xiangyi.com.zhou_er;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import demo.xiangyi.com.zhou_er.adapter.Shoppingadapter_one;
import demo.xiangyi.com.zhou_er.adapter.Shoppingadapter_two;
import demo.xiangyi.com.zhou_er.bean.Bannerbean;
import demo.xiangyi.com.zhou_er.bean.Shoppinbean;
import demo.xiangyi.com.zhou_er.bean.Shoppinbean_two;
import demo.xiangyi.com.zhou_er.di.contraser.ShoppingContar;
import demo.xiangyi.com.zhou_er.di.prenser.ShoppingPrenser;
import retrofit2.Retrofit;

public class ShowActivity extends AppCompatActivity implements ShoppingContar.ShoppingView {

    @BindView(R.id.text_one)
    TextView textOne;
    @BindView(R.id.text_two)
    TextView textTwo;
    @BindView(R.id.shangpin)
    RecyclerView shangpin;
    @BindView(R.id.rexiao)
    RecyclerView rexiao;
    @BindView(R.id.draw)
    DrawerLayout draw;
    private Retrofit retrofit;
    private Api api;
    private ShoppingPrenser shoppingPrenser;
    private List<Shoppinbean.ResultsBean> results;
    private Shoppingadapter_one shoppingadapter_one;
    private List<Shoppinbean_two.ResultsBean> results_two;
    private Shoppingadapter_two shoppingadapter_two;
    private List<String> list = new ArrayList<>();
    private List<Bannerbean.ResultBean> banner1;
    private Banner banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);

        banner = findViewById(R.id.banner);

        shoppingPrenser = new ShoppingPrenser();
        shoppingPrenser.attachView(this);

        shangpin.setLayoutManager(new LinearLayoutManager(ShowActivity.this));
        shoppingadapter_one = new Shoppingadapter_one(R.layout.one, results);
        shangpin.setAdapter(shoppingadapter_one);
        shoppingPrenser.requesData();

        rexiao.setLayoutManager(new LinearLayoutManager(ShowActivity.this));
        shoppingadapter_two = new Shoppingadapter_two(R.layout.two, results_two);
        rexiao.setAdapter(shoppingadapter_two);
        shoppingPrenser.requesData();

//        textOne.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(ShowActivity.this, "现在就在首页", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        textTwo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(ShowActivity.this, ShoppingActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });

    }

    @Override
    public void shataData(Shoppinbean shoppinbean) {
        results = shoppinbean.getResults();
        shoppingadapter_one.notifyDataSetChanged();
        ArrayList<Shoppinbean.ResultsBean> results1 = (ArrayList<Shoppinbean.ResultsBean>) shoppinbean.getResults();
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        shangpin.setLayoutManager(manager);
        Shoppingadapter_one one = new Shoppingadapter_one(R.layout.one, results1);
        shangpin.setAdapter(one);
    }

    @Override
    public void shataData(Shoppinbean_two shoppinbean) {
        results_two = shoppinbean.getResults();
        shoppingadapter_two.notifyDataSetChanged();
        ArrayList<Shoppinbean_two.ResultsBean> results3 = (ArrayList<Shoppinbean_two.ResultsBean>) shoppinbean.getResults();
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        rexiao.setLayoutManager(manager);
        Shoppingadapter_two two = new Shoppingadapter_two(R.layout.two, results3);
        rexiao.setAdapter(two);
    }

    //banner轮播图
    @Override
    public void shataData(Bannerbean bannerbean) {
        banner1 = bannerbean.getResult();
        for (int i = 0; i < banner1.size(); i++) {
            String imageUrl = banner1.get(i).getImageUrl();
            list.add(imageUrl);
        }
        banner.setImages(list);
        start();
    }

    public void start() {

        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(list);
        //设置轮播时间
        banner.setDelayTime(1500);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    public void aaa(View view) {
        draw.openDrawer(Gravity.LEFT);
    }

    private class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).into(imageView);
        }
    }
}
