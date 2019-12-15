package com.example.loginregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class SecondActivity extends AppCompatActivity {

    private TextView textView;
    private Button logout;
    FirebaseAuth firebaseAuth;

    private LinearLayout Layout1, Layout2;
    private Button letsGo;
    private Animation animUptoDown, animDownToup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // textView = findViewById(R.id.textView);
        letsGo = findViewById(R.id.btnLetsgo);
        Layout1 = findViewById(R.id.layout1);
        Layout2 = findViewById(R.id.layout2);

        animUptoDown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        Layout1.setAnimation(animUptoDown);
        animDownToup = AnimationUtils.loadAnimation(this, R.anim.downtoup);
        Layout2.setAnimation(animDownToup);


        firebaseAuth = FirebaseAuth.getInstance();

        letsGo.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                Logout();
            }
        });

    }





    private void Logout(){

        //firebaseAuth.signOut();
        finish();
        startActivity(new Intent(SecondActivity.this, ThirdActivity.class));
    }

   /* private void About(){

        startActivity(new Intent(SecondActivity.this, AboutActivity.class));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

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