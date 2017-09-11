package com.jodelapp.data.api;

import com.jodelapp.data.models.Album;
import com.jodelapp.data.models.Photo;
import com.jodelapp.data.models.ToDo;
import com.jodelapp.data.models.User;
import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/todos")
    Observable<List<ToDo>> getToDos(@Query("userId") String userId);

    @GET("photos")
    Observable<List<Photo>> getPhotos(@Query("albumId") String albumId); // Get photos by albumId; The result of each albumId query is for one page.

    @GET("albums")
    Observable<List<Album>> getAlbums(@Query("userId") String userId); // Get list of albums by userId.

    @GET("users")
    Observable<List<User>> getUsers(); // Get list of users.

}
