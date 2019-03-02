package demo.xiangyi.com.zhou_er.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import demo.xiangyi.com.zhou_er.R;
import demo.xiangyi.com.zhou_er.bean.Shoppinbean_two;


public class Shoppingadapter_two extends BaseQuickAdapter<Shoppinbean_two.ResultsBean,BaseViewHolder> {


    public Shoppingadapter_two(int layoutResId, @Nullable List<Shoppinbean_two.ResultsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Shoppinbean_two.ResultsBean item) {
        helper.setText(R.id.text_two,item.getDesc());
        Glide.with(mContext).load(item.getUrl()).into((ImageView) helper.getView(R.id.image_two));
    }
}
