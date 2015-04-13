package com.baandmazso.audiomemo;

import java.sql.SQLException;

import com.baandmazso.audiomemo.model.DataManager;
import com.baandmazso.audiomemo.model.DatabaseHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private DataManager dm;

	private Button btnNewSinglePlayer;
	private Button btnSinglePlayer;
	private Button btnMultiPlayer;
	private ImageView questonMark;
	private ImageView settings;
	private TextView userName;
	
	Editor editor;
	
	//Aktuális felhasználó azonosítója
	private int userID;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnNewSinglePlayer = (Button) findViewById(R.id.btnNewSinglePlayer);
		btnSinglePlayer = (Button) findViewById(R.id.btnSinglePlayer);
		btnMultiPlayer = (Button) findViewById(R.id.btnMultiPlayer);
		questonMark = (ImageView) findViewById(R.id.imageView1);
		settings = (ImageView) findViewById(R.id.imageView2);
		userName = (TextView) findViewById(R.id.textView2);
		
		dm = DataManager.getInstance(getApplicationContext());
		
		//SplashScreen meghívása
				Intent intentSpalsh = new Intent(getApplicationContext(),SplashScreen.class);
				startActivity(intentSpalsh);
		
		int runCounter;
		
		SharedPreferences sp = dm.getSharedprefs();
		editor = sp.edit();
		
		
		
		runCounter = sp.getInt("runCounter", 0);
		//Első futás-e?
		if(runCounter==0){
				Toast.makeText(getApplicationContext(), "Első futás", Toast.LENGTH_SHORT).show();
				
				runCounter = (runCounter+1);
				editor.putInt("runCounter", runCounter);
				editor.commit();
				
				
				
				
				AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
				LayoutInflater inflater = MainActivity.this.getLayoutInflater();
				View dialogView = inflater.inflate(R.layout.first_run, null);
				dialogBuilder.setView(dialogView);

				dialogBuilder.setPositiveButton("Rendben", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent(getApplicationContext(), NewPlayer.class);
						startActivity(intent);
						dialog.dismiss();
					}
				});
				final AlertDialog dialog = dialogBuilder.create();
				dialog.show();
				
			}

		

		
		//Előzőleg beállított felhasználó adatai 
		userName.setText(sp.getString("userName", "NÉV"));
		userID = sp.getInt("userId", 0);
		
		//LocalBoradcast receiver
		
		BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
		
			
		//Felhaszn.listából kiválasztott neve és id-ja	
			@Override
			public void onReceive(Context context, Intent intent) {
				String message = intent.getStringExtra("name");
				int i = intent.getIntExtra("id", 0);
				userName.setText(message);
				userID = i;
				
				//Az átvett nevet és ID-t kimentjük sharedPref-be
				editor.putString("userName", message);
				editor.putInt("userId", userID);
				editor.commit();
				
			}
		};
		
		
		
		LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(mMessageReceiver, new IntentFilter("Játékos átadás"));
		
		
		
		
		
		

		btnNewSinglePlayer.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
				
				LayoutInflater inflater = MainActivity.this.getLayoutInflater();
				View dialogView = inflater.inflate(R.layout.szint_valaszt,null);

				// dialogView.findviewbyid..........

				
				dialogBuilder.setView(dialogView);

				// Gombok hozzáadása, onclick() létrehozása
				Button level1 = (Button) dialogView.findViewById(R.id.level1);
				Button level2 = (Button) dialogView.findViewById(R.id.level2);
				Button level3 = (Button) dialogView.findViewById(R.id.level3);
				Button level4 = (Button) dialogView.findViewById(R.id.level4);
				Button level5 = (Button) dialogView.findViewById(R.id.level5);
				Button level6 = (Button) dialogView.findViewById(R.id.level6);
				Button level7 = (Button) dialogView.findViewById(R.id.level7);
				Button level8 = (Button) dialogView.findViewById(R.id.level8);
				Button level9 = (Button) dialogView.findViewById(R.id.level9);

				dialogBuilder.setNegativeButton("Mégse", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});

				final AlertDialog dialog = dialogBuilder.create();
				
				

				level1.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(getApplicationContext(), NewSinglePlayerActivity.class);
						intent.putExtra("level", (int) 1);
						intent.putExtra("userID",userID);
						startActivity(intent);
						dialog.dismiss();
					}
				});

				level2.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(getApplicationContext(), NewSinglePlayerActivity.class);
						intent.putExtra("level", (int) 2);
						intent.putExtra("userID",userID);
						startActivity(intent);
						dialog.dismiss();
					}
				});

				level3.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(getApplicationContext(), NewSinglePlayerActivity.class);
						intent.putExtra("level", (int) 3);
						intent.putExtra("userID",userID);
						startActivity(intent);
						dialog.dismiss();
					}
				});

				level4.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(getApplicationContext(), NewSinglePlayerActivity.class);
						intent.putExtra("level", (int) 4);
						intent.putExtra("userID",userID);
						startActivity(intent);
						dialog.dismiss();
					}
				});

				level5.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(getApplicationContext(), NewSinglePlayerActivity.class);
						intent.putExtra("level", (int) 5);
						intent.putExtra("userID",userID);
						startActivity(intent);
						dialog.dismiss();
					}
				});

				level6.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(getApplicationContext(), NewSinglePlayerActivity.class);
						intent.putExtra("level", (int) 6);
						intent.putExtra("userID",userID);
						startActivity(intent);
						dialog.dismiss();
					}
				});

				level7.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(getApplicationContext(), NewSinglePlayerActivity.class);
						intent.putExtra("level", (int) 7);
						intent.putExtra("userID",userID);
						startActivity(intent);
						dialog.dismiss();
					}
				});

				level8.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(getApplicationContext(), NewSinglePlayerActivity.class);
						intent.putExtra("level", (int) 8);
						intent.putExtra("userID",userID);
						startActivity(intent);
						dialog.dismiss();
					}
				});

				level9.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(getApplicationContext(), NewSinglePlayerActivity.class);
						intent.putExtra("level", (int) 9);
						intent.putExtra("userID",userID);
						startActivity(intent);
						dialog.dismiss();
					}
				});

				dialog.show();

				// Intent intent = new Intent(getApplicationContext(), SinglePlayerActivity.class);
				// startActivity(intent);
			}
		});
		
		btnSinglePlayer.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);

				LayoutInflater inflater = MainActivity.this.getLayoutInflater();
				View dialogView = inflater.inflate(R.layout.szint_valaszt, null);

				// dialogView.findviewbyid..........

				dialogBuilder.setView(dialogView);

				// Gombok hozzáadása, onclick() létrehozása
				Button level1 = (Button) dialogView.findViewById(R.id.level1);
				Button level2 = (Button) dialogView.findViewById(R.id.level2);
				Button level3 = (Button) dialogView.findViewById(R.id.level3);
				Button level4 = (Button) dialogView.findViewById(R.id.level4);
				Button level5 = (Button) dialogView.findViewById(R.id.level5);
				Button level6 = (Button) dialogView.findViewById(R.id.level6);
				Button level7 = (Button) dialogView.findViewById(R.id.level7);
				Button level8 = (Button) dialogView.findViewById(R.id.level8);
				Button level9 = (Button) dialogView.findViewById(R.id.level9);

				dialogBuilder.setNegativeButton("Mégse", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});

				final AlertDialog dialog = dialogBuilder.create();

				level1.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(getApplicationContext(), SinglePlayerActivity.class);
						intent.putExtra("level", (int) 1);
						startActivity(intent);
						dialog.dismiss();
					}
				});

				level2.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(getApplicationContext(), SinglePlayerActivity.class);
						intent.putExtra("level", (int) 2);
						startActivity(intent);
						dialog.dismiss();
					}
				});

				level3.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(getApplicationContext(), SinglePlayerActivity.class);
						intent.putExtra("level", (int) 3);
						startActivity(intent);
						dialog.dismiss();
					}
				});

				level4.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(getApplicationContext(), SinglePlayerActivity.class);
						intent.putExtra("level", (int) 4);
						startActivity(intent);
						dialog.dismiss();
					}
				});

				level5.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(getApplicationContext(), SinglePlayerActivity.class);
						intent.putExtra("level", (int) 5);
						startActivity(intent);
						dialog.dismiss();
					}
				});

				level6.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(getApplicationContext(), SinglePlayerActivity.class);
						intent.putExtra("level", (int) 6);
						startActivity(intent);
						dialog.dismiss();
					}
				});

				level7.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(getApplicationContext(), SinglePlayerActivity.class);
						intent.putExtra("level", (int) 7);
						startActivity(intent);
						dialog.dismiss();
					}
				});

				level8.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(getApplicationContext(), SinglePlayerActivity.class);
						intent.putExtra("level", (int) 8);
						startActivity(intent);
						dialog.dismiss();
					}
				});

				level9.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(getApplicationContext(), SinglePlayerActivity.class);
						intent.putExtra("level", (int) 9);
						startActivity(intent);
						dialog.dismiss();
					}
				});

				dialog.show();

				// Intent intent = new Intent(getApplicationContext(), SinglePlayerActivity.class);
				// startActivity(intent);
			}
		});

		btnMultiPlayer.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), MultiPlayerActivity.class);
				startActivity(intent);
			}
		});

		// sugó onClick()-je
		questonMark.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
				LayoutInflater inflater = MainActivity.this.getLayoutInflater();
				View dialogView = inflater.inflate(R.layout.help, null);
				dialogBuilder.setView(dialogView);

				dialogBuilder.setNegativeButton("Mégse", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
				final AlertDialog dialog = dialogBuilder.create();
				dialog.show();
			}
		});

		// beállítások onClick()-je
		settings.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				/*AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
				LayoutInflater inflater = MainActivity.this.getLayoutInflater();
				View dialogView = inflater.inflate(R.layout.settings, null);
				dialogBuilder.setView(dialogView);

				dialogBuilder.setNegativeButton("Mégse", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
				final AlertDialog dialog = dialogBuilder.create();
				dialog.show();*/
				Intent intent = new Intent(getApplicationContext(), Settings.class);
				startActivity(intent);

			}
		});
	}

}
