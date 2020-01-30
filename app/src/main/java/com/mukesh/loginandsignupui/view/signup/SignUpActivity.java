package com.mukesh.loginandsignupui.view.signup;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mukesh.loginandsignupui.R;
import com.mukesh.loginandsignupui.utils.SystemUtils;
import com.mukesh.loginandsignupui.utils.Utils;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private TextInputEditText editUserName, editPassword, editFullName, editPhoneNumber, editEmail;
    private TextInputLayout username, password, fullName, phoneNumber, emailId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initView();
    }

    private void initView() {

        editFullName = findViewById(R.id.editFullname);
        editUserName = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
        editPhoneNumber = findViewById(R.id.editPhoneNumber);
        editEmail = findViewById(R.id.editEmailId);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        fullName = findViewById(R.id.fullName);
        phoneNumber = findViewById(R.id.phoneNumber);
        emailId = findViewById(R.id.email);

        findViewById(R.id.alreadyHaveAccount).setOnClickListener(this);
        findViewById(R.id.signUpButton).setOnClickListener(this);
    }

    private void inputValidation() {
        SystemUtils.hideKeyBoard(this);

        if (!Utils.inputValidation(editFullName)) {
            fullName.setError("Please enter your Fullname");
            return;
        } else {
            fullName.setErrorEnabled(false);
        }
        if (!Utils.inputValidation(editUserName)) {
            username.setError("Please enter your username");
            return;
        } else {
            username.setErrorEnabled(false);
        }
        if (!Utils.inputValidation(editEmail)) {
            emailId.setError("Please enter your email id");
            return;
        } else {
            emailId.setErrorEnabled(false);
        }
        if (!Utils.inputValidation(editPhoneNumber)) {
            phoneNumber.setError("Please enter your phone no");
            return;
        } else {
            phoneNumber.setErrorEnabled(false);
        }
        if (!Utils.inputValidation(editPassword)) {
            password.setError("Please enter your password");
            return;
        } else {
            password.setErrorEnabled(false);

        }

        Toast.makeText(this, "Continue for Sign up", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signUpButton:
                inputValidation();
                break;
            case R.id.alreadyHaveAccount:
                onBackPressed();
                break;
            default:
                break;
        }
    }
}
