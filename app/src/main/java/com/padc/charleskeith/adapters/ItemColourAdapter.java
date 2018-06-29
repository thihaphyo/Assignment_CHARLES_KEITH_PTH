package com.padc.charleskeith.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.charleskeith.R;
import com.padc.charleskeith.viewholders.ColourProductViewHolder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Phyo Thiha on 6/29/18.
 */
public class ItemColourAdapter extends RecyclerView.Adapter<ColourProductViewHolder> {

    private HashMap<String,Integer> mColourMap;

    public ItemColourAdapter(){
        mColourMap = new HashMap<>();
    }

    @NonNull
    @Override
    public ColourProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.from(parent.getContext()).inflate(R.layout.view_holder_colour_item,parent,false);
        return new ColourProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ColourProductViewHolder holder, int position) {
        Object keys[] = mColourMap.keySet().toArray();
        String key = keys[position].toString();
        holder.setData(key,mColourMap.get(key));
    }


    @Override
    public int getItemCount() {
        return 3;
    }

    public void setColourMap(HashMap<String,Integer> colorMap){
        this.mColourMap = colorMap;
        notifyDataSetChanged();
    }
}
