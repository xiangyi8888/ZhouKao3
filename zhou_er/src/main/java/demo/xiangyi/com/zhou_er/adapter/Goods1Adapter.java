package demo.xiangyi.com.zhou_er.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import demo.xiangyi.com.zhou_er.R;
import demo.xiangyi.com.zhou_er.beant.DataBean;
import demo.xiangyi.com.zhou_er.beant.ListBean;

public class Goods1Adapter extends RecyclerView.Adapter {

    private Context context;
    private List<DataBean> list = new ArrayList();
    private MyHolder myHolder;
    private myGoodsInter myGoodsInter;
    private double a;
    private boolean b;

    public void setMyGoodsInter(Goods1Adapter.myGoodsInter myGoodsInter) {
        this.myGoodsInter = myGoodsInter;
    }

    public interface myGoodsInter{
        void CarPrice(String price);
    }

    public Goods1Adapter(Context context) {
        this.context = context;
    }

    public void add(List<DataBean> data){
        list.addAll(data);
        notifyDataSetChanged();
    }
    public void addT(boolean okAno){
        b = okAno;
        a=0;
        notifyDataSetChanged();
    }

    public void clear(){
        list.clear();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        myHolder = new MyHolder(LayoutInflater.from(context).inflate(R.layout.goods_item1_layout, null));
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final DataBean goodsBean = list.get(position);
        myHolder.name.setText(goodsBean.getSellerName());/*
        myGoodsInter.CarPrice(a+"");*/

        final GoodsAdapter goodsAdapter = new GoodsAdapter(context);

        myHolder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                List<ListBean> list1 = goodsBean.getList();
                if(isChecked){
                    for (int i = 0; i< list1.size(); i++){
                        list1.get(i).setaBoolean(true);
                    }
                    goodsAdapter.notifyDataSetChanged();
                }else{
                    for (int i = 0; i< list1.size(); i++){
                        list1.get(i).setaBoolean(false);
                    }
                    goodsAdapter.notifyDataSetChanged();
                }
            }
        });

        myHolder.recy.setLayoutManager(new GridLayoutManager(context,1));
        myHolder.recy.setAdapter(goodsAdapter);
        goodsAdapter.add(list.get(position).getList());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        private final TextView name;
        private final CheckBox cb;
        private final RecyclerView recy;

        public MyHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name2);
            cb = itemView.findViewById(R.id.ccbb);
            recy = itemView.findViewById(R.id.recy1);
        }
    }

}
