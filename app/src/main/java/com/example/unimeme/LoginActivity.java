package com.example.unimeme;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername;
    private static final String KEY_USERNAME = "key_username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        Button btn = findViewById(R.id.btnLogin);

        // 복원: SharedPreferences 우선, 없으면 savedInstanceState
        String saved = Prefs.get(this).getString(KEY_USERNAME, null);
        if (saved != null) etUsername.setText(saved);
        else if (savedInstanceState != null)
            etUsername.setText(savedInstanceState.getString(KEY_USERNAME, ""));

        btn.setOnClickListener(v -> {
            String name = etUsername.getText().toString().trim();
            Prefs.get(this).edit().putString(KEY_USERNAME, name).apply(); // 상태 유지
            startActivity(new Intent(this, ForYouActivity.class));
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_USERNAME, etUsername.getText().toString());
    }
}
