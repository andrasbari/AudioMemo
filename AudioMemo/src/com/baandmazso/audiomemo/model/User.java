package com.baandmazso.audiomemo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = User.TABLE_NAME)
public class User implements Serializable {
	private static final long serialVersionUID = 1543607649271615534L;
	public static final String TABLE_NAME = "users";
	public static final String FIELD_ID = "id";
	public static final String FIELD_NAME = "name";
	public static final String FIELD_BIRTH_YEAR = "birth_year";
	public static final String FIELD_GENDER = "gender";

	public static final int GENDER_SECRET = 0;
	public static final int GENDER_MALE = 1;
	public static final int GENDER_FEMALE = 2;

	@DatabaseField(columnName = FIELD_ID, generatedId = true)
	private int id = 0;
	@DatabaseField(columnName = FIELD_NAME, index = true)
	private String name;
	@DatabaseField(columnName = FIELD_BIRTH_YEAR, index = true)
	private int birth_year;
	@DatabaseField(columnName = FIELD_GENDER, index = true)
	private int gender = GENDER_SECRET;
	
	public User(){
		// needed by ormLite
	};
	
	public User(String name,int gender,int birthYear){
		this.name = name;
		this.gender = gender;
		this.birth_year = birthYear;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getBirth_year() {
		return birth_year;
	}

	public int getGender() {
		return gender;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBirth_year(int birth_year) {
		this.birth_year = birth_year;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}
}
