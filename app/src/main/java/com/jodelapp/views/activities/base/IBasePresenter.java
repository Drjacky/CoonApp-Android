package com.jodelapp.views.activities.base;

public interface IBasePresenter<V extends BaseView> {
    void onAttach(V baseView);
    void onDetach();
}
