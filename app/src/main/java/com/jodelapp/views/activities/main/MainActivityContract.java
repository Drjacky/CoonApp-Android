package com.jodelapp.views.activities.main;

import com.jodelapp.views.activities.base.BaseView;
import com.jodelapp.views.activities.base.IBasePresenter;

public interface MainActivityContract {

    interface View extends BaseView {

        //void loadToDoPage();
    }

    interface Presenter <V extends MainActivityContract.View> extends IBasePresenter<V> {

        /*void onCreate();

        void onDestroy();*/
    }
}
