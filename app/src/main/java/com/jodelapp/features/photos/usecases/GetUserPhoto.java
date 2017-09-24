package com.jodelapp.features.photos.usecases;

import android.support.annotation.NonNull;
import com.jodelapp.data.api.ApiService;
import com.jodelapp.features.photos.models.UserPhotoAlbumPresentationModel;
import com.jodelapp.features.photos.models.UserPhotoPresentationModel;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Single;

public interface GetUserPhoto {

    Single<List<UserPhotoAlbumPresentationModel>> getAlbums(@NonNull String userId); // Get list of albums from server by userId.

    Single<List<UserPhotoPresentationModel>> getPhotos(@NonNull String albumId); // Get list of photos from server by albumId.
}


