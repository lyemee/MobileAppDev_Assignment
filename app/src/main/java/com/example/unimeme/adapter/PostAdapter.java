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

    // ★ 콜백 시그니처 변경: 변경된 Post 전체를 넘김
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

    // onBindViewHolder 안의 바인딩 로직만 바꿔주면 됩니다.
    @Override public void onBindViewHolder(@NonNull VH h, int pos) {
        Post p = items.get(pos);
        h.tvUser.setText("@"+p.username);
        h.iv.setImageResource(p.imageRes);
        h.tvLikes.setText(h.itemView.getContext().getString(R.string.likes, p.likes));

        // ★ 현재 상태에 따른 하트(♡/♥) + 라벨
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

    // ★ 하트/접근성 라벨 업데이트 헬퍼
    private void updateLikeUi(VH h, Post p) {
        String label = p.liked
                ? "♥ " + h.itemView.getContext().getString(R.string.unlike)
                : "♡ " + h.itemView.getContext().getString(R.string.like);
        h.btnLike.setText(label);
        h.btnLike.setContentDescription(label); // 접근성
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
