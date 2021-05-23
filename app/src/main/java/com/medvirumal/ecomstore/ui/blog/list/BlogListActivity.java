package com.medvirumal.ecomstore.ui.blog.list;

import android.os.Bundle;

import com.medvirumal.ecomstore.R;
import com.medvirumal.ecomstore.databinding.ActivityBlogListBinding;
import com.medvirumal.ecomstore.ui.common.PSAppCompactActivity;

import androidx.databinding.DataBindingUtil;

public class BlogListActivity extends PSAppCompactActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityBlogListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_blog_list);

        initUI(binding);

    }

    private void initUI(ActivityBlogListBinding binding) {

        // Toolbar
        initToolbar(binding.toolbar, getResources().getString(R.string.blog_list__title));

        // setup Fragment

        setupFragment(new BlogListFragment());

    }
}
