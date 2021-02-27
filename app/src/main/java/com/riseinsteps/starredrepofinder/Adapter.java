package com.riseinsteps.starredrepofinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends BaseAdapter {
    private List<User> userList = new ArrayList<>();
    private Context context;

    public Adapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        if (position < 0 || position >= userList.size()) {
            return null;
        } else {
            return userList.get(position);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.setData(userList.get(position).getName(), userList.get(position).getDescription(),
                userList.get(position).getLanguage(), userList.get(position).getStargazersCount());
        view.setTag(viewHolder);
        return view;
    }

    public static class ViewHolder {
        private TextView textRRepoName;
        private TextView textRepoDesc;
        private TextView textRepoLanguage;
        private TextView textRepoStars;

        public ViewHolder(View itemView) {
            textRRepoName = itemView.findViewById(R.id.text_repo_name);
            textRepoDesc = itemView.findViewById(R.id.text_repo_description);
            textRepoLanguage = itemView.findViewById(R.id.text_language);
            textRepoStars = itemView.findViewById(R.id.text_stars);
        }

        private void setData(final String repoName, final String repoDesc, final String repoLang, final int repoStars) {
            textRRepoName.setText(repoName);
            textRepoDesc.setText(repoDesc);
            textRepoLanguage.setText(repoLang);
            textRepoStars.setText(String.valueOf(repoStars));
        }

    }
}
