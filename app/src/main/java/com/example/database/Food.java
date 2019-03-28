package com.example.database;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class Food extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{

    FirebaseDatabase database;
    Button button;
    TextView textView;
    TextView tv;
    Calendar mCurrentDate;
    int day, month, year;
    RadioGroup rg1;
    RadioButton rb1;
    RadioGroup rg2;
    RadioButton rb2;
    DatabaseReference mRef;
    TextView tvPlace;
    String address;
    int PLACE_PICKER_REQUEST=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_food );
        button = (Button) findViewById( R.id.bt2 );
        textView=(TextView)findViewById(R.id.textView16);

        tvPlace=(TextView) findViewById(R.id.button);

        rg1=(RadioGroup)findViewById(R.id.radioGroup);
        rg2=(RadioGroup)findViewById(R.id.radioGroup2);
        mRef = FirebaseDatabase.getInstance().getReference("users");

        tv = (TextView) findViewById( R.id.textView4 );
        mCurrentDate = Calendar.getInstance();

        day = mCurrentDate.get( Calendar.DAY_OF_MONTH );
        month = mCurrentDate.get( Calendar.MONTH );
        year = mCurrentDate.get( Calendar.YEAR );

        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent( Food.this, Main4Activity.class );
                startActivity( b );
            }
        } );

        month = month +1;
        tv.setText( day + "/" + month + "/" + year );

        tv.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatePickerDialog datePickerDialog = new DatePickerDialog( Food.this,new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        monthOfYear = monthOfYear + 1;
                        tv.setText( dayOfMonth + "/" + monthOfYear + "/" + year );
                    }

                }, year, month, day );
                datePickerDialog.show();
            }
        } );



    rg1.setOnCheckedChangeListener( new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            int selectedId = rg1.getCheckedRadioButtonId();
            rb1 = (RadioButton) findViewById(selectedId);

            switch (checkedId)
            {
                case R.id.radioButton:
                    String key="Type";
                    FirebaseDatabase.getInstance().getReference( "users" )
                    .child( FirebaseAuth.getInstance().getCurrentUser().getUid() ).push().child(key).setValue("Veg");
                    break;
                case R.id.radioButton2:
                    String keys="Type";
                    FirebaseDatabase.getInstance().getReference( "users" )
                            .child( FirebaseAuth.getInstance().getCurrentUser().getUid() ).push().child(keys).setValue( "Non-Veg" );
                    break;
                case R.id.radioButton3:
                    String keyss="Type";
                    FirebaseDatabase.getInstance().getReference( "users" )
                            .child( FirebaseAuth.getInstance().getCurrentUser().getUid() ).push().child(keyss).setValue( "Both" );
                    break;
            }
        }
    } );

        rg2.setOnCheckedChangeListener( new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int selectedId = rg2.getCheckedRadioButtonId();
                rb2 = (RadioButton) findViewById(selectedId);
                switch (checkedId)
                {
                    case R.id.radioButton7:
                        String key="Serving";
                        FirebaseDatabase.getInstance().getReference( "users" )
                                .child( FirebaseAuth.getInstance().getCurrentUser().getUid() ).push().child(key).setValue( "10-30" );
                        break;
                    case R.id.radioButton4:
                        String keys="Serving";
                        FirebaseDatabase.getInstance().getReference( "users" )
                                .child( FirebaseAuth.getInstance().getCurrentUser().getUid() ).push().child(keys).setValue( "30-50" );
                        break;
                    case R.id.radioButton5:
                        String keyss="Serving";
                        FirebaseDatabase.getInstance().getReference( "users" )
                                .child( FirebaseAuth.getInstance().getCurrentUser().getUid() ).push().child(keyss).setValue( "50-100" );
                        break;
                    case R.id.radioButton6:
                        String keysss="Serving";
                        FirebaseDatabase.getInstance().getReference( "users" )
                                .child( FirebaseAuth.getInstance().getCurrentUser().getUid() ).push().child(keysss).setValue( "100+" );
                        break;
                }
            }
        } );

        textView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker= new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(),"time picker");
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
                Place place = PlacePicker.getPlace(Food.this, data);
                address = String.format("Shri Baghubhai Mafatlal Polytechnic %s", place.getAddress());
                tvPlace.setText(address);
            }
        }
    }



}
