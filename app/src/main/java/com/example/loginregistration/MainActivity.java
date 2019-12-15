package com.example.loginregistration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView Info, tvTitle;
    private Button Login,etRegister;
    private  int counter = 5;
    private TextView  ForgotPassword;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpView();



        Info.setText("No of attempts remaning : 5");

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();


        if (user != null){

            startActivity(new Intent(MainActivity.this, ThirdActivity.class));
            finish();
        }

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (userLogin())
                    validate(Name.getText().toString().trim(), Password.getText().toString().trim());
            }
        });


        ForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(MainActivity.this, PasswordActivity.class));
            }
        });


        etRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));

            }
        });

    }

    /*private void validate(String userName, String userPassword){

        if (userName.equals("Admin") && userPassword.equals("123")){
            Intent intent = new Intent(MainActivity.this, SecondActivity.class );
            startActivity(intent);
        }else{
            counter--;

            Info.setText("No of attempts remaining : "+ String.valueOf(counter));

            if (counter == 0){
                Login.setEnabled(false);
            }
        }
    }
*/


    private void validate(String userName, String userPassword){

        progressDialog.setMessage("Verifing");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    progressDialog.dismiss();

                  /*                    Mail verification use na kore eita use korbo

                  Toast.makeText(MainActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                   startActivity(new Intent(MainActivity.this, SecondActivity.class));*/

                    EmailVerification();

                }else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    // Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    counter--;


                    Info.setText("No of attempts remaining : "+ String.valueOf(counter));

                    progressDialog.dismiss();

                    if (counter == 0){
                        Login.setEnabled(false);
                    }

                }
            }
        });
    }


    private void EmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();

        //startActivity(new Intent(MainActivity.this, SecondActivity.class));

        if (emailflag){
            finish();
            startActivity(new Intent(MainActivity.this, SecondActivity.class));
        }else {
            Toast.makeText(MainActivity.this, "Plese verify Email", Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }


    private void setUpView(){

        tvTitle = findViewById(R.id.tvTitle);
        Name = findViewById(R.id.etName);
        Password = findViewById(R.id.etPassword);
        Info = findViewById(R.id.info);
        Login = findViewById(R.id.btnLogin);
        etRegister = findViewById(R.id.tvRegister);
        ForgotPassword = findViewById(R.id.tvForgotPassword);
    }



    private Boolean userLogin() {

        setUpView();

        Boolean result = false;

        String email = Name.getText().toString().trim();
        String password = Password.getText().toString().trim();

        if (email.isEmpty()) {
            Name.setError("Email is required");
            Name.requestFocus();
            progressDialog.dismiss();
            return false;

        }

        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Name.setError("Please enter a valid email");
            Name.requestFocus();
            progressDialog.dismiss();
            return false;
        }

        else if (password.isEmpty()) {
            Password.setError("Password is required");
            Password.requestFocus();
            progressDialog.dismiss();
            return false;

        }

        else if (password.length() < 6) {
            Password.setError("password is too short");
            Password.requestFocus();
            progressDialog.dismiss();
            return false;

        }else {
            result = true;
        }
        return result;

    }
}








//=================================================================DONE==========================================================================================
//-----------------------------------------------------------------DONE------------------------------------------------------------------------------------------
//=================================================================DONE==========================================================================================
















    /*FirebaseAuth firebaseAuth;
    EditText editTextEmail, editTextPassword;
    private TextView Info;
    Button Login;
    ProgressBar progressBar;
    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        editTextEmail = (EditText) findViewById(R.id.etName);
        editTextPassword = (EditText) findViewById(R.id.etPassword);


        findViewById(R.id.tvRegister).setOnClickListener(this);
        findViewById(R.id.btnLogin).setOnClickListener(this);

    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Minimum lenght of password should be 6");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    finish();
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    counter--;


                    Info.setText("No of attempts remaining : "+ String.valueOf(counter));

                    progressBar.setVisibility(View.GONE);

                    if (counter == 0){
                        Login.setEnabled(false);
                    }
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (firebaseAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(this, SecondActivity.class));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvRegister:
                finish();
                startActivity(new Intent(this, RegistrationActivity.class));
                break;

            case R.id.btnLogin:
                userLogin();
                break;
        }
    }*/