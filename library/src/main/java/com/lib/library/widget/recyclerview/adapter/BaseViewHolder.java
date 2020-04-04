package com.lib.library.widget.recyclerview.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

/**
 * 有时候Adpater不需要ViewHolder的时候，就用官方的RecyclerView.ViewHolder，但由于它是抽象类，所以我们自己实现一个
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

}
