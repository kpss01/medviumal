package com.medvirumal.ecomstore.ui.collection;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.medvirumal.ecomstore.Config;
import com.medvirumal.ecomstore.R;
import com.medvirumal.ecomstore.databinding.ActivityCollectionBinding;
import com.medvirumal.ecomstore.ui.common.PSAppCompactActivity;
import com.medvirumal.ecomstore.utils.Constants;
import com.medvirumal.ecomstore.utils.MyContextWrapper;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

public class CollectionActivity extends PSAppCompactActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityCollectionBinding activityFilteringBinding = DataBindingUtil.setContentView(this, R.layout.activity_collection);

        initUI(activityFilteringBinding);

    }

    @Override
    protected void attachBaseContext(Context newBase) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(newBase);
        String CURRENT_LANG_CODE = preferences.getString(Constants.LANGUAGE_CODE, Config.DEFAULT_LANGUAGE);
        String CURRENT_LANG_COUNTRY_CODE = preferences.getString(Constants.LANGUAGE_COUNTRY_CODE, Config.DEFAULT_LANGUAGE_COUNTRY_CODE);

        super.attachBaseContext(MyContextWrapper.wrap(newBase, CURRENT_LANG_CODE, CURRENT_LANG_COUNTRY_CODE, true));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void initUI(ActivityCollectionBinding binding) {

        String title = getIntent().getStringExtra(Constants.COLLECTION_NAME);

        if (title != null) {
            initToolbar(binding.toolbar, title);
        } else {
            initToolbar(binding.toolbar, getResources().getString(R.string.product_list_title));
        }

        setupFragment(new CollectionFragment());

    }

}
