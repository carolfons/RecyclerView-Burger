package com.caroline.recyclerview02.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class ViewHolderBinder<M> extends RecyclerView.ViewHolder {
    protected Context context;
    public ViewHolderBinder(@NonNull ViewGroup parent, @NonNull @LayoutRes Integer layout) {
        super(LayoutInflater.from(parent.getContext()).inflate(layout, parent, false));
        this.context = parent.getContext();
    }
    public abstract void onBindViewHolder(final M model , int position);
}
