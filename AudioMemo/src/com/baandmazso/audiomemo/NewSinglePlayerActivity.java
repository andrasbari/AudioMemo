package com.baandmazso.audiomemo;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import com.baandmazso.audiomemo.model.Card;
import com.baandmazso.audiomemo.model.DataManager;
import com.baandmazso.audiomemo.model.DatabaseHelper;
import com.baandmazso.audiomemo.model.Game;
import com.baandmazso.audiomemo.model.Pair;
import com.baandmazso.audiomemo.model.Table;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class NewSinglePlayerActivity extends Activity {
	private MediaPlayer mp;
	private DataManager dm;
	
	protected static final String TAG = null;

	private int level;
	private int selected;

	private Game game;

	int col_count = 0;
	int row_count = 0;
	
	int player1_click1_row = -1;
	int player1_click1_col = -1;
	int player1_click2_row = -1;
	int player1_click2_col = -1;
	int player1_prev_click1_row;
	int player1_prev_click1_col;
	int player1_prev_click2_row;
	int player1_prev_click2_col;

	int player2_click1_row = -1;
	int player2_click1_col = -1;
	int player2_click2_row = -1;
	int player2_click2_col = -1;
	
	int flippedCards=0;
	
	long startTime = 0L;
	long timeInMillies = 0L;
	long timeSwap = 0L;
	long finalTime = 0L;
	
	private TextView tvPlayer1Time;
	private Handler myTimeHandler = new Handler();
	private boolean isTimerStarted = false;

	private ArrayList<ArrayList<RelativeLayout>> tableLayout = new ArrayList<ArrayList<RelativeLayout>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.table);
		
		dm = DataManager.getInstance(getApplicationContext());

		
		final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);

		level = getIntent().getIntExtra("level", (int) 1);
		
		try {
			game = new Game(level, 1);
			dm.saveGame(game);
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		TextView tvPlayer1Name = (TextView) findViewById(R.id.tvPlayer1Name);
		TextView tvPlayer1Point = (TextView) findViewById(R.id.tvPlayer1Point);
		tvPlayer1Time = (TextView) findViewById(R.id.tvPlayer1Time);
		TextView tvPlayer2Name = (TextView) findViewById(R.id.tvPlayer2Name);
		TextView tvPlayer2Point = (TextView) findViewById(R.id.tvPlayer2Point);
		TextView tvPlayer2Time = (TextView) findViewById(R.id.tvPlayer2Time);
		ImageView ivPlayPause = (ImageView) findViewById(R.id.ivPlayPause);
		
		tvPlayer2Name.setVisibility(View.GONE);
		tvPlayer2Point.setVisibility(View.GONE);
		tvPlayer2Time.setVisibility(View.GONE);

		RelativeLayout card_1_1 = (RelativeLayout) findViewById(R.id.card_1_1);
		RelativeLayout card_2_1 = (RelativeLayout) findViewById(R.id.card_2_1);
		RelativeLayout card_3_1 = (RelativeLayout) findViewById(R.id.card_3_1);
		RelativeLayout card_4_1 = (RelativeLayout) findViewById(R.id.card_4_1);
		RelativeLayout card_5_1 = (RelativeLayout) findViewById(R.id.card_5_1);
		RelativeLayout card_6_1 = (RelativeLayout) findViewById(R.id.card_6_1);
		RelativeLayout card_7_1 = (RelativeLayout) findViewById(R.id.card_7_1);
		RelativeLayout card_8_1 = (RelativeLayout) findViewById(R.id.card_8_1);
		ArrayList<RelativeLayout> tmpSet1 = new ArrayList<RelativeLayout>();
		tmpSet1.add(card_1_1);
		tmpSet1.add(card_2_1);
		tmpSet1.add(card_3_1);
		tmpSet1.add(card_4_1);
		tmpSet1.add(card_5_1);
		tmpSet1.add(card_6_1);
		tmpSet1.add(card_7_1);
		tmpSet1.add(card_8_1);
		tableLayout.add(tmpSet1);

		RelativeLayout card_1_2 = (RelativeLayout) findViewById(R.id.card_1_2);
		RelativeLayout card_2_2 = (RelativeLayout) findViewById(R.id.card_2_2);
		RelativeLayout card_3_2 = (RelativeLayout) findViewById(R.id.card_3_2);
		RelativeLayout card_4_2 = (RelativeLayout) findViewById(R.id.card_4_2);
		RelativeLayout card_5_2 = (RelativeLayout) findViewById(R.id.card_5_2);
		RelativeLayout card_6_2 = (RelativeLayout) findViewById(R.id.card_6_2);
		RelativeLayout card_7_2 = (RelativeLayout) findViewById(R.id.card_7_2);
		RelativeLayout card_8_2 = (RelativeLayout) findViewById(R.id.card_8_2);
		ArrayList<RelativeLayout> tmpSet2 = new ArrayList<RelativeLayout>();
		tmpSet2.add(card_1_2);
		tmpSet2.add(card_2_2);
		tmpSet2.add(card_3_2);
		tmpSet2.add(card_4_2);
		tmpSet2.add(card_5_2);
		tmpSet2.add(card_6_2);
		tmpSet2.add(card_7_2);
		tmpSet2.add(card_8_2);
		tableLayout.add(tmpSet2);

		RelativeLayout card_1_3 = (RelativeLayout) findViewById(R.id.card_1_3);
		RelativeLayout card_2_3 = (RelativeLayout) findViewById(R.id.card_2_3);
		RelativeLayout card_3_3 = (RelativeLayout) findViewById(R.id.card_3_3);
		RelativeLayout card_4_3 = (RelativeLayout) findViewById(R.id.card_4_3);
		RelativeLayout card_5_3 = (RelativeLayout) findViewById(R.id.card_5_3);
		RelativeLayout card_6_3 = (RelativeLayout) findViewById(R.id.card_6_3);
		RelativeLayout card_7_3 = (RelativeLayout) findViewById(R.id.card_7_3);
		RelativeLayout card_8_3 = (RelativeLayout) findViewById(R.id.card_8_3);
		ArrayList<RelativeLayout> tmpSet3 = new ArrayList<RelativeLayout>();
		tmpSet3.add(card_1_3);
		tmpSet3.add(card_2_3);
		tmpSet3.add(card_3_3);
		tmpSet3.add(card_4_3);
		tmpSet3.add(card_5_3);
		tmpSet3.add(card_6_3);
		tmpSet3.add(card_7_3);
		tmpSet3.add(card_8_3);
		tableLayout.add(tmpSet3);

		RelativeLayout card_1_4 = (RelativeLayout) findViewById(R.id.card_1_4);
		RelativeLayout card_2_4 = (RelativeLayout) findViewById(R.id.card_2_4);
		RelativeLayout card_3_4 = (RelativeLayout) findViewById(R.id.card_3_4);
		RelativeLayout card_4_4 = (RelativeLayout) findViewById(R.id.card_4_4);
		RelativeLayout card_5_4 = (RelativeLayout) findViewById(R.id.card_5_4);
		RelativeLayout card_6_4 = (RelativeLayout) findViewById(R.id.card_6_4);
		RelativeLayout card_7_4 = (RelativeLayout) findViewById(R.id.card_7_4);
		RelativeLayout card_8_4 = (RelativeLayout) findViewById(R.id.card_8_4);
		ArrayList<RelativeLayout> tmpSet4 = new ArrayList<RelativeLayout>();
		tmpSet4.add(card_1_4);
		tmpSet4.add(card_2_4);
		tmpSet4.add(card_3_4);
		tmpSet4.add(card_4_4);
		tmpSet4.add(card_5_4);
		tmpSet4.add(card_6_4);
		tmpSet4.add(card_7_4);
		tmpSet4.add(card_8_4);
		tableLayout.add(tmpSet4);

		RelativeLayout card_1_5 = (RelativeLayout) findViewById(R.id.card_1_5);
		RelativeLayout card_2_5 = (RelativeLayout) findViewById(R.id.card_2_5);
		RelativeLayout card_3_5 = (RelativeLayout) findViewById(R.id.card_3_5);
		RelativeLayout card_4_5 = (RelativeLayout) findViewById(R.id.card_4_5);
		RelativeLayout card_5_5 = (RelativeLayout) findViewById(R.id.card_5_5);
		RelativeLayout card_6_5 = (RelativeLayout) findViewById(R.id.card_6_5);
		RelativeLayout card_7_5 = (RelativeLayout) findViewById(R.id.card_7_5);
		RelativeLayout card_8_5 = (RelativeLayout) findViewById(R.id.card_8_5);
		ArrayList<RelativeLayout> tmpSet5 = new ArrayList<RelativeLayout>();
		tmpSet5.add(card_1_5);
		tmpSet5.add(card_2_5);
		tmpSet5.add(card_3_5);
		tmpSet5.add(card_4_5);
		tmpSet5.add(card_5_5);
		tmpSet5.add(card_6_5);
		tmpSet5.add(card_7_5);
		tmpSet5.add(card_8_5);
		tableLayout.add(tmpSet5);

		RelativeLayout card_1_6 = (RelativeLayout) findViewById(R.id.card_1_6);
		RelativeLayout card_2_6 = (RelativeLayout) findViewById(R.id.card_2_6);
		RelativeLayout card_3_6 = (RelativeLayout) findViewById(R.id.card_3_6);
		RelativeLayout card_4_6 = (RelativeLayout) findViewById(R.id.card_4_6);
		RelativeLayout card_5_6 = (RelativeLayout) findViewById(R.id.card_5_6);
		RelativeLayout card_6_6 = (RelativeLayout) findViewById(R.id.card_6_6);
		RelativeLayout card_7_6 = (RelativeLayout) findViewById(R.id.card_7_6);
		RelativeLayout card_8_6 = (RelativeLayout) findViewById(R.id.card_8_6);
		ArrayList<RelativeLayout> tmpSet6 = new ArrayList<RelativeLayout>();
		tmpSet6.add(card_1_6);
		tmpSet6.add(card_2_6);
		tmpSet6.add(card_3_6);
		tmpSet6.add(card_4_6);
		tmpSet6.add(card_5_6);
		tmpSet6.add(card_6_6);
		tmpSet6.add(card_7_6);
		tmpSet6.add(card_8_6);
		tableLayout.add(tmpSet6);

		row_count = game.getTable().getHeight();
		col_count = game.getTable().getWidth();

		for (int row = 0; row < tableLayout.size(); row++) {
			for (int col = 0; col < tableLayout.get(row).size(); col++) {
				RelativeLayout rl = tableLayout.get(row).get(col);
				if (col < game.getTable().getWidth() && row < game.getTable().getHeight()) {
					final int fcol = col;
					final int frow = row;

					rl.setVisibility(View.VISIBLE);
					rl.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							if (!game.clickCard(frow, fcol)) {
								return;
							}
							
							Card currCard = game.getCurrent_card();
							playSound(frow, fcol, currCard.getAudioRes());
							
							if (player1_click1_row < 0) {
								player1_click1_row = frow;
								player1_click1_col = fcol;
								tableLayout.get(frow).get(fcol).setBackgroundColor(Color.rgb(118, 118, 118));
								selected++;
								flippedCards++;
								//Első lap fordításkor elindítja az időmérést
								if(!isTimerStarted){
									startTime = SystemClock.uptimeMillis();
									myTimeHandler.postDelayed(updateTimerMethod, 1000);
									isTimerStarted=true;
								}
								
								if(selected==3){
									if(((player1_click1_row == player1_prev_click1_row) && (player1_click1_col == player1_prev_click1_col))){
										tableLayout.get(player1_click1_row).get(player1_click1_col).setBackgroundColor(Color.rgb(118,118,118));
										tableLayout.get(player1_prev_click2_row).get(player1_prev_click2_col).setBackgroundColor(Color.rgb(0, 0, 0));
										
										player1_prev_click1_row = player1_click1_row;
										player1_prev_click1_col = player1_click1_col;
										
										selected=1;
									
									}else if(((player1_click1_row == player1_prev_click2_row) && (player1_click1_col == player1_prev_click2_col))){
										tableLayout.get(frow).get(fcol).setBackgroundColor(Color.rgb(118,118,118));
										tableLayout.get(player1_prev_click1_row).get(player1_prev_click1_col).setBackgroundColor(Color.rgb(0,0,0));
										
										player1_prev_click1_row = player1_click1_row;
										player1_prev_click1_col = player1_click1_col;
										
										selected=1;
									}else{
										tableLayout.get(player1_prev_click1_row).get(player1_prev_click1_col).setBackgroundColor(Color.rgb(0,0,0));
										tableLayout.get(player1_prev_click2_row).get(player1_prev_click2_col).setBackgroundColor(Color.rgb(0, 0, 0));
										
										player1_prev_click1_row = player1_click1_row;
										player1_prev_click1_col = player1_click1_col;
										
										selected=1;
									}
								}else{
									
									player1_prev_click1_row = player1_click1_row;
									player1_prev_click1_col = player1_click1_col;
								}
								
								
								
							} else if (player1_click2_row < 0) {
								player1_click2_row = frow;
								player1_click2_col = fcol;
								player1_prev_click2_row = player1_click2_row;
								player1_prev_click2_col = player1_click2_col;
								selected++;
								flippedCards++;
								
								
								// ha az első kártya klikk és a második kártya klikk nem egyezik
								if (!((player1_click1_row == player1_click2_row) && (player1_click1_col == player1_click2_col))
										&& game.getTable().getCard(player1_click1_row, player1_click1_col).getAudioRes() == game.getTable().getCard(player1_click2_row, player1_click2_col).getAudioRes()) {
									game.getTable().foundPair(new Pair(currCard.getAudioRes()));
									tableLayout.get(player1_click1_row).get(player1_click1_col).setBackgroundColor(Color.rgb(62,168,62));
									tableLayout.get(player1_click2_row).get(player1_click2_col).setBackgroundColor(Color.rgb(62,168,62));
									
									tableLayout.get(player1_click1_row).get(player1_click1_col).startAnimation(animAlpha);
									tableLayout.get(player1_click2_row).get(player1_click2_col).startAnimation(animAlpha);
									
									tableLayout.get(player1_click1_row).get(player1_click1_col).setVisibility(View.INVISIBLE);
									tableLayout.get(player1_click2_row).get(player1_click2_col).setVisibility(View.INVISIBLE);
				
									        	

									// megtaláltam az összes párt
									if (game.getTable().getFoundpairs() == col_count*row_count/2) {
									
										AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(NewSinglePlayerActivity.this);
										 LayoutInflater inflater = NewSinglePlayerActivity.this.getLayoutInflater();
										 View dialogView = inflater.inflate(R.layout.game_finish, null);
										 dialogBuilder.setView(dialogView);
										 TextView flippedCard = (TextView) dialogView.findViewById(R.id.flippedcard);
										 flippedCard.setText(String.valueOf(flippedCards));
										 TextView timeData = (TextView) dialogView.findViewById(R.id.timeData);
										 timeData.setText(tvPlayer1Time.getText().toString());
										 mp.stop();
										 myTimeHandler.removeCallbacks(updateTimerMethod);
										 
										 

										 dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
										 @Override
										 public void onClick(DialogInterface dialog, int which) {
											 /*Intent intent = new Intent(getApplicationContext(), MainActivity.class);
											 startActivity(intent);*/ 
											 dialog.dismiss();
											 finish();
										 }
										 });
										 final  AlertDialog dialog =  dialogBuilder.create();
										 dialog.show();
										 
										 try {
											dm.saveGame(game);
										} catch (SQLException e) {
											e.printStackTrace();
										}
									}
								}else{
								tableLayout.get(frow).get(fcol).setBackgroundColor(Color.rgb(192, 64, 64));
								tableLayout.get(player1_prev_click1_row).get(player1_prev_click1_col).setBackgroundColor(Color.rgb(192, 64, 64));
								}
								player1_click1_row = -1;
								player1_click1_col = -1;
								player1_click2_row = -1;
								player1_click2_col = -1;
							} else {
								player1_click1_row = -1;
								player1_click1_col = -1;
								player1_click2_row = -1;
								player1_click2_col = -1;
							}
						}
					});
				} else {
					rl.setVisibility(View.GONE);
				}
			}
			if (row < game.getTable().getHeight()) {
				((View) tableLayout.get(row).get(0).getParent()).setVisibility(View.VISIBLE);
			} else {
				((View) tableLayout.get(row).get(0).getParent()).setVisibility(View.GONE);
			}
		}
		/*
		 * ArrayList<LinearLayout> rows = new ArrayList<LinearLayout>(); ArrayList<RelativeLayout> cells = new ArrayList<RelativeLayout>(); final ViewGroup rootView = (ViewGroup) ((ViewGroup)
		 * this.findViewById(android.R.id.content)).getChildAt(0); for (int i = 0; i < row_count; i++) { final int i2 = i; LinearLayout row = (LinearLayout) rootView.getChildAt(i); rows.add(row); for (int k = 0; k < col_count; k++) { final
		 * int k2 = k; RelativeLayout cell = (RelativeLayout) row.getChildAt(k); /* cell.setOnClickListener(new OnClickListener() {
		 * @Override public void onClick(View v) { playSound(i2, k2); } });
		 */
		/*
		 * cell.setOnTouchListener(new OnTouchListener() {
		 * @Override public boolean onTouch(View v, MotionEvent event) { switch (event.getAction()) { case MotionEvent.ACTION_DOWN: v.setBackgroundColor(Color.argb(255, 192, 64, 64)); playSound(i2, k2); break; case MotionEvent.ACTION_UP:
		 * v.setBackgroundColor(Color.argb(0, 0, 0, 0)); stopSound(); break; } return true; } }); cells.add(cell); } }
		 */
		
		ivPlayPause.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(NewSinglePlayerActivity.this);
				LayoutInflater inflater = NewSinglePlayerActivity.this.getLayoutInflater();
				View dialogView = inflater.inflate(R.layout.game_pause, null);
				dialogBuilder.setView(dialogView);
				timeSwap += timeInMillies;
				myTimeHandler.removeCallbacks(updateTimerMethod);

				dialogBuilder.setPositiveButton("Folytatás", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						startTime = SystemClock.uptimeMillis();
						myTimeHandler.postDelayed(updateTimerMethod, 1000);
						
					}
				});
				final AlertDialog dialog = dialogBuilder.create();
				dialog.show();
			}
		});
	}

	private void playSound(int row, int col, int res) {
		stopSound();
		Log.d("playSound", String.valueOf(row) + " x " + String.valueOf(col));
		mp = MediaPlayer.create(getApplicationContext(), res);
		// mp.setLooping(true);

		// balansz beállítása
		float balance = ((float) col) / ((float) col_count - 1f);
		Log.d("balance", String.valueOf(balance));
		float leftVolume;
		float rightVolume;
		if (balance > 0.5f) {
			leftVolume = (1f - balance) * 2f;
			rightVolume = 1f;
		} else {
			leftVolume = 1f;
			rightVolume = balance * 2f;
		}
		Log.d("balance", "leftVolume = " + String.valueOf(leftVolume));
		Log.d("balance", "rightVolume = " + String.valueOf(rightVolume));

		// "mélység" beállítása, minél távolabb van az ikon annál halkabban szól
		// még nem tökéletes...
		float min_volume = 0.1f;
		float damping_factor = (1.0f - min_volume) * (row) / (float) (row_count - 1);
		damping_factor = 0.0f;
		leftVolume -= leftVolume * (float) (row_count - 1 - row) * damping_factor;
		rightVolume -= rightVolume * (float) (row_count - 1 - row) * damping_factor;

		if (leftVolume < 0f) {
			leftVolume = 0f;
		} else if (leftVolume > 1f) {
			leftVolume = 1f;
		}

		if (rightVolume < 0f) {
			rightVolume = 0f;
		} else if (rightVolume > 1f) {
			rightVolume = 1f;
		}

		Log.d("damping", "damping_factor = " + String.valueOf(damping_factor));
		Log.d("damping", "leftVolume = " + String.valueOf(leftVolume));
		Log.d("damping", "rightVolume = " + String.valueOf(rightVolume));

		mp.setVolume(leftVolume, rightVolume);
		mp.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				// stopSound();
			}
		});
		mp.start();
	}

	private void stopSound() {
		if (mp != null && mp.isPlaying()) {
			mp.reset();
			mp.release();
			mp = null;
		}
	}
	
	@Override
	public void onBackPressed() {
		stopSound();
		super.onBackPressed();
	}
	
private Runnable updateTimerMethod = new Runnable() {
		
		@Override
		public void run() {
			timeInMillies = SystemClock.uptimeMillis()-startTime;
			finalTime = timeSwap+timeInMillies;
			int seconds = (int)(finalTime/1000);
			int minutes = seconds/60;
			
			
			tvPlayer1Time.setText(""+String.format("%02d",minutes)+":"+String.format("%02d", (seconds%60)));
			myTimeHandler.postDelayed(this, 1000);
			
		}
	};
}
