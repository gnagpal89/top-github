package com.gnagpal.top_github.ViewModels;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.gnagpal.top_github.Model.User;
import com.gnagpal.top_github.Network.ReposRepository;

import java.util.List;

public class RepoListViewModel extends ViewModel {
    private LiveData<List<User>> users;
    private Application application;

    public RepoListViewModel(Application application, String language){
        this.application = application;
        users = ReposRepository.getInstance().getUsers(language);
    }
    public LiveData<List<User>> getUsers(String language) {
        return users;
    }
}
