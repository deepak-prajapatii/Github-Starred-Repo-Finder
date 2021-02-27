package com.riseinsteps.starredrepofinder;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubInterface {

    @GET("users/{user}/starred")
    Call<List<User>> getStarredRepo(@Path("user") String userName);
}
