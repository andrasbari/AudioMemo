package com.baandmazso.audiomemo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import android.util.ArrayMap;
import android.util.Log;

import com.baandmazso.audiomemo.R;

public class Table {
	private int level;
	private int width;
	private int height;
	private static final int[] audio_resources = { R.raw.sin_1khz, R.raw.click_train, R.raw.impulse1, R.raw.male_v, R.raw.whitenoise, R.raw.sq_1khz, R.raw.sin_5khz, R.raw.pinknoise, R.raw.female_oh, R.raw.sweeplin, R.raw.guitar2,
			R.raw.violin1, R.raw.bells, R.raw.drum6, R.raw.flute, R.raw.phone2, R.raw.toytrain, R.raw.whistle_long, R.raw.drum5, R.raw.guitar8, R.raw.percussion, R.raw.chime, R.raw.kiss_kiss, R.raw.toccata };
	private Map<Integer, Pair> pairs = new HashMap<Integer, Pair>();
	private List<Card> cards = new ArrayList<Card>();

	private int foundpairs = 0;

	public Table(int level) throws Exception {
		switch (level) {
		case 1:
			this.level = level;
			this.width = 5;
			this.height = 2;
			break;
		case 2:
			this.level = level;
			this.width = 4;
			this.height = 3;
			break;
		case 3:
			this.level = level;
			this.width = 4;
			this.height = 4;
			break;
		case 4:
			this.level = level;
			this.width = 5;
			this.height = 4;
			break;
		case 5:
			this.level = level;
			this.width = 6;
			this.height = 4;
			break;
		case 6:
			this.level = level;
			this.width = 6;
			this.height = 5;
			break;
		case 7:
			this.level = level;
			this.width = 6;
			this.height = 6;
			break;
		case 8:
			this.level = level;
			this.width = 7;
			this.height = 6;
			break;
		case 9:
			this.level = level;
			this.width = 8;
			this.height = 6;
			break;
		default:
			throw new Exception("Nincs ilyen szint!");
		}
		generateTable();
	}

	private void generateTable() {
		int pair_count = (int) (this.width * this.height / 2);
		for (int i = 0; i < pair_count; i++) {
			Pair pair = new Pair(audio_resources[i]);
			pairs.put(pair.getAudioRes(), pair);
			cards.add(pair.getCards()[0]);
			cards.add(pair.getCards()[1]);
		}
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				int[] pos = new int[2];
				pos[0] = row;
				pos[1] = col;
				try {
					Log.d("row * width + col", String.valueOf(row * width + col));
					cards.get(row * width + col).setPosition(pos);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		int randcount = width * height * 10;
		for (int i = 0; i < randcount; i++) {
			int rand_width1 = new Random().nextInt(width);
			int rand_height1 = new Random().nextInt(height);
			int rand_width2 = new Random().nextInt(width);
			int rand_height2 = new Random().nextInt(height);

			try {
				getCard(rand_height1, rand_width1).flipCards(getCard(rand_height2, rand_width2));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public int getLevel() {
		return level;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public List<Card> getCards() {
		return cards;
	}

	public Card getCard(int row, int col) {
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).getPosition()[0] == row && cards.get(i).getPosition()[1] == col) {
				return cards.get(i);
			}
		}
		return null;
	}

	public void foundPair(int audio_res) {
		if (pairs.containsKey(audio_res)) {
			pairs.get(audio_res).found();
			foundpairs++;
		}
	}

	public int getFoundpairs() {
		return foundpairs;
	}
}
