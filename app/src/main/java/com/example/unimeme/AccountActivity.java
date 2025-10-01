package com.example.unimeme;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AccountActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        EditText etNick = findViewById(R.id.etNickname);
        Button btnSave = findViewById(R.id.btnSaveNick);

        btnSave.setOnClickListener(v -> {
            String newNick = etNick.getText().toString();
            Toast.makeText(this, "닉네임 저장됨: " + newNick, Toast.LENGTH_SHORT).show();
        });
    }
}
