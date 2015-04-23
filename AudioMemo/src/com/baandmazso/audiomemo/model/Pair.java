package com.baandmazso.audiomemo.model;

import java.io.Serializable;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

// alapvetően nem kell!!!!!!!!!
@DatabaseTable(tableName = Pair.TABLE_NAME)
public class Pair implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6319931442147516982L;
	public static final String TABLE_NAME = "pairs";
	public static final String FIELD_ID = "id";
	public static final String FIELD_AUDIO_RES = "audio_res";
	public static final String FIELD_CARD1 = "card1";
	public static final String FIELD_CARD2 = "card2";
	public static final String FIELD_FOUND = "found";
	
	@DatabaseField(columnName = FIELD_ID, generatedId = true)
	private int id = 0;
	@DatabaseField(columnName = FIELD_AUDIO_RES)
	private int audio_res = 0;
	@DatabaseField(columnName = FIELD_CARD1, foreign=true)
	private Card card1;
	@DatabaseField(columnName = FIELD_CARD2, foreign=true)
	private Card card2;
	@DatabaseField(columnName = FIELD_FOUND)
	private Date found;
	@DatabaseField(foreign = true)
	private Player player;
	@DatabaseField(foreign = true)
	private Table table = null;

	public Pair() {
		
	}
	
	public Pair(int audio_res) {
		this.audio_res = audio_res;
		card1 = new Card(audio_res);
		card2 = new Card(audio_res);
	}

	public Pair(Card card1, Card card2) throws Exception {
		if (card1 != null && card2 != null) {
			if (card1.getAudioRes() == card2.getAudioRes()) {
				this.card1 = card1;
				this.card2 = card2;
				this.audio_res = card1.getAudioRes();
			} else {
				throw new Exception("Egy pár csak két ugyanolyan típusú kártyából állhat!");
			}
		} else {
			throw new Exception("Egy párnak két kártyából kell állnia!");
		}
	}

	public Pair(Card[] cards) throws Exception {
		if (cards.length == 2 && cards[0] != null && cards[1] != null) {
			if (cards[0].getAudioRes() == cards[1].getAudioRes()) {
				this.card1 = cards[0];
				this.card2 = cards[1];
				this.audio_res = cards[0].getAudioRes();
			} else {
				throw new Exception("Egy pár csak két ugyanolyan típusú kártyából állhat!");
			}
		} else {
			throw new Exception("Egy párnak két kártyából kell állnia!");
		}
	}

	public Card[] getCards() {
		Card[] cards = new Card[2];
		cards[0] = card1;
		cards[1] = card2;
		return cards;
	}

	public Date isFound() {
		return found;
	}

	public void found() {
		this.found = new Date();
	}

	public int getAudioRes() {
		return audio_res;
	}

	public Card getCard1() {
		return card1;
	}

	public Card getCard2() {
		return card2;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}
	
	public JSONObject getJsonObj() {
		JSONObject jsonObj = null;
		JSONArray jsonArr = null;
		try {
			jsonObj = new JSONObject();
			jsonObj.put("id", id);
			jsonObj.put("audio_res", audio_res);
			jsonObj.put("card1", card1.getJsonObj());
			jsonObj.put("card2", card2.getJsonObj());
			jsonObj.put("found", found);
			jsonObj.put("player", player.getJsonObj());
		} catch (JSONException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return jsonObj;
	}
}
