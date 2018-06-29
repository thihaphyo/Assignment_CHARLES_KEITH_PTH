package com.padc.charleskeith.data.models;

import android.annotation.SuppressLint;

import com.padc.charleskeith.data.vos.NewProductVO;
import com.padc.charleskeith.events.GetNewProductForceSuccessEvent;
import com.padc.charleskeith.events.GetNewProductSuccessEvent;
import com.padc.charleskeith.network.RetrofitDataAgentImpl;
import com.padc.charleskeith.network.ShopDataAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Phyo Thiha on 6/29/18.
 */
public class NewProductsModel {

    private static NewProductsModel objectReference;

    private final String ACCESS_TOKEN = "b002c7e1a528b7cb460933fc2875e916";

    private ShopDataAgent mDataAgent;

    private int mPage;

    private Map<Integer, NewProductVO> mNewProductRepo;


    @SuppressLint("UseSparseArrays")
    private NewProductsModel() {
        mDataAgent = RetrofitDataAgentImpl.getObjectReference();
        mNewProductRepo = new HashMap<>();
        mPage = 1;
        EventBus.getDefault().register(this);
    }

    public static NewProductsModel getObjectReference() {
        if (objectReference == null) {
            objectReference = new NewProductsModel();
        }
        return objectReference;
    }


    public void loadNewProducts() {
        mDataAgent.loadNewProducts(mPage, ACCESS_TOKEN, false);
        mPage++;
    }

    public void forceRefreshNewProducts() {
        mPage = 1;
        mDataAgent.loadNewProducts(1, ACCESS_TOKEN, true);
        mPage++;
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onSuccessGetNewProduct(GetNewProductSuccessEvent event) {

        setDataRepository(event.getmNewProducts());

    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onSuccessForce(GetNewProductForceSuccessEvent event) {
        setDataRepository(event.getmNewProducts());

    }

    private void setDataRepository(List<NewProductVO> newProducts) {

        for (NewProductVO newProduct : newProducts) {
            mNewProductRepo.put(newProduct.getProductId(), newProduct);
        }

    }

    public NewProductVO getProductById(int productId) {
        //return null;
        return mNewProductRepo.get(productId);
    }
}
