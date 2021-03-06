package com.medvirumal.ecomstore.Madhu;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.medvirumal.ecomstore.R;
import com.medvirumal.ecomstore.api.ApiClient;
import com.medvirumal.ecomstore.api.PSApiService;

import retrofit2.Call;
import retrofit2.Callback;

public class DrAppoinment extends AppCompatActivity {

    RecyclerView recyclerView ;
    PSApiService apiInterface ;
    ConnectionDetector cd ;
    DrApponimetAdapter labtestAdapter ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.labtest_layout);

        apiInterface = ApiClient.getInstance().create(PSApiService.class);
        cd = new ConnectionDetector(this);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Doctor's List");

        recyclerView=(RecyclerView)findViewById(R.id.recycleview);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DrAppoinment.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        retro();
    }

    private void retro() {
        try {
            if (!cd.isConnectingToInternet()) {
                Toast.makeText(DrAppoinment.this, "Internet connection not available...", Toast.LENGTH_SHORT).show();
            } else {
                Call<LabModel> call = apiInterface.getDrList();
                call.enqueue(new Callback<LabModel>() {
                    @Override
                    public void onResponse(Call<LabModel> call, retrofit2.Response<LabModel> response) {
                        LabModel pro = response.body();

                        if (pro.testlist.size() == 0) {
                            Toast.makeText(DrAppoinment.this,"No Doctor available",Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(DrAppoinment.this,String.valueOf(pro.testlist.size()),Toast.LENGTH_LONG).show();
                            labtestAdapter = new DrApponimetAdapter(pro.testlist,DrAppoinment.this);
                            recyclerView.setAdapter(labtestAdapter);
                        }
                    }
                    @Override
                    public void onFailure(Call<LabModel> call, Throwable t) {
                        call.cancel();

                    }
                });
            }
        }catch (Exception e){
            Toast.makeText(DrAppoinment.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }


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
