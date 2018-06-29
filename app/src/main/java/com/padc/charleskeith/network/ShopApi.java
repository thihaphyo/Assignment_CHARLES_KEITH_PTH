package com.padc.charleskeith.network;

import com.padc.charleskeith.network.responses.GetNewProductResponse;
import com.padc.charleskeith.utils.AppConstants;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Phyo Thiha on 6/29/18.
 */
public interface ShopApi {

    @FormUrlEncoded
    @POST(AppConstants.API_GET_NEW_PRODUCTS)
    Call<GetNewProductResponse> loadNewProducts(
            @Field(AppConstants.PARAM_ACCESS_TOKEN) String accessToken,
            @Field(AppConstants.PARAM_PAGE) int page
    );
}
