package com.baandmazso.audiomemo.model;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.Dao.CreateOrUpdateStatus;
import com.j256.ormlite.stmt.QueryBuilder;

public class DataManager {
	public static final String SHAREDPREF_NAME = "audiomemo";
	private static DataManager dm = null;
	private Context context = null;
	private SharedPreferences sharedpref = null;
	private DatabaseHelper databaseHelper = null;

	public static DataManager getInstance(Context context) {
		if (dm == null) {
			dm = new DataManager();
			if (dm.context == null) {
				dm.context = context;
			}
			if (dm.sharedpref == null) {
				dm.sharedpref = dm.context.getSharedPreferences(SHAREDPREF_NAME, Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);
			}
		}
		return dm;
	}

	public DatabaseHelper getDatabaseHelper() {
		openDatabaseHelper();
		return dm.databaseHelper;
	}

	public DatabaseHelper openDatabaseHelper() {
		if (dm.databaseHelper == null) {
			dm.databaseHelper = OpenHelperManager.getHelper(dm.context, DatabaseHelper.class);
		}
		return dm.databaseHelper;
	}

	public void closeDatabaseHelper() {
		if (dm.databaseHelper != null) {
			OpenHelperManager.releaseHelper();
			dm.databaseHelper = null;
		}
	}

	public Card getCard(String domain_name) {
		/*try {
			Dao<Card, Integer> dao = dm.getDatabaseHelper().getCardDao();
			QueryBuilder<Card, Integer> querybuilder = dao.queryBuilder();
			querybuilder.where().eq(Card.FIELD_DOMAIN_NAME, domain_name);
			querybuilder.limit(1l);
			return dao.queryForFirst(querybuilder.prepare());
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return null;
	}

	public boolean addDomain(Card domain) {
		/*try {
			Dao<Card, Integer> dao = dm.getDatabaseHelper().getCardDao();
			CreateOrUpdateStatus status = dao.createOrUpdate(domain);
			if (status.isCreated() || status.isUpdated()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return false;
	}

}
