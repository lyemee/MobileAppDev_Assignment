package com.example.unimeme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Button;

import com.example.unimeme.adapter.LeaderboardAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LeaderboardActivity extends AppCompatActivity {
    private final List<Post> leaders = new ArrayList<>();
    private LeaderboardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        RecyclerView rv = findViewById(R.id.rvLeaders);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LeaderboardAdapter(leaders);
        rv.setAdapter(adapter);

        // Refresh button reloads the leaderboard data
        Button refresh = findViewById(R.id.btnRefresh);
        refresh.setOnClickListener(v -> load());
        load();
    }

    private void load() {
        leaders.clear();
        leaders.add(new Post("p1","yena", Prefs.get(this).getInt("likes_p1", 12), R.mipmap.ic_launcher, false));
        leaders.add(new Post("p2","memeQueen", Prefs.get(this).getInt("likes_p2", 3), R.mipmap.ic_launcher_round, false));
        leaders.add(new Post("p3","cs_student", Prefs.get(this).getInt("likes_p3", 7), R.mipmap.ic_launcher, false));

        // Sort posts by likes (highest first)
        Collections.sort(leaders, Comparator.comparingInt(p -> -p.likes));
        adapter.notifyDataSetChanged();
    }
}

