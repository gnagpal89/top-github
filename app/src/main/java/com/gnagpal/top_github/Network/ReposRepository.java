package com.gnagpal.top_github.Network;

import android.arch.lifecycle.MutableLiveData;

import com.gnagpal.top_github.Model.Repo;
import com.gnagpal.top_github.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReposRepository {

    private static ReposRepository reposRepository;
    private RepoService repoService;

    public static ReposRepository getInstance(){
        if(reposRepository == null){
            reposRepository = new ReposRepository();
        }
        return reposRepository;
    }

    private ReposRepository(){
        repoService = ApiClient.getClient().create(RepoService.class);
    }

    public MutableLiveData<List<User>> getUsers(String language){
        final MutableLiveData<List<User>> users = new MutableLiveData<>();

        repoService.getRepos(language, "weekly").enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                users.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
        return users;
    }
}