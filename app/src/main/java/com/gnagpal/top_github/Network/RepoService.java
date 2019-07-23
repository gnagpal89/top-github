package com.gnagpal.top_github.Network;

import com.gnagpal.top_github.Model.Developer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RepoService {

    @GET("developers")
    Call<List<Developer>> getRepos(@Query("language") String language, @Query("since") String since);
}
