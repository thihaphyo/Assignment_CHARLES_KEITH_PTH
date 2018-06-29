package com.padc.charleskeith.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.padc.charleskeith.R;
import com.padc.charleskeith.data.vos.NewProductVO;
import com.padc.charleskeith.delegates.NewProductDetailDelegate;
import com.padc.charleskeith.utils.GlideApp;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Phyo Thiha on 6/29/18.
 */
public class ProductDetailViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_product_hero)
    ImageView ivProductHero;

    private NewProductDetailDelegate mDelegate;

    private NewProductVO mNewProduct;

    public ProductDetailViewHolder(View itemView, NewProductDetailDelegate detailDelegate) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        mDelegate = detailDelegate;
        itemView.setOnClickListener((v) -> mDelegate.onTapProduct(mNewProduct));

    }

    public void setData(NewProductVO newProduct) {
        mNewProduct = newProduct;
        GlideApp.with(ivProductHero)
                .load(mNewProduct.getProductImage())
                .placeholder(R.drawable.loader)
                .error(R.drawable.loader)
                .into(ivProductHero);
    }
}
