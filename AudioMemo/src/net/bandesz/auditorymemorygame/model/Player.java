package net.bandesz.auditorymemorygame.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = Player.TABLE_NAME)
public class Player implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6898902292858945973L;
	public static final String TABLE_NAME = "players";
	public static final String FIELD_ID = "id";
	public static final String FIELD_NAME = "name";
	public static final String FIELD_BIRTH_YEAR = "birth_year";
	public static final String FIELD_GENDER = "gender";

	public static final int GENDER_SECRET = 0;
	public static final int GENDER_MALE = 1;
	public static final int GENDER_FEMALE = 2;

	@DatabaseField(columnName = FIELD_ID, generatedId = true)
	private int id = 0;
	@DatabaseField(columnName = FIELD_NAME)
	private String name;
	@DatabaseField(columnName = FIELD_BIRTH_YEAR)
	private int birth_year;
	@DatabaseField(columnName = FIELD_GENDER)
	private int gender = GENDER_SECRET;
	// megtalált párok
	@ForeignCollectionField(eager = true, maxEagerLevel = 10)
	private Collection<Pair> found_pairs = new ArrayList<Pair>();
	@DatabaseField(foreign = true)
	private Game game;
	
	// bizonyos kártyát hányszor fordított fel
	private HashMap<Card, Integer> shown_cards = new HashMap<Card, Integer>();
	
	public Player() {
		
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
	// user adatainak bemásolása a player ojjektumba
	public void cloneUser(User user) {
		if (user.getName() == null) {
			this.name = null;
		} else {
			this.name = String.valueOf(user.getName());
		}
		this.gender = user.getGender();
		this.birth_year = user.getBirth_year();
	}
	
	public JSONObject getJsonObj() {
		JSONObject jsonObj = null;
		try {
			jsonObj = new JSONObject();
			jsonObj.put("id", id);
			jsonObj.put("name", name);
			jsonObj.put("birth_year", birth_year);
			jsonObj.put("gender", gender);
		} catch (JSONException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return jsonObj;
	}
}
