package com.medvirumal.ecomstore.viewmodel.aboutus;

import com.medvirumal.ecomstore.Config;
import com.medvirumal.ecomstore.repository.aboutus.AboutUsRepository;
import com.medvirumal.ecomstore.utils.AbsentLiveData;
import com.medvirumal.ecomstore.viewmodel.common.PSViewModel;
import com.medvirumal.ecomstore.viewobject.AboutUs;
import com.medvirumal.ecomstore.viewobject.common.Resource;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

/**
 * Created by Panacea-Soft on 12/30/17.
 * Contact Email : teamps.is.cool@gmail.com
 */

public class AboutUsViewModel extends PSViewModel {


    //region Variables

    // Get AboutUs
    private final LiveData<Resource<AboutUs>> aboutUsLiveData;
    private MutableLiveData<String> aboutUsObj = new MutableLiveData<>();

    public String aboutId;
    //endregion


    //region Constructors

    @Inject
    AboutUsViewModel(AboutUsRepository repository) {

        aboutUsLiveData = Transformations.switchMap(aboutUsObj, newsId -> {
            if (newsId.isEmpty()) {
                return AbsentLiveData.create();
            }
            return repository.getAboutUs(Config.API_KEY);
        });

    }

    //endregion


    //region Public Methods

    public void setAboutUsObj(String aboutUsObj) {
        this.aboutUsObj.setValue(aboutUsObj);
    }

    public LiveData<Resource<AboutUs>> getAboutUsData() {
        return aboutUsLiveData;
    }

    //endregion

}
