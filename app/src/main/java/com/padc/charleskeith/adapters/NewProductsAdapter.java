package com.padc.charleskeith.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.charleskeith.R;
import com.padc.charleskeith.data.vos.NewProductVO;
import com.padc.charleskeith.delegates.NewProductDelegate;
import com.padc.charleskeith.viewholders.NewProductViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Phyo Thiha on 6/28/18.
 */
public class NewProductsAdapter extends RecyclerView.Adapter<NewProductViewHolder> {

    private NewProductDelegate mDelegate;

    private List<NewProductVO> mProductList;

    private boolean isTwoGrid;

    public NewProductsAdapter(NewProductDelegate delegate) {
        mDelegate = delegate;
        isTwoGrid = true;
        mProductList = new ArrayList<>();
    }

    public void setLayoutManager(boolean isTwoGrid){
        this.isTwoGrid = isTwoGrid;
    }

    @NonNull
    @Override
    public NewProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_holder_item, parent, false);
        return new NewProductViewHolder(view, mDelegate);
    }

    @Override
    public void onBindViewHolder(@NonNull NewProductViewHolder holder, int position) {

        holder.setData(mProductList.get(position),isTwoGrid);

    }


    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    public void setNewProductList(List<NewProductVO> newProductList) {
        mProductList = newProductList;
        notifyDataSetChanged();
    }

    public void appendNewProductList(List<NewProductVO> newProductList){
        mProductList.addAll(newProductList);
        notifyDataSetChanged();
    }
}
