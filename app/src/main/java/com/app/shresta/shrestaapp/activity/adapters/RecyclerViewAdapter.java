package com.app.shresta.shrestaapp.activity.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.shresta.shrestaapp.R;
import com.app.shresta.shrestaapp.activity.model.KeyValuesModel;

import java.util.List;

/**
 * Created by shoaib.farhan on 14-12-2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ProductViewHolder> {

    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<KeyValuesModel> productList;

    //getting the context and product list with constructor
    public RecyclerViewAdapter(Context mCtx, List<KeyValuesModel> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public RecyclerViewAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.key_value_item, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ProductViewHolder holder, int position) {
//getting the product of the specified position
        KeyValuesModel keyValuesModel = productList.get(position);

        //binding the data with the viewholder views
        holder.key_tv.setText(keyValuesModel.getKey());
        holder.createdat.setText(keyValuesModel.getKeyCreatedat());
        holder.updatedat.setText(keyValuesModel.getKeyUpdatedat());

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView key_tv, createdat, updatedat;

        public ProductViewHolder(View itemView) {
            super(itemView);

            key_tv = itemView.findViewById(R.id.key_tv);
            createdat = itemView.findViewById(R.id.createdat);
            updatedat = itemView.findViewById(R.id.updatedat);

        }
    }

}
