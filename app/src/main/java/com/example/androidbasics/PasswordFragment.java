package com.example.androidbasics;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

public class PasswordFragment extends Fragment {

    private static final String ARG_USERNAME = "username";

    private String username;

    public PasswordFragment() {
        // Required empty public constructor
    }

    public static PasswordFragment newInstance(String username) {
        PasswordFragment fragment = new PasswordFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USERNAME, username);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            username = getArguments().getString(ARG_USERNAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_password, container, false);
        TextView welcomeTextView = view.findViewById(R.id.tv_greetings);

        EditText passwordEditText = view.findViewById(R.id.password);
        Button submitPasswordButton = view.findViewById(R.id.submitButton);

        welcomeTextView.setText("Hi " + username + "!");

        submitPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = passwordEditText.getText().toString();
                if (password.length() >= 8) {
                    // Password is valid, perform desired action
                } else {
                    // Display an error message
                    // For example, you can use a TextView to display the error message
                    // ErrorTextView.setText("Invalid Password: Must be at least 8 characters");
                }
            }
        });

        return view;
    }
}