package com.jodelapp.views.activities;


public interface MainActivityContract {

    interface View {


        void loadToDoPage();
    }

    interface Presenter {

        void onCreate();

        void onDestroy();
    }
}
