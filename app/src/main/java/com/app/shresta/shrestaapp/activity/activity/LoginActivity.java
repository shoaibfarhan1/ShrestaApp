package com.app.shresta.shrestaapp.activity.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.app.shresta.shrestaapp.activity.handler.DatabaseHandler;
import com.app.shresta.shrestaapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by shoaib.farhan on 09-11-2017.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText MobileNumber,Password;
    TextView Register;
    Button Login;
    String MobileNumberStr,PasswordStr;
    DatabaseHandler dbHandler;
    public static String mobilenumber_forotp;
    public static List<String> UserList=new ArrayList<String>();
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    String mVerificationId;
    LinearLayout LoginLayout,OtpLayout;

    EditText Otp;
    TextView resend,login_text;
    Button Submit;
    String OtpStr;
    ProgressDialog progressDialog;
    private static final String TAG = "PhoneAuthActivity";

    // Font path
    String Ubuntubold = "font/Ubuntubold.ttf";
    String UbuntuR = "font/UbuntuR.ttf";
    String UbuntuC = "font/UbuntuC.ttf";
    String Electrofied = "font/Electrofied.ttf";

    // Choose an arbitrary request code value
    private static final int RC_SIGN_IN = 123;
    private TextInputLayout inputLayoutMobileNumber,inputLayoutPassword;
    ImageView Logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //code that displays the content in full screen mode
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.login_activity);
        inputLayoutMobileNumber = (TextInputLayout) findViewById(R.id.input_layout_name);
        inputLayoutPassword=(TextInputLayout) findViewById(R.id.input_layout_name);
        login_text=(TextView)findViewById(R.id.login_text);
        LoginLayout=(LinearLayout)findViewById(R.id.login_layout);
        MobileNumber=(EditText)findViewById(R.id.mobilenumber_et);
        Password=(EditText)findViewById(R.id.password_et);
        Register=(TextView)findViewById(R.id.register);
        Login=(Button)findViewById(R.id.login_btn);

        OtpLayout=(LinearLayout)findViewById(R.id.otp_layout);
        Otp=(EditText)findViewById(R.id.otp_et);
        Submit=(Button)findViewById(R.id.otp_submit_btn);
        resend=(TextView)findViewById(R.id.resend_tv);

        // Loading Font Face
        Typeface Ubuntubold1 = Typeface.createFromAsset(getAssets(), Ubuntubold);
        Typeface UbuntuC1 = Typeface.createFromAsset(getAssets(), UbuntuC);
        Typeface UbuntuR1 = Typeface.createFromAsset(getAssets(), UbuntuR);

        // Applying font
        login_text.setTypeface(Ubuntubold1);
        inputLayoutMobileNumber.setTypeface(UbuntuR1);
        inputLayoutPassword.setTypeface(UbuntuR1);
        MobileNumber.setTypeface(UbuntuR1);
        Password.setTypeface(UbuntuR1);
        Register.setTypeface(UbuntuR1);
        Login.setTypeface(Ubuntubold1);
        Otp.setTypeface(UbuntuR1);
        resend.setTypeface(Ubuntubold1);
        Submit.setTypeface(Ubuntubold1);

        dbHandler=new DatabaseHandler(LoginActivity.this);
        Login.setOnClickListener(this);
        Register .setOnClickListener(this);
        Submit.setOnClickListener(this);
        resend.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                Log.d(TAG, "onVerificationCompleted:" + credential);
                signInWithPhoneAuthCredential(credential);
                Toast.makeText(LoginActivity.this, "code sent", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Log.w(TAG, "onVerificationFailed", e);
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    //MobileNumber.setError("Invalid phone number.");
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    Toast.makeText(LoginActivity.this, "Quota Exceed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                Log.d(TAG, "onCodeSent:" + verificationId);
                mVerificationId = verificationId;
                mResendToken = token;
                Toast.makeText(LoginActivity.this, "code sent", Toast.LENGTH_SHORT).show();
            }
        };
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        System.out.println("***************************dsfsdfsdfds*********");
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");
                           // Toast.makeText(LoginActivity.this, "signInWithCredential:success", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = task.getResult().getUser();
                            startActivity(new Intent(LoginActivity.this, DashBoardActivity.class));
                            finish();
                        } else {
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(LoginActivity.this, "Invalid OTP", Toast.LENGTH_SHORT).show();
                               // mVerificationField.setError("Invalid code.");
                            }
                        }
                    }
                });
    }

    private void startPhoneNumberVerification(String phoneNumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
         //   Toast.makeText(LoginActivity.this, "startPhoneNumberVerification", Toast.LENGTH_SHORT).show();
    }

    private void verifyPhoneNumberWithCode(String verificationId, String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        System.out.println("************************************");
      //  Toast.makeText(LoginActivity.this, "verifyPhoneNumberWithCode", Toast.LENGTH_SHORT).show();
        signInWithPhoneAuthCredential(credential);
    }

    private void resendVerificationCode(String phoneNumber,
                                        PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks,         // OnVerificationStateChangedCallbacks
                token);             // ForceResendingToken from callbacks
     //   Toast.makeText(LoginActivity.this, "resendVerificationCode", Toast.LENGTH_SHORT).show();
    }

    private boolean validatePhoneNumber() {
        String phoneNumber = mobilenumber_forotp;
        if (TextUtils.isEmpty(phoneNumber)) {
            MobileNumber.setError("Invalid phone number.");
            return false;
        }
        return true;
    }

   /* @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        System.out.println("===============currentuser : " + currentUser);
        System.out.println("===============mAuth.getCurrentUser() : " + mAuth.getCurrentUser());
        if (currentUser != null) {
            startActivity(new Intent(LoginActivity.this, DashBoardActivity.class));
            finish();
        }
    }*/
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
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_btn:
                String mobilenumber=MobileNumber.getText().toString();
                String password=Password.getText().toString();
                mobilenumber_forotp="+91"+mobilenumber;
             /*   if(mobilenumber.equals("")){
                    Toast.makeText(LoginActivity.this, "Mobile Number Required", Toast.LENGTH_SHORT).show();
                }else */if(password.equals("")){
                    Toast.makeText(LoginActivity.this, "Password Required", Toast.LENGTH_SHORT).show();
            }else{
                    UserList=dbHandler.docLogin(mobilenumber);
                    if(UserList.size()>0){
                        // Call isNetworkAvailable class
                        if (!isNetworkAvailable()) {
                            Toast.makeText(this, "Internet Connection is not available", Toast.LENGTH_SHORT).show();
                        } else{
                       /* LoginLayout.setVisibility(View.GONE);
                            OtpLayout.setVisibility(View.VISIBLE);
                            startPhoneNumberVerification(LoginActivity.mobilenumber_forotp);
            */
                       Intent intent=new Intent(LoginActivity.this,DashBoardActivity.class);
                        startActivity(intent);
                        }
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "Mobile Number/Password is invalid", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.otp_submit_btn:
                String code = Otp.getText().toString();
               if(code.equals(" ")){
                   Toast.makeText(this, "Please enter valid OTP", Toast.LENGTH_SHORT).show();
               }
                verifyPhoneNumberWithCode(mVerificationId, code);
                break;
            case R.id.resend_tv:
                resendVerificationCode(mobilenumber_forotp, mResendToken);
                break;
            case  R.id.register:
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
}
