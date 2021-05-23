package com.medvirumal.ecomstore.viewmodel.product;

import com.medvirumal.ecomstore.Config;
import com.medvirumal.ecomstore.repository.product.ProductRepository;
import com.medvirumal.ecomstore.utils.AbsentLiveData;
import com.medvirumal.ecomstore.utils.Utils;
import com.medvirumal.ecomstore.viewmodel.common.PSViewModel;
import com.medvirumal.ecomstore.viewobject.Product;
import com.medvirumal.ecomstore.viewobject.common.Resource;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class ProductRelatedViewModel extends PSViewModel {
    //for product detail list

    private final LiveData<Resource<List<Product>>> productRelatedData;
    private MutableLiveData<ProductRelatedViewModel.TmpDataHolder> productRelatedListObj = new MutableLiveData<>();

    //region Constructor

    @Inject
    ProductRelatedViewModel(ProductRepository productRepository) {
        //  product detail List
        productRelatedData = Transformations.switchMap(productRelatedListObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            Utils.psLog("ProductRelatedViewModel.");
            return productRepository.getRelatedList(Config.API_KEY, obj.productId, obj.catId);
        });

    }

    //endregion
    //region Getter And Setter for product detail List

    public void setProductRelatedListObj(String productId, String catId) {
        if (!isLoading) {
            ProductRelatedViewModel.TmpDataHolder tmpDataHolder = new ProductRelatedViewModel.TmpDataHolder();
            tmpDataHolder.productId = productId;
            tmpDataHolder.catId = catId;
            productRelatedListObj.setValue(tmpDataHolder);

            // start loading
            setLoadingState(true);
        }
    }

    public LiveData<Resource<List<Product>>> getProductRelatedData() {
        return productRelatedData;
    }

    //endregion

    //region Holder
    class TmpDataHolder {
        public String offset = "";
        public String productId = "";
        public String catId = "";
        public String shopId = "";
        public Boolean isConnected = false;
    }
    //endregion
}
