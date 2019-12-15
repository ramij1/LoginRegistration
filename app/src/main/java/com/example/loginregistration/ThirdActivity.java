package com.example.loginregistration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class ThirdActivity extends AppCompatActivity {

    private TextView textView;
    private Button logout;
    FirebaseAuth firebaseAuth;
    /* Toolbar toolbar;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        textView = findViewById(R.id.textView);
        logout = findViewById(R.id.btnLogout);
        /*toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu);*/


        firebaseAuth = FirebaseAuth.getInstance();

        logout.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                // Logout();

                startActivity(new Intent(ThirdActivity.this, BookListActivity.class));
            }
        });

    }

    private void Logout() {

        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(ThirdActivity.this, MainActivity.class));
    }

    private void About() {

        startActivity(new Intent(ThirdActivity.this, AboutActivity.class));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.aboutMenu:
                About();
                return true;
            case R.id.profileMenu:
                startActivity(new Intent(ThirdActivity.this, ProfileActivity.class));
                return true;
            case R.id.logoutMenu:
                Logout();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }


    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.logoutMenu:{
                Logout();
                break;
            }
            case R.id.aboutMenu:{
                About();
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }*/
}
