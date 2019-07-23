package com.gnagpal.top_github;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.gnagpal.top_github.Model.User;
import com.squareup.picasso.Picasso;

import static com.gnagpal.top_github.ReposRecyclerViewAdapter.EXTRA_KEY_REPO_DETAIL;

public class RepoDetailActivity extends AppCompatActivity {

    User user;
    TextView repoDetailView, repoUrlView, userNameView, repoNameView, userUrlView, nameView;
    ImageView avatarView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_detail);

        user = (User) getIntent().getSerializableExtra(EXTRA_KEY_REPO_DETAIL);

        initViews();

        populateViews();
    }

    private void initViews() {
        repoDetailView = findViewById(R.id.repo_detail);
        repoNameView = findViewById(R.id.repo_name_detail);
        repoUrlView = findViewById(R.id.repo_url);
        userNameView = findViewById(R.id.username_detail);
        userUrlView = findViewById(R.id.url);
        avatarView = findViewById(R.id.image_avatar_detail);
        nameView = findViewById(R.id.name);
    }

    private void populateViews() {
        if(user!=null){
            repoDetailView.setText(user.getRepo().getDescription());
            repoUrlView.setText(user.getRepo().getUrl());
            repoNameView.setText(user.getRepo().getName());
            nameView.setText(user.getName());
            userNameView.setText(user.getUsername());
            userUrlView.setText(user.getUrl());
            Picasso.get().load(user.getAvatar()).into(avatarView);

        }
    }
}
