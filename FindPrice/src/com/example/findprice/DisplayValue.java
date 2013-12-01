package com.example.findprice;

import com.example.findprice.R.layout;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View.OnClickListener;
import android.widget.Button; 
import android.widget.TextView;
import android.view.*;


public class DisplayValue extends Activity {

	TextView textView1;
	TextView textView2;
    
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scan_results);
		Intent myIntent= getIntent(); // gets the previously created intent
		//converting the obtained value to string.
		String firstKeyName = myIntent.getStringExtra("symbol"); // will return symbol value
		String secondKeyName= myIntent.getStringExtra("barcode"); // will return barcode number
		// printing the value on screen
		textView1 = (TextView) findViewById(R.id.textView3);
		textView1.setText(firstKeyName);
		
		textView2 = (TextView) findViewById(R.id.textView4);
		textView2.setText(secondKeyName);
		
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	

}


