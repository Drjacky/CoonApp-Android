package com.jodelapp.features.profile.presentation;

import com.jodelapp.features.profile.models.UserProfilePresentationModel;
import com.jodelapp.views.activities.base.BaseView;
import com.jodelapp.views.activities.base.IBasePresenter;

import java.util.List;

public interface UserProfileContract {

    interface View extends BaseView {

        void loadUserList(List<UserProfilePresentationModel> providers);
    }

    interface Presenter <V extends UserProfileContract.View> extends IBasePresenter<V> {
        void getUsersList();

        //void onDetached();
    }
}
