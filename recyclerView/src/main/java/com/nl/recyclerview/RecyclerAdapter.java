package com.nl.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * recyclerView 适配器
 */

public class RecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder<T>> implements RecyclerViewHolder.OnViewHolderElementClickListener {
    private final Class<? extends RecyclerViewHolder> viewHolderCls;
    protected final int layoutId;
    protected final List<T> data;
    protected LayoutInflater layoutInflater;

    public RecyclerAdapter(Class<? extends RecyclerViewHolder> viewHolderCls, int layoutId, List<T> data) {
        this.viewHolderCls = viewHolderCls;
        this.layoutId = layoutId;
        this.data = data;
    }

    @Override
    public RecyclerViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        View contentView = layoutInflater.inflate(layoutId, null);
        contentView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        try {
            Constructor<? extends RecyclerViewHolder> constructor = viewHolderCls.getConstructor(View.class);
            constructor.setAccessible(true);
            RecyclerViewHolder<T> recyclerViewHolder = constructor.newInstance(contentView);
            recyclerViewHolder.setElementClickListener(this);
            return recyclerViewHolder;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return new RecyclerViewHolder<>(contentView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder<T> holder, int position) {
        holder.onBind(this, data.get(position));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public T getItem(int position) {
        return data == null ? null : data.get(position);
    }

    @Override
    public void onViewHolderElementClick(RecyclerView.ViewHolder viewHolder, int position, String tag) {

    }
}
