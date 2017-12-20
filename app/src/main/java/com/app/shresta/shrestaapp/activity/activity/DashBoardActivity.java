package com.app.shresta.shrestaapp.activity.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.shresta.shrestaapp.R;
import com.app.shresta.shrestaapp.activity.adapters.KeyValueAdapter;
import com.app.shresta.shrestaapp.activity.adapters.RecyclerViewAdapter;
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
    TextView nodataLayout,keystext;
    DatabaseHandler databaseHandler;
    private SQLiteDatabase sqLiteDatabase;
    private Cursor c;
    String key,keyValue,keyValuesListStr,keyId,keycreatedat,keyupdatedat;
    String DecryptedKeyValueStr, DecryptedKeyStr;

    public List<String> listofgetkeyvalues = new ArrayList<String>();
    ArrayList<String> key_Arrlist = new ArrayList<String>();
    ArrayList<String> key_value_Arrlist = new ArrayList<String>();
    ArrayList<String> key_createdat_Arrlist = new ArrayList<String>();
    ArrayList<String> key_updatedat_Arrlist = new ArrayList<String>();
    ArrayList<KeyValuesModel> keyValuesModels = new ArrayList<KeyValuesModel>();

    DatabaseHandler dbHandler;
    ImageView brandLogo;
    TextView brandName;
    //a list to store all the products
    List<KeyValuesModel> productList;

    //the recyclerview
    RecyclerView recyclerView;
    // Font path
    String Ubuntubold = "font/Ubuntubold.ttf";
    String UbuntuR = "font/UbuntuR.ttf";
    String UbuntuC = "font/UbuntuC.ttf";
    String Verdana = "font/verdana.ttf";
    String VerdanaB = "font/verdanab.ttf";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);
        dbHandler=new DatabaseHandler(DashBoardActivity.this);

        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.key_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the productlist
        productList = new ArrayList<>();

       nodataLayout=(TextView)findViewById(R.id.nodatafound);
        keystext=(TextView)findViewById(R.id.keystext);

        getSupportActionBar().setCustomView(R.layout.custom_actionbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        brandLogo = (ImageView) findViewById(R.id.brand_logo_icon);
        brandName = (TextView) findViewById(R.id.brand_name);
        // brandLogo.setImageResource(R.drawable.logo);
        brandName.setText("Dash Board");

        // Loading Font Face
        Typeface Ubuntubold1 = Typeface.createFromAsset(getAssets(), Ubuntubold);
        Typeface UbuntuC1 = Typeface.createFromAsset(getAssets(), UbuntuC);
        Typeface UbuntuR1 = Typeface.createFromAsset(getAssets(), UbuntuR);
        Typeface Verdana1 = Typeface.createFromAsset(getAssets(), Verdana);
        Typeface VerdanaB1 = Typeface.createFromAsset(getAssets(), VerdanaB);

        brandName.setTypeface(Ubuntubold1);

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

      /*  listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String keyintent = listofgetkeyvalues.get(position);
                for (int i = 0; i < listofgetkeyvalues.size(); i++) {
                    String[] keyArr = keyintent.split(Pattern.quote("***"));
                    keyId = keyArr[0];
                    Log.i("keyId", keyId);
                    key = keyArr[1];
                    Log.i("key", key);
                    keyValue = keyArr[2];
                    Log.i("keyvalue", keyValue);
                }
                System.out.println("=======keyIntent in DashBoard : " + keyintent);
                Intent intent=new Intent(DashBoardActivity.this, AddKeyItemActivity.class);
                intent.putExtra("keyid",keyId);
                intent.putExtra("keyname",key);
                intent.putExtra("keyvalue",keyValue);

                startActivity(intent);
            }
        });

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                String keyintent = listofgetkeyvalues.get(pos);
                for (int i = 0; i < listofgetkeyvalues.size(); i++) {
                    String[] keyArr = keyintent.split(Pattern.quote("***"));
                    keyId = keyArr[0];
                    Log.i("keyId", keyId);
                    key = keyArr[1];
                    Log.i("key", key);
                    keyValue = keyArr[2];
                    Log.i("keyvalue", keyValue);
                }
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DashBoardActivity.this);
                alertDialogBuilder.setMessage("Do you want to Delete this Item?");
                        alertDialogBuilder.setPositiveButton("yes",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {

                                        dbHandler.delete_key(Integer.parseInt(keyId),key,keyValue);
                                        Toast.makeText(DashBoardActivity.this, "Key deleted", Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(DashBoardActivity.this,DashBoardActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);

                                    }
                                });

                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();



                               return true;
            }
        });*/
       /* mAuth = FirebaseAuth.getInstance();
        if(mAuth!=null){
           // fireBaseId.setText(mAuth.getCurrentUser().getPhoneNumber());
        }*/


        // Applying font
        keystext.setTypeface(UbuntuR1);
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
     //       Log.i("key", key);
            keyValue = c.getString(2);
       //     Log.i("keyvalue", keyValue);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getKeyValues(){
        listofgetkeyvalues = databaseHandler.getValuesList();
        if(listofgetkeyvalues.size()<=0){
         //   listview.setVisibility(View.GONE);
            nodataLayout.setVisibility(View.VISIBLE);
        }else{
            for (int i = 0; i < listofgetkeyvalues.size(); i++) {
                keyValuesListStr = listofgetkeyvalues.get(i);
              //  Log.i("keyValuesListStr", keyValuesListStr);
                String[] keyArr = keyValuesListStr.split(Pattern.quote("***"));
                key = keyArr[1];
                try{
                    DecryptedKeyStr = AESHelper.decrypt("AES Algo", key);
                    Log.i("DecryptedKeyValueStr", DecryptedKeyStr);
                    System.out.println("==========key decrypted ; " + DecryptedKeyStr);
                }catch (Exception e){
                    e.printStackTrace();
                }
                key_Arrlist.add(key);
              //  Log.i("key", key);
                keyValue = keyArr[2];

                try{
                    DecryptedKeyValueStr = AESHelper.decrypt("AES Algo", keyValue);
                    Log.i("DecryptedKeyValueStr", DecryptedKeyValueStr);
                    System.out.println("==========key Value decrypted ; " + DecryptedKeyValueStr);
                }catch (Exception e){
                    e.printStackTrace();
                }
                key_value_Arrlist.add(keyValue);
                keycreatedat=keyArr[3];
                key_createdat_Arrlist.add(keycreatedat);
                keyupdatedat=keyArr[4];
                key_updatedat_Arrlist.add(keyupdatedat);
            }
            for(int i=0;i<listofgetkeyvalues.size();i++){
                KeyValuesModel keyValuesModel=new KeyValuesModel();
                keyValuesModel.setKey(key_Arrlist.get(i));
                keyValuesModel.setKeyValue(key_value_Arrlist.get(i));
                keyValuesModel.setKeyCreatedat(key_createdat_Arrlist.get(i));
                keyValuesModel.setKeyUpdatedat(key_updatedat_Arrlist.get(i));
                keyValuesModels.add(keyValuesModel);
            }
            RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(DashBoardActivity.this, keyValuesModels);
            recyclerView.setAdapter(recyclerViewAdapter);
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
                Intent intent=new Intent(DashBoardActivity.this, FourDigitActivity.class);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
