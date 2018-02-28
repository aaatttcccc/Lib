package com.lib.library.widget.recyclerview.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * 父类适配器
 * Created by zhongjh on 2015/11/6.
 */
public abstract class BaseRecyclerAdapter<T,VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected List<T> mData;
    protected Activity mActivity;

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public List<T> getData() {
        return mData;
    }

    public T getObject(int position) {
        return mData.get(position);
    }

    public BaseRecyclerAdapter(Activity activity, List<T> datas) {
        mActivity = activity;
        mData = datas;
    }

    public void setData(List<T> datas) {
        this.mData.clear();
        this.mData.addAll(datas);
    }

    /**
     * 添加
     * @param position 索引
     * @param object 对象
     */
    public void addItem(int position, T object) {
        mData.add(position, object);
        notifyItemInserted(position);
        notifyItemRangeChanged(position, mData.size());
    }

    /**
     * 更新
     * @param position 索引
     * @param object 对象
     */
    public void updateItem(int position,T object){
        mData.set(position, object);
        notifyItemChanged(position);
    }

    /**
     * 删除
     * @param position 索引
     */
    public void removeItem(int position){
        mData.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mData.size());
    }

}
