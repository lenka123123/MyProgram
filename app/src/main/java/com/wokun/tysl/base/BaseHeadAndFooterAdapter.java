package com.wokun.tysl.base;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.model.bean.HeadAndFootEntity;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseHeadAndFooterAdapter<T extends HeadAndFootEntity, K extends BaseViewHolder> extends BaseQuickAdapter<T, K> {

    protected int headerResId;
    protected int footerResId;
    public final static int HEADER = 9;
    public final static int BODY = 8;
    public final static int FOOTER = 7;

    public BaseHeadAndFooterAdapter(@LayoutRes int layoutResId, @LayoutRes int headerResId, @Nullable List<T> data) {
        super(layoutResId, data);
        this.headerResId = headerResId;
    }

    public BaseHeadAndFooterAdapter(@LayoutRes int layoutResId, @LayoutRes int headerResId, @LayoutRes int footerResId, @Nullable List<T> data) {
        super(layoutResId, data);
        this.headerResId = headerResId;
        this.footerResId = footerResId;
    }

    List<Integer> headerPositions = new ArrayList<>();
    List<Integer> bodyPositions = new ArrayList<>();
    List<Integer> footerPositions = new ArrayList<>();

    @Override
    protected int getDefItemViewType(int position) {
        if (mData.get(position).currentType == HEADER) {
            headerPositions.add(position);
            return HEADER;
        } else if (mData.get(position).currentType == FOOTER) {
            footerPositions.add(position);
            return FOOTER;
        }
        bodyPositions.add(position);
        return BODY;
    }

    @Override
    protected K onCreateDefViewHolder(ViewGroup parent, int viewType) {
        if (HEADER == viewType) {
            return createBaseViewHolder(getItemView(headerResId, parent));
        } else if (FOOTER == viewType) {
            return createBaseViewHolder(getItemView(footerResId, parent));
        }
        return super.onCreateDefViewHolder(parent, viewType);
    }

    @Override
    protected boolean isFixedViewType(int type) {
        return super.isFixedViewType(type) || type == HEADER || type == FOOTER;
    }

    @Override
    public void onBindViewHolder(K holder, int position) {
        switch (holder.getItemViewType()) {
            case HEADER:
                setFullSpan(holder);
                convertHead(holder, getItem(position - getHeaderLayoutCount()));
                break;
            case FOOTER:
                setFullSpan(holder);
                convertFoot(holder, getItem(position + getFooterLayoutCount()));
                break;
            default:
                super.onBindViewHolder(holder, position);
                break;
        }
    }

    protected abstract void convertHead(K helper, T item);

    protected abstract void convertFoot(K helper, T item);
}
