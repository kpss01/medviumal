package com.medvirumal.ecomstore.viewmodel.coupondiscount;

import com.medvirumal.ecomstore.repository.coupondiscount.CouponDiscountRepository;
import com.medvirumal.ecomstore.ui.checkout.CheckoutActivity;
import com.medvirumal.ecomstore.utils.AbsentLiveData;
import com.medvirumal.ecomstore.viewmodel.common.PSViewModel;
import com.medvirumal.ecomstore.viewobject.CouponDiscount;
import com.medvirumal.ecomstore.viewobject.common.Resource;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class CouponDiscountViewModel extends PSViewModel {

    private final LiveData<Resource<CouponDiscount>> couponDiscountData;
    private final MutableLiveData<CouponDiscountViewModel.TmpDataHolder> couponDiscountObj = new MutableLiveData<>();

    @Inject
    CouponDiscountViewModel(CouponDiscountRepository repository) {

        couponDiscountData = Transformations.switchMap(couponDiscountObj, obj -> {

            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.getCouponDiscount(obj.code,CheckoutActivity.user_id);
        });
    }

    public void setCouponDiscountObj(String code) {
        TmpDataHolder tmpDataHolder = new TmpDataHolder();
        tmpDataHolder.code = code;

        couponDiscountObj.setValue(tmpDataHolder);
    }

    public LiveData<Resource<CouponDiscount>> getCouponDiscountData() {
        return couponDiscountData;
    }


    class TmpDataHolder {

        public String code = "";
        public String shopId = "";
    }

}
