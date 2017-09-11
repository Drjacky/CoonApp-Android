package com.jodelapp.features.photos.presentation;

import com.jodelapp.features.photos.models.UserPhotoAlbumPresentationModel;
import com.jodelapp.features.photos.models.UserPhotoPresentationModel;
import java.util.List;

public interface UserPhotoListContract {

    interface View {

        void loadPhotoList(List<UserPhotoPresentationModel> providers, List<UserPhotoAlbumPresentationModel> albums);

    }

    interface Presenter {

        void onAttached(String userId);

        void onDetached();

        void getAlbumList(String userId);

        void getNextPhotos(String albumId);
    }
}
