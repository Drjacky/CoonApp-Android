package com.jodelapp.features.profile.presentation;

import com.jodelapp.features.profile.models.UserProfilePresentationModel;
import java.util.List;

public interface UserProfileContract {

    interface View {

        void loadUserList(List<UserProfilePresentationModel> providers);
    }

    interface Presenter {
        void onAttached();

        void onDetached();
    }
}
