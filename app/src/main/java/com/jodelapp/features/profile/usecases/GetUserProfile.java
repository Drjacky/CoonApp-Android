package com.jodelapp.features.profile.usecases;

import com.jodelapp.data.api.ApiService;
import com.jodelapp.features.profile.models.UserProfilePresentationModel;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Single;

public interface GetUserProfile {

    Single<List<UserProfilePresentationModel>> call();

}


