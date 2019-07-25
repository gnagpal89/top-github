package com.gnagpal.top_github;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView;
import android.widget.TextView;

import com.gnagpal.top_github.ImageCaching.ImageLoader;
import com.gnagpal.top_github.Model.User;

import static com.gnagpal.top_github.ReposRecyclerViewAdapter.EXTRA_KEY_REPO_DETAIL;

public class RepoDetailActivity extends AppCompatActivity {

    User user;
    TextView repoDetailView, userNameView, repoNameView, nameView, userUrlView, repoUrlView;
    ImageView avatarView;
    ImageLoader imageLoader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_detail2);

        user = (User) getIntent().getSerializableExtra(EXTRA_KEY_REPO_DETAIL);

        initViews();

        populateViews();
    }

    private void initViews() {
        repoDetailView = findViewById(R.id.repo_detail);
        repoNameView = findViewById(R.id.repo_name);
        userNameView = findViewById(R.id.username);
        avatarView = findViewById(R.id.image_avatar_detail);
        nameView = findViewById(R.id.name);
        userUrlView = findViewById(R.id.user_url);
        repoUrlView = findViewById(R.id.repo_url);
    }

    private void populateViews() {
        imageLoader = new ImageLoader(getApplicationContext());

        if(user!=null){
            repoDetailView.setText(user.getRepo().getDescription());
            repoNameView.setText(user.getRepo().getName());

//            repoNameView.setText(getSpannable(user.getRepo().getName(), user.getRepo().getUrl()), TextView.BufferType.SPANNABLE);
            nameView.setText(user.getName());
//            userNameView.setText(getSpannable(user.getUsername(), user.getUrl()), TextView.BufferType.SPANNABLE);
            userNameView.setText(user.getUsername());

            userUrlView.setText(user.getUrl());
            repoUrlView.setText(user.getRepo().getUrl());
//            Picasso.get().load(user.getAvatar()).into(avatarView);

            imageLoader.DisplayImage(user.getAvatar(), avatarView);



        }
    }
    Spannable getSpannable(String s1, String s2){

        String finalString = s1 + " - " + s2;
        Spannable sb = new SpannableString(finalString);
        sb.setSpan(new ForegroundColorSpan(this.getResources().getColor(R.color.colorPrimary)), 0, s1.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        sb.setSpan(new ForegroundColorSpan(this.getResources().getColor(R.color.colorPrimaryDark)), finalString.indexOf(s2), finalString.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        return sb;
    }
}
