package com.padc.charleskeith.network.responses;

import com.google.gson.annotations.SerializedName;
import com.padc.charleskeith.data.vos.NewProductVO;

import java.util.List;

/**
 * Created by Phyo Thiha on 6/29/18.
 */
public class GetNewProductResponse {

    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String message;
    @SerializedName("apiVersion")
    private String apiVersion;
    @SerializedName("page")
    private String page;
    @SerializedName("newProducts")
    private List<NewProductVO> newProduct;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public List<NewProductVO> getNewProduct() {
        return newProduct;
    }

    public String getPage() {
        return page;
    }

    public boolean isResponseOk(){
        return code == 200 && newProduct!=null;
    }
}
