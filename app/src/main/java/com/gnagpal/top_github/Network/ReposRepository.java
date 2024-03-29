package com.gnagpal.top_github.Network;

import android.arch.lifecycle.MutableLiveData;

import com.gnagpal.top_github.Model.DataWrapper;
import com.gnagpal.top_github.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReposRepository {

    private RepoService repoService;

    public ReposRepository(){
        repoService = ApiClient.getClient().create(RepoService.class);
    }

    public MutableLiveData<DataWrapper> getUsers(String language){

        final MutableLiveData<DataWrapper> users = new MutableLiveData<>();

        repoService.getRepos(language, "weekly").enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                users.postValue(new DataWrapper(response.body()));
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                users.postValue(new DataWrapper(t));
            }
        });
        return users;
    }
}
