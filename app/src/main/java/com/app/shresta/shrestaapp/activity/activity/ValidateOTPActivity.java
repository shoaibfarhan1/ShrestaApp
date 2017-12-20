package com.app.shresta.shrestaapp.activity.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.app.shresta.shrestaapp.R;

/**
 * Created by shoaib.farhan on 12-12-2017.
 */

public class ValidateOTPActivity extends AppCompatActivity {

    Button ValidateOTP;
    EditText DigitOne, DigitTwo, DigitThree, DigitFour, DigitFive, DigitSix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activation_activity);

        DigitOne=(EditText)findViewById(R.id.first_digit);
        DigitTwo=(EditText)findViewById(R.id.second_digit);
        DigitThree=(EditText)findViewById(R.id.third_digit);
        DigitFour=(EditText)findViewById(R.id.fourth_digit);
        DigitFive=(EditText)findViewById(R.id.fifth_digit);
        DigitOne=(EditText)findViewById(R.id.sixth_digit);
        ValidateOTP=(Button)findViewById(R.id.validad_otp_btn);








    }
}
