package com.baandmazso.audiomemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends Activity {

	//Splash screen eddig látható, jelenleg 2 sec
	private static int SPLASH_TIME_OUT = 2000;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);
		
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				/*Intent i = new Intent(SplashScreen.this,MainActivity.class);
				i.putExtra("Showed", true);
				startActivity(i);*/
				
				finish();
				
			}
		}, SPLASH_TIME_OUT);
		
	}

}
