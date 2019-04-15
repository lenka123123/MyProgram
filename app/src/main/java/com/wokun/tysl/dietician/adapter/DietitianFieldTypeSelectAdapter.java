package com.wokun.tysl.dietician.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shantoo.widget.imageview.SelectorImageView;
import com.wokun.tysl.R;
import com.wokun.tysl.dietician.bean.DieticianFieldTypeBean;

import java.util.List;

public class DietitianFieldTypeSelectAdapter extends BaseQuickAdapter<DieticianFieldTypeBean,BaseViewHolder> {

    private StringBuilder sb = new StringBuilder();

    public DietitianFieldTypeSelectAdapter(@LayoutRes int layoutResId, @Nullable List<DieticianFieldTypeBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final DieticianFieldTypeBean item) {
        helper.setText(R.id.title, item.getFieldName());
        helper.getView(R.id.iv_arrow).setVisibility(View.GONE);

        final SelectorImageView siv =  helper.getView(R.id.iv_select);
        siv.setVisibility(View.VISIBLE);
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(siv.isChecked()){
                    siv.toggle(false);
                    String str = sb.toString().replaceAll(item.getFieldId()+",","");
                    sb.replace(0,sb.length(),"").append(str);
                }else{
                    siv.toggle(true);
                    sb.append(item.getFieldId()).append(",");
                }
                String str = "";
                if(sb.length()>=1){
                    str = sb.toString().substring(0,sb.length()-1);
                }
                mOnFieldTypeSelectListener.onFieldTypeSelect(str);
            }
        });
    }

    private OnFieldTypeSelectListener mOnFieldTypeSelectListener;

    public interface OnFieldTypeSelectListener{
        void onFieldTypeSelect(String fieldIds);
    }

    public void setOnFieldTypeSelectListener(OnFieldTypeSelectListener listener){
        this.mOnFieldTypeSelectListener = listener;
    }
}
