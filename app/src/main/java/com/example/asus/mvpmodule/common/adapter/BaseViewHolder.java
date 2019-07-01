package com.example.asus.mvpmodule.common.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Map;

public class BaseViewHolder extends RecyclerView.ViewHolder {
    private Map<Integer,View> mMap = new ArrayMap<>();
    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void setText(int id,String text){
        TextView view = (TextView) getView(id);
        view.setText(text==null?"":text);
    }
    public void setTextColor(int id,String text){
        TextView view = (TextView) getView(id);
        view.setText(text==null?"":text);
    }

    public void setTextSize(int id,float size){
        TextView view = (TextView) getView(id);
        view.setTextSize(size);
    }

    public void setImageResource(int id,int resourceImag){
        ImageView view = (ImageView) getView(id);
        view.setImageResource(resourceImag);
    }

    public void setViewOnClick(int id,View.OnClickListener onClickListener){
        View view = getView(id);
        view.setOnClickListener(onClickListener);
    }

    public void setItemOnClick(View.OnClickListener onClickListener){
        itemView.setOnClickListener(onClickListener);
    }

    public void setIsVisible(int id,boolean bool){
        View view = getView(id);
        if(bool){
            view.setVisibility(View.VISIBLE);
        }else{
            view.setVisibility(View.GONE);
        }
    }

    public View getView(int viewId){
        View view;
        if(!mMap.containsKey(viewId)){
            view = itemView.findViewById(viewId);
        }else{
            view =  mMap.get(viewId);
        }
        return view;
    }
}
