package com.app.shresta.shrestaapp.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.shresta.shrestaapp.R;
import com.app.shresta.shrestaapp.activity.aesalgorithm.AESHelper;
import com.app.shresta.shrestaapp.activity.handler.DatabaseHandler;

/**
 * Created by shoaib.farhan on 26-11-2017.
 */

public class AddKeyItemActivity extends AppCompatActivity implements View.OnClickListener{

    EditText KeyET, KeyValueET;
    Button AddBtn;
    String KeyStr,KeyValueStr;
    DatabaseHandler dbHandler;
    // Font path
    String Ubuntubold = "font/Ubuntubold.ttf";
    String UbuntuR = "font/UbuntuR.ttf";
    String UbuntuC = "font/UbuntuC.ttf";
    String EncryptedKeyStr,EncryptedKeyValueStr,DecryptedKeyStr,DecryptedKeyValueStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addkeyvalues);
        dbHandler=new DatabaseHandler(AddKeyItemActivity.this);

        KeyET=(EditText)findViewById(R.id.addkey_et);
        KeyValueET=(EditText)findViewById(R.id.addkeynumber_et);
        AddBtn=(Button) findViewById(R.id.add_btn);

        // Loading Font Face
        Typeface Ubuntubold1 = Typeface.createFromAsset(getAssets(), Ubuntubold);
        Typeface UbuntuC1 = Typeface.createFromAsset(getAssets(), UbuntuC);
        Typeface UbuntuR1 = Typeface.createFromAsset(getAssets(), UbuntuR);
        // Applying font
        KeyET.setTypeface(UbuntuR1);
        KeyValueET.setTypeface(UbuntuR1);
        AddBtn.setTypeface(Ubuntubold1);

        AddBtn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_btn:
                KeyStr = KeyET.getText().toString();
                KeyValueStr = KeyValueET.getText().toString();

                if(KeyStr.equals("")){
                    Toast.makeText(AddKeyItemActivity.this, "Key Required", Toast.LENGTH_SHORT).show();
                }else if(KeyValueStr.equals("")){
                    Toast.makeText(AddKeyItemActivity.this, "Key Value Required", Toast.LENGTH_SHORT).show();
                }else {
                    System.out.println("key  ; " + KeyStr);
                    System.out.println("key value ; " + KeyValueStr);
                    try {
                        EncryptedKeyStr = AESHelper.encrypt("AES Algo", KeyStr);
                        DecryptedKeyStr = AESHelper.decrypt("AES Algo", EncryptedKeyStr);
                     System.out.println("Normal Text ::"+KeyStr +" \n Encrypted Value :: "+EncryptedKeyStr +" \n Decrypted value :: "+ DecryptedKeyStr);
                        //setContentView(txe);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    try {
                        EncryptedKeyValueStr = AESHelper.encrypt("AES Algo", KeyValueStr);
                        DecryptedKeyValueStr = AESHelper.decrypt("AES Algo", EncryptedKeyValueStr);
                        System.out.println("Normal Text ::"+KeyStr +" \n Encrypted key Value :: "+EncryptedKeyValueStr +" \n Decrypted key value :: "+ DecryptedKeyValueStr);
                        //setContentView(txe);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    dbHandler.addKeyValues(KeyStr, EncryptedKeyValueStr);
                    Toast.makeText(AddKeyItemActivity.this, "Successfully added", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(AddKeyItemActivity.this,DashBoardActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }
}
