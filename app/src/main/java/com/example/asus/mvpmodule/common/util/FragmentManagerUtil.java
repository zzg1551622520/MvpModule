package com.example.asus.mvpmodule.common.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

public class FragmentManagerUtil {
   private static FragmentManager sFragmentManager;
   private static FragmentManagerUtil sFragmentManagerUtils;
   private List<Fragment> mFragments = new ArrayList<>();
    public static FragmentManagerUtil getFragmentManagerUtils(FragmentManager fragmentManager) {
        if(sFragmentManagerUtils == null){
            sFragmentManagerUtils = new FragmentManagerUtil();
        }
        sFragmentManager = fragmentManager;
        return sFragmentManagerUtils;
    }
    /**
     * add fragment
     * 添加
     * */
    public void addFragment(int fraLayoutId,Fragment fragment){
        if(fragment == null)return;
        mFragments.add(fragment);
        sFragmentManager.beginTransaction()
                .add(fraLayoutId, fragment)
                .commit();
    }

    /**
     * remove fragment
     * 移除
     * */
    public void removeFragment(Fragment fragment){
        if(fragment == null)return;
        if(mFragments.contains(fragment)) mFragments.remove(fragment);
        sFragmentManager.beginTransaction()
                .remove(fragment)
                .commit();
    }

    /**
     * show fragment
     * 显示
     * */
    public void showFragment(Fragment fragment){
        if(fragment == null)return;
        sFragmentManager.beginTransaction()
                .show(fragment)
                .commit();
    }
    /**
     * add and show fragment
     * 添加并显示
     * */
    public void addAndShowFragment(int fraLayoutId,Fragment fragment){
        if(fragment == null)return;
        sFragmentManager.beginTransaction()
                .add(fraLayoutId,fragment)
                .show(fragment)
                .commit();
    }
    /**
     * hide fragment
     * 隐藏fragment
     * */
    public void hideFragment(Fragment fragment){
        if(fragment == null)return;
        sFragmentManager.beginTransaction()
                .hide(fragment)
                .commit();
    }

    /**
     * hild  fragmetns
     * 隐藏所有fragment
     * */
    public void hideAllFragment(){
        FragmentTransaction transaction = sFragmentManager.beginTransaction();
        for (int i = 0; i < mFragments.size(); i++) {
            transaction.hide(mFragments.get(i));
        }
        transaction.commit();
    }

    /**
     * show  fragments
     * 显示所有fragment
     * */
    public void showAllFragment(){
        FragmentTransaction transaction = sFragmentManager.beginTransaction();
        for (int i = 0; i < mFragments.size(); i++) {
            transaction.show(mFragments.get(i));
        }
        transaction.commit();
    }

    /**
     * replace fragment
     * 替换fragment
     * */
    public void replaceFragment(int fraLayoutId,Fragment fragment){
        if(fragment == null)return;
        sFragmentManager.beginTransaction()
                .replace(fraLayoutId,fragment)
                .commit();
    }
    /**
     * get fragment list
     * 获取存储fragment集合对象
     * */
    public List<Fragment> getFragments() {
        return mFragments;
    }
}
