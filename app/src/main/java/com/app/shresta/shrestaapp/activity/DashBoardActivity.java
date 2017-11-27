package com.app.shresta.shrestaapp.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.app.shresta.shrestaapp.R;
import com.app.shresta.shrestaapp.activity.adapters.KeyValueAdapter;
import com.app.shresta.shrestaapp.activity.aesalgorithm.AESHelper;
import com.app.shresta.shrestaapp.activity.handler.DatabaseHandler;
import com.app.shresta.shrestaapp.activity.model.KeyValuesModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by shoaib.farhan on 09-11-2017.
 */

public class DashBoardActivity extends AppCompatActivity {

    TextView Signout;
    private static final String TAG = "PhoneAuthActivity";
    private FirebaseAuth mAuth;
    ListView listview;
    TextView nodataLayout;
    DatabaseHandler databaseHandler;
    private SQLiteDatabase sqLiteDatabase;
    private Cursor c;
    String key,keyValue,keyValuesListStr;
    String DecryptedKeyValueStr, DecryptedKeyStr;

    public List<String> listofgetkeyvalues = new ArrayList<String>();
    ArrayList<String> key_Arrlist = new ArrayList<String>();
    ArrayList<String> key_value_Arrlist = new ArrayList<String>();
    ArrayList<KeyValuesModel> keyValuesModels = new ArrayList<KeyValuesModel>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);

       listview=(ListView)findViewById(R.id.key_listview);
       nodataLayout=(TextView)findViewById(R.id.nodatafound);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_key_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DashBoardActivity.this, AddKeyItemActivity.class);
                startActivity(intent);
            }
        });
        databaseHandler = new DatabaseHandler(DashBoardActivity.this);
        getKeyValues();

       /* mAuth = FirebaseAuth.getInstance();
        if(mAuth!=null){
           // fireBaseId.setText(mAuth.getCurrentUser().getPhoneNumber());
        }*/
    }
    protected void openDatabase() {
        sqLiteDatabase = openOrCreateDatabase("shresta_app", Context.MODE_PRIVATE, null);
    }

    protected void moveNext() {
        if (!c.isLast())
            c.moveToNext();
        showRecords();
    }
    public void showRecords(){
        try {
            key = c.getString(1);
            Log.i("key", key);
            keyValue = c.getString(2);
            Log.i("keyvalue", keyValue);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getKeyValues(){
        listofgetkeyvalues = databaseHandler.getValuesList();
        if(listofgetkeyvalues.size()<=0){
            listview.setVisibility(View.GONE);
            nodataLayout.setVisibility(View.VISIBLE);
        }else{
            for (int i = 0; i < listofgetkeyvalues.size(); i++) {
                keyValuesListStr = listofgetkeyvalues.get(i);
                Log.i("keyValuesListStr", keyValuesListStr);
                String[] keyArr = DecryptedKeyValueStr.split(Pattern.quote("***"));
                key = keyArr[0];
                key_Arrlist.add(key);
                Log.i("key", key);
                keyValue = keyArr[2];
                System.out.println("==========key Value encrypted ; " + keyValue);
                /*try{
                    DecryptedKeyValueStr = AESHelper.decrypt("AES Algo", keyValue);
                    Log.i("DecryptedKeyValueStr", DecryptedKeyValueStr);
                }catch (Exception e){
                    e.printStackTrace();
                }*/
                key_value_Arrlist.add(keyValue);
            }
            for(int i=0;i<listofgetkeyvalues.size();i++){
                KeyValuesModel keyValuesModel=new KeyValuesModel();
                keyValuesModel.setKey(key_Arrlist.get(i));
                keyValuesModel.setKeyValue(key_value_Arrlist.get(i));
                keyValuesModels.add(keyValuesModel);
            }
            KeyValueAdapter keyValueAdapter = new KeyValueAdapter(DashBoardActivity.this, keyValuesModels);
            listview.setAdapter(keyValueAdapter);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.signout:
                Intent intent=new Intent(DashBoardActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;
          /*  case R.id.help:
                startActivity(new Intent(this, Help.class));
                return true;*/
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
