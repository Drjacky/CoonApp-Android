package com.jodelapp.features.photos.presentation;

import com.jodelapp.features.photos.models.UserPhotoAlbumPresentationModel;
import com.jodelapp.features.photos.models.UserPhotoPresentationModel;
import com.jodelapp.features.photos.usecases.GetUserPhoto;
import com.jodelapp.utilities.StringUtils;
import com.jodelapp.utilities.rx.RxDisposableFactory;
import com.jodelapp.utilities.rx.RxDisposables;
import com.jodelapp.utilities.rx.ThreadTransformer;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.functions.Consumer;

public final class UserPhotoListPresenter implements UserPhotoListContract.Presenter {

    private static final String USER_ID = "1"; // Default userId, if user didn't click on the UserProfileView fragment.
    private final UserPhotoListContract.View view;
    private final GetUserPhoto getUserPhoto;
    private final ThreadTransformer threadTransformer;
    private final StringUtils stringUtils;
    private final RxDisposables disposables;
    private List<UserPhotoAlbumPresentationModel> mAlbums;
    private List<UserPhotoPresentationModel> mPhotos;

    @Inject
    public UserPhotoListPresenter(UserPhotoListContract.View view,
                                  GetUserPhoto getUserPhoto,
                                  ThreadTransformer threadTransformer,
                                  StringUtils stringUtils,
                                  RxDisposableFactory factory) {
        this.view = view;
        this.getUserPhoto = getUserPhoto;
        this.threadTransformer = threadTransformer;
        this.stringUtils = stringUtils;
        this.disposables = factory.get();
    }

    @Override
    public void onAttached(String userId) {
        getAlbumList(stringUtils.valueOrDefault(userId, USER_ID));
    }

    @Override
    public void onDetached() {
        disposables.clear();
    }

    @Override
    public void getAlbumList(String userId){
        disposables.add(
                getUserPhoto.getAlbums(userId)
                        .compose(threadTransformer.applySchedulers())
                        .subscribe(userPhotoAlbumPresentationModels -> {
                            mAlbums = userPhotoAlbumPresentationModels;
                            getNextPhotos(mAlbums.get(0).getId());
                        }));
    }

    @Override
    public void getNextPhotos(String albumId) {
        disposables.add(
                getUserPhoto.getPhotos(albumId)
                        .compose(threadTransformer.applySchedulers())
                        .subscribe(userPhotoPresentationModels -> {
                            mPhotos = userPhotoPresentationModels;
                            view.loadPhotoList(mPhotos, mAlbums); // Pass the mAlbums to the view, for pagination usage.
                        }));
    }

}
