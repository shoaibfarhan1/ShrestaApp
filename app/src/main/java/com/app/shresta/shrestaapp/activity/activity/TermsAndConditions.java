package com.app.shresta.shrestaapp.activity.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.shresta.shrestaapp.R;

/**
 * Created by shoaib.farhan on 07-12-2017.
 */

public class TermsAndConditions extends AppCompatActivity implements View.OnClickListener{

    TextView company_name,termsandconditions_text,tandc_data,disclaimer_content,disclaimer;
    Button proceed_btn;

    // Font path
    String Ubuntubold = "font/Ubuntubold.ttf";
    String UbuntuR = "font/UbuntuR.ttf";
    String UbuntuC = "font/UbuntuC.ttf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terms_and_conditions);

        // Loading Font Face
        Typeface Ubuntubold1 = Typeface.createFromAsset(getAssets(), Ubuntubold);
        Typeface UbuntuC1 = Typeface.createFromAsset(getAssets(), UbuntuC);
        Typeface UbuntuR1 = Typeface.createFromAsset(getAssets(), UbuntuR);

        company_name=(TextView)findViewById(R.id.company_name);
        tandc_data=(TextView)findViewById(R.id.tandc_data);
        disclaimer_content=(TextView)findViewById(R.id.disclaimer_content);
        disclaimer=(TextView)findViewById(R.id.disclaimer);
        termsandconditions_text=(TextView)findViewById(R.id.termsandconditions_text);
        proceed_btn=(Button)findViewById(R.id.proceed_btn);

        company_name.setTypeface(Ubuntubold1);
        tandc_data.setTypeface(Ubuntubold1);
        disclaimer_content.setTypeface(UbuntuR1);
        disclaimer.setTypeface(Ubuntubold1);
        termsandconditions_text.setTypeface(Ubuntubold1);
        proceed_btn.setTypeface(Ubuntubold1);

        proceed_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
        case R.id.proceed_btn:

            Intent intent=new Intent(TermsAndConditions.this,FourDigitActivity.class);
            startActivity(intent);

            break;
    }
    }
}
