package com.fancy.mvvmdemo.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author pengkuanwang
 * @date 2019-08-21
 */
public abstract class BaseBindingAdapter<M, B extends ViewDataBinding> extends RecyclerView.Adapter {
    protected Context context;
    protected ObservableArrayList<M> items;

    public BaseBindingAdapter(Context mContext) {
        this(mContext, null);
    }

    public BaseBindingAdapter(Context context, ObservableArrayList<M> items) {
        this.context = context;
        this.items = items == null ? new ObservableArrayList<M>() : items;
    }

    public void setItems(ObservableArrayList<M> items) {
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        B binding = DataBindingUtil.inflate(LayoutInflater.from(context), getLayoutResId(), parent, false);
        return new BaseBindingViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        B binding = DataBindingUtil.getBinding(holder.itemView);
        onBindItem(binding, items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @LayoutRes
    protected abstract int getLayoutResId();

    protected abstract void onBindItem(B binding, M item);

    public class BaseBindingViewHolder extends RecyclerView.ViewHolder {
        public BaseBindingViewHolder(View itemView) {
            super(itemView);
        }
    }
}
