package com.example.elson.gitrxjava;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import rx.Observable;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitClient {
    private static final String GIT_BASE_URL = "https://api.github.com/";
    private static GitClient mGitClient;
    private GitService mGitService;

    private GitClient() {
        final Gson gson =
                new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(GIT_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        mGitService = retrofit.create(GitService.class);
    }

    public static GitClient getGitClient() {
        if (mGitClient == null) {
            mGitClient = new GitClient();
        }
        return mGitClient;
    }

    public Observable<List<GitRepo>> getStarredRepos(String userName) {
        return mGitService.getStarredRepositories(userName);
    }
}
