package com.example.cheolmin.learningbackendless;

import android.widget.EditText;

/**
 * Created by cheolminhwang11 on 2/20/18.
 */

public class PersonalAc {
    private String userNameText, passwordText,passwordConfirmText,emailText, firstNameText, lastNameText;

    public PersonalAc(String userName, String password, String passwordConfirm, String email, String firstName, String lastName)
    {
        userNameText=userName;
        passwordText=password;
        passwordConfirmText=passwordConfirm;
        emailText=email;
        lastNameText=lastName;
    }

    public PersonalAc() {
    }

    public PersonalAc(EditText userNameText, EditText passwordText, EditText passwordConfirmText, EditText firstNameText, EditText lastNameText) {
    }

    public String getUserName(){
        return userNameText;
    }
    public String getpassword(){
        return passwordText;
    }
    public String getPasswordConfirm(){
        return passwordConfirmText;
    }
    public String getEmail(){
        return emailText;
    }
    public String getFirstName(){
        return firstNameText;
    }
    public String getLastName(){
        return lastNameText;
    }
    
}
