package com.medvirumal.ecomstore.ui.gallery;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.preference.PreferenceManager;

import com.medvirumal.ecomstore.Config;
import com.medvirumal.ecomstore.R;
import com.medvirumal.ecomstore.databinding.ActivityGalleryBinding;
import com.medvirumal.ecomstore.ui.common.PSAppCompactActivity;
import com.medvirumal.ecomstore.utils.Constants;
import com.medvirumal.ecomstore.utils.MyContextWrapper;

public class GalleryActivity extends PSAppCompactActivity {


    //region Override Methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityGalleryBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_gallery);

        // Init all UI
        initUI(binding);

    }

    @Override
    protected void attachBaseContext(Context newBase) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(newBase);
        String CURRENT_LANG_CODE = preferences.getString(Constants.LANGUAGE_CODE, Config.DEFAULT_LANGUAGE);
        String CURRENT_LANG_COUNTRY_CODE = preferences.getString(Constants.LANGUAGE_COUNTRY_CODE, Config.DEFAULT_LANGUAGE_COUNTRY_CODE);

        super.attachBaseContext(MyContextWrapper.wrap(newBase, CURRENT_LANG_CODE, CURRENT_LANG_COUNTRY_CODE, true));
    }
    //endregion


    //region Private Methods

    private void initUI(ActivityGalleryBinding binding) {

        // Toolbar
        initToolbar(binding.toolbar, getResources().getString(R.string.gallery__title));

        // setup Fragment
        setupFragment(new GalleryFragment());
        // Or you can call like this
        //setupFragment(new NewsListFragment(), R.id.content_frame);

    }

    //endregion


}