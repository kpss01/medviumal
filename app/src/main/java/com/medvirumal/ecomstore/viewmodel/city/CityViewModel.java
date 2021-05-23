package com.medvirumal.ecomstore.viewmodel.city;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import com.medvirumal.ecomstore.Config;
import com.medvirumal.ecomstore.repository.city.CityRepository;
import com.medvirumal.ecomstore.utils.AbsentLiveData;
import com.medvirumal.ecomstore.utils.Utils;
import com.medvirumal.ecomstore.viewmodel.common.PSViewModel;
import com.medvirumal.ecomstore.viewobject.City;
import com.medvirumal.ecomstore.viewobject.common.Resource;

import java.util.List;

import javax.inject.Inject;

public class CityViewModel extends PSViewModel {
    private LiveData<Resource<List<City>>> cityListData;
    private MutableLiveData<CityViewModel.TmpDataHolder> cityListObj = new MutableLiveData<>();

    private LiveData<Resource<Boolean>> nextPageCityListData;
    private MutableLiveData<CityViewModel.TmpDataHolder> nextPageCityListObj = new MutableLiveData<>();

    public String countryId;

    @Inject
    CityViewModel(CityRepository repository) {
        Utils.psLog("Inside CityViewModel");

        cityListData = Transformations.switchMap(cityListObj, obj -> {

            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.getCityListWithShopId(Config.API_KEY, obj.shopId, obj.countryId, obj.limit, obj.offset);
        });

        nextPageCityListData = Transformations.switchMap(nextPageCityListObj, obj -> {

            if (obj == null) {
                return AbsentLiveData.create();
            }

            return repository.getNextPageCityListWithShopId(obj.shopId, obj.countryId, obj.limit, obj.offset);
        });
    }

    public void setCityListObj(String shopId, String countryId, String limit, String offset) {
        CityViewModel.TmpDataHolder tmpDataHolder = new CityViewModel.TmpDataHolder();
        tmpDataHolder.shopId = shopId;
        tmpDataHolder.countryId = countryId;
        tmpDataHolder.limit = limit;
        tmpDataHolder.offset = offset;

        cityListObj.setValue(tmpDataHolder);
    }

    public LiveData<Resource<List<City>>> getCityListData() {

        return cityListData;
    }

    public void setNextPageCityListObj(String shopId, String countryId, String limit, String offset) {
        CityViewModel.TmpDataHolder tmpDataHolder = new CityViewModel.TmpDataHolder();
        tmpDataHolder.shopId = shopId;
        tmpDataHolder.countryId = countryId;
        tmpDataHolder.limit = limit;
        tmpDataHolder.offset = offset;

        nextPageCityListObj.setValue(tmpDataHolder);
    }

    public LiveData<Resource<Boolean>> getNextPageCityListData() {

        return nextPageCityListData;
    }

    class TmpDataHolder {
        public String loginUserId = "";
        public String limit = "";
        public String offset = "";
        public String shopId = "";
        public String countryId = "";

    }
}
