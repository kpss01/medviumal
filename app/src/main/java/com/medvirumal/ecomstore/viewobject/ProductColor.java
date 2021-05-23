package com.medvirumal.ecomstore.viewobject;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;

@Entity(primaryKeys = "id")
public class ProductColor implements Cloneable{

    @SerializedName("id")
    @NonNull
    public final String id;

    @SerializedName("product_id")
    public final String productId;

    @SerializedName("color_value")
    public final String colorValue;

    @SerializedName("added_date")
    public final String addedDate;

    @SerializedName("added_user_id")
    public final String addedUserId;

    @SerializedName("updated_date")
    public final String updatedDate;

    @SerializedName("updated_user_id")
    public final String updatedUserId;

    @SerializedName("updated_flag")
    public final String updatedFlag;

    @SerializedName("is_empty_object")
    public final String isEmptyObject;

    @Ignore
    public boolean isColorSelect = false;

    public ProductColor(@NonNull String id, String productId, String colorValue, String addedDate, String addedUserId, String updatedDate, String updatedUserId, String updatedFlag, String isEmptyObject) {
        this.id = id;
        this.productId = productId;
        this.colorValue = colorValue;
        this.addedDate = addedDate;
        this.addedUserId = addedUserId;
        this.updatedDate = updatedDate;
        this.updatedUserId = updatedUserId;
        this.updatedFlag = updatedFlag;
        this.isEmptyObject = isEmptyObject;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
