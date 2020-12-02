package com.example.plants.contract;

import com.example.plants.base.BaseView;

/**
 * @Description :注册和登录的契合接口
 * @Author MIKE-MILK
 * @Date 11/11/20 5:05 PM
 */

public interface LoginRegisterContract {

    interface Model{
        //登录
        void login();
        //注册
        void register();
    }

    interface View extends BaseView{

        void success();

        @Override
        void onError(Throwable throwable);
    }

    interface Presenter {
        void login();
        void register();
    }
}
