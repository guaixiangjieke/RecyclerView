package com.nl.recyclerview;

import java.util.List;

/**
 * Created by NiuLei on 2018/2/28.
 * 简单适配器
 */

public class SimpleRecyclerAdapter<T> extends RecyclerAdapter<T> {
    public SimpleRecyclerAdapter(List<T> data) {
        super(RecyclerViewHolder.class, android.R.layout.simple_list_item_1, data);
    }

    public SimpleRecyclerAdapter(int layoutId, List<T> data) {
        super(RecyclerViewHolder.class, layoutId, data);
    }
}
