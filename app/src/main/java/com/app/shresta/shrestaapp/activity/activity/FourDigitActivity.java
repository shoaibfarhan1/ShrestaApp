package com.app.shresta.shrestaapp.activity.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.app.shresta.shrestaapp.R;

/**
 * Created by shoaib.farhan on 08-12-2017.
 */

public class FourDigitActivity extends AppCompatActivity {

    EditText digit_one,digit_two,digit_three,digit_four;
    TextView fourdigitPIN,dnthaveText,sign_up_TV,disclaimer,disclaimer_content;

    // Font path
    String Ubuntubold = "font/Ubuntubold.ttf";
    String UbuntuR = "font/UbuntuR.ttf";
    String UbuntuC = "font/UbuntuC.ttf";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.four_digit_pin);

        digit_one=(EditText)findViewById(R.id.digit_one);
        digit_two=(EditText)findViewById(R.id.digit_two);
        digit_three=(EditText)findViewById(R.id.digit_three);
        digit_four=(EditText)findViewById(R.id.digit_four);

        fourdigitPIN=(TextView)findViewById(R.id.fourdigitPIN);
        sign_up_TV=(TextView)findViewById(R.id.sign_up_TV);
        dnthaveText=(TextView)findViewById(R.id.dnthaveText);
        disclaimer=(TextView)findViewById(R.id.disclaimer);
        disclaimer_content=(TextView)findViewById(R.id.disclaimer_content);


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








    }
}
