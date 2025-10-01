package com.example.unimeme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String SP = "unimeme_prefs";
    private static final String KEY_LOGGED_IN = "logged_in";
    private static final String KEY_AUTO = "auto";
    private static final String KEY_EMAIL = "email";

    private EditText etEmail, etPassword;
    private CheckBox cbAuto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sp = getSharedPreferences(SP, MODE_PRIVATE);
        boolean auto = sp.getBoolean(KEY_AUTO, false);
        boolean logged = sp.getBoolean(KEY_LOGGED_IN, false);

        // 자동로그인: 체크 + 이전 로그인 성공일 때만
        if (auto && logged) {
            goForYou();
            return;
        }

        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        cbAuto = findViewById(R.id.cbAutoLogin);
        Button btnLogin = findViewById(R.id.btnLogin);

        etEmail.setText(sp.getString(KEY_EMAIL, ""));
        cbAuto.setChecked(auto);

        btnLogin.setOnClickListener(v -> {
            // 백엔드 없이 통과 (아무 값이나 OK)
            sp.edit()
                    .putBoolean(KEY_LOGGED_IN, true)
                    .putBoolean(KEY_AUTO, cbAuto.isChecked())
                    .putString(KEY_EMAIL, etEmail.getText().toString().trim())
                    .apply();
            goForYou();
        });
    }

    private void goForYou() {
        startActivity(new Intent(this, ForYouActivity.class));
        finish(); // 로그인 화면 제거
    }
}
