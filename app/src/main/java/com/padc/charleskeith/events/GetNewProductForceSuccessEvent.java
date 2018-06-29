package com.padc.charleskeith.events;

import com.padc.charleskeith.data.vos.NewProductVO;

import java.util.List;

/**
 * Created by Phyo Thiha on 6/29/18.
 */
public class GetNewProductForceSuccessEvent  {

    private List<NewProductVO> mNewProducts;

    public GetNewProductForceSuccessEvent(List<NewProductVO> mNewProducts) {
        this.mNewProducts = mNewProducts;
    }

    public List<NewProductVO> getmNewProducts() {
        return mNewProducts;
    }
}
