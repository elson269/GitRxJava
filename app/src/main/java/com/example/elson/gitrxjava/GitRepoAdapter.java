package com.example.elson.gitrxjava;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GitRepoAdapter extends BaseAdapter {
    private List<GitRepo> mGitRepos = new ArrayList<>();
    @Override
    public int getCount() {
        return mGitRepos.size();
    }

    @Override
    public Object getItem(int position) {
        if (position < 0 || position >= mGitRepos.size()) {
            return null;
        } else {
            return mGitRepos.get(position);
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View view = (convertView != null ? convertView : createView(parent));
        final GitRepoViewHolder viewHolder = (GitRepoViewHolder) view.getTag();
        viewHolder.setGitRepos((GitRepo) getItem(position));
        return view;
    }

    private View createView(ViewGroup parent) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.git_repo_item, parent, false);
        final GitRepoViewHolder viewHolder = new GitRepoViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    public void setGitRepos(@Nullable List<GitRepo> repos) {
        if (repos == null) {
            return;
        }
        mGitRepos.clear();
        mGitRepos.addAll(repos);
        notifyDataSetChanged();
    }

    private static class GitRepoViewHolder {

        private TextView textRepoName;
        private TextView textRepoDescription;
        private TextView textLanguage;
        private TextView textStars;

        public GitRepoViewHolder(View view) {
            textRepoName = (TextView) view.findViewById(R.id.text_repo_name);
            textRepoDescription = (TextView) view.findViewById(R.id.text_repo_description);
            textLanguage = (TextView) view.findViewById(R.id.text_language);
            textStars = (TextView) view.findViewById(R.id.text_stars);
        }

        public void setGitRepos(GitRepo gitHubRepo) {
            textRepoName.setText(gitHubRepo.name);
            textRepoDescription.setText(gitHubRepo.description);
            textLanguage.setText("Language: " + gitHubRepo.language);
            textStars.setText("Stars: " + gitHubRepo.stargazersCount);
        }
    }

}
