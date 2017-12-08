package com.app.shresta.shrestaapp.activity.activity;

import android.content.Intent;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.shresta.shrestaapp.activity.handler.DatabaseHandler;
import com.app.shresta.shrestaapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shoaib.farhan on 11/2/2017.
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText fullName, mobileNumber, passWord, email, userName;
    TextView registration_text;
    Spinner titleSpinner;
    String title, fullNameStr, mobileNumberStr, passWordStr, emailStr, userNameStr;
    Button registerBtn;
    DatabaseHandler dbHandler;
    public static List<String> docList=new ArrayList<String>();
    // Font path
    String Ubuntubold = "font/Ubuntubold.ttf";
    String UbuntuR = "font/UbuntuR.ttf";
    String UbuntuC = "font/UbuntuC.ttf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        // Loading Font Face
        Typeface Ubuntubold1 = Typeface.createFromAsset(getAssets(), Ubuntubold);
        Typeface UbuntuC1 = Typeface.createFromAsset(getAssets(), UbuntuC);
        Typeface UbuntuR1 = Typeface.createFromAsset(getAssets(), UbuntuR);
        registration_text=(TextView)findViewById(R.id.registration_text);
        fullName = (EditText) findViewById(R.id.fullname_edit_txt);
        mobileNumber = (EditText) findViewById(R.id.mobilenumber_edit_txt);
        email = (EditText) findViewById(R.id.email_edit_txt);
        userName=(EditText)findViewById(R.id.username_edit_txt);
        passWord = (EditText) findViewById(R.id.password_edit_txt);

        registerBtn = (Button) findViewById(R.id.registration_btn);

        // Applying font
        registration_text.setTypeface(Ubuntubold1);
        fullName.setTypeface(UbuntuR1);
        mobileNumber.setTypeface(UbuntuR1);
        email.setTypeface(UbuntuR1);
        userName.setTypeface(UbuntuR1);
        passWord.setTypeface(UbuntuR1);
        registerBtn.setTypeface(Ubuntubold1);

        registerBtn.setOnClickListener(this);
        dbHandler=new DatabaseHandler(RegisterActivity.this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.registration_btn:
                getText();
                break;
        }
    }

    public void getText() {
        fullNameStr = fullName.getText().toString().trim();
        mobileNumberStr = mobileNumber.getText().toString().trim();
        emailStr = email.getText().toString().trim();
        passWordStr = passWord.getText().toString().trim();
        userNameStr = userName.getText().toString().trim();

        if(fullNameStr.equals("")){
            Toast.makeText(RegisterActivity.this, "Full Name Required", Toast.LENGTH_SHORT).show();
        }else if(mobileNumberStr.equals("")){
            Toast.makeText(RegisterActivity.this, "Mobile Number Required", Toast.LENGTH_SHORT).show();
        } else if(emailStr.equals("")){
            Toast.makeText(RegisterActivity.this, "Email Required", Toast.LENGTH_SHORT).show();
        } else if(userNameStr.equals("")){
            Toast.makeText(RegisterActivity.this, "UserName Required", Toast.LENGTH_SHORT).show();
        } else if(passWordStr.equals("")){
            Toast.makeText(RegisterActivity.this, "Password Required", Toast.LENGTH_SHORT).show();
        }/*else if (passWordStr.length()<6) {
            Toast.makeText(RegisterActivity.this, "Passwor must be greater then 6 letters", Toast.LENGTH_SHORT).show();
        }*/
            else{
            /*docList=dbHandler.doc+Login(mobileNumberStr,passWordStr);
            if(docList.size()>0){
                Toast.makeText(RegisterActivity.this, " User already exists", Toast.LENGTH_SHORT).show();
            }
            else {*/
                dbHandler.docReg(fullNameStr, mobileNumberStr, emailStr, userNameStr, passWordStr);
                Intent in=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(in);
                Toast.makeText(RegisterActivity.this, "Successfully registered", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
