package com.mukesh.loginandsignupui.view.signin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mukesh.loginandsignupui.R;
import com.mukesh.loginandsignupui.utils.SystemUtils;
import com.mukesh.loginandsignupui.utils.Utils;
import com.mukesh.loginandsignupui.view.signup.SignUpActivity;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView appLogo;
    private TextView tvWelcomeThere;
    private TextView tvSignInToContinue;
    private TextInputLayout username, password;
    private Button goButton, signUpButton;
    private TextInputEditText editUserName,editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        initView();

    }

    private void initView() {
        appLogo = findViewById(R.id.logo_image);
        tvWelcomeThere = findViewById(R.id.welcome_message);
        tvSignInToContinue = findViewById(R.id.sign_in_message);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        goButton = findViewById(R.id.goButton);
        signUpButton = findViewById(R.id.signUpButton);

        editPassword=findViewById(R.id.editPassword);
        editUserName=findViewById(R.id.editUsername);
        goButton.setOnClickListener(this);

        signUpButton.setOnClickListener(this);

    }

    private void openSignUpPage() {

        //Attach all the elements those you want to animate in design
        Pair[] pairs = new Pair[7];
        pairs[0] = new Pair<View, String>(appLogo, "app_icon");
        pairs[1] = new Pair<View, String>(tvWelcomeThere, getString(R.string.welcome));
        pairs[2] = new Pair<View, String>(tvSignInToContinue, getString(R.string.signup_to_start_your_new_journey));
        pairs[3] = new Pair<View, String>(username, getString(R.string.username));

        pairs[4] = new Pair<View, String>(password, getString(R.string.password));
        pairs[5] = new Pair<View, String>(goButton, getString(R.string.sign_up));

        pairs[6] = new Pair<View, String>(signUpButton, getString(R.string.already_have_an_account_login));

        Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                SignInActivity.this, pairs);


        startActivity(intent, options.toBundle());
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.goButton:
                loginValidation();
                break;
            case R.id.signUpButton:
                openSignUpPage();
                break;
            default:
                break;
        }
    }
    private void loginValidation() {
        if (Utils.inputValidation(editUserName)) {
            username.setErrorEnabled(false);

            if (Utils.inputValidation(editPassword)) {
                SystemUtils.hideKeyBoard(this);
                password.setErrorEnabled(false);
                Toast.makeText(this, "Hit your login API", Toast.LENGTH_SHORT).show();
            } else {

                password.setError("Please enter your password");
            }
        } else {

            username.setError("Please enter your username");
        }
    }

}
