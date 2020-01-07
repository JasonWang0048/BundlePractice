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

public class EmailActivity extends AppCompatActivity {

    private EditText edEmail;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edEmail = findViewById(R.id.email);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailNext();
            }
        });
    }
    public void emailNext() {
        pref = getSharedPreferences("LOGIN", MODE_PRIVATE);
        pref.edit()
                .putString("EMAIL", edEmail.getText().toString())
                .commit();

        Intent intent = new Intent();
        intent.setClass(EmailActivity.this, PasswordActivity.class);
        startActivity(intent);
        finish();
    }
}
