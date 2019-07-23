package com.gnagpal.top_github;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gnagpal.top_github.Model.Developer;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

class ReposRecyclerViewAdapter extends RecyclerView.Adapter<ReposRecyclerViewAdapter.RepoViewHolder>{

    private static final String EXTRA_KEY_REPO_DETAIL = "key_repo_detail";
    Context context;
    List<Developer> repos;

    public ReposRecyclerViewAdapter(Context context, List<Developer> repos) {
        this.context = context;
        this.repos = repos;
    }

    @NonNull
    @Override
    public ReposRecyclerViewAdapter.RepoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.repo_list_item, viewGroup, false);
        return new RepoViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ReposRecyclerViewAdapter.RepoViewHolder repoViewHolder, int i) {
        final Developer developer = repos.get(i);

        repoViewHolder.bind(developer);
        repoViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RepoDetailActivity.class);
                intent.putExtra(EXTRA_KEY_REPO_DETAIL, (Serializable) developer);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    class RepoViewHolder extends RecyclerView.ViewHolder{

        private ImageView avatarImageView;
        private TextView userNameTextView, repoNameTextView;

        public RepoViewHolder(@NonNull View itemView) {
            super(itemView);
            avatarImageView = itemView.findViewById(R.id.image_avatar);
            repoNameTextView = itemView.findViewById(R.id.repo_name);
            userNameTextView = itemView.findViewById(R.id.username);

        }

        public void bind(final Developer developer) {
            userNameTextView.setText(developer.getUsername());
            repoNameTextView.setText(developer.getRepo().getName());
            Picasso.get().load(developer.getAvatar()).into(avatarImageView);
        }
    }

    public interface RepoClickListener{
        void onClick(Developer developer);
    }
}
