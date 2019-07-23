package com.gnagpal.top_github;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.gnagpal.top_github.Model.Developer;
import com.gnagpal.top_github.Network.ApiClient;
import com.gnagpal.top_github.Network.RepoService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    AutoCompleteTextView actv;
    RecyclerView recyclerView;
    ReposRecyclerViewAdapter reposRecyclerViewAdapter;
    List<Developer> repos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        initViews();
        populateData();

    }

    private void initViews() {

        //Getting the instance of AutoCompleteTextView
        actv = findViewById(R.id.autoCompleteTextView_languages);
        recyclerView = findViewById(R.id.rv_repos);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    private void populateData() {

        final List<String> languages = Arrays.asList(getResources().getStringArray(R.array.languages_array));

        adapter = new ArrayAdapter<>
                (this, android.R.layout.select_dialog_item, languages);

        actv.setThreshold(1);
        actv.setAdapter(adapter);
        actv.setTextColor(Color.RED);

        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final ProgressDialog progress = new ProgressDialog(ListActivity.this);
                progress.setMessage("Fetching repos...");
                progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
                progress.show();

                final RepoService repoService = ApiClient.getClient().create(RepoService.class);
                Call<List<Developer>> call = repoService.getRepos(languages.get(position), "weekly");

                call.enqueue(new Callback<List<Developer>>() {
                    @Override
                    public void onResponse(Call<List<Developer>> call, Response<List<Developer>> response) {
                        progress.dismiss();

                        if(response.body()!=null){
                            repos = response.body();
                            ReposRecyclerViewAdapter adapter = new ReposRecyclerViewAdapter(ListActivity.this, repos);
                            recyclerView.setAdapter(adapter);

                        }
                    }

                    @Override
                    public void onFailure(Call<List<Developer>> call, Throwable t) {
                        progress.dismiss();
                        Toast.makeText(ListActivity.this, "Something unexpected occurred!", Toast.LENGTH_LONG).show();
                        Log.e("ListActivity", "Error getting repos");
                    }
                });
            }
        });
    }

}
