package com.example.unimeme.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unimeme.Post;
import com.example.unimeme.R;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.VH> {

    public interface OnLikeChanged { void onChanged(Post post, int newLikes); }
    private final List<Post> items;
    private final OnLikeChanged cb;

    public PostAdapter(List<Post> items, OnLikeChanged cb) {
        this.items = items; this.cb = cb;
    }

    @NonNull @Override public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new VH(v);
    }

    @Override public void onBindViewHolder(@NonNull VH h, int pos) {
        Post p = items.get(pos);
        h.tvUser.setText("@"+p.username);
        h.iv.setImageResource(p.imageRes);
        h.tvLikes.setText(h.itemView.getContext().getString(R.string.likes, p.likes));
        h.btnLike.setOnClickListener(v -> {
            p.likes += 1;
            h.tvLikes.setText(v.getContext().getString(R.string.likes, p.likes));
            if (cb != null) cb.onChanged(p, p.likes);
        });
    }

    @Override public int getItemCount() { return items.size(); }

    static class VH extends RecyclerView.ViewHolder {
        TextView tvUser, tvLikes; ImageView iv; Button btnLike;
        VH(@NonNull View v) {
            super(v);
            tvUser = v.findViewById(R.id.tvUsername);
            tvLikes = v.findViewById(R.id.tvLikes);
            iv = v.findViewById(R.id.ivMeme);
            btnLike = v.findViewById(R.id.btnLike);
        }
    }
}
