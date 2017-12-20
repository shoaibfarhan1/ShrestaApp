package com.app.shresta.shrestaapp.activity.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.shresta.shrestaapp.R;
import com.app.shresta.shrestaapp.activity.handler.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shoaib.farhan on 08-12-2017.
 */

public class FourDigitActivity extends AppCompatActivity implements View.OnClickListener,View.OnFocusChangeListener {

    EditText digit_one,digit_two,digit_three,digit_four;
    TextView fourdigitPIN,dnthaveText,sign_up_TV,disclaimer,disclaimer_content;
    Button Signin;
    DatabaseHandler dbHandler;

    // Font path
    String Ubuntubold = "font/Ubuntubold.ttf";
    String UbuntuR = "font/UbuntuR.ttf";
    String UbuntuC = "font/UbuntuC.ttf";

    public static List<String> UserList=new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //code that displays the content in full screen mode
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.four_digit_pin);
        dbHandler=new DatabaseHandler(FourDigitActivity.this);

        digit_one=(EditText)findViewById(R.id.digit_one);
        digit_two=(EditText)findViewById(R.id.digit_two);
        digit_three=(EditText)findViewById(R.id.digit_three);
        digit_four=(EditText)findViewById(R.id.digit_four);
        Signin=(Button)findViewById(R.id.signin);

        fourdigitPIN=(TextView)findViewById(R.id.fourdigitPIN);
        sign_up_TV=(TextView)findViewById(R.id.sign_up_TV);
        dnthaveText=(TextView)findViewById(R.id.dnthaveText);
        disclaimer=(TextView)findViewById(R.id.disclaimer);
        disclaimer_content=(TextView)findViewById(R.id.disclaimer_content);

        SpannableString content = new SpannableString("SignUp");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        sign_up_TV.setText(content);

        // Loading Font Face
        Typeface Ubuntubold1 = Typeface.createFromAsset(getAssets(), Ubuntubold);
        Typeface UbuntuC1 = Typeface.createFromAsset(getAssets(), UbuntuC);
        Typeface UbuntuR1 = Typeface.createFromAsset(getAssets(), UbuntuR);

        digit_one.setTypeface(Ubuntubold1);
        digit_two.setTypeface(Ubuntubold1);
        disclaimer_content.setTypeface(UbuntuR1);
        disclaimer.setTypeface(Ubuntubold1);
        digit_three.setTypeface(Ubuntubold1);
        digit_four.setTypeface(Ubuntubold1);
        sign_up_TV.setTypeface(Ubuntubold1);
        fourdigitPIN.setTypeface(Ubuntubold1);
        dnthaveText.setTypeface(UbuntuR1);
        Signin.setTypeface(Ubuntubold1);

        digit_one.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (digit_one.getText().toString().trim().length() == 1)     //size as per your requirement
                {
                    digit_two.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        digit_two.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (digit_two.getText().toString().trim().length() == 1)     //size as per your requirement
                {
                    digit_three.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        digit_three.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (digit_three.getText().toString().trim().length() == 1)     //size as per your requirement
                {
                    digit_four.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

 /*       digit_one.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (digit_one.getText().toString().trim().length() == 1)     //size as per your requirement
                {
                    digit_two.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
*/
        sign_up_TV.setOnClickListener(this);
        Signin.setOnClickListener(this);
    }

    // Private class isNetworkAvailable
    private boolean isNetworkAvailable() {
        // Using ConnectivityManager to check for Network Connection
        ConnectivityManager connectivityManager = (ConnectivityManager) this
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sign_up_TV:
                Intent intent=new Intent(FourDigitActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;

            case R.id.signin:
                String digitone=digit_one.getText().toString();
                String digittwo=digit_two.getText().toString();
                String digitthree=digit_three.getText().toString();
                String digitfour=digit_four.getText().toString();
                if(digitone.equals("") || digittwo.equals("")|| digitthree.equals("")|| digitfour.equals("")){
                    Toast.makeText(this, "Please enter Password ", Toast.LENGTH_SHORT).show();
                }else {

                    String PWdigits = digitone + digittwo + digitthree + digitfour;


                    UserList = dbHandler.docLogin(PWdigits);
                    if (UserList.size() > 0) {
                        // Call isNetworkAvailable class
                        if (!isNetworkAvailable()) {
                            Toast.makeText(this, "Internet Connection is not available", Toast.LENGTH_SHORT).show();
                        } else {
                       /* LoginLayout.setVisibility(View.GONE);
                            OtpLayout.setVisibility(View.VISIBLE);
                            startPhoneNumberVerification(LoginActivity.mobilenumber_forotp);
            */
                            Intent intent1 = new Intent(FourDigitActivity.this, DashBoardActivity.class);
                            startActivity(intent1);
                        }
                    } else {
                        Toast.makeText(FourDigitActivity.this, "Password is invalid", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        v.requestFocus();
    }
}
