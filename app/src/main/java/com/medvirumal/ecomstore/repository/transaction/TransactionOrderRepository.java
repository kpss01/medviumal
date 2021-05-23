package com.medvirumal.ecomstore.repository.transaction;

import com.medvirumal.ecomstore.AppExecutors;
import com.medvirumal.ecomstore.Config;
import com.medvirumal.ecomstore.api.ApiResponse;
import com.medvirumal.ecomstore.api.PSApiService;
import com.medvirumal.ecomstore.db.PSCoreDb;
import com.medvirumal.ecomstore.db.TransactionOrderDao;
import com.medvirumal.ecomstore.repository.common.NetworkBoundResource;
import com.medvirumal.ecomstore.repository.common.PSRepository;
import com.medvirumal.ecomstore.utils.Utils;
import com.medvirumal.ecomstore.viewobject.TransactionDetail;
import com.medvirumal.ecomstore.viewobject.common.Resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

@Singleton
public class TransactionOrderRepository extends PSRepository {

    //region variable
    private final TransactionOrderDao transactionOrderDao;
    //end region

    //region constructor
    @Inject
    TransactionOrderRepository(PSApiService psApiService, AppExecutors appExecutors, PSCoreDb db, TransactionOrderDao transactionOrderDao) {
        super(psApiService, appExecutors, db);
        this.transactionOrderDao = transactionOrderDao;
    }
    //end constructor

    //region start get transaction order list
    public LiveData<Resource<List<TransactionDetail>>> getTransactionOrderList(String apiKey, String transactionHeaderId, String offset) {

        return new NetworkBoundResource<List<TransactionDetail>, List<TransactionDetail>>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull List<TransactionDetail> item) {
                Utils.psLog("SaveCallResult of recent transaction order.");

                db.beginTransaction();

                try {

                    transactionOrderDao.deleteAllTransactionOrderList(transactionHeaderId);

                    transactionOrderDao.insertAllTransactionOrderList(item);

                    db.setTransactionSuccessful();

                } catch (Exception e) {
                    Utils.psErrorLog("Error in doing transaction order of recent transaction order list.", e);
                } finally {
                    db.endTransaction();
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<TransactionDetail> data) {
                return connectivity.isConnected();
            }

            @NonNull
            @Override
            protected LiveData<List<TransactionDetail>> loadFromDb() {
                Utils.psLog("Load Recent transaction From Db");
                return transactionOrderDao.getAllTransactionOrderList(transactionHeaderId);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<TransactionDetail>>> createCall() {
                return psApiService.getTransactionOrderList(apiKey, transactionHeaderId, String.valueOf(Config.TRANSACTION_ORDER_COUNT), offset);
            }
        }.asLiveData();
    }
    //region end of transaction order list

    //region start next page transaction order list
    public LiveData<Resource<Boolean>> getNextPageTransactionOrderList(String transactionHeaderId, String offset) {

        final MediatorLiveData<Resource<Boolean>> statusLiveData = new MediatorLiveData<>();
        LiveData<ApiResponse<List<TransactionDetail>>> apiResponse = psApiService.getTransactionOrderList(Config.API_KEY, transactionHeaderId, String.valueOf(Config.TRANSACTION_ORDER_COUNT), offset);

        statusLiveData.addSource(apiResponse, response -> {

            statusLiveData.removeSource(apiResponse);

            //noinspection Constant Conditions
            if (response.isSuccessful()) {

                appExecutors.diskIO().execute(() -> {


                    try {
                        db.beginTransaction();

                        if (response.body != null) {
                            db.transactionOrderDao().insertAllTransactionOrderList(response.body);
                        }

                        db.setTransactionSuccessful();
                    } catch (NullPointerException ne) {
                        Utils.psErrorLog("Null Pointer Exception : ", ne);
                    } catch (Exception e) {
                        Utils.psErrorLog("Exception : ", e);
                    } finally {
                        db.endTransaction();
                    }

                    statusLiveData.postValue(Resource.success(true));

                });
            } else {
                statusLiveData.postValue(Resource.error(response.errorMessage, false));
            }

        });

        return statusLiveData;
    }
    //region end of next page transaction order list
}
