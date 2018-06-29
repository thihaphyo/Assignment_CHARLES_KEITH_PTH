package com.padc.charleskeith.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.padc.charleskeith.R;
import com.padc.charleskeith.adapters.ItemColourAdapter;
import com.padc.charleskeith.adapters.ProductDetailAdapter;
import com.padc.charleskeith.data.models.NewProductsModel;
import com.padc.charleskeith.data.vos.NewProductVO;
import com.padc.charleskeith.delegates.NewProductDetailDelegate;
import com.padc.charleskeith.delegates.RightDrawerDelegate;
import com.padc.charleskeith.utils.AppConstants;
import com.padc.charleskeith.viewpods.EmptyViewPod;
import com.padc.charleskeith.viewpods.RightDrawerViewPod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Phyo Thiha on 6/29/18.
 */
public class ProductDetailActivity extends BaseActivity implements RightDrawerDelegate, NewProductDetailDelegate {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView tvTitle;
    @BindView(R.id.btnColours)
    ImageView btnColours;
    @BindView(R.id.rv_details)
    RecyclerView rvDetails;
    @BindView(R.id.vp_colours)
    RightDrawerViewPod vpColoursDrawer;
    @BindView(R.id.tv_product_name)
    TextView tvProductName;
    @BindView(R.id.vp_empty)
    EmptyViewPod vpEmpty;
    @BindView(R.id.rl_container)
    RelativeLayout rlContainer;
    @BindView(R.id.btnInfo)
    Button btnInfo;


    private ProductDetailAdapter adapter;
    private NewProductVO newProductVO;
    private List<NewProductVO> mProductList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ButterKnife.bind(this, this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        int productId = getIntent().getIntExtra(AppConstants.NEW_PRODUCT_ID, 0);
        newProductVO = NewProductsModel.getObjectReference().getProductById(productId);
        mProductList = new ArrayList<>();
        if (newProductVO == null){

            vpEmpty.setVisibility(View.VISIBLE);
            vpEmpty.setEmptyData("https://cdn.dribbble.com/users/1753953/screenshots/3818675/animasi-emptystate.gif","မိတ္ေဆြ ၾကည့္ ႐ႈ လို ေသာ item မွာ မထင္ မွတ္ ထား ေသာ ျပသနာ ေၾကာင့္ မထုတ္ ျပ ႏိုင္ေသး ပါ");
            rlContainer.setVisibility(View.GONE);

        }else{
            vpEmpty.setVisibility(View.GONE);
            rlContainer.setVisibility(View.VISIBLE);
            bindData();
        }

        adapter = new ProductDetailAdapter(this);
        rvDetails.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        rvDetails.setAdapter(adapter);
        adapter.setNewProductList(mProductList);

        btnColours.setOnClickListener((v) -> {
            vpColoursDrawer.setVisibility(View.VISIBLE);
            btnInfo.setVisibility(View.GONE);
        });

        vpColoursDrawer.setDelegate(this);

    }

    private void bindData(){
        for (int i = 0; i < 10; i++) {
            mProductList.add(newProductVO);
        }
        tvProductName.setText(newProductVO.getProductTitle());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onDrawerClose() {
        vpColoursDrawer.setVisibility(View.GONE);
        btnInfo.setVisibility(View.VISIBLE);
    }


    @Override
    public void onTapProduct(NewProductVO newProduct) {

    }


}
