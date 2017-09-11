package com.jodelapp.features.photos.presentation;


public interface UserPhotoListContract {

    interface View {

    }

    interface Presenter {
        void onAttached();

        void onDetached();
    }
}
