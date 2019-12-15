package com.example.loginregistration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    private EditText userName, userPassword, userEmail, userPhone, userAge;
    private Button regButton;
    private Button userLogin;
    private ImageView userImage;


    FirebaseDatabase database;
    DatabaseReference ref;
    //private StorageReference mStorageRef;


     String name;
    String password ;
    String email;
    String phone;
    String age ;

    private FirebaseAuth firebaseAuth;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        setUpView();

        progressBar=findViewById(R.id.progressBar);


        firebaseAuth = FirebaseAuth.getInstance();
       // mStorageRef = FirebaseStorage.getInstance().getReference();


        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    //register to database
                    String user_email = userEmail.getText().toString().trim();
                    String user_password = userPassword.getText().toString().trim();


                    progressBar.setVisibility(View.VISIBLE);

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            progressBar.setVisibility(View.GONE);

                            if (task.isSuccessful()) {


                                //sendUserData();
                               /* =============================Emaill Verification use na korle eita use korbo

                               Toast.makeText(RegistrationActivity.this, "Registration Succesfull", Toast.LENGTH_SHORT).show();
                               startActivity(new Intent(RegistrationActivity.this, MainActivity.class));*/

                                sendEmailVerification();



                            }else {

                                if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                    Toast.makeText(getApplicationContext(), "You are Already registered", Toast.LENGTH_SHORT).show();
                                } else {

                                    Toast.makeText(RegistrationActivity.this, "Registration Failled", Toast.LENGTH_SHORT).show();

                                }
                            }
                        }
                    });

                }
            }
        });



        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));

            }
        });

        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




    }


    private void sendEmailVerification(){

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser!= null){

            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {

                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if (task.isSuccessful()){

                        sendUserData();
                        Toast.makeText(RegistrationActivity.this, "Successfully Registered, Verification mail send", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                    }else{
                        Toast.makeText(RegistrationActivity.this, "Verification Mial hasen't sent", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }



    private void sendUserData(){


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
        UserProfile  userProfile = new UserProfile(age, email, name, phone, password);
        myRef.setValue(userProfile);
    }




    private void setUpView(){

        userName = findViewById(R.id.etUserName);
        userPassword = findViewById(R.id.etUserPassword);
        userEmail = findViewById(R.id.etUserEmail);
        userPhone = findViewById(R.id.etUserPhone);
        userAge = findViewById(R.id.etUserAge);
        regButton = findViewById(R.id.btnRegister);
        userImage = findViewById(R.id.ivUserPic);
        userLogin = findViewById(R.id.tvLogin);


    }

    private Boolean validate(){
        Boolean result = false;

        name = userName.getText().toString().trim();
        password = userPassword.getText().toString().trim();
        email = userEmail.getText().toString().trim();
        phone = userPhone.getText().toString().trim();
        age = userAge.getText().toString().trim();


        if (name.isEmpty() || password.isEmpty() || email.isEmpty() || phone.isEmpty() || age.isEmpty()){

            userName.setError("Name is Required");
            userName.requestFocus();

            userEmail.setError("Email is Required");
            userEmail.requestFocus();

            userPhone.setError("Phone is Required");
            userPhone.requestFocus();

            userAge.setError("Age is Required");
            userAge.requestFocus();

            userPassword.setError("Password is Required");
            userPassword.requestFocus();

            return false;

        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            userEmail.setError("Please Enter Valid Email");
            userEmail.requestFocus();
        }
        if (password.length()<6){
            userPassword.setError("Password length should be 6");
            userPassword.requestFocus();
        }

        else{
            result = true;
        }
        return result;
    }

}

