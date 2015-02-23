package com.baandmazso.audiomemo.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
	private String name;
	private int birth_year;
	private String gender;
	// megtalált párok
	private ArrayList<Pair> found_pairs = new ArrayList<Pair>();
	// bizonyos kártyát hányszor fordított fel
	private HashMap<Card, Integer> shown_cards = new HashMap<Card, Integer>();
}
