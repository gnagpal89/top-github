package com.gnagpal.top_github;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gnagpal.top_github.ImageCaching.ImageLoader;
import com.gnagpal.top_github.Model.User;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

class ReposRecyclerViewAdapter extends RecyclerView.Adapter<ReposRecyclerViewAdapter.RepoViewHolder>{

    public static final String EXTRA_KEY_REPO_DETAIL = "key_repo_detail";
    Context context;
    List<User> users;

    ImageLoader imageLoader;

    public ReposRecyclerViewAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
        imageLoader = new ImageLoader(context.getApplicationContext());
    }

    @NonNull
    @Override
    public ReposRecyclerViewAdapter.RepoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.repo_list_item, viewGroup, false);
        return new RepoViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ReposRecyclerViewAdapter.RepoViewHolder repoViewHolder, int i) {
        final User user = users.get(i);

        repoViewHolder.bind(user);
        repoViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RepoDetailActivity.class);
                intent.putExtra(EXTRA_KEY_REPO_DETAIL, (Serializable) user);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class RepoViewHolder extends RecyclerView.ViewHolder{

        private ImageView avatarImageView;
        private TextView userNameTextView, repoNameTextView;

        public RepoViewHolder(@NonNull View itemView) {
            super(itemView);
            avatarImageView = itemView.findViewById(R.id.image_avatar);
            userNameTextView = itemView.findViewById(R.id.user_repo);

        }

        public void bind(final User user) {

            imageLoader.DisplayImage(user.getAvatar(), avatarImageView);

            String userName = user.getUsername();
            String repoName = user.getRepo().getName();

            String finalString = userName + " / " + repoName;
            Spannable sb = new SpannableString(finalString);
            sb.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.colorPrimary)), 0, userName.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
            sb.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.colorPrimaryDark)), finalString.indexOf(repoName), finalString.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

            userNameTextView.setText(sb, TextView.BufferType.SPANNABLE);

        }
    }

    public interface RepoClickListener{
        void onClick(User user);
    }
}
