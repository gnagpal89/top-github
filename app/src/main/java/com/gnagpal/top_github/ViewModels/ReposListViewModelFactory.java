package com.gnagpal.top_github.ViewModels;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

public class ReposListViewModelFactory implements ViewModelProvider.Factory {

    private Application mApplication;
    private String language;

    public ReposListViewModelFactory(Application application, String language) {
        mApplication = application;
        this.language = language;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new RepoListViewModel(mApplication, language);
    }
}
