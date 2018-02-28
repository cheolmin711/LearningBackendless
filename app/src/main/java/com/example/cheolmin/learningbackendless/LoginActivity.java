package com.example.cheolmin.learningbackendless;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.UserService;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private String userName, password;
    private EditText userNameInput,passwordInput;
    private Button createAccount, loginButton;
    private ProgressBar loadingBar;
    private Boshulleng bo;

    public static final int REG_REQUEST = 3434;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName= this.userName;
        password=this.password;
        wireWidgets();

        Backendless.initApp( this,BackendSettings.APP_ID, BackendSettings.API_KEY );

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegistrationActivity.class);
                //startActivity(i);
                startActivityForResult(i, REG_REQUEST);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Backendless.UserService.login(userNameInput.getText().toString(), passwordInput.getText().toString(),
                        new AsyncCallback<BackendlessUser>()
                        {
                            public void handleResponse(BackendlessUser response)
                            {
                                String user = (String)response.getProperty("username");
                                //response.clearProperties();
                                //Toast.makeText(LoginActivity.this, "Boshulleng Guide", Toast.LENGTH_SHORT).show();
                                loadingBar.setVisibility(View.GONE);
                                //TODO: put intent
                                testDataRetrieval();
                                testDataManipulation();
                            }

                            public void handleFault(BackendlessFault fault)
                            {
                                //failure
                                Toast.makeText(LoginActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
                                loadingBar.setVisibility(View.GONE);
                            }
                        });
            }
        });


    }

    private void testDataManipulation() {
        //create
//            //1. make a restaurant object with the info you want
        Boshulleng bo = new Boshulleng("Food Barn", "Generic Food", "The Barn down the street", null, null, 5, 1);
        Backendless.Persistence.of(Boshulleng.class).findFirst(new AsyncCallback<Boshulleng>() {
            @Override
            public void handleResponse(Boshulleng response) {
                Backendless.Persistence.of(Boshulleng.class).remove(response, new AsyncCallback<Long>() {
                    @Override
                    public void handleResponse(Long response) {
                        Log.d("REMOVAL", "handleResponse: " + response);
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(LoginActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
//        Backendless.Persistence.save(bo, new AsyncCallback<Boshulleng>() {
//            @Override
//            public void handleResponse(Boshulleng response) {
//                Log.d("CREATED NEW:", "handleResponse: " + response);
//            }
//
//            @Override
//            public void handleFault(BackendlessFault fault) {
//                Toast.makeText(LoginActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
        //update
//        bo.setObjectId("AE3713EE-C230-F134-FFF7-2B1443B9BD00");
//        bo.setName("SOMETHING DIFFERENT");
//        bo.setOwnerID(Backendless.UserService.CurrentUser().getUserId());
//        Backendless.Persistence.save(bo, new AsyncCallback<Boshulleng>() {
//            @Override
//            public void handleResponse(Boshulleng response) {
//                Log.d("CREATED NEW:", "handleResponse: " + response);
//
//            }
//
//            @Override
//            public void handleFault(BackendlessFault fault) {
//                Toast.makeText(LoginActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
        //delete

    }

    private void testDataRetrieval(){
        String whereClause = "name= 'Gus''s BBQ'";

        String id = Backendless.UserService.CurrentUser().getUserId();
        //String whereClause = "ownerId = '" +id + "'";//ownerId of Boshulleng is objectId of User

        DataQueryBuilder dataQuery = DataQueryBuilder.create();
        dataQuery.setWhereClause(whereClause);
        Backendless.Data.of(Boshulleng.class).find(dataQuery, new AsyncCallback<List<Boshulleng>>() {
            @Override
            public void handleResponse(List<Boshulleng> response) {
                Log.d("LOOK HERE:", "handleResponse: " + response);
                Toast.makeText(LoginActivity.this, ""+response, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(LoginActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
//        Backendless.Persistence.of(Boshulleng.class).find(new AsyncCallback<List<Boshulleng>>() {
//            @Override
//            public void handleResponse(List<Boshulleng> response) {
//                Log.d("LOOK HERE:", "handleResponse: " + response);
//            }
//
//            @Override
//            public void handleFault(BackendlessFault fault) {
//                Toast.makeText(LoginActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });

    }

    public void wireWidgets() {
        userNameInput = (EditText)findViewById(R.id.usernameinput);
        createAccount = (Button)findViewById(R.id.createaccount);
        passwordInput = (EditText)findViewById(R.id.passwordinput);
        loginButton= (Button)findViewById(R.id.loginButton);
        loadingBar = (ProgressBar)findViewById(R.id.progressBar);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == REG_REQUEST){
            userNameInput.setText(data.getStringExtra((RegistrationActivity.EXTRA_USERNAME)));
        }
    }
}