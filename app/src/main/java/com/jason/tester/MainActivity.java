package com.jason.tester;

import android.content.DialogInterface;
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

public class MainActivity extends AppCompatActivity {

    private EditText edAccount;
    private EditText edEmail;
    private EditText edPassword;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edAccount = findViewById(R.id.account);
        edEmail = findViewById(R.id.email);
        edPassword = findViewById(R.id.password);
        pref = getSharedPreferences("Testing", MODE_PRIVATE);

        Button okButton = findViewById(R.id.ok);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
                reset();
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void login() {
        String user = edAccount.getText().toString();
        String mail = edEmail.getText().toString();
        String pass = edPassword.getText().toString();
        if (user.equals("") || mail.equals("") || pass.equals("")) {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Error")
                    .setMessage("Please enter your data completely, Thanks!")
                    .setPositiveButton("Ok", null)
                    .show();
        } else {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, ResultActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putString("Account", user);
//        bundle.putString("E-mail", mail);
//        bundle.putString("Password", pass);
//        intent.putExtras(bundle);
            pref.edit()
                    .putString("Account", user)
                    .putString("E-mail", mail)
                    .putString("Password", pass)
                    .commit();
            startActivity(intent);
        }
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
