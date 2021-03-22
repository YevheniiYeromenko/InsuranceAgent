package com.example.insuranceagent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    // TODO: 21.03.2021 "Создать одну рабочую активити з авторизацией FirebaseUI" 
    // TODO: 21.03.2021 "Создать одну рабочую активити з авторизацией FirebaseUI"

    private BottomNavigationView nav_view;
    private Toolbar main_toolbar;

    private static final int RC_SIGN_IN = 5791;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private List<AuthUI.IdpConfig> providers;


    @Override
    protected void onStart() {
        super.onStart();
        Log.wtf("TAG", "onStart");
        //auth.addAuthStateListener(authStateListener);

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.wtf("TAG", "onStop");
        //auth.removeAuthStateListener(authStateListener);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.wtf("TAG", "onCreate");
        setContentView(R.layout.activity_main);

        nav_view = findViewById(R.id.nav_view);
        main_toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(main_toolbar);


        main_toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.logOut:
                        AuthUI.getInstance().signOut(getApplicationContext()).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                showSignInOptions();
                                Toast.makeText(getApplicationContext(), "User signed out", Toast.LENGTH_SHORT).show();
                                //finish();
                            }
                        });
                        //auth.signOut();

                        break;
                }
                return false;
            }
        });


        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.clientsFragment, R.id.infoFragment)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(nav_view, navController);


        // Choose authentication providers
        providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());

        auth = FirebaseAuth.getInstance();
        //Log.wtf("USER", auth.getCurrentUser().getEmail());
        if (auth.getCurrentUser() == null)
            showSignInOptions();
        else
            Log.wtf("USER", auth.getCurrentUser().getEmail());
        //finish();
        /*authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){

                }
                else {
                    showSignInOptions();
                }

            }
        };*/

        //showSignInOptions();


    }

    private void showSignInOptions() {
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

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (response.getError().getClass().getSimpleName().equals("UserCancellationException")){
                Log.wtf("ХУЙ", "ХУЙ");
            }

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Log.wtf("TAG", user.getEmail() + user.getDisplayName());
                Log.wtf("TAG", user.getEmail() + user.getPhotoUrl());
                //Intent intent = new Intent(this, MainActivity.class);
                //startActivity(intent);
                //onRestart();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }


}