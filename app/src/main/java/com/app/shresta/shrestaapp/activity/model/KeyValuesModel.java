package com.app.shresta.shrestaapp.activity.model;

/**
 * Created by shoaib.farhan on 26-11-2017.
 */

public class KeyValuesModel {

    private String Key="";
    private String KeyValue="";
    private String KeyCreatedat="";
    private String KeyUpdatedat="";

  /*  public KeyValuesModel(String Key, String KeyValue, String KeyCreatedat, String KeyUpdateat){
        this.Key=Key;
        this.KeyValue=KeyValue;
        this.KeyCreatedat=KeyCreatedat;
        this.KeyUpdatedat=KeyUpdateat;
    }*/


    public String getKeyCreatedat() {
        return KeyCreatedat;
    }

    public void setKeyCreatedat(String keyCreatedat) {
        KeyCreatedat = keyCreatedat;
    }

    public String getKeyUpdatedat() {
        return KeyUpdatedat;
    }

    public void setKeyUpdatedat(String keyUpdatedat) {
        KeyUpdatedat = keyUpdatedat;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getKeyValue() {
        return KeyValue;
    }

    public void setKeyValue(String keyValue) {
        KeyValue = keyValue;
    }
}
