package com.example.unimeme;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername;
    private CheckBox cbKeep; // Checkbox for "keep signed in"
    private static final String KEY_USERNAME = "key_username";
    private static final String KEY_KEEP    = "key_keep_signed_in";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // If user has chosen to stay signed in and username exists, skip login screen
        boolean keepSignedIn = Prefs.get(this).getBoolean(KEY_KEEP, false);
        String savedUser = Prefs.get(this).getString(KEY_USERNAME, null);
        if (keepSignedIn && savedUser != null && !savedUser.trim().isEmpty()) {
            // Go directly to ForYouActivity
            startActivity(new Intent(this, ForYouActivity.class));
            finish();
            return;
        }

        // Otherwise, show login screen
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        cbKeep     = findViewById(R.id.cbKeepSignedIn);
        Button btn = findViewById(R.id.btnLogin);

        // Restore username (optional, not auto-logged-in yet)
        String saved = Prefs.get(this).getString(KEY_USERNAME, null);
        if (saved != null) etUsername.setText(saved);

        // Restore checkbox (default false if never set)
        cbKeep.setChecked(Prefs.get(this).getBoolean(KEY_KEEP, false));

        // Login button click listener
        btn.setOnClickListener(v -> {
            String name = etUsername.getText().toString().trim();

            Prefs.get(this).edit()
                    .putString(KEY_USERNAME, name)
                    .putBoolean(KEY_KEEP, cbKeep.isChecked())
                    .apply();

            // Navigate to the personalized feed (ForYouActivity)
            startActivity(new Intent(this, ForYouActivity.class));
        });
    }
}
