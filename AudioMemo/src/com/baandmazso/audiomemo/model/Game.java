package com.baandmazso.audiomemo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baandmazso.audiomemo.R;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

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

@DatabaseTable(tableName = Game.TABLE_NAME)
public class Game implements Serializable {
	public static final String TABLE_NAME = "games";
	public static final String FIELD_ID = "id";
	public static final String FIELD_LEVEL = "level";
	public static final String FIELD_PLAYER_COUNT = "player_count";

	@DatabaseField(columnName = FIELD_ID, generatedId = true)
	private int id = 0;
	@DatabaseField(columnName = FIELD_LEVEL)
	private int level = 0;
	@DatabaseField(columnName = FIELD_PLAYER_COUNT)
	private int player_count = 0;
	@DatabaseField(foreign = true)
	private Table table = null;
	@ForeignCollectionField(eager = true, maxEagerLevel = 10)
	private Collection<Player> players = new ArrayList<Player>();
	
	private int click1_row = -1;
	private int click1_col = -1;
	private int click2_row = -1;
	private int click2_col = -1;

	int col_count = 0;
	int row_count = 0;
	private int current_player_number = 0;
	private int current_round = 0;
	private int shown_cards = 0;

	public Game() {
		
	}
	
	public Game(int level, int player_count) {
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
	}
	
	public void clickCard(int row, int col) {
		if (table.getCard(row, col) != null) {
			showCard(row, col);
			if (click1_row < 0 && click1_col < 0) {
				click1_row = row;
				click1_col = col;
			} else {
				if (click2_row < 0 && click2_col < 0) {
					click2_row = row;
					click2_col = col;
				} else {
					if (table.getCard(click1_row, click1_col).getAudioRes() == table.getCard(click2_row, click2_col).getAudioRes()) {
						
					} else {
						
					}
					click1_row = -1;
					click1_col = -1;
					click2_row = -1;
					click2_col = -1;
				}
			}
		}
	}
	
	public void playSound(int row, int col, int audio_res) {
		
	}

	public void showCard(int row, int col) {
		Card currCard = table.getCard(row, col);
		playSound(row, col, currCard.getAudioRes());
		currCard.show();
		shown_cards++;
		if (shown_cards % 2 == 0) {
			nextPlayer();
		}
	}
	
	public void foundPair(int audio_res) {
		
	}

	public void nextPlayer() {
		if (current_player_number < players.size() - 1) {
			current_player_number++;
		} else {
			nextRound();
		}
	}

	public void nextRound() {
		current_player_number = 0;
		current_round++;
	}
	
	public int getCurrentRound() {
		return current_round;
	}
	
	public int getCurrentPlayerNumber() {
		return current_player_number;
	}
	
	public Player getCurrentPlayer() {
		return ((ArrayList<Player>)players).get(current_player_number);
	}
}
