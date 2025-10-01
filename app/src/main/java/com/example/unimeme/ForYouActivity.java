package com.example.unimeme;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ForYouActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_you);

        Button btnAccount = findViewById(R.id.btnGoAccount);
        Button btnLeaderboard = findViewById(R.id.btnGoLeaderboard);
        Button btnLogout = findViewById(R.id.btnLogout);

        if (btnAccount != null) {
            btnAccount.setOnClickListener(v ->
                    startActivity(new Intent(this, AccountActivity.class)));
        } else Log.e("ForYouActivity", "btnGoAccount missing");

        if (btnLeaderboard != null) {
            btnLeaderboard.setOnClickListener(v ->
                    startActivity(new Intent(this, LeaderboardActivity.class)));
        } else Log.e("ForYouActivity", "btnGoLeaderboard missing");

        if (btnLogout != null) {
            btnLogout.setOnClickListener(v -> {
                getSharedPreferences("unimeme_prefs", MODE_PRIVATE)
                        .edit()
                        .putBoolean("logged_in", false)
                        .putBoolean("auto", false)
                        .apply();
                startActivity(new Intent(this, MainActivity.class));
                finish();
            });
        } else Log.e("ForYouActivity", "btnLogout missing");
    }
}
