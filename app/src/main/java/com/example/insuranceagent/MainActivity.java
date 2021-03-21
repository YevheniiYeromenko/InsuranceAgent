package com.example.insuranceagent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.insuranceagent.business.BusinessActivity;
import com.example.insuranceagent.registration.RegistrationActivity;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    // TODO: 21.03.2021 "Создать одну рабочую активити з авторизацией FirebaseUI" 
    private static final int RC_SIGN_IN = 5791;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;

    private EditText etLoginEmail;
    private EditText etLoginPassword;
    private Button bLogin;
    private TextView tvToRegistration;


    @Override
    protected void onStart() {
        super.onStart();
        Log.wtf("TAG", "onStart");
        //auth.addAuthStateListener(authStateListener);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Intent intent = new Intent(getApplicationContext(), BusinessActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.wtf("TAG", "onStop");
        //auth.removeAuthStateListener(authStateListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.wtf("TAG", "onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.wtf("TAG", "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.wtf("TAG", "onPause");
    }

    private void goToNewActivity() {
        Intent intent = new Intent(getApplicationContext(), BusinessActivity.class);
        // установка флагов
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.wtf("TAG", "onCreate");
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());

        // Create and launch sign-in intent
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setIsSmartLockEnabled(false)
                        .setLogo(R.drawable.metlife_logo_1)
                        .setTheme(R.style.MyUI)
                        .build(),
                RC_SIGN_IN);
        //finish();



/*        etLoginEmail = findViewById(R.id.etLoginEmail);
        etLoginPassword = findViewById(R.id.etLoginPassword);
        bLogin = findViewById(R.id.bLogin);
        tvToRegistration = findViewById(R.id.tvToRegistration);*/


//        auth = FirebaseAuth.getInstance();
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//
//        bLogin.setOnClickListener(v -> {
//            String email = etLoginEmail.getText().toString();
//            String password = etLoginPassword.getText().toString();
//            login(email, password);
//
//        });
//
//        tvToRegistration.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
//            }
//        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Log.wtf("TAG", user.getEmail() + user.getDisplayName());
                Log.wtf("TAG", user.getEmail() + user.getPhotoUrl());
//                Intent intent = new Intent(getApplicationContext(), BusinessActivity.class);
//                startActivity(intent);
                //finish();
                // ...
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }

    private void login(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.wtf("TAG", "signInWithEmail:success");
                            FirebaseUser user = auth.getCurrentUser();
                            Log.wtf("TAG", user.getDisplayName() + "   " + user.getEmail());
                            goToNewActivity();
                        } else {
                            // If sign in fails, display a message to the user.
                            Exception e = task.getException();
                            if (e.getClass().getSimpleName().equals("FirebaseAuthInvalidCredentialsException")) {
                                Log.wtf("Fail", "Email");
                            }
                            if (e.getClass().getSimpleName().equals("FirebaseAuthWeakPasswordException")) {
                                Log.wtf("Fail", "Password");
                            }
                            if (e.getClass().getSimpleName().equals("FirebaseNetworkException")) {
                                Log.wtf("Fail", "Internet");
                            }
                            Log.wtf("TAG", "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}