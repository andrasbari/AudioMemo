package com.baandmazso.audiomemo.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

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
	private int badPairCounter=0;

	public Game() {

	}
	
	/**
	 * a megadott usaerek adatait átmásoljuk ugyanannyi darab player objektumba, amiket mentünk a játékhoz
	 * @param level
	 * @param users
	 * @throws Exception 
	 */
	public Game(int level, ArrayList<User> users) throws Exception {
		this.level = level;
		this.player_count = users.size();
		for (int i = 0; i < player_count; i++) {
			// jelenlegi userke
			User user = users.get(i);
			Player player = new Player();
			player.setGame(this);
			// betöltöm a playerbe a user adatokat
			player.cloneUser(user);
			players.add(player);
		}
		
		table = new Table(level);

		row_count = table.getHeight();
		col_count = table.getWidth();
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
					// mázli faktor detektor
					if (isFluke(click1_row, click1_col, row, col)) {
						try {
							table.getNextUnShownCard().flipCards(table.getCard(row, col));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
					showCard(row, col);
					click2_row = row;
					click2_col = col;
					
					//ha megtalálta a párt
					if (table.getCard(click1_row, click1_col).getAudioRes() == table.getCard(click2_row, click2_col).getAudioRes()) {
						table.foundPair(table.getCard(click1_row, click1_col).getAudioRes());
					} else {
						//ha nem talált párt megnézzük hogy rossz párnak minősül e, azaz egy pár mind2 része szolt már mégsem azt találta meg
						isBadPair(click1_row, click1_col, row, col);
						
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
	
	/**
	 * Ellenőrzi hogy mázli vagy sem, mázli ha még egyszer sem volt felfordítva kártya de már megtalálta a párját. Viszont olyan kártyára nem szabad mázlit alkalmazni amit már egyszer meghalgatott!
	 * @param row1
	 * @param col1
	 * @param row2
	 * @param col2
	 * @return
	 */
	public boolean isFluke(int row1, int col1, int row2, int col2) {
		if (isPair(row1, col1, row2, col2)) {
			if (shown_cards < col_count * row_count) {
				Card card2 = table.getCard(row2, col2);
				// ha még eygszer sem hallgatta meg a második kártyát akkor mázli, ha már egyszer hallotta akkor emlékezhetett rá
				if (card2.getShowCount() == 0) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isPair(int row1, int col1, int row2, int col2) {
		return table.getCard(row1, col1).getAudioRes() == table.getCard(row2, col2).getAudioRes();
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
	
	public void isBadPair(int row1, int col1, int row2, int col2){
		Card card1 = table.getCard(row1, col1);
		Card card2 = table.getCard(row2, col2);
		List<Card> cardList = table.getCards();
		Boolean badPair=false;
		
			for(Card card : cardList){
				if((card1.getAudioRes()==card.getAudioRes()) && (card1.getPositionCol()!=card.getPositionCol() || card1.getPositionRow()!=card.getPositionRow()) && card.getShowCount()>0  ){
					badPair=true;
					
				}
				
			}
		
		
			if(badPair){
				badPairCounter++;
			}
		
	}
	
	public int getBadPairCounter() {
		return badPairCounter;
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
	
	public String getJsonStr() {
		JSONObject jsonObj = null;
		JSONArray jsonArr = null;
		try {
			jsonObj = new JSONObject();
			jsonObj.put("id", id);
			jsonObj.put("level", level);
			jsonObj.put("player_count", player_count);
			jsonObj.put("table", table.getJsonObj());
			jsonArr = new JSONArray();
			for (Player player : players) {
				jsonArr.put(player.getJsonObj());
			}
			jsonObj.put("players", jsonArr);
		} catch (JSONException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		String json = jsonObj.toString();
		Log.d("json", json);
		
		try {
			FileOutputStream outputStream;
			File file = new File(DataManager.getContext().getExternalFilesDir("games"), new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".json");
			 outputStream = new FileOutputStream(file);
			  outputStream.write(json.getBytes());
			  outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return json;
	}
}
