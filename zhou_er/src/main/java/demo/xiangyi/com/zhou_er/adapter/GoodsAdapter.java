package demo.xiangyi.com.zhou_er.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import demo.xiangyi.com.zhou_er.R;
import demo.xiangyi.com.zhou_er.addordeducing.AddSubLayout;
import demo.xiangyi.com.zhou_er.beant.ListBean;

public class GoodsAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<ListBean> list = new ArrayList();
    private MyHolder myHolder;
    private myGoodsInter myGoodsInter;
    public static double h;

    public void setMyGoodsInter(GoodsAdapter.myGoodsInter myGoodsInter) {
        this.myGoodsInter = myGoodsInter;
    }

    public interface myGoodsInter{
        void CarPrice(double price);
    }

    public GoodsAdapter(Context context) {
        this.context = context;
    }

    public void add(List<ListBean> data){
        list.addAll(data);
        notifyDataSetChanged();
    }

    public void clear(){
        list.clear();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        myHolder = new MyHolder(LayoutInflater.from(context).inflate(R.layout.goods_item_layout, null));
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ListBean goodsBean = list.get(position);
        String[] sourceStrArray = goodsBean.getImages().split("\\|");
        Glide.with(context).load(sourceStrArray[0]).into(myHolder.imag);
        myHolder.name.setText(goodsBean.getTitle());
        myHolder.price.setText("￥"+goodsBean.getPrice());
        myHolder.addsub.setCount(goodsBean.getNum());
        if(goodsBean.isaBoolean()){
            myHolder.cb.setChecked(true);
            h+=goodsBean.getPrice()*goodsBean.getNum();
        }else{
            myHolder.cb.setChecked(false);
        }
        List<String> lists = new ArrayList<>();
        lists.add(h+"");
        EventBus.getDefault().postSticky(lists);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        private final ImageView imag;
        private final TextView name;
        private final TextView price;
        private final AddSubLayout addsub;
        private final CheckBox cb;

        public MyHolder(View itemView) {
            super(itemView);

            imag = itemView.findViewById(R.id.gImage);
            name = itemView.findViewById(R.id.gName);
            price = itemView.findViewById(R.id.gPrice);
            addsub = itemView.findViewById(R.id.addsub);
            cb = itemView.findViewById(R.id.ccb);
        }
    }

}
