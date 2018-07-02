package com.example.elson.gitrxjava;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface GitService {
    @GET("users/{user}/starred")
    Observable<List<GitRepo>> getStarredRepositories(@Path("user") String userName);
}
