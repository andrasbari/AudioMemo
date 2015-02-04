package com.baandmazso.audiomemo.model;

public class Pair {
	private Card[] cards = new Card[2];
	private boolean found = false;

	public Pair(Card card1, Card card2) throws Exception {
		if (card1 != null && card2 != null) {
			if (card1.getAudio_res() == card2.getAudio_res()) {
				cards[0] = card1;
				cards[1] = card2;
			} else {
				throw new Exception("Egy pár csak két ugyanolyan típusú kártyából állhat!");
			}
		} else {
			throw new Exception("Egy párnak két kártyából kell állnia!");
		}
	}

	public Pair(Card[] cards) throws Exception {
		if (cards.length == 2 && cards[0] != null && cards[1] != null) {
			if (cards[0].getAudio_res() == cards[1].getAudio_res()) {
				this.cards = cards;
			} else {
				throw new Exception("Egy pár csak két ugyanolyan típusú kártyából állhat!");
			}
		} else {
			throw new Exception("Egy párnak két kártyából kell állnia!");
		}
	}

	public Card[] getCards() {
		return cards;
	}

	public boolean isFound() {
		return found;
	}

	public void setFound(boolean found) {
		this.found = found;
	}
}
