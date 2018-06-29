package com.padc.charleskeith.network;

/**
 * Created by Phyo Thiha on 6/29/18.
 */
public interface ShopDataAgent {

    void loadNewProducts(int page,String accessToken,boolean isForceRefresh);
}
