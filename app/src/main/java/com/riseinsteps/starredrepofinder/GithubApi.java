package com.riseinsteps.starredrepofinder;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GithubApi {
    public static final String BASE_URL = "https://api.github.com/";

    private static GithubApi instance;
    private GithubInterface githubInterface;

    private GithubApi() {
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        githubInterface = retrofit.create(GithubInterface.class);
    }

    public static GithubApi getInstance() {
        if (instance == null) {
            instance = new GithubApi();
        }
        return instance;
    }

    public Call<List<User>> getStarredRepo(String userName) {
        return githubInterface.getStarredRepo(userName);
    }
}
