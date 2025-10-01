package com.example.unimeme.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unimeme.Post;
import com.example.unimeme.R;

import java.util.List;

// Adapter for displaying leaderboard of posts
public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.VH> {
    // list of Post objects
    private final List<Post> data;
    public LeaderboardAdapter(List<Post> data) { this.data = data; }

    //
    @NonNull @Override public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        return new VH(v);
    }

    // binds data to the views
    @Override public void onBindViewHolder(@NonNull VH h, int pos) {
        Post p = data.get(pos);
        h.title.setText((pos+1)+". @"+p.username);
        h.subtitle.setText(h.itemView.getContext().getString(R.string.likes, p.likes));
    }

    // return the number of items in the list
    @Override public int getItemCount() { return data.size(); }

    static class VH extends RecyclerView.ViewHolder {
        TextView title, subtitle;
        ImageView icon;
        VH(@NonNull View v) {
            super(v);
            title = v.findViewById(android.R.id.text1);
            subtitle = v.findViewById(android.R.id.text2);
        }
    }
}

