package com.jodelapp.features.photos.presentation;

import android.util.Log;

import com.jodelapp.features.photos.models.UserPhotoAlbumPresentationModel;
import com.jodelapp.features.photos.models.UserPhotoPresentationModel;
import com.jodelapp.features.photos.usecases.GetUserPhoto;
import com.jodelapp.utilities.StringUtils;
import com.jodelapp.utilities.rx.RxDisposableFactory;
import com.jodelapp.utilities.rx.RxDisposables;
import com.jodelapp.utilities.rx.ThreadTransformer;
import com.jodelapp.views.activities.base.BasePresenter;
import java.util.List;
import javax.inject.Inject;

import io.reactivex.functions.Consumer;

public final class UserPhotoListPresenter <V extends UserPhotoListContract.View> extends BasePresenter<V> implements UserPhotoListContract.Presenter<V> {

    private static final String USER_ID = "1"; // Default userId, if user didn't click on the UserProfileView fragment.
    private final GetUserPhoto getUserPhoto;
    private final ThreadTransformer threadTransformer;
    private final StringUtils stringUtils;
    private final RxDisposables disposables;
    private List<UserPhotoAlbumPresentationModel> mAlbums;
    private List<UserPhotoPresentationModel> mPhotos;

    @Inject
    public UserPhotoListPresenter(
                                  GetUserPhoto getUserPhoto,
                                  ThreadTransformer threadTransformer,
                                  StringUtils stringUtils,
                                  RxDisposableFactory rxDisposableFactory) {
        super(threadTransformer, rxDisposableFactory);
        this.getUserPhoto = getUserPhoto;
        this.stringUtils = stringUtils;
        this.threadTransformer = getThreadTransformer();
        this.disposables = getRxDisposables();
    }

    @Override
    public void getAlbumList(String userId){
        getMvpView().showLoading();
        disposables.add(
                getUserPhoto.getAlbums(userId)
                        .compose(threadTransformer.applySchedulers())
                        .subscribe(userPhotoAlbumPresentationModels -> {
                            getMvpView().hideLoading();
                            mAlbums = userPhotoAlbumPresentationModels;
                            getNextPhotos(mAlbums.get(0).getId());
                        }, throwable -> {
                            getMvpView().hideLoading();
                            Log.e("UserPhotoAlbum", throwable.getMessage());
                        }));
    }

    @Override
    public void getNextPhotos(String albumId) {
        getMvpView().showLoading();
        disposables.add(
                getUserPhoto.getPhotos(albumId)
                        .compose(threadTransformer.applySchedulers())
                        .subscribe(userPhotoPresentationModels -> {
                            getMvpView().hideLoading();
                            mPhotos = userPhotoPresentationModels;
                            getMvpView().loadPhotoList(mPhotos, mAlbums); // Pass the mAlbums to the view, for pagination usage.
                        }, throwable -> {
                            getMvpView().hideLoading();
                            Log.e("UserPhoto", throwable.getMessage());
                        }));
    }

}
