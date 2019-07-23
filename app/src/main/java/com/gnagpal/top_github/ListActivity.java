package com.gnagpal.top_github;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.Arrays;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    AutoCompleteTextView actv;
    RecyclerView recyclerView;
    ReposRecyclerViewAdapter reposRecyclerViewAdapter;

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

    }

    private void populateData() {

        List<String> languages = Arrays.asList(getResources().getStringArray(R.array.languages_array));

        adapter = new ArrayAdapter<>
                (this, android.R.layout.select_dialog_item, languages);

        actv.setThreshold(1);
        actv.setAdapter(adapter);
        actv.setTextColor(Color.RED);
    }

}
