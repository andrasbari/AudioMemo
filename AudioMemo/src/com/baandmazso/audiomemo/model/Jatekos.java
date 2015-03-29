package com.baandmazso.audiomemo.model;

import java.util.ArrayList;
import java.util.Collection;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = Player.TABLE_NAME)
public class Jatekos {

	
	private static final long serialVersionUID = -6898902292858945973L;
	public static final String TABLE_NAME = "players";
	public static final String FIELD_ID = "id";
	public static final String FIELD_NAME = "name";
	public static final String FIELD_BIRTH_YEAR = "birth_year";
	public static final String FIELD_GENDER = "gender";

	

	@DatabaseField(columnName = FIELD_ID, generatedId = true)
	private int id = 0;
	@DatabaseField(columnName = FIELD_NAME)
	private String name;
	@DatabaseField(columnName = FIELD_BIRTH_YEAR)
	private int birth_year;
	@DatabaseField(columnName = FIELD_GENDER)
	private String gender;
	// megtalált párok
	
	public Jatekos(){
		// needed by ormLite
	};
	
	public Jatekos(String name,String gender,int birthYear){
		this.name = name;
		this.gender = gender;
		this.birth_year = birthYear;
	}
}
