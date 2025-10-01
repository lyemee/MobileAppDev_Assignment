package com.example.unimeme;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

public class AccountActivity extends AppCompatActivity {
    private EditText etDisplay, etBio;
    private CheckBox cbPrivate;

    private static final String K_DISPLAY = "k_display";
    private static final String K_BIO = "k_bio";
    private static final String K_PRIV = "k_priv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        etDisplay = findViewById(R.id.etDisplayName);
        etBio = findViewById(R.id.etBio);
        cbPrivate = findViewById(R.id.cbPrivate);

        // 복원: SharedPreferences -> savedInstanceState
        if (savedInstanceState != null) {
            etDisplay.setText(savedInstanceState.getString(K_DISPLAY, ""));
            etBio.setText(savedInstanceState.getString(K_BIO, ""));
            cbPrivate.setChecked(savedInstanceState.getBoolean(K_PRIV, false));
        } else {
            etDisplay.setText(Prefs.get(this).getString(K_DISPLAY, ""));
            etBio.setText(Prefs.get(this).getString(K_BIO, ""));
            cbPrivate.setChecked(Prefs.get(this).getBoolean(K_PRIV, false));
        }

        Button save = findViewById(R.id.btnSave);
        save.setOnClickListener(v -> {
            Prefs.get(this).edit()
                    .putString(K_DISPLAY, etDisplay.getText().toString())
                    .putString(K_BIO, etBio.getText().toString())
                    .putBoolean(K_PRIV, cbPrivate.isChecked())
                    .apply();
            Toast.makeText(this, getString(R.string.toast_saved), Toast.LENGTH_SHORT).show();
        });

        findViewById(R.id.btnBackToFeed).setOnClickListener(v ->
                startActivity(new Intent(this, ForYouActivity.class))
        );
        findViewById(R.id.btnToLeader).setOnClickListener(v ->
                startActivity(new Intent(this, LeaderboardActivity.class))
        );
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(K_DISPLAY, etDisplay.getText().toString());
        outState.putString(K_BIO, etBio.getText().toString());
        outState.putBoolean(K_PRIV, cbPrivate.isChecked());
    }
}

