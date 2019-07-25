package com.gnagpal.top_github.ViewModels;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.gnagpal.top_github.Model.DataWrapper;
import com.gnagpal.top_github.Network.ReposRepository;

public class RepoListViewModel extends ViewModel {
    private LiveData<DataWrapper> users;
    private ReposRepository reposRepository = new ReposRepository();
    private MutableLiveData<String> language = new MutableLiveData<>();

    public LiveData<DataWrapper> getUsers() {
        if(users == null){
            users = Transformations.switchMap(language, language -> reposRepository.getUsers(language));
        }
        return users;
    }

    public void setLanguage(String lang){
        language.setValue(lang);
    }

}
