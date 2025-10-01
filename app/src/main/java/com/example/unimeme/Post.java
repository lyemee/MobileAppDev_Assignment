package com.example.unimeme;

public class Post {
    public final String id; // Unique identifier for the post
    public final String username;
    public int likes;
    public int imageRes; // Resource ID of the post image

    public boolean liked; // True if the current user liked the post

    public Post(String id, String username, int likes, int imageRes, boolean liked) {
        this.id = id;
        this.username = username;
        this.likes = likes;
        this.imageRes = imageRes;

        this.liked = liked;

    }
}
