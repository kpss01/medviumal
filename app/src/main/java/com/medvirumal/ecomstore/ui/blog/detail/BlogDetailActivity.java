package com.medvirumal.ecomstore.ui.blog.detail;

import android.os.Bundle;

import com.medvirumal.ecomstore.R;
import com.medvirumal.ecomstore.databinding.ActivityBlogDetailBinding;
import com.medvirumal.ecomstore.ui.common.PSAppCompactActivity;

import androidx.databinding.DataBindingUtil;

public class BlogDetailActivity extends PSAppCompactActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityBlogDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_blog_detail);

        initUI(binding);

    }

    private void initUI(ActivityBlogDetailBinding binding) {

        // Toolbar
        initToolbar(binding.toolbar, getResources().getString(R.string.blog_detail__title));

        // setup Fragment
        setupFragment(new BlogDetailFragment());

    }
}
