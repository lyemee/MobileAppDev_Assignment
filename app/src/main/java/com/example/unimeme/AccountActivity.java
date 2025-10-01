package com.example.unimeme;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

// Allows user to edit and save their account info
public class AccountActivity extends AppCompatActivity {
    private EditText etDisplay, etBio; // input fields for display name and bio
    private CheckBox cbPrivate; // checkbox

    // Keys for saving/restoring instance state and SharedPreferences
    private static final String K_DISPLAY = "k_display";
    private static final String K_BIO = "k_bio";
    private static final String K_PRIV = "k_priv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        // Bind UI elements
        etDisplay = findViewById(R.id.etDisplayName);
        etBio = findViewById(R.id.etBio);
        cbPrivate = findViewById(R.id.cbPrivate);

        if (savedInstanceState != null) {
            etDisplay.setText(savedInstanceState.getString(K_DISPLAY, ""));
            etBio.setText(savedInstanceState.getString(K_BIO, ""));
            cbPrivate.setChecked(savedInstanceState.getBoolean(K_PRIV, false));
        } else {
            etDisplay.setText(Prefs.get(this).getString(K_DISPLAY, ""));
            etBio.setText(Prefs.get(this).getString(K_BIO, ""));
            cbPrivate.setChecked(Prefs.get(this).getBoolean(K_PRIV, false));
        }

        // Save button: stores account data in SharedPreferences
        Button save = findViewById(R.id.btnSave);
        save.setOnClickListener(v -> {
            Prefs.get(this).edit()
                    .putString(K_DISPLAY, etDisplay.getText().toString())
                    .putString(K_BIO, etBio.getText().toString())
                    .putBoolean(K_PRIV, cbPrivate.isChecked())
                    .apply();
            Toast.makeText(this, getString(R.string.toast_saved), Toast.LENGTH_SHORT).show();
        });

        // Navigate back to the "For You" feed
        findViewById(R.id.btnBackToFeed).setOnClickListener(v ->
                startActivity(new Intent(this, ForYouActivity.class))
        );

        // Navigate to leaderboard activity
        findViewById(R.id.btnToLeader).setOnClickListener(v ->
                startActivity(new Intent(this, LeaderboardActivity.class))
        );

        // Sign-out button logic
        findViewById(R.id.btnSignOut).setOnClickListener(v -> {
            // 1) Turn off auto sign-in
            Prefs.get(this).edit()
                    .putBoolean("key_keep_signed_in", false)
                    .apply();

            // 2) Send the user to Login and clear the entire back stack
            Intent i = new Intent(this, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);

            // 3) Finish this activity
            finish();
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(K_DISPLAY, etDisplay.getText().toString());
        outState.putString(K_BIO, etBio.getText().toString());
        outState.putBoolean(K_PRIV, cbPrivate.isChecked());
    }
}

