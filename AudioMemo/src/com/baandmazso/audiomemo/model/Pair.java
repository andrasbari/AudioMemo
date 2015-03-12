package com.baandmazso.audiomemo.model;

import java.io.Serializable;
import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

// alapvetően nem kell!!!!!!!!!
@DatabaseTable(tableName = Pair.TABLE_NAME)
public class Pair implements Serializable {
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
	private Card card1 = new Card();
	@DatabaseField(columnName = FIELD_CARD2, foreign=true)
	private Card card2 = new Card();
	@DatabaseField(columnName = FIELD_FOUND)
	private Date found;
	@DatabaseField(foreign = true)
	private Player player;

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
}
