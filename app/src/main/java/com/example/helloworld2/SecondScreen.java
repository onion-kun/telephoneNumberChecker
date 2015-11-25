package com.example.helloworld2;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;


public class SecondScreen extends Activity {

    private static final int demoValue = 250; //just for the demo

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondscreen2);

        //get back the text passed through the intent
        String phoneNumberToCheck = getIntent().getStringExtra("theString").toString();
        TextView numberEntered = (TextView) this.findViewById(R.id.enterNumberTextValue);
        numberEntered.setText(phoneNumberToCheck);

        int countryCode = 1;
        String regionCode = null;
        Boolean validNumber;

        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        try {
            // phone must begin with '+'
            PhoneNumber numberProto = phoneUtil.parse(phoneNumberToCheck, "");
            countryCode = numberProto.getCountryCode();
            regionCode = phoneUtil.getRegionCodeForCountryCode(countryCode);
            validNumber = phoneUtil.isValidNumber(numberProto);

            if (validNumber) {
                TextView theTextView = (TextView) this.findViewById(R.id.validNumberText);
                theTextView.setText(R.string.number_valid);
                theTextView.setTextColor(getResources().getColor(R.color.darkgreen));
            }

        } catch (NumberParseException e) {
            //
            System.err.println("NumberParseException was thrown: " + e.toString());
        }


        //show toast as well for demo
        //Toast.makeText(this, theText, Toast.LENGTH_LONG).show();
        if (countryCode != 1) {
            TextView theTextView = (TextView) this.findViewById(R.id.regionCodeText);
            theTextView.setText(regionCode);
        }
    }

    public void onClick(View view) {
        //called from button click

        //String enteredPhoneNumber = getIntent().getStringExtra("theString").toString();

        Intent myIntent = new Intent("com.example.helloworld2.MainActivity1");

        //name-pair value
        //myIntent.putExtra("theString", enteredPhoneNumber);

        startActivity(myIntent);

        //destroy the activity - though could really use the standard android back
        //setResult(RESULT_OK);
        //finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        //outState.putInt("testKey", sbValue); //example key

        //show toast here as demo
        //String theText = "onSaveInstanceState Called. Here you would use the Bundle Object outState.putSerializable(KEYVALUE, OBJECT NAME) to pass objects  or putInt / putString. Pass on: " + demoValue + "!";
       // Toast.makeText(this, theText, Toast.LENGTH_LONG).show();
        //super.onSaveInstanceState(outState);


    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

    //

    }

}
