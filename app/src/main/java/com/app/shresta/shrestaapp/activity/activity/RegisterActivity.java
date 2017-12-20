package com.app.shresta.shrestaapp.activity.activity;

import android.content.Intent;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
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

    private FirebaseAuth mAuth;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    String mVerificationId;
    private static final String TAG = "PhoneAuthActivity";
    // Font path
    String Ubuntubold = "font/Ubuntubold.ttf";
    String UbuntuR = "font/UbuntuR.ttf";
    String UbuntuC = "font/UbuntuC.ttf";
    String Verdana = "font/verdana.ttf";
    String VerdanaB = "font/verdanab.ttf";

    LinearLayout resister_layout;

    Button ValidateOTP;
    EditText DigitOne, DigitTwo, DigitThree, DigitFour, DigitFive, DigitSix;
    LinearLayout activation_Layout;
    public static String updatemobilenumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //code that displays the content in full screen mode
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.register_activity);

        // Loading Font Face
        Typeface Ubuntubold1 = Typeface.createFromAsset(getAssets(), Ubuntubold);
        Typeface UbuntuC1 = Typeface.createFromAsset(getAssets(), UbuntuC);
        Typeface UbuntuR1 = Typeface.createFromAsset(getAssets(), UbuntuR);
        Typeface Verdana1 = Typeface.createFromAsset(getAssets(), Verdana);
        Typeface VerdanaB1 = Typeface.createFromAsset(getAssets(), VerdanaB);

        registration_text=(TextView)findViewById(R.id.registration_text);
        fullName = (EditText) findViewById(R.id.fullname_edit_txt);
        mobileNumber = (EditText) findViewById(R.id.mobilenumber_edit_txt);
        email = (EditText) findViewById(R.id.email_edit_txt);
        userName=(EditText)findViewById(R.id.username_edit_txt);
        passWord = (EditText) findViewById(R.id.password_edit_txt);
        resister_layout=(LinearLayout)findViewById(R.id.resister_layout);

        registerBtn = (Button) findViewById(R.id.registration_btn);

        DigitOne=(EditText)findViewById(R.id.first_digit);
        DigitTwo=(EditText)findViewById(R.id.second_digit);
        DigitThree=(EditText)findViewById(R.id.third_digit);
        DigitFour=(EditText)findViewById(R.id.fourth_digit);
        DigitFive=(EditText)findViewById(R.id.fifth_digit);
        DigitSix=(EditText)findViewById(R.id.sixth_digit);
        ValidateOTP=(Button)findViewById(R.id.validate_otp_btn);
        activation_Layout=(LinearLayout)findViewById(R.id.activation_Layout);

        // Applying font
        registration_text.setTypeface(VerdanaB1);
        fullName.setTypeface(Verdana1);
        mobileNumber.setTypeface(Verdana1);
        email.setTypeface(Verdana1);
        userName.setTypeface(Verdana1);
        passWord.setTypeface(Verdana1);
        registerBtn.setTypeface(VerdanaB1);

        registerBtn.setOnClickListener(this);
        ValidateOTP.setOnClickListener(this);
        dbHandler=new DatabaseHandler(RegisterActivity.this);

        mAuth = FirebaseAuth.getInstance();
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                Log.d(TAG, "onVerificationCompleted:" + credential);
                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Log.w(TAG, "onVerificationFailed", e);
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    //mPhoneNumberField.setError("Invalid phone number.");
                    Toast.makeText(RegisterActivity.this, "Invalid Phone Number", Toast.LENGTH_SHORT).show();
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    Snackbar.make(findViewById(android.R.id.content), "Quota exceeded.",
                            Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                Log.d(TAG, "onCodeSent:" + verificationId);
                mVerificationId = verificationId;
                mResendToken = token;
            }
        };
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = task.getResult().getUser();
                            startActivity(new Intent(RegisterActivity.this, ActivatedSuccess.class));
                            //finish();
                        } else {
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Intent intent=new Intent(RegisterActivity.this, ActivationFailure.class);
                                startActivity(intent);
                                //   mVerificationField.setError("Invalid code.");
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
    }

    private void verifyPhoneNumberWithCode(String verificationId, String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        System.out.println("************************************");
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
    }

    private boolean validatePhoneNumber() {
        String phoneNumber = mobileNumber.getText().toString();
        if (TextUtils.isEmpty(phoneNumber)) {
            //   mPhoneNumberField.setError("Invalid phone number.");
            return false;
        }
        return true;
    }

   /* @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(RegisterActivity.this, FourDigitActivity.class));
          //  finish();
        }
    }*/

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.validate_otp_btn:

                String DigitOneStr = DigitOne.getText().toString();
                String DigitSecondStr = DigitTwo.getText().toString();
                String DigitThirdStr = DigitThree.getText().toString();
                String DigitfourStr = DigitFour.getText().toString();
                String DigitFiveStr = DigitFive.getText().toString();
                String DigitSixStr = DigitSix.getText().toString();

                String OTPStr= DigitOneStr+DigitSecondStr+DigitThirdStr+DigitfourStr+DigitFiveStr+DigitSixStr;
                System.out.println("=======OTPstr : " + OTPStr);

                if(OTPStr.equals(" ")){
                    Toast.makeText(this, "Please enter valid OTP", Toast.LENGTH_SHORT).show();
                }
                verifyPhoneNumberWithCode(mVerificationId, OTPStr);
                break;

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
        }else if (passWordStr.length()!=4) {
            Toast.makeText(RegisterActivity.this, "Please Enter 4 letters Password", Toast.LENGTH_SHORT).show();
        }
            else{
            /*docList=dbHandler.doc+Login(mobileNumberStr,passWordStr);
            if(docList.size()>0){
                Toast.makeText(RegisterActivity.this, " User already exists", Toast.LENGTH_SHORT).show();
            }
            else {*/
                dbHandler.docReg(fullNameStr, mobileNumberStr, emailStr, userNameStr, passWordStr);
                //Intent in=new Intent(RegisterActivity.this,ValidateOTPActivity.class);
            if (!validatePhoneNumber()) {
                return;
            }
            resister_layout.setVisibility(View.GONE);
            activation_Layout.setVisibility(View.VISIBLE);

            updatemobilenumber=mobileNumber.getText().toString();
            String Chnagedmobilenumber= "+91" + updatemobilenumber;

            startPhoneNumberVerification(Chnagedmobilenumber);
              //  startActivity(in);

                Toast.makeText(RegisterActivity.this, "Successfully registered and OTP sent to registered mobile number", Toast.LENGTH_SHORT).show();
               // finish();
            }
        }



    }
