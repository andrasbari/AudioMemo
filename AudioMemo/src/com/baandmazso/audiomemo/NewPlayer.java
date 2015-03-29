package com.baandmazso.audiomemo;


import com.j256.ormlite.field.DatabaseField;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class NewPlayer extends Activity {
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_player);
		
		EditText  etNameValue = (EditText) findViewById(R.id.nameValue);
		Spinner  spGenderValue = (Spinner) findViewById(R.id.genderValue);
		EditText  etbirthYearValue = (EditText) findViewById(R.id.birthYearValue);
		Button btnSave = (Button) findViewById(R.id.save);
		Button btnCancel = (Button) findViewById(R.id.cancel);
		
		btnSave.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
	}

	
}
