package com.medvirumal.ecomstore.ui.product;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.medvirumal.ecomstore.Madhu.ConnectionDetector;
import com.medvirumal.ecomstore.R;
import com.squareup.picasso.Picasso;

public class FullImageActivity extends AppCompatActivity {

    ConnectionDetector cd;
    NewZoomableImageView imageView ;
    String image  = "",catid="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullimageview);

        cd = new ConnectionDetector(FullImageActivity.this);
        imageView = (NewZoomableImageView)findViewById(R.id.imgFullView);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Full Product View");

        Intent in = getIntent();
        image  =   in.getStringExtra("image").replaceAll(" ", "%20");
        catid  =   in.getStringExtra("catid");

        Toast.makeText(FullImageActivity.this,catid,Toast.LENGTH_LONG).show();

        Picasso.with(FullImageActivity.this).load(image).into(imageView);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
