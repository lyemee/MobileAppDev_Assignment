package com.example.unimeme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.VH> {

    public interface OnPostChanged { void onChanged(Post post); }
    private final List<Post> items;
    private final OnPostChanged cb;

    public PostAdapter(List<Post> items, OnPostChanged cb) {
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

        updateLikeUi(h, p);

        h.btnLike.setOnClickListener(v -> {
            if (p.liked) {
                if (p.likes > 0) p.likes -= 1;
                p.liked = false;
            } else {
                p.likes += 1;
                p.liked = true;
            }
            h.tvLikes.setText(v.getContext().getString(R.string.likes, p.likes));
            updateLikeUi(h, p);
            if (cb != null) cb.onChanged(p);
        });
    }

    private void updateLikeUi(VH h, Post p) {
        String label = p.liked
                ? "♥ " + h.itemView.getContext().getString(R.string.unlike)
                : "♡ " + h.itemView.getContext().getString(R.string.like);
        h.btnLike.setText(label);
        h.btnLike.setContentDescription(label);
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
