package com.example.plants.base;

import android.view.View;

/**
 * @Description :
 * @Author MIKE-MILK
 * @Date 11/11/20 4:32 PM
 */

public class BasePresenter<V extends BaseView> {

    protected V mView;

    //绑定View
    void attachView(V view){
        this.mView=view;
    }
    //取消绑定
    void detachView(){
        this.mView=null;
    }
    //判断是否绑定
    public boolean isViewAttached() {
        return mView != null;
    }
}
