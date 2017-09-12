package com.jodelapp.features.photos.presentation;

import com.jodelapp.features.photos.models.UserPhotoAlbumPresentationModel;
import com.jodelapp.features.photos.models.UserPhotoPresentationModel;
import java.util.List;

public interface UserPhotoListContract {

    interface View {
        void loadPhotoList(List<UserPhotoPresentationModel> providers, List<UserPhotoAlbumPresentationModel> albums); // Set list of photos to adapter.
    }

    interface Presenter {
        void onAttached(String userId); // Replaced by getAlbumList in OnResume class, in UserPhotoListView.

        void onDetached();

        void getAlbumList(String userId); // Get list of albums by userId.

        void getNextPhotos(String albumId); // Get list of photos by albumId.
    }
}
