package com.example.findprice;



import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View.OnClickListener;
import android.widget.Button; 
import android.view.*;


public class MainActivity extends Activity {

	
	Button button;
    
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		addListenerOnButton();
	}



	private void addListenerOnButton() {
		// TODO Auto-generated method stub
		final Context context = this;
		 
		button = (Button) findViewById(R.id.findprice_button);
 
		button.setOnClickListener(new OnClickListener() {
 
			public void onClick(View arg0) {
 
			    Intent intent = new Intent(context, ScanditSDKDemoSimple.class);
                            startActivity(intent);   
 
			}
 
		});
 
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	

}
