package com.gnagpal.top_github;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

class ReposRecyclerViewAdapter extends RecyclerView.Adapter<ReposRecyclerViewAdapter.RepoViewHolder>{
    @NonNull
    @Override
    public ReposRecyclerViewAdapter.RepoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ReposRecyclerViewAdapter.RepoViewHolder repoViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class RepoViewHolder extends RecyclerView.ViewHolder {
        public RepoViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
