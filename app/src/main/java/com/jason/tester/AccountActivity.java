package com.jason.tester;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;

public class AccountActivity extends AppCompatActivity {

    private SharedPreferences pref;
    private EditText edAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edAccount = findViewById(R.id.account);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accountNext();
            }
        });
    }
    public void accountNext() {
        pref = getSharedPreferences("LOGIN", MODE_PRIVATE);
        pref.edit()
                .putString("ACCOUNT", edAccount.getText().toString())
                .commit();

        if (!edAccount.getText().toString().equals("")) {
            Intent intent = new Intent();
            intent.setClass(AccountActivity.this, EmailActivity.class);
            startActivity(intent);
            finish();
        } else {
            new AlertDialog.Builder(AccountActivity.this)
                    .setTitle("錯誤")
                    .setMessage("請輸入資料, 謝謝!")
                    .setPositiveButton("OK", null)
                    .show();
        }
    }
}
