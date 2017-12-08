package com.app.shresta.shrestaapp.activity.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.shresta.shrestaapp.R;
import com.app.shresta.shrestaapp.activity.aesalgorithm.AESHelper;
import com.app.shresta.shrestaapp.activity.handler.DatabaseHandler;

/**
 * Created by shoaib.farhan on 26-11-2017.
 */

public class AddKeyItemActivity extends AppCompatActivity implements View.OnClickListener{

    EditText KeyET, KeyValueET;
    Button AddBtn,SaveBtn;
    TextView keystext;
    String KeyStr,KeyValueStr;
    DatabaseHandler dbHandler;
    // Font path
    String Ubuntubold = "font/Ubuntubold.ttf";
    String UbuntuR = "font/UbuntuR.ttf";
    String UbuntuC = "font/UbuntuC.ttf";
    String EncryptedKeyStr,EncryptedKeyValueStr,DecryptedKeyStr,DecryptedKeyValueStr;
    String keyname,keyvalue,keyid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addkeyvalues);
        dbHandler=new DatabaseHandler(AddKeyItemActivity.this);

        KeyET=(EditText)findViewById(R.id.addkey_et);
        KeyValueET=(EditText)findViewById(R.id.addkeynumber_et);
        AddBtn=(Button) findViewById(R.id.add_btn);

        SaveBtn=(Button)findViewById(R.id.save);

    try {
        KeyET.setText("");
        KeyValueET.setText("");
            Intent intent = getIntent();
        keyid = intent.getStringExtra("keyid");
        System.out.println("********keyName******** : " + keyid);
            keyname = intent.getStringExtra("keyname");
            System.out.println("********keyName******** : " + keyname);
            keyvalue = intent.getStringExtra("keyvalue");
            System.out.println("********keyvalue******** : " + keyvalue);

            KeyET.setText(keyname);
            KeyValueET.setText(keyvalue);

          /*SaveBtn.setVisibility(View.VISIBLE);
          AddBtn.setVisibility(View.GONE);*/

        }catch (Exception e){
            e.printStackTrace();
        }

        // Loading Font Face
        Typeface Ubuntubold1 = Typeface.createFromAsset(getAssets(), Ubuntubold);
        Typeface UbuntuC1 = Typeface.createFromAsset(getAssets(), UbuntuC);
        Typeface UbuntuR1 = Typeface.createFromAsset(getAssets(), UbuntuR);
        // Applying font
        KeyET.setTypeface(UbuntuR1);
        KeyValueET.setTypeface(UbuntuR1);
        AddBtn.setTypeface(Ubuntubold1);

        AddBtn.setOnClickListener(this);
        SaveBtn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.add_btn:
                addORsave();
                break;

            case R.id.save:
                Save();
                break;
        }
    }

    public void addORsave(){

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
            dbHandler.addKeyValues(KeyStr, KeyValueStr);
            Toast.makeText(AddKeyItemActivity.this, "Successfully added", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(AddKeyItemActivity.this,DashBoardActivity.class);
            startActivity(intent);
        }
    }

    public void Save(){
        KeyStr = KeyET.getText().toString();
        KeyValueStr = KeyValueET.getText().toString();

        if(KeyStr.equals("")){
            Toast.makeText(AddKeyItemActivity.this, "Key Required", Toast.LENGTH_SHORT).show();
        }else if(KeyValueStr.equals("")){
            Toast.makeText(AddKeyItemActivity.this, "Key Value Required", Toast.LENGTH_SHORT).show();
        }else {
            System.out.println("keyId  ; " + keyid);
            System.out.println("key  ; " + KeyStr);
            System.out.println("key value ; " + KeyValueStr);

            dbHandler.update_keyList(Integer.parseInt(keyid),KeyStr,KeyValueStr);
            Toast.makeText(AddKeyItemActivity.this, "Key Details Updated", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(AddKeyItemActivity.this,DashBoardActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);


        }

    }
}
