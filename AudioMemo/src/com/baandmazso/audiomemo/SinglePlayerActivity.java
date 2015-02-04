package com.baandmazso.audiomemo;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class SinglePlayerActivity extends Activity {
	private MediaPlayer mp;
	private int row_count = 3;
	private int col_count = 5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.table_5x3);

		ArrayList<LinearLayout> rows = new ArrayList<LinearLayout>();
		ArrayList<RelativeLayout> cells = new ArrayList<RelativeLayout>();

		final ViewGroup rootView = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
		for (int i = 0; i < row_count; i++) {
			final int i2 = i;
			LinearLayout row = (LinearLayout) rootView.getChildAt(i);
			rows.add(row);
			for (int k = 0; k < col_count; k++) {
				final int k2 = k;
				RelativeLayout cell = (RelativeLayout) row.getChildAt(k);
				/*
				 * cell.setOnClickListener(new OnClickListener() {
				 * @Override public void onClick(View v) { playSound(i2, k2); } });
				 */
				cell.setOnTouchListener(new OnTouchListener() {
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						switch (event.getAction()) {
						case MotionEvent.ACTION_DOWN:
							v.setBackgroundColor(Color.argb(255, 192, 64, 64));
							playSound(i2, k2);
							break;
						case MotionEvent.ACTION_UP:
							v.setBackgroundColor(Color.argb(0, 0, 0, 0));
							stopSound();
							break;
						}
						return true;
					}
				});
				cells.add(cell);
			}
		}
	}

	private void playSound(int row, int col) {
		stopSound();
		Log.d("playSound", String.valueOf(row) + " x " + String.valueOf(col));
		mp = MediaPlayer.create(getApplicationContext(), R.raw.sound_1khz_44100hz_16bit_05sec);
		mp.setLooping(true);

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
		float damping_factor = (1.0f - min_volume) * (row + 1f) / (float) row_count;
		damping_factor = 0.3f;
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
}
