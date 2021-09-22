package com.caroline.recyclerview02.adapter;

import android.text.TextUtils;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter <M> extends RecyclerView.Adapter<ViewHolderBinder<M>> {

    public interface OnInteractionListener{
        <M> void onItemClicked(M item);
    }
    private final String sourceTag;
    private final OnInteractionListener listener;
    protected final ArrayList<M> list;
    protected final Class<?> type;

    public RecyclerViewAdapter(@NonNull Class<? extends ViewHolderBinder<M>> type,
                               @NonNull ArrayList<M> list) {
        this.sourceTag = null;
        this.listener = null;
        this.list = list;
        this.type = type;
    }
    public RecyclerViewAdapter(@NonNull Class<? extends ViewHolderBinder<M>> type,
                               @NonNull ArrayList<M> list,
                               @NonNull String sourceTag) {
        this.type = type;
        this.list = list;
        this.sourceTag = sourceTag;
        this.listener = null;
    }

    public RecyclerViewAdapter(@NonNull Class<? extends ViewHolderBinder<M>> type,
                               @NonNull ArrayList<M> list,
                               @Nullable OnInteractionListener listener) {
        this.type = type;
        this.list = list;
        this.sourceTag = null;
        this.listener = listener;
    }

    public RecyclerViewAdapter(@NonNull Class<? extends ViewHolderBinder<M>> type,
                               @NonNull ArrayList<M> list,
                               @Nullable OnInteractionListener listener,
                               @NonNull String sourceTag) {
        this.type = type;
        this.list = list;
        this.listener = listener;
        this.sourceTag = sourceTag;
    }

    @NonNull
    @Override
    public ViewHolderBinder<M> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderBinder<M> holder, int position) {
        M item = list.get(position);

        if(item != null){
            holder.onBindViewHolder(item, position);
            if(listener!=null){
                holder.itemView.setOnClickListener(view -> listener.onItemClicked(item));
            }
        }

    }

    @Override
    public int getItemCount() {
        return list!=null ? list.size():0;
    }

    private ViewHolderBinder<M> create(ViewGroup viewGroup){
        try{
            return TextUtils.isEmpty(sourceTag)?(ViewHolderBinder<M>) type.getDeclaredConstructor(viewGroup.getClass()).newInstance(viewGroup)
                    :(ViewHolderBinder<M>) type.getDeclaredConstructor(String.class, viewGroup.getClass()).newInstance(sourceTag);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
