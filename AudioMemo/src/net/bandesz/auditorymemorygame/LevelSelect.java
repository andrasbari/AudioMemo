package net.bandesz.auditorymemorygame;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class LevelSelect extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_level_select);
		
		Button level1 = (Button) findViewById(R.id.level1);
		Button level2 = (Button) findViewById(R.id.level2);
		Button level3 = (Button) findViewById(R.id.level3);
		Button level4 = (Button) findViewById(R.id.level4);
		Button level5 = (Button) findViewById(R.id.level5);
		Button level6 = (Button) findViewById(R.id.level6);
		Button level7 = (Button) findViewById(R.id.level7);
		Button level8 = (Button) findViewById(R.id.level8);
		Button level9 = (Button) findViewById(R.id.level9);
		
		
		level1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), NewSinglePlayerActivity.class);
				intent.putExtra("level", (int) 1);
				intent.putExtra("userID",MainActivity.getUserID());
				startActivity(intent);
				finish();
				
			}
		});
		
		level2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), NewSinglePlayerActivity.class);
				intent.putExtra("level", (int) 2);
				intent.putExtra("userID",MainActivity.getUserID());
				startActivity(intent);
				finish();				
			}
		});
		
		level3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), NewSinglePlayerActivity.class);
				intent.putExtra("level", (int) 3);
				intent.putExtra("userID",MainActivity.getUserID());
				startActivity(intent);
				finish();				
			}
		});
		
		level4.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), NewSinglePlayerActivity.class);
				intent.putExtra("level", (int) 4);
				intent.putExtra("userID",MainActivity.getUserID());
				startActivity(intent);
				finish();				
			}
		});
		
		level5.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), NewSinglePlayerActivity.class);
				intent.putExtra("level", (int) 5);
				intent.putExtra("userID",MainActivity.getUserID());
				startActivity(intent);
				finish();				
			}
		});
		
		level6.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), NewSinglePlayerActivity.class);
				intent.putExtra("level", (int) 6);
				intent.putExtra("userID",MainActivity.getUserID());
				startActivity(intent);
				finish();				
			}
		});
		
		level7.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), NewSinglePlayerActivity.class);
				intent.putExtra("level", (int) 7);
				intent.putExtra("userID",MainActivity.getUserID());
				startActivity(intent);
				finish();				
			}
		});
		
		level8.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), NewSinglePlayerActivity.class);
				intent.putExtra("level", (int) 8);
				intent.putExtra("userID",MainActivity.getUserID());
				startActivity(intent);
				finish();				
			}
		});
		
		level9.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), NewSinglePlayerActivity.class);
				intent.putExtra("level", (int) 9);
				intent.putExtra("userID",MainActivity.getUserID());
				startActivity(intent);
				finish();				
			}
		});
	}

	
	
}
