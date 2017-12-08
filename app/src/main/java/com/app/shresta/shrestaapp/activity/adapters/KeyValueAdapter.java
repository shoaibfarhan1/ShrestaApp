package com.app.shresta.shrestaapp.activity.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.shresta.shrestaapp.R;
import com.app.shresta.shrestaapp.activity.model.KeyValuesModel;

import java.util.ArrayList;

/**
 * Created by shoaib.farhan on 26-11-2017.
 */

public class KeyValueAdapter extends BaseAdapter implements View.OnClickListener {

    /*********** Declare Used Variables *********/
    private Context context;
    private ArrayList data;
    private static LayoutInflater inflater=null;
    public Resources res;
    KeyValuesModel tempValues=null;
    int i=0;
    String Ubuntubold = "font/Ubuntubold.ttf";
    String UbuntuR = "font/UbuntuR.ttf";
    String UbuntuC = "font/UbuntuC.ttf";

    /*************  CustomAdapter Constructor *****************/
    public KeyValueAdapter(Context c, ArrayList d) {

        /********** Take passed values **********/
        context = c;
        data=d;
        //res = resLocal;

        /***********  Layout inflator to call external xml layout () **********************/
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    /******** What is the size of Passed Arraylist Size ************/
    public int getCount() {

        if(data.size()<=0)
            return 1;
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onClick(View v) {

    }

    /********* Create a holder to contain inflated xml file elements ***********/
    public static class ViewHolder{

        public TextView key_TV;
        public TextView keyvalue_TV;
        public ImageView Delete;

    }

    /*********** Depends upon data size called for each row , Create each ListView row ***********/
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi=convertView;
        ViewHolder holder;

        if(convertView==null){

            /********** Inflate tabitem.xml file for each row ( Defined below ) ************/
            vi = inflater.inflate(R.layout.key_value_item, null);

            /******** View Holder Object to contain tabitem.xml file elements ************/
            holder=new ViewHolder();
            holder.key_TV=(TextView)vi.findViewById(R.id.key_tv);
           // holder.Delete=(ImageView)vi.findViewById(R.id.delete);
            //holder.keyvalue_TV=(TextView)vi.findViewById(R.id.keyvalue_tv);
            vi.setTag(holder);
        }
        else
            holder=(ViewHolder)vi.getTag();

        if(data.size()<=0)
        {
            holder.key_TV.setText("No Data");

        }
        else
        {
            /***** Get each Model object from Arraylist ********/
            tempValues=null;
            tempValues = (KeyValuesModel) data.get(position);
            // Loading Font Face
            Typeface Ubuntubold1 = Typeface.createFromAsset(context.getAssets(), Ubuntubold);
            Typeface UbuntuC1 = Typeface.createFromAsset(context.getAssets(), UbuntuC);
            Typeface UbuntuR1 = Typeface.createFromAsset(context.getAssets(), UbuntuR);
            // Applying font
            holder.key_TV.setTypeface(UbuntuR1);
            //KeyValueET.setTypeface(UbuntuR1);
            /************  Set Model values in Holder elements ***********/
            holder.key_TV.setText(tempValues.getKey());
           // holder.keyvalue_TV.setText("keyvalue : "+tempValues.getKeyValue());

            String keyStr=tempValues.getKey().toString();
            String keyvalueStr=tempValues.getKeyValue().toString();

        }
        return vi;
    }
}
