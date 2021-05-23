package com.medvirumal.ecomstore.db;

import com.medvirumal.ecomstore.viewobject.ShippingMethod;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface ShippingMethodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ShippingMethod> shippingMethods);

    @Query("SELECT * FROM ShippingMethod")
    LiveData<List<ShippingMethod>> getShippingMethods();

    @Query("DELETE FROM ShippingMethod")
    void deleteAll();
}
