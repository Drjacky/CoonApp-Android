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

final class GetUserPhotoImpl implements GetUserPhoto {

    private final ApiService apiService;

    @Inject
    public GetUserPhotoImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Single<List<UserPhotoAlbumPresentationModel>> getAlbums(@NonNull String userId) {
        return apiService.getAlbums(userId)
                .flatMapIterable(albums -> albums)
                .map(album ->  new UserPhotoAlbumPresentationModel(String.valueOf(album.getId()), album.getTitle(), String.valueOf(album.getUserId())))
                .toList();
    }

    @Override
    public Single<List<UserPhotoPresentationModel>> getPhotos(@NonNull String albumId) {
        return apiService.getPhotos(albumId)
                .flatMapIterable(photos -> photos)
                .map(photo -> new UserPhotoPresentationModel(String.valueOf(photo.getAlbumId()), String.valueOf(photo.getId()), photo.getTitle(), photo.getUrl(), photo.getThumbnailUrl()))
                .toList();
    }
}
