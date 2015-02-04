package com.baandmazso.audiomemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button btnSinglePlayer;
	private Button btnMultiPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnSinglePlayer = (Button) findViewById(R.id.btnSinglePlayer);
		btnMultiPlayer = (Button) findViewById(R.id.btnMultiPlayer);

		btnSinglePlayer.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), SinglePlayerActivity.class);
				startActivity(intent);
			}
		});

		btnMultiPlayer.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), MultiPlayerActivity.class);
				startActivity(intent);
			}
		});
	}
}
