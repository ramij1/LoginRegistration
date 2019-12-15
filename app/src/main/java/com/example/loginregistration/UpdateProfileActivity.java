package com.example.loginregistration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateProfileActivity extends AppCompatActivity {


    private EditText newUserName, newUserPhone, newuserAge;
    private TextView newUserEmail, newuserPassword;
    private Button save;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        newUserName = findViewById(R.id.etNameUpdate);
        newUserPhone = findViewById(R.id.etPhoneUpdate);
        newuserAge = findViewById(R.id.etAgeUpdate);
        newUserEmail = findViewById(R.id.tvEmailUpdate);
        newuserPassword = findViewById(R.id.tvPasswardUpdate);

        save = findViewById(R.id.btnSave);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        final DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                newUserName.setText(userProfile.getUserName());
                newUserEmail.setText(userProfile.getUserEmail());
                newUserPhone.setText(userProfile.getUserPhone());
                newuserAge.setText(userProfile.getUserAge());
                newuserPassword.setText(userProfile.getUserPassword());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(UpdateProfileActivity.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = newUserName.getText().toString().trim();
                String phone = newUserPhone.getText().toString().trim();
                String age = newuserAge.getText().toString().trim();
                String email = newUserEmail.getText().toString();
                String password = newuserPassword.getText().toString();


                UserProfile userProfile = new UserProfile(age, email, name, phone, password);

                databaseReference.setValue(userProfile);
                Toast.makeText(UpdateProfileActivity.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(UpdateProfileActivity.this, ProfileActivity.class));


            }
        });

    }
}
