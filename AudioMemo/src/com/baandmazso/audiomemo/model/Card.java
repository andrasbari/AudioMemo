package com.baandmazso.audiomemo.model;

import java.util.ArrayList;
import java.util.Date;

public class Card {
	// a res/raw mappában lévő hangfájl azonosító száma
	private int audio_res;
	// felfordítási szám
	private int show_count = 0;
	// kártya pozíciója a táblán
	private int[] position = { 0, 0 };
	private ArrayList<Date> shown = new ArrayList<Date>();

	public Card(int audio_res) {
		this.audio_res = audio_res;
	}

	public int getAudioRes() {
		return audio_res;
	}

	public int getShowCount() {
		return show_count;
	}

	public int[] getPosition() {
		return position;
	}

	/**
	 * kártya pozíciójának/helyének megadása
	 * 
	 * @param position
	 * @throws Exception
	 */
	public void setPosition(int[] position) throws Exception {
		if (position[0] >= 0 && position[1] >= 0) {
			this.position = position;
		} else {
			throw new Exception("Egy kártya pozíciója/helye a mátrixban nem lehet 0!");
		}
	}

	/**
	 * Két kártya pozíciójának/helyének felcserélése
	 * 
	 * @param another_card
	 * @throws Exception
	 */
	public void flipCards(Card another_card) throws Exception {
		if (this.position[0] < 0 && this.position[1] < 0) {
			throw new Exception("A jelenlegi kártya nincs még elhelyezve a táblán!");
		}
		if (another_card.getPosition()[0] < 0 && another_card.getPosition()[1] < 0) {
			throw new Exception("A másik kártya nincs még elhelyezve a táblán!");
		}
		int[] tmp_position = another_card.getPosition();
		another_card.setPosition(this.position);
		this.setPosition(tmp_position);
	}

	public void show() {
		show_count++;
		shown.add(new Date());
	}
}
