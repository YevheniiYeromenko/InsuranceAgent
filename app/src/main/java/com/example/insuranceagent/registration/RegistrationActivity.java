package com.example.insuranceagent.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.insuranceagent.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    private EditText etRegEmail;
    private EditText etRegPassword;
    private EditText etRepeatRegPassword;
    private Button bRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etRegEmail = findViewById(R.id.etRegEmail);
        etRegPassword = findViewById(R.id.etRegPassword);
        etRepeatRegPassword = findViewById(R.id.etRepeatRegPassword);
        bRegistration = findViewById(R.id.bRegistration);

        auth = FirebaseAuth.getInstance();

        bRegistration.setOnClickListener(v -> {
            String email = etRegEmail.getText().toString();
            String password = etRegPassword.getText().toString();
            String repeatPassword = etRepeatRegPassword.getText().toString();

            if (!password.equals(repeatPassword))
                Log.wtf("TAG", "Repeat correct password");
            else //if (Patterns.EMAIL_ADDRESS.matcher(email).matches())
                registration(email, password);
            //else Log.wtf("TAG", "Email not matches");
        });

    }

    private void registration(String email, String password) {

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // Sign in success, update UI with the signed-in user's information
                Log.wtf("TAG", "createUserWithEmail:success");
                FirebaseUser user = auth.getCurrentUser();
            } else {
                // If sign in fails, display a message to the user.
                Exception e = task.getException();
                if (e.getClass().getSimpleName().equals("FirebaseAuthInvalidCredentialsException")){
                    Log.wtf("Fail", "Email");
                }
                if (e.getClass().getSimpleName().equals("FirebaseAuthWeakPasswordException")){
                    Log.wtf("Fail", "Password");
                }
                if (e.getClass().getSimpleName().equals("FirebaseNetworkException")){
                    Log.wtf("Fail", "Internet");
                }

                Toast.makeText(RegistrationActivity.this, "Authentication failed.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}