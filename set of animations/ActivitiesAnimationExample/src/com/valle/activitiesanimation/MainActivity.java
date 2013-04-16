package com.valle.activitiesanimation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends Activity {

	Spinner spinnerIn;
	Spinner spinnerOut;
	Button goButton;
	
	int inSelected = 0;
	int outSelected = 0;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
		
		spinnerIn = (Spinner) findViewById(R.id.spinnerIn);
		String [] listIn = getResources().getStringArray(R.array.array_go_in);
		ArrayAdapter<String> inAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, listIn);
			inAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerIn.setAdapter(inAdapter);
		
		spinnerIn.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int pos, long arg3) {
				inSelected = pos;
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		spinnerOut = (Spinner) findViewById(R.id.spinnerOut);
		String [] listOut = getResources().getStringArray(R.array.array_go_out);
		ArrayAdapter<String> outAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, listOut);
			outAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerOut.setAdapter(outAdapter);
		
		
		spinnerOut.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int pos, long arg3) {
				outSelected = pos;
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		goButton = (Button) findViewById(R.id.goButton);
		
		goButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				goFunction();
			}
			
		});
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void goFunction()
	{
		int idIn = 0;
		int idOut = 0;
		
		switch(inSelected)
		{
			case 0:
				idIn = R.anim.push_bot_in;
			break;
			
			case 1:
				idIn = R.anim.push_bot_in_with_fade;
			break;
			
			case 2:
				idIn = R.anim.push_left_in;
			break;
			
			case 3:
				idIn = R.anim.push_left_in_with_fade;
			break;
			
			case 4:
				idIn = R.anim.push_right_in;
			break;
			
			case 5:
				idIn = R.anim.push_right_in_with_fade;
			break;
			
			case 6:
				idIn = R.anim.push_top_in;
			break;
			
			case 7:
				idIn = R.anim.push_top_in_with_fade;
			break;
			
			case 8:
				idIn = R.anim.rotate_in;
			break;			
		}
		
		
		switch(outSelected)
		{
			case 0:
				idOut = R.anim.not_move_out;
			break;
			
			case 1:
				idOut = R.anim.push_bot_out;
			break;
			
			case 2:
				idOut = R.anim.push_bot_out_with_fade;
			break;
			
			case 3:
				idOut = R.anim.push_left_out;
			break;
			
			case 4:
				idOut = R.anim.push_left_out_with_fade;
			break;
			
			case 5:
				idOut = R.anim.push_right_out;
			break;
			
			case 6:
				idOut = R.anim.push_right_out_with_fade;
			break;
			
			case 7:
				idOut = R.anim.push_top_out;
			break;
			
			case 8:
				idOut = R.anim.push_top_out_with_fade;
			break;
			
			case 9:
				idOut = R.anim.rotate_out;
			break;
		}
		
		Intent intent = new Intent(this,Hello.class);
		startActivity(intent);
		overridePendingTransition(idIn, idOut);

		
	}

}
