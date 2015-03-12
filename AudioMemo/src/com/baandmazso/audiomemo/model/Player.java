package com.baandmazso.audiomemo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = Player.TABLE_NAME)
public class Player implements Serializable {
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
}
