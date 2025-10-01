package com.example.unimeme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.unimeme.adapter.PostAdapter;

import java.util.ArrayList;
import java.util.List;

public class ForYouActivity extends AppCompatActivity {
    private final List<Post> posts = new ArrayList<>();
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_you);
        sp = Prefs.get(this);

        TextView header = findViewById(R.id.tvHeader);
        String username = sp.getString("key_username", "guest");
        header.setText(getString(R.string.for_you_title) + " — @" + username);

        seedData(); // 목업 데이터

        RecyclerView rv = findViewById(R.id.rvPosts);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new PostAdapter(posts, (post, newLikes) -> {
            post.likes = newLikes;
            sp.edit().putInt("likes_" + post.id, newLikes).apply(); // 상태 저장
        }));

        Button toLb = findViewById(R.id.btnLeaderboard);
        Button toAcc = findViewById(R.id.btnAccount);
        toLb.setOnClickListener(v -> startActivity(new Intent(this, LeaderboardActivity.class)));
        toAcc.setOnClickListener(v -> startActivity(new Intent(this, AccountActivity.class)));
    }

    private void seedData() {
        // 이미지 리소스는 임시로 런처 아이콘 사용
        posts.clear();
        posts.add(new Post("p1","yena", sp.getInt("likes_p1", 12), R.mipmap.ic_launcher));
        posts.add(new Post("p2","memeQueen", sp.getInt("likes_p2", 3), R.mipmap.ic_launcher_round));
        posts.add(new Post("p3","cs_student", sp.getInt("likes_p3", 7), R.mipmap.ic_launcher));
    }
}
