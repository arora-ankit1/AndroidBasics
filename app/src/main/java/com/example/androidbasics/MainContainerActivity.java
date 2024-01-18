package com.example.androidbasics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

public class MainContainerActivity extends AppCompatActivity implements LoginFragment.loginCallBackListener, PasswordFragment.passwordCallBackListener {

    String TAG = "MainContainerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_container);
        Log.d(TAG, "onCreate: " + TAG);
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(getString(R.string.username),"");
        String password = sharedPreferences.getString(getString(R.string.password),"");
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            if (username.isEmpty() && password.isEmpty()) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, LoginFragment.newInstance())
                        .commit();
            } else if (!username.isEmpty() && password.isEmpty()) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, PasswordFragment.newInstance(username))
                        .commit();
            } else {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, HomeFragment.newInstance(username))
                        .commit();
            }
        }
    }

    @Override
    public void onLoginSuccess(String username) {
        // Replace LoginFragment with PasswordFragment and pass the username
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, PasswordFragment.newInstance(username))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onPasswordSuccess(String username) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, HomeFragment.newInstance(username))
                .addToBackStack(null)
                .commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: " + TAG);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: " + TAG);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: " + TAG);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: " + TAG);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: " + TAG);
    }
}