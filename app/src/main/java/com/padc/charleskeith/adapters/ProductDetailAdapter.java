package com.padc.charleskeith.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.charleskeith.R;
import com.padc.charleskeith.data.vos.NewProductVO;
import com.padc.charleskeith.delegates.NewProductDetailDelegate;
import com.padc.charleskeith.viewholders.ProductDetailViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Phyo Thiha on 6/29/18.
 */
public class ProductDetailAdapter extends RecyclerView.Adapter<ProductDetailViewHolder> {

    private NewProductDetailDelegate mDelegate;

    private List<NewProductVO> newProductList;

    public ProductDetailAdapter(NewProductDetailDelegate delegate) {
        mDelegate = delegate;
        newProductList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ProductDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_holder_product, parent, false);
        return new ProductDetailViewHolder(view, mDelegate);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductDetailViewHolder holder, int position) {
        holder.setData(newProductList.get(position));
    }


    @Override
    public int getItemCount() {
        return newProductList.size();
    }

    public void setNewProductList(List<NewProductVO> newProductList) {
        this.newProductList = newProductList;
        notifyDataSetChanged();
    }
}
