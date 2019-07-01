package com.example.asus.mvpmodule.common.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class BaseAdapter<O> extends RecyclerView.Adapter<BaseViewHolder> {
    private List<O> mList;
    private int layotId;
    private Context mContext;

    public BaseAdapter(List<O> list, int layotId, Context context) {
        mList = list;
        this.layotId = layotId;
        mContext = context;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(layotId, viewGroup, false);
        BaseViewHolder viewHolder = new BaseViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder viewHolder, int position) {
        bindview(viewHolder,mList.get(position),position);
    }

    protected abstract void bindview(BaseViewHolder viewHolder, O bean, int i);

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setData(List<O> list,boolean isClearList){
        if(isClearList){
            this.mList.clear();
        }
        this.mList.addAll(list);
    }

}
