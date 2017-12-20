package com.app.shresta.shrestaapp.activity.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.app.shresta.shrestaapp.R;

/**
 * Created by shoaib.farhan on 12-12-2017.
 */

public class ActivatedSuccess extends AppCompatActivity implements View.OnClickListener {

    TextView Successfully, Activated,activation_header;
    Button Continue;

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

        setContentView(R.layout.activated_success);

        Successfully=(TextView)findViewById(R.id.successfully);
        Activated=(TextView)findViewById(R.id.activated);
        activation_header=(TextView)findViewById(R.id.activation_header);
        Continue=(Button)findViewById(R.id.Continue_btn);

        // Loading Font Face
        Typeface Ubuntubold1 = Typeface.createFromAsset(getAssets(), Ubuntubold);
        Typeface UbuntuC1 = Typeface.createFromAsset(getAssets(), UbuntuC);
        Typeface UbuntuR1 = Typeface.createFromAsset(getAssets(), UbuntuR);
        Typeface Verdana1 = Typeface.createFromAsset(getAssets(), Verdana);
        Typeface VerdanaB1 = Typeface.createFromAsset(getAssets(), VerdanaB);

        Successfully.setTypeface(Verdana1);
        Activated.setTypeface(Verdana1);
        activation_header.setTypeface(VerdanaB1);
        Continue.setTypeface(Verdana1);

        Continue.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Continue_btn:
                Intent intent=new Intent(ActivatedSuccess.this,DashBoardActivity.class);
                startActivity(intent);
                break;
        }
    }
}
