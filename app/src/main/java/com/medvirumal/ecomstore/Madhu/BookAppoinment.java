package com.medvirumal.ecomstore.Madhu;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.medvirumal.ecomstore.R;
import com.medvirumal.ecomstore.api.ApiClient;
import com.medvirumal.ecomstore.api.PSApiService;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;

public class BookAppoinment extends AppCompatActivity implements View.OnClickListener {

        EditText etptName , etptMobile , etptAddress , etptEmail ,
        etptDoctor , etptSpeciality ;
        TextView tvTime , tvDate, tvChoose ;
        Button btnSubmitAskdr ;
        ConnectionDetector cd ;
        PSApiService apiInterface ;
        String name = "",email = "",mobile="",address="", doctor= "",
        speciality = "" ,time="",date="";
        DatePickerDialog datepicker;
        TimePickerDialog mTimePicker;
        ProgressDialog pd ;
        String type = "",drid="";

       @Override
       protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.appinment_layout);

        apiInterface = ApiClient.getInstance().create(PSApiService.class);
        cd = new ConnectionDetector(this);
        pd = new ProgressDialog(this);
        pd.setCancelable(false);
        pd.setMessage("Please wait....");

        etptName = (EditText)findViewById(R.id.etpatient);
        etptMobile = (EditText)findViewById(R.id.etPatientMobile);
        etptAddress = (EditText)findViewById(R.id.etPatientAddress);
        etptEmail = (EditText)findViewById(R.id.etPatientEmail);
        etptDoctor = (EditText)findViewById(R.id.etDrName);
        etptSpeciality = (EditText)findViewById(R.id.etDrSpeciality);
        btnSubmitAskdr=(Button)findViewById(R.id.btnSubmitAskDoc);
        tvTime=(TextView) findViewById(R.id.etTime);
        tvDate=(TextView)findViewById(R.id.etDate);
        tvChoose=(TextView)findViewById(R.id.tvChoose);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Book Your Appoinment");

        tvDate.setOnClickListener(this);
        tvTime.setOnClickListener(this);
        tvChoose.setOnClickListener(this);
        btnSubmitAskdr.setOnClickListener(this);

        Intent in = getIntent();
        type  =  in.getStringExtra("type");
        if(type.equals("true")){
        doctor = in.getStringExtra("name");
        drid = in.getStringExtra("id");
        speciality = in.getStringExtra("speciality");

        etptSpeciality.setText(speciality);
        etptDoctor.setText(doctor);
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

      @Override
      public void onClick(View view) {
        switch (view.getId()){
        case R.id.tvChoose:
          Intent in = new Intent(BookAppoinment.this,DrAppoinment.class);
          startActivity(in);
          finish();
        break;
        case R.id.btnSubmitAskDoc:
          main();
        break;
        case R.id.etTime:
             Calendar mcurrentTime = Calendar.getInstance();
            int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
           int minute = mcurrentTime.get(Calendar.MINUTE);
          mTimePicker = new TimePickerDialog(BookAppoinment.this, new TimePickerDialog.OnTimeSetListener() {
       @Override
        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
        tvTime.setText( selectedHour + ":" + selectedMinute);
        }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
        break;
        case R.id.etDate:
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        // date picker dialog
        datepicker = new DatePickerDialog(BookAppoinment.this,
        new DatePickerDialog.OnDateSetListener() {
@Override
public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        tvDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
        }
        }, year, month, day);
        datepicker.show();
        break;
default:

        break;

        }
        }

public void main (){

        name = etptName.getText().toString();
        email = etptEmail.getText().toString();
        mobile = etptMobile.getText().toString();
        address = etptAddress.getText().toString();
        doctor = etptDoctor.getText().toString();
        speciality = etptSpeciality.getText().toString();
        time=tvTime.getText().toString();
        date=tvDate.getText().toString();

        if(name.equals("")){
        etptName.setError("Please Enter Name");
        }else if(email.equals("")){
        etptEmail.setError("Please Enter Email");
        }else if(mobile.equals("")){
        etptMobile.setError("Please Enter Mobile No");
        }else if(address.equals("")){
        etptAddress.setError("Please Enter Address");
        }else if(date.equals("")){
        Toast.makeText(BookAppoinment.this,"Please Enter Date",Toast.LENGTH_LONG).show();
        }else if(time.equals("")){
        Toast.makeText(BookAppoinment.this,"Please Enter Time",Toast.LENGTH_LONG).show();
        }else if(doctor.equals("")){
        etptDoctor.setError("Please Enter Doctor Name");
        }else if(speciality.equals("")){
        etptSpeciality.setError("Please Enter Speciality");
        }else{
        checkout(drid,name,mobile,time,date,email,address,speciality);
        }

        }


public void checkout(String drid, String name, String mobile , String time ,
                 String date ,  String email ,  String address, String spl ) {
        try{
        pd.show();
        Call<LabModel> call = apiInterface.getAppoinwmnt(drid,name,mobile,time,date,email,address,spl);
        call.enqueue(new Callback<LabModel>() {
         @Override
           public void onResponse(Call<LabModel> call, retrofit2.Response<LabModel> response) {
        LabModel resource = response.body();

        if(resource.status.equals("Success")){
        pd.dismiss();
        Toast.makeText(BookAppoinment.this, "Successfully book appoinment", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(BookAppoinment.this, "Sorry , your appoinment can not be book", Toast.LENGTH_SHORT).show();

        }
        }
       @Override
       public void onFailure(Call<LabModel> call, Throwable t) {
        Toast.makeText(BookAppoinment.this, t.getMessage(), Toast.LENGTH_SHORT).show();
        call.cancel();
        pd.dismiss();
        }
        });
        }catch (Exception e){
        pd.dismiss();
        Toast.makeText(BookAppoinment.this, e.getMessage(), Toast.LENGTH_SHORT).show();

           }

        }

        }
