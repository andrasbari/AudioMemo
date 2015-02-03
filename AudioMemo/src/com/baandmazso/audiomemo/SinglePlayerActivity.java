package com.baandmazso.audiomemo;

import java.util.ArrayList;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class SinglePlayerActivity extends Activity {
	private int row_count = 2;
	private int col_count = 5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.table_5x2);

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
				cell.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						playSound(i2, k2);
					}
				});
				cells.add(cell);
			}
		}

	}

	private void playSound(int row, int col) {
		Log.d("playSound", String.valueOf(row) + " x " + String.valueOf(col));
		MediaPlayer mp;
		mp = MediaPlayer.create(getApplicationContext(), R.raw.sound_1khz_44100hz_16bit_05sec);

		float rightVolume = (float) col / (float) col_count;
		float leftVolume = 1.0f - rightVolume;
		mp.setVolume(leftVolume, rightVolume);
		mp.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				mp.reset();
				mp.release();
				mp = null;
			}
		});
		mp.start();
	}
}
