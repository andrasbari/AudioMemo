package com.baandmazso.audiomemo;

import java.sql.SQLException;

import com.baandmazso.audiomemo.model.DataManager;
import com.baandmazso.audiomemo.model.DatabaseHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private DataManager dm;

	private Button btnNewSinglePlayer;
	private Button btnSinglePlayer;
	private Button btnMultiPlayer;
	private ImageView questonMark;
	private ImageView settings;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		dm = DataManager.getInstance(getApplicationContext());

		btnNewSinglePlayer = (Button) findViewById(R.id.btnNewSinglePlayer);
		btnSinglePlayer = (Button) findViewById(R.id.btnSinglePlayer);
		btnMultiPlayer = (Button) findViewById(R.id.btnMultiPlayer);
		questonMark = (ImageView) findViewById(R.id.imageView1);
		settings = (ImageView) findViewById(R.id.imageView2);
		
		//SplashScreen meghívása
		Intent intentSpalsh = new Intent(getApplicationContext(),SplashScreen.class);
		startActivity(intentSpalsh);
		
		

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
