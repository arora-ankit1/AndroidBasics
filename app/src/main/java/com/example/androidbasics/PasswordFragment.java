package com.example.androidbasics;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class PasswordFragment extends Fragment {

    private static final String ARG_USERNAME = "username";
    public static final String TAG = "PasswordFragment";

    private String username;
    private passwordCallBackListener mListener;

    interface passwordCallBackListener{

        void onPasswordSuccess(String username);
    }

    public PasswordFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof LoginFragment.loginCallBackListener) {
            mListener = (PasswordFragment.passwordCallBackListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnLoginFragmentInteractionListener");
        }
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

        TextInputEditText passwordEditText = view.findViewById(R.id.passwordInputText);
        TextInputLayout passwordInputLayout = view.findViewById(R.id.passwordInputLayout);
        Button submitPasswordButton = view.findViewById(R.id.submitButton);

        welcomeTextView.setText("Hi " + username + "!");

        submitPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    String password = passwordEditText.getText().toString();
                    if (password.length() >= 8) {
                        passwordInputLayout.setError(null);
                        Context context = getActivity();
                        SharedPreferences sharedPref = context.getSharedPreferences(
                                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString(getString(R.string.username), username);
                        editor.putString(getString(R.string.password), password);
                        editor.apply();
                        mListener.onPasswordSuccess(username);

                        // Password is valid, perform desired action
                    } else {
                        passwordInputLayout.setError(getString(R.string.password_error));
                        // Display an error message
                        // For example, you can use a TextView to display the error message
                        // ErrorTextView.setText("Invalid Password: Must be at least 8 characters");
                    }
                } catch (Exception exception) {
                    Log.e(TAG, "onClick: ",exception );
                }
            }
        });

        return view;
    }
}