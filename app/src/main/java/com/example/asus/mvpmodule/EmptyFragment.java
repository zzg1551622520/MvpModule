package com.example.asus.mvpmodule;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.example.asus.mvpmodule.common.adapter.BaseAdapter;
import com.example.asus.mvpmodule.common.adapter.BaseViewHolder;
import com.example.asus.mvpmodule.common.bean.News;
import com.example.asus.mvpmodule.common.config.API;
import com.example.asus.mvpmodule.common.mvp.MvpModel;
import com.example.asus.mvpmodule.common.mvp.MvpPresenter;
import com.example.asus.mvpmodule.common.ui.BaseMVPFragment;
import com.example.asus.mvpmodule.common.util.GlideUtil;
import com.example.asus.mvpmodule.common.util.ToastUtil;
import com.example.asus.mvpmodule.databinding.FragmentEmptyBinding;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class EmptyFragment extends BaseMVPFragment<MvpPresenter,FragmentEmptyBinding> implements XRecyclerView.LoadingListener {

    private List<News.DataBean> mDataBeans = new ArrayList<>();
    private BaseAdapter<News.DataBean> mAdapter;
    private boolean isRefresh =false;
    private int page = 2;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_empty;
    }

    @Override
    protected void initView() {
        mAdapter = new BaseAdapter<News.DataBean>(mDataBeans, R.layout.item, getContext()) {
            @Override
            protected void bindview(BaseViewHolder viewHolder, News.DataBean bean, final int position) {
                viewHolder.setText(R.id.tv,position+"");
                GlideUtil.loadCircleImage(bean.getUrl(),(ImageView) viewHolder.getView(R.id.image),R.drawable.study);
                viewHolder.setItemOnClick(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.showShortToast(String.valueOf(position));
                    }
                });
            }
        };
        dataBinding.recycler.setAdapter(mAdapter);
        dataBinding.recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        dataBinding.recycler.setLoadingListener(this);
    }

    @Override
    protected void setListener() {
    }

    @Override
    protected void fillData() {
        mP.getData(API.news,page);
    }

    @Override
    protected MvpPresenter getPresenter() {
        return new MvpPresenter(new MvpModel(),this);
    }

    @Override
    protected void netSuccessResult(Object o, int API_TAG) {
        switch (API_TAG){
            case API.news:
                News news = (News) o;
                List<News.DataBean> data = news.getData();
                if(isRefresh){
                    mAdapter.setData(data,true);
                    dataBinding.recycler.refreshComplete();
                }else{
                    mAdapter.setData(data,false);
                    dataBinding.recycler.loadMoreComplete();
                }
                mAdapter.notifyDataSetChanged();
                break;

            default:

                break;
        }
    }

    @Override
    protected void click(View view) {
    }

    @Override
    public void onRefresh() {
        isRefresh = true;
        page = 2;
        mP.getData(API.news,page);
    }

    @Override
    public void onLoadMore() {
        isRefresh = false;
        page++;
        mP.getData(API.news,page);
    }
}
