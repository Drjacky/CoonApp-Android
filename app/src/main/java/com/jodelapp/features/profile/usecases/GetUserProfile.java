package com.jodelapp.features.profile.usecases;


import com.jodelapp.data.api.ApiService;
import com.jodelapp.features.profile.models.UserProfilePresentationModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public interface GetUserProfile {

    Single<List<UserProfilePresentationModel>> call();
}

final class GetUserProfileImpl implements GetUserProfile {

    private final ApiService apiService;

    @Inject
    public GetUserProfileImpl(ApiService apiService) {
        this.apiService = apiService;
    }


    @Override
    public Single<List<UserProfilePresentationModel>> call() {
        return apiService.getUsers()
                .flatMapIterable(users -> users)
                .map(user -> new UserProfilePresentationModel(String.valueOf(user.getId()), user.getName(), user.getUsername(), user.getEmail(), user.getAddress(), user.getPhone(), user.getWebsite(), user.getCompany()))
                .toList();
    }
}
