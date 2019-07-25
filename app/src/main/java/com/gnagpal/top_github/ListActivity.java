package com.gnagpal.top_github;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.gnagpal.top_github.Model.DataWrapper;
import com.gnagpal.top_github.Model.User;
import com.gnagpal.top_github.ViewModels.RepoListViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    AutoCompleteTextView actv;
    RecyclerView recyclerView;
    ReposRecyclerViewAdapter reposRecyclerViewAdapter;
    List<User> users = new ArrayList<>();
    List<String> languages;
    String selectedLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        if(savedInstanceState!=null)
        selectedLanguage = savedInstanceState.getString("language");

        initViews();
        populateData();

    }

    private void initViews() {

        actv = findViewById(R.id.autoCompleteTextView_languages);
        recyclerView = findViewById(R.id.rv_repos);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void populateData() {

        languages = Arrays.asList(getResources().getStringArray(R.array.languages_array));

        adapter = new ArrayAdapter<>
                (this, android.R.layout.select_dialog_item, languages);

        actv.setThreshold(1);
        actv.setAdapter(adapter);
        actv.setTextColor(Color.RED);

        if(!TextUtils.isEmpty(selectedLanguage)){
            observeViewModel(selectedLanguage);
        }

        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                selectedLanguage = (String) parent.getItemAtPosition(position);

                observeViewModel(selectedLanguage);
            }
        });
    }

    void observeViewModel(String language){

        RepoListViewModel repoListViewModel =
                ViewModelProviders.of(ListActivity.this)
                        .get(RepoListViewModel.class);

        repoListViewModel.setLanguage(language);

        repoListViewModel.getUsers().observe(ListActivity.this, new Observer<DataWrapper>() {
            @Override
            public void onChanged(@Nullable DataWrapper usersWrapper) {
                if(usersWrapper.getError()!=null){
                    Log.e("ListActivity", "Error getting users");
                    Toast.makeText(ListActivity.this, "Error getting users", Toast.LENGTH_LONG).show();
                } else {

                    ReposRecyclerViewAdapter adapter = new ReposRecyclerViewAdapter(ListActivity.this, usersWrapper.getUsers());
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("language", selectedLanguage);
    }
}
