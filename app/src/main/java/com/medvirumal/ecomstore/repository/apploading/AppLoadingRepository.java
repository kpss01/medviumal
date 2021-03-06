package com.medvirumal.ecomstore.repository.apploading;

import com.medvirumal.ecomstore.AppExecutors;
import com.medvirumal.ecomstore.Config;
import com.medvirumal.ecomstore.api.ApiResponse;
import com.medvirumal.ecomstore.api.PSApiService;
import com.medvirumal.ecomstore.db.PSCoreDb;
import com.medvirumal.ecomstore.repository.common.PSRepository;
import com.medvirumal.ecomstore.utils.Constants;
import com.medvirumal.ecomstore.utils.Utils;
import com.medvirumal.ecomstore.viewobject.DeletedObject;
import com.medvirumal.ecomstore.viewobject.PSAppInfo;
import com.medvirumal.ecomstore.viewobject.common.Resource;

import java.io.IOException;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Response;

public class AppLoadingRepository extends PSRepository {

    @Inject
    AppLoadingRepository(PSApiService psApiService, AppExecutors appExecutors, PSCoreDb db) {
        super(psApiService, appExecutors, db);
    }

    public LiveData<Resource<PSAppInfo>> deleteTheSpecificObjects(String startDate, String endDate) {

        final MutableLiveData<Resource<PSAppInfo>> statusLiveData = new MutableLiveData<>();

        appExecutors.networkIO().execute(() -> {

            Response<PSAppInfo> response;

            try {
                response = psApiService.getDeletedHistory(Config.API_KEY, startDate, endDate).execute();

                ApiResponse<PSAppInfo> apiResponse = new ApiResponse<>(response);

                if (apiResponse.isSuccessful()) {

                    try {
                        db.beginTransaction();

                        if (apiResponse.body != null) {

                            if (apiResponse.body.deletedObjects.size() > 0) {
                                for (DeletedObject deletedObject: apiResponse.body.deletedObjects)
                                {
                                    switch (deletedObject.typeName) {
                                        case Constants.APPINFO_NAME_SHOP:
                                            db.shopDao().deleteShopById(deletedObject.id);

                                            break;
                                        case Constants.APPINFO_NAME_PRODUCT:
                                            db.productDao().deleteProductById(deletedObject.id);
                                            db.historyDao().deleteHistoryProductById(deletedObject.id);

                                            break;
                                        case Constants.APPINFO_NAME_CATEGORY:
                                            db.categoryDao().deleteCategoryById(deletedObject.id);
                                            break;
                                    }
                                }
                            }
                        }

                        db.setTransactionSuccessful();
                    } catch (NullPointerException ne) {
                        Utils.psErrorLog("Null Pointer Exception : ", ne);
                    } catch (Exception e) {
                        Utils.psErrorLog("Exception : ", e);
                    } finally {
                        db.endTransaction();
                    }

                    statusLiveData.postValue(Resource.success(apiResponse.body));

                } else {
                    statusLiveData.postValue(Resource.error(apiResponse.errorMessage, null));
                }

            } catch (IOException e) {
                statusLiveData.postValue(Resource.error(e.getMessage(), null));
            }

        });

        return statusLiveData;
    }
}
