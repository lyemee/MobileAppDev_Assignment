package com.example.unimeme;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {
    private static final String NAME = "meme_prefs";
    public static SharedPreferences get(Context c) {
        return c.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }
}

