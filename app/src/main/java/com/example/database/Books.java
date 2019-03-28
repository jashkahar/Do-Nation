package com.example.database;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Books extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{

    Button button;
    TextView tvplace2;
    TextView textView2;
    TextView textView;
    Calendar mCurrentDate;
    int day, month, year;
    String address;
    int PLACE_PICKER_REQUEST=1;
    CheckBox mFirstCheckBox;
    CheckBox mSecondCheckBox;
    CheckBox mThirdCheckBox;
    CheckBox mFourthCheckBox;
    CheckBox mFifthCheckBox;
    CheckBox mSixthCheckBox;
    DatabaseReference mRef;
    TextView tv;
    EditText editText;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_books );
        tvplace2=(TextView) findViewById(R.id.button);
        textView=(TextView)findViewById(R.id.textView6);
        textView2=(TextView) findViewById(R.id.textview10);
        editText=(EditText)findViewById(R.id.et1);

        tv = (TextView) findViewById( R.id.textView4 );
        mCurrentDate = Calendar.getInstance();

        day = mCurrentDate.get( Calendar.DAY_OF_MONTH );
        month = mCurrentDate.get( Calendar.MONTH );
        year = mCurrentDate.get( Calendar.YEAR );

        mFirstCheckBox = findViewById(R.id.cb1);
        mSecondCheckBox = findViewById(R.id.cb2);
        mThirdCheckBox = findViewById(R.id.cb3);
        mFourthCheckBox = findViewById(R.id.cb4);
        mFifthCheckBox = findViewById(R.id.cb5);
        mSixthCheckBox = findViewById(R.id.cb6);

        mRef = FirebaseDatabase.getInstance().getReference("users");

        button=(Button)findViewById(R.id.bt1);

        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check();
                Intent intent = new Intent( Books.this, Main4Activity.class );
                startActivity( intent );
            }});


        textView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker= new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(),"time picker");
            }
        } );
        month = month +1;
        textView2.setText( day + "/" + month + "/" + year );

        textView2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatePickerDialog datePickerDialog = new DatePickerDialog( Books.this,new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        monthOfYear = monthOfYear + 1;
                        textView2.setText( dayOfMonth + "/" + monthOfYear + "/" + year );
                    }

                }, year, month, day );
                datePickerDialog.show();

            }
        } );

    }
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String time;
        if(hourOfDay>12)
        {
            hourOfDay -=12;
            time="PM";
        }
        else if(hourOfDay == 0)
        {
            hourOfDay += 12;
            time="AM";
        }
        else if(hourOfDay == 12)
        {
            time="PM";
        }
        else
        {
            time="AM";

        }        textView.setText(hourOfDay + " : " +minute+" "+time);
    }

    public void goPlacePicker(View view){
        int PLACE_PICKER_REQUEST=1;
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try
        {
            startActivityForResult(builder.build(this),PLACE_PICKER_REQUEST);

        }catch (GooglePlayServicesRepairableException e){
            e.printStackTrace();
        }
        catch (GooglePlayServicesNotAvailableException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        if(requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(Books.this, data);
                address = String.format("Shri Baghubhai Mafatlal Polytechnic %s", place.getAddress());
                tvplace2.setText(address);
            }
        }
    }

    private void check(){
        if(mFirstCheckBox.isChecked()){
            String key="Age Group";
            FirebaseDatabase.getInstance().getReference( "users" )
                    .child( FirebaseAuth.getInstance().getCurrentUser().getUid() ).push().child(key).setValue("Pre-School");
        }
        if(mSecondCheckBox.isChecked()){
            String keys="Age Group";
            FirebaseDatabase.getInstance().getReference( "users" )
                    .child( FirebaseAuth.getInstance().getCurrentUser().getUid() ).push().child(keys).setValue("Primary");
        }
        if(mThirdCheckBox.isChecked()){
            String keyss="Age Group";
            FirebaseDatabase.getInstance().getReference( "users" )
                    .child( FirebaseAuth.getInstance().getCurrentUser().getUid() ).push().child(keyss).setValue("Secondary");
        }
        if(mFourthCheckBox.isChecked()){
            String keysss="Age Group";
            FirebaseDatabase.getInstance().getReference( "users" )
                    .child( FirebaseAuth.getInstance().getCurrentUser().getUid() ).push().child(keysss).setValue("Bachelors");
        }
        if(mFifthCheckBox.isChecked()){
            String keyssss="Age Group";
            FirebaseDatabase.getInstance().getReference( "users" )
                    .child( FirebaseAuth.getInstance().getCurrentUser().getUid() ).push().child(keyssss).setValue("Masters");
        }
        if(mSixthCheckBox.isChecked()){
            String keysssss="Age Group";
            FirebaseDatabase.getInstance().getReference( "users" )
                    .child( FirebaseAuth.getInstance().getCurrentUser().getUid() ).push().child(keysssss).setValue("Other");
        }
    }
}
