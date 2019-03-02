package demo.xiangyi.com.zhou_er.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import demo.xiangyi.com.zhou_er.R;
import demo.xiangyi.com.zhou_er.bean.Shoppinbean;


public class Shoppingadapter_one extends BaseQuickAdapter<Shoppinbean.ResultsBean,BaseViewHolder> {
    public Shoppingadapter_one(int layoutResId, @Nullable List<Shoppinbean.ResultsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Shoppinbean.ResultsBean item) {
        helper.setText(R.id.text_one,item.getDesc());
        Glide.with(mContext).load(item.getUrl()).into((ImageView) helper.getView(R.id.image_one));
    }
}
