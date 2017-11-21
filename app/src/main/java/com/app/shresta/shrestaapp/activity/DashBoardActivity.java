package com.app.shresta.shrestaapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.app.shresta.shrestaapp.R;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by shoaib.farhan on 09-11-2017.
 */

public class DashBoardActivity extends AppCompatActivity {

    TextView Signout;
    private static final String TAG = "PhoneAuthActivity";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);
        Signout=(TextView)findViewById(R.id.signout);

        Signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(DashBoardActivity.this, LoginActivity.class));
                finish();
            }
        });

        mAuth = FirebaseAuth.getInstance();
        if(mAuth!=null){
           // fireBaseId.setText(mAuth.getCurrentUser().getPhoneNumber());
        }
    }
}
