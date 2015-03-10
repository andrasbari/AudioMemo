package com.baandmazso.audiomemo.model;

import java.util.Date;

public class Pair {
	private int audio_res = 0;
	private Card card1 = new Card();
	private Card card2 = new Card();
	//private Card[] cards = new Card[2];
	private Date found;

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
