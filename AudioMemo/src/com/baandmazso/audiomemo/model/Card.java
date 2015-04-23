package com.baandmazso.audiomemo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = Card.TABLE_NAME)
public class Card implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5574053712464115196L;
	public static final String TABLE_NAME = "cards";
	public static final String FIELD_ID = "id";
	public static final String FIELD_AUDIO_RES = "audio_res";
	public static final String FIELD_SHOW_COUNT = "show_count";
	public static final String FIELD_POSITION_ROW = "position_row";
	public static final String FIELD_POSITION_COL = "position_col";
	public static final String FIELD_SHOWN = "shown";

	@DatabaseField(columnName = FIELD_ID, generatedId = true)
	private int id = 0;
	// a res/raw mappában lévő hangfájl azonosító száma
	@DatabaseField(columnName = FIELD_AUDIO_RES, index = true)
	private int audio_res = 0;
	// felfordítási szám
	@DatabaseField(columnName = FIELD_SHOW_COUNT)
	private int show_count = 0;
	// kártya pozíciója a táblán
	@DatabaseField(columnName = FIELD_POSITION_ROW)
	private int position_row = 0;
	@DatabaseField(columnName = FIELD_POSITION_COL)
	private int position_col = 0;
	@DatabaseField(columnName = FIELD_SHOWN, dataType = DataType.SERIALIZABLE)
	private ArrayList<Date> shown = new ArrayList<Date>();
	@DatabaseField(foreign = true)
	private Table table = null;

	public Card() {

	}

	public Card(int audio_res) {
		this.audio_res = audio_res;
	}

	public int getAudioRes() {
		return audio_res;
	}

	public int getShowCount() {
		return show_count;
	}

	public int getPositionRow() {
		return position_row;
	}

	public int getPositionCol() {
		return position_col;
	}

	public void setPositionRow(int position_row) throws Exception {
		if (position_row >= 0) {
			this.position_row = position_row;
		} else {
			throw new Exception("Egy kártya pozíciója/helye a mátrixban nem lehet 0!");
		}
	}

	public void setPositionCol(int position_col) throws Exception {
		if (position_col >= 0) {
			this.position_col = position_col;
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
		if (this.position_row < 0 && this.position_col < 0) {
			throw new Exception("A jelenlegi kártya nincs még elhelyezve a táblán!");
		}
		if (another_card.getPositionRow() < 0 && another_card.getPositionCol() < 0) {
			throw new Exception("A másik kártya nincs még elhelyezve a táblán!");
		}
		int tmp_position_row = another_card.getPositionRow();
		int tmp_position_col = another_card.getPositionCol();
		another_card.setPositionRow(this.position_row);
		another_card.setPositionCol(this.position_col);
		this.setPositionRow(tmp_position_row);
		this.setPositionCol(tmp_position_col);
	}

	public void show() {
		show_count++;
		shown.add(new Date());
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
			jsonObj.put("show_count", show_count);
			jsonObj.put("position_row", position_row);
			jsonObj.put("position_col", position_col);
			jsonArr = new JSONArray();
			for (Date date : shown) {
				jsonArr.put(date.getTime());
			}
			jsonObj.put("shown", jsonArr);
		} catch (JSONException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return jsonObj;
	}
}
