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
	/**
	 * 
	 */
	private static final long serialVersionUID = -1869553443141769390L;
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
	private Card current_card = null;
	private int current_player_number = 0;
	private int current_round = 0;
	private int shown_cards = 0;

	public Game() {

	}

	public Game(int level, int player_count) throws Exception {
		this.level = level;
		this.player_count = player_count;
		for (int i = 0; i < player_count; i++) {
			Player player = new Player();
			player.setGame(this);
			players.add(player);
		}

		table = new Table(level);

		row_count = table.getHeight();
		col_count = table.getWidth();
	}

	public boolean clickCard(int row, int col) {
		if (table.getCard(row, col) != null) {
			// ha az első fordítás történik
			if (click1_row < 0 && click1_col < 0) {
				showCard(row, col);
				click1_row = row;
				click1_col = col;
				
				return true;
			} // ha második fordítás történik
			else if (click2_row < 0 && click2_col < 0) {
				// ha a második kártya ugyanaz mint az első kártya akkor nem csinál semmit sem!
				if ( row == click1_row && col == click1_col) {
					return false;
				} else {
					showCard(row, col);
					click2_row = row;
					click2_col = col;
					
					//ha megtalálta a párt
					if (table.getCard(click1_row, click1_col).getAudioRes() == table.getCard(click2_row, click2_col).getAudioRes()) {
						table.foundPair(table.getCard(click1_row, click1_col).getAudioRes());
					} else {
						
					}

					click1_row = -1;
					click1_col = -1;
					click2_row = -1;
					click2_col = -1;
					
					return true;
				}
			} else {
				click1_row = -1;
				click1_col = -1;
				click2_row = -1;
				click2_col = -1;
				
				return false;
			}
		} else {
			return false;
		}		
	}

	public void playSound(int row, int col, int audio_res) {

	}

	public void showCard(int row, int col) {
		current_card = table.getCard(row, col);
		//playSound(row, col, current_card.getAudioRes());
		current_card.show();
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
		return ((ArrayList<Player>) players).get(current_player_number);
	}

	public ArrayList<Player> getPlayers() {
		return (ArrayList<Player>)players;
	}

	public Table getTable() {
		return table;
	}

	public Card getCurrent_card() {
		return current_card;
	}
}
