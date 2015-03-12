package com.baandmazso.audiomemo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import android.util.Log;

import com.baandmazso.audiomemo.R;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = Table.TABLE_NAME)
public class Table implements Serializable {
	public static final String TABLE_NAME = "tables";
	public static final String FIELD_ID = "id";
	public static final String FIELD_LEVEL = "level";
	public static final String FIELD_WIDTH = "width";
	public static final String FIELD_HEIGHT = "height";

	private static final int[] AUDIO_RESOURCES = { R.raw.sin_1khz, R.raw.click_train, R.raw.impulse1, R.raw.male_v, R.raw.whitenoise, R.raw.sq_1khz, R.raw.sin_5khz, R.raw.pinknoise, R.raw.female_oh, R.raw.sweeplin, R.raw.guitar2,
			R.raw.violin1, R.raw.bells, R.raw.drum6, R.raw.flute, R.raw.phone2, R.raw.toytrain, R.raw.whistle_long, R.raw.drum5, R.raw.guitar8, R.raw.percussion, R.raw.chime, R.raw.kiss_kiss, R.raw.toccata };

	@DatabaseField(columnName = FIELD_ID, generatedId = true)
	private int id = 0;
	@DatabaseField(columnName = FIELD_LEVEL, index = true)
	private int level = 0;
	@DatabaseField(columnName = FIELD_WIDTH)
	private int width = 0;
	@DatabaseField(columnName = FIELD_HEIGHT)
	private int height = 0;
	@ForeignCollectionField(eager = true, maxEagerLevel = 10)
	private Collection<Card> cards = new ArrayList<Card>();

	private int found_pair_count = 0;
	private Map<Integer, Pair> found_pairs = new HashMap<Integer, Pair>();

	public Table() {
		
	}
	
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

	private void generateTable() throws Exception {
		if (width < 1 || height < 1) {
			throw new Exception("Előbb add meg a tábla méretét!");
		}
		if (width * height % 2 == 1) {
			throw new Exception("A kért tábla páratlan számú kártyából állna!");
		}
		int pair_count = (int) (this.width * this.height / 2);
		for (int i = 0; i < pair_count; i++) {
			cards.add(new Card(AUDIO_RESOURCES[i]));
			cards.add(new Card(AUDIO_RESOURCES[i]));
		}
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				try {
					int pos = row * width + col;
					Log.d("row * width + col", String.valueOf(pos));
					((ArrayList<Card>)cards).get(pos).setPositionRow(row);
					((ArrayList<Card>)cards).get(pos).setPositionCol(col);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		int randcount = width * height * width * height;
		for (int i = 0; i < randcount; i++) {
			int rand_col1 = new Random().nextInt(width);
			int rand_row1 = new Random().nextInt(height);
			int rand_col2 = new Random().nextInt(width);
			int rand_row2 = new Random().nextInt(height);
			if (rand_col1 == rand_col2 && rand_row1 == rand_row2) {
				i--;
				continue;
			}
			try {
				getCard(rand_row1, rand_col1).flipCards(getCard(rand_row2, rand_col2));
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

	public List<Card> getCards() {
		return ((ArrayList<Card>)cards);
	}

	public Card getCard(int row, int col) {
		for (int i = 0; i < cards.size(); i++) {
			if (((ArrayList<Card>)cards).get(i).getPositionRow() == row && ((ArrayList<Card>)cards).get(i).getPositionCol() == col) {
				return ((ArrayList<Card>)cards).get(i);
			}
		}
		return null;
	}

	public void foundPair(Pair pair) {
		found_pairs.put(pair.getAudioRes(), pair);
		found_pair_count++;
	}

	public int getFoundpairs() {
		return found_pair_count;
	}
}
