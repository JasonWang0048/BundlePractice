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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView edAccount;
    private TextView edEmail;
    private TextView edPassword;
    private String title;
    private String message;
    private SharedPreferences pref;
    private String account;
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edAccount = findViewById(R.id.ip1);
        edEmail = findViewById(R.id.ip2);
        edPassword = findViewById(R.id.ip3);
        pref = getSharedPreferences("LOGIN", MODE_PRIVATE);

        Button loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        Button registerButton = findViewById(R.id.register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transfer();
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });
        signUp();
    }

    public void signUp() {
        account = pref.getString("ACCOUNT", "Data not found");
        email = pref.getString("EMAIL", null);
        password = pref.getString("PASSWORD", "Data not found");
        if (!account.equals("Data not found") && !password.equals("Data not found")) {
            edAccount.setText(account);
            edEmail.setText(email);
            edPassword.setText(password);
        }
    }

    public void signIn() {
//        String account = edAccount.getText().toString();
//        String password = edPassword.getText().toString();
        if (edAccount.getText().toString().equals(account) && edPassword.getText().toString().equals(password)) {
            title = "登入成功";
            message = "歡迎, " + account + "!";
        } else {
            title = "登入失敗";
            message = "帳號或密碼輸入錯誤!";
        } new AlertDialog.Builder(MainActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    public void transfer() {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, AccountActivity.class);
        startActivity(intent);
    }

    public void reset() {
        edAccount.setText("");
        edEmail.setText("");
        edPassword.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
