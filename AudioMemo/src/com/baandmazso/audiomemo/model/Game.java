package com.baandmazso.audiomemo.model;

import java.util.ArrayList;
import java.util.List;

import com.baandmazso.audiomemo.R;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Game {
	private Context context;
	private MediaPlayer mp;
	
	private int level = 1;
	private int player_count = 1;
	private Table table;
	int col_count = 0;
	int row_count = 0;
	private ArrayList<ArrayList<ImageView>> tableLayout = new ArrayList<ArrayList<ImageView>>();

	private List<Player> players = new ArrayList<Player>();
	private int current_player = 0;
	private int current_round = 0;
	private int shown_cards = 0;

	public Game(Context context, int level, int player_count) {
		this.context = context;
		this.level = level;
		this.player_count = player_count;
		for (int i = 0; i < player_count; i++) {
			players.add(new Player());
		}

		try {
			table = new Table(level);
		} catch (Exception e) {
			e.printStackTrace();
		}

		row_count = table.getHeight();
		col_count = table.getWidth();

		for (int row = 0; row < row_count; row++) {
			ArrayList<ImageView> tmpSet = new ArrayList<ImageView>();
			for (int col = 0; col < col_count; col++) {
				ImageView tmpiv = new ImageView(context);
				tmpiv.setImageResource(R.drawable.ic_launcher);
				tmpSet.add(tmpiv);
			}
			tableLayout.add(tmpSet);
		}
		
		/*for (int row = 0; row < tableLayout.size(); row++) {
			for (int col = 0; col < tableLayout.get(row).size(); col++) {
				RelativeLayout rl = tableLayout.get(row).get(col);
				if (col < table.getWidth() && row < table.getHeight()) {
					final int fcol = col;
					final int frow = row;

					rl.setVisibility(View.VISIBLE);
					rl.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							if (table.getCard(frow, fcol) != null) {
								Card currCard = table.getCard(frow, fcol);
								playSound(frow, fcol, currCard.getAudioRes());
								currCard.show();
								if (player1_click1_row < 0) {
									player1_click1_row = frow;
									player1_click1_col = fcol;
									tableLayout.get(frow).get(fcol).setBackgroundColor(Color.rgb(192, 64, 64));
								} else if (player1_click2_row < 0) {
									player1_click2_row = frow;
									player1_click2_col = fcol;
									tableLayout.get(frow).get(fcol).setBackgroundColor(Color.rgb(192, 64, 64));

									if (!((player1_click1_row == player1_click2_row) && (player1_click1_col == player1_click2_col))
											&& table.getCard(player1_click1_row, player1_click1_col).getAudioRes() == table.getCard(player1_click2_row, player1_click2_col).getAudioRes()) {
										table.foundPair(currCard.getAudioRes());
										tableLayout.get(player1_click1_row).get(player1_click1_col).setVisibility(View.INVISIBLE);
										tableLayout.get(player1_click2_row).get(player1_click2_col).setVisibility(View.INVISIBLE);
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
						}
					});
				} else {
					rl.setVisibility(View.GONE);
				}
			}
			if (row < table.getHeight()) {
				((View) tableLayout.get(row).get(0).getParent()).setVisibility(View.VISIBLE);
			} else {
				((View) tableLayout.get(row).get(0).getParent()).setVisibility(View.GONE);
			}
		}*/
	}

	private void playSound(int row, int col, int res) {
		stopSound();
		Log.d("playSound", String.valueOf(row) + " x " + String.valueOf(col));
		mp = MediaPlayer.create(this.context.getApplicationContext(), res);
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

	public void showCard(int row, int col) {
		shown_cards++;
		if (shown_cards % 2 == 0) {
			nextPlayer();
		}
	}

	public void nextPlayer() {
		if (current_player < players.size() - 1) {
			current_player++;
		} else {
			nextRound();
		}
	}

	public void nextRound() {
		current_round++;
	}
}
