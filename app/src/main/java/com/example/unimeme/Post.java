package com.example.unimeme;

public class Post {
    public final String id;
    public final String username;
    public int likes;
    public int imageRes;

    public boolean liked; // ★ 토글 상태

    public Post(String id, String username, int likes, int imageRes, boolean liked) {
        this.id = id;
        this.username = username;
        this.likes = likes;
        this.imageRes = imageRes;

        this.liked = liked;

    }
}
