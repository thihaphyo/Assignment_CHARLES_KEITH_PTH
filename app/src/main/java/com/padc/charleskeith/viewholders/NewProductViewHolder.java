package com.padc.charleskeith.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.padc.charleskeith.R;
import com.padc.charleskeith.data.vos.NewProductVO;
import com.padc.charleskeith.delegates.NewProductDelegate;
import com.padc.charleskeith.utils.GlideApp;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Phyo Thiha on 6/28/18.
 */
public class NewProductViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.iv_item_image)
    ImageView ivItemImage;
    @BindView(R.id.tv_item_name)
    TextView tvItemName;

    private NewProductDelegate mDelegate;
    private NewProductVO mNewProduct;
    public NewProductViewHolder(View itemView, NewProductDelegate delegate) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        mDelegate = delegate;
        itemView.setOnClickListener((v) -> {
            mDelegate.onTapProduct(mNewProduct);
        });
    }

    public void setData(NewProductVO newProduct,boolean isTwoGrid) {

        mNewProduct = newProduct;
        tvItemName.setText(mNewProduct.getProductTitle());

        if (mNewProduct.getProductImage() == null){

            ivItemImage.setImageResource(R.drawable.placeholder);

        }else{

                GlideApp.with(ivItemImage)
                        .load(mNewProduct.getProductImage())
                        .placeholder(R.drawable.loader)
                        .error(R.drawable.loader)
                        .into(ivItemImage);


        }


    }
}
