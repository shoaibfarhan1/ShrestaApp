package com.app.shresta.shrestaapp.activity.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.app.shresta.shrestaapp.R;

/**
 * Created by shoaib.farhan on 07-12-2017.
 */

public class TermsAndConditions extends AppCompatActivity implements View.OnClickListener{

    TextView company_name,termsandconditions_text,tandc_data,disclaimer_content,disclaimer;
    Button proceed_btn;
    LinearLayout disclaimer_layout;
    RadioButton accept_radiobtn;
    RadioGroup acceptRadioGroup;


    // Font path
    String Ubuntubold = "font/Ubuntubold.ttf";
    String UbuntuR = "font/UbuntuR.ttf";
    String UbuntuC = "font/UbuntuC.ttf";
    String Verdana = "font/verdana.ttf";
    String VerdanaB = "font/verdanab.ttf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //code that displays the content in full screen mode
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.terms_and_conditions);

        // Loading Font Face
        Typeface Ubuntubold1 = Typeface.createFromAsset(getAssets(), Ubuntubold);
        Typeface UbuntuC1 = Typeface.createFromAsset(getAssets(), UbuntuC);
        Typeface UbuntuR1 = Typeface.createFromAsset(getAssets(), UbuntuR);
        Typeface Verdana1 = Typeface.createFromAsset(getAssets(), Verdana);
        Typeface VerdanaB1 = Typeface.createFromAsset(getAssets(), VerdanaB);

        company_name=(TextView)findViewById(R.id.company_name);
        tandc_data=(TextView)findViewById(R.id.tandc_data);
        disclaimer_content=(TextView)findViewById(R.id.disclaimer_content);
        disclaimer=(TextView)findViewById(R.id.disclaimer);
        termsandconditions_text=(TextView)findViewById(R.id.termsandconditions_text);
        disclaimer_layout=(LinearLayout)findViewById(R.id.termsandconditions_layout);
        acceptRadioGroup=(RadioGroup)findViewById(R.id.accepr_radiogroup);
        proceed_btn=(Button)findViewById(R.id.proceed_btn);

        company_name.setTypeface(Ubuntubold1);
        tandc_data.setTypeface(Verdana1);
        disclaimer_content.setTypeface(Verdana1);
        disclaimer.setTypeface(VerdanaB1);
        termsandconditions_text.setTypeface(VerdanaB1);
     //   accept_radiobtn.setTypeface(Verdana1);
        proceed_btn.setTypeface(Verdana1);

        SpannableString content = new SpannableString("Terms and Conditions");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 2);
        termsandconditions_text.setText(content);

        acceptRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                accept_radiobtn=(RadioButton)findViewById(R.id.accept_radiobutton);
                if (null != accept_radiobtn && checkedId > -1) {
                    accept_radiobtn.setTextColor(Color.parseColor("#558B2F"));
                    //disclaimer_layout.setVisibility(View.VISIBLE);
                }

            }
        });

        proceed_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
        case R.id.proceed_btn:
            accept_radiobtn = (RadioButton) acceptRadioGroup.findViewById(acceptRadioGroup.getCheckedRadioButtonId());
            //String rg=accept_radiobtn.getText().toString();
            try {
                if (accept_radiobtn.getText().equals("")) {

                } else {

                    Intent intent = new Intent(TermsAndConditions.this, FourDigitActivity.class);
                    startActivity(intent);
                }
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(this, "Please accept the Terms and Conditions", Toast.LENGTH_SHORT).show();
            }
            break;
    }
    }
}
