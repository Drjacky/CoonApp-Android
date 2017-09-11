package com.jodelapp.features.photos.presentation;

import com.jodelapp.features.photos.models.UserPhotoAlbumPresentationModel;
import com.jodelapp.features.photos.models.UserPhotoPresentationModel;
import com.jodelapp.features.photos.usecases.GetUserPhoto;
import com.jodelapp.utilities.rx.RxDisposableFactory;
import com.jodelapp.utilities.rx.RxDisposables;
import com.jodelapp.utilities.rx.ThreadTransformer;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

public final class UserPhotoListPresenter implements UserPhotoListContract.Presenter {

    private static final String USER_ID = "1";

    private final UserPhotoListContract.View view;
    private final GetUserPhoto getUserPhoto;
    private final ThreadTransformer threadTransformer;
    private final RxDisposables disposables;
    private List<UserPhotoAlbumPresentationModel> mAlbums;
    private List<UserPhotoPresentationModel> mPhotos;

    @Inject
    public UserPhotoListPresenter(UserPhotoListContract.View view,
                                  GetUserPhoto getUserPhoto,
                                  ThreadTransformer threadTransformer,
                                  RxDisposableFactory factory) {
        this.view = view;
        this.getUserPhoto = getUserPhoto;
        this.threadTransformer = threadTransformer;
        this.disposables = factory.get();
    }


    @Override
    public void onAttached(String userId) {


        getAlbumList(userId == null ? USER_ID : userId);
        //getNextPhotos("1");

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
                        .subscribe(new Consumer<List<UserPhotoAlbumPresentationModel>>() {
                            @Override
                            public void accept(List<UserPhotoAlbumPresentationModel> userPhotoAlbumPresentationModels) throws Exception {
                                mAlbums = userPhotoAlbumPresentationModels;
                                getNextPhotos(mAlbums.get(0).getId());
                            }
                        }));

                        /*.subscribe(
                                album ->
                                        getNextPhotos(mAlbums.get(0).getId()),
                                error -> Log.e("UserPhotoAlbum", error.getMessage()
                                )));*/




        //return getUserPhoto.getAlbums(userId);
                /*.compose(threadTransformer.applySchedulersOnObservable())
                .subscribe(
                        new Consumer<List<UserPhotoAlbumPresentationModel>>() {
                            @Override
                            public void accept(List<UserPhotoAlbumPresentationModel> userPhotoAlbumPresentationModels) throws Exception {
                                Log.v("userPhotoAlbumPresentationModels",userPhotoAlbumPresentationModels.size()+" !!!");
                            }
                        }
                );
    }*/
        /*return Observable.defer(new Callable<ObservableSource<? extends List<UserPhotoAlbumPresentationModel>>>() {
            @Override
            public ObservableSource<? extends List<UserPhotoAlbumPresentationModel>> call() throws Exception {
                return Observable.just(getUserPhoto.getAlbums(userId));
            }
        });*/

        //return getUserPhoto.getAlbums(userId);
        //return getUserPhoto.getAlbums(userId == null ? USER_ID : userId);
        //return getUserPhoto.getAlbums(userId == null ? USER_ID : userId);



        /*disposables.add(
                getUserPhoto.callAlbum(userId)
                        .compose(threadTransformer.applySchedulers())
                        .subscribe(
                                new Consumer<List<UserPhotoAlbumPresentationModel>>() {
                                    @Override
                                    public void accept(List<UserPhotoAlbumPresentationModel> userPhotoAlbumPresentationModels) throws Exception {
                                        return
                                    }
                                }
                        ));*/
    }

    /*public void goNextAlbum(String userId) {
        disposables.add(
                getUserPhoto.getAlbums(userId)
                        .compose(threadTransformer.applySchedulersOnObservable())
                        .subscribe(
                                album ->
                                        getNextPhotos(album.get(0).getId()),
                                error -> Log.e("UserPhotoAlbum", error.getMessage()
                                )));
    }*/

    @Override
    public void getNextPhotos(String albumId) {
        disposables.add(
                getUserPhoto.getPhotos(albumId)
                        .compose(threadTransformer.applySchedulers())
                        .subscribe(new Consumer<List<UserPhotoPresentationModel>>() {
                            @Override
                            public void accept(List<UserPhotoPresentationModel> userPhotoPresentationModels) throws Exception {
                                mPhotos = userPhotoPresentationModels;
                                view.loadPhotoList(mPhotos, mAlbums);
                            }
                        }));

                        /*.subscribe(
                                view::loadPhotoList,
                                error -> Log.e("UserPhoto", error.getMessage()
                                )));*/
    }

}
