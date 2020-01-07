package com.jason.tester;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;

public class PasswordActivity extends AppCompatActivity {

    private EditText edPassword;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edPassword = findViewById(R.id.password);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passwordNext();
            }
        });
    }
    public void passwordNext() {
        pref = getSharedPreferences("LOGIN", MODE_PRIVATE);
        pref.edit()
                .putString("PASSWORD", edPassword.getText().toString())
                .commit();

        Intent intent = new Intent();
        intent.setClass(PasswordActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
