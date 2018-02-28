package com.example.cheolmin.learningbackendless;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

/**
 * Created by cheolminhwang11 on 2/12/18.
 */

public class RegistrationActivity extends AppCompatActivity{

    private EditText userNameText, passwordText,passwordConfirmText,emailText, firstNameText, lastNameText;
    private Button register;

    public static final String EXTRA_USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        wireWidgets();

        register();

        Backendless.initApp(this, BackendSettings.APP_ID, BackendSettings.API_KEY);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }

        });
    }


    private void register(){
        if(passwordText.getText().toString().equals(passwordConfirmText.getText().toString())){
            BackendlessUser user = new BackendlessUser();
            user.setProperty("username", userNameText.getText().toString());
            user.setPassword(passwordText.getText().toString());
            user.setProperty("firstname", firstNameText.getText().toString());
            user.setProperty("lastname", lastNameText.getText().toString());
            user.setProperty("email", emailText.getText().toString());

            Backendless.UserService.register( user, new AsyncCallback<BackendlessUser>()
            {
                public void handleResponse( BackendlessUser registeredUser )
                {
                    // user has been registered and now can login
                    Intent i = new Intent();
                    i.putExtra(EXTRA_USERNAME, userNameText.getText().toString());
                    setResult(RESULT_OK, i);
                    finish();

                }

                public void handleFault( BackendlessFault fault )
                {
                    // an error has occurred, the error code can be retrieved with fault.getCode()
                    Toast.makeText(RegistrationActivity.this, "Check all fields", Toast.LENGTH_SHORT).show();
                }
            } );
        }
    }

    private void wireWidgets() {
        firstNameText = (EditText)findViewById(R.id.firstname);
        lastNameText = (EditText)findViewById(R.id.lastname);
        userNameText = (EditText)findViewById(R.id.username);
        passwordText = (EditText)findViewById(R.id.password);
        passwordConfirmText = (EditText)findViewById(R.id.passwordconfirm);
        emailText = (EditText)findViewById(R.id.email);
        register = (Button)findViewById(R.id.registerButton);
    }
}
