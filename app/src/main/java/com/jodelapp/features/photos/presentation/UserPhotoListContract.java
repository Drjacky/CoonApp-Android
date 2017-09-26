package com.jodelapp.features.photos.presentation;

import com.jodelapp.features.photos.models.UserPhotoAlbumPresentationModel;
import com.jodelapp.features.photos.models.UserPhotoPresentationModel;
import com.jodelapp.views.activities.base.BaseView;
import com.jodelapp.views.activities.base.IBasePresenter;

import java.util.List;

public interface UserPhotoListContract {

    interface View extends BaseView {

        void loadPhotoList(List<UserPhotoPresentationModel> providers, List<UserPhotoAlbumPresentationModel> albums); // Set list of photos to adapter.
    }

    interface Presenter <V extends UserPhotoListContract.View> extends IBasePresenter<V> {

        void getAlbumList(String userId); // Get list of albums by userId.

        void getNextPhotos(String albumId); // Get list of photos by albumId.
    }
}
