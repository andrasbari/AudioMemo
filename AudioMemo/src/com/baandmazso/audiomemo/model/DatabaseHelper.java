package com.baandmazso.audiomemo.model;

import java.io.File;
import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
	public static final int DATABASE_VERSION = 32;

	private Context context;

	private Dao<Card, Integer> CardDao;
	private Dao<Game, Integer> GameDao;
	private Dao<MemoryTable, Integer> MemoryTableDao;
	private Dao<Pair, Integer> PairDao;
	private Dao<Player, Integer> PlayerDao;
	private Dao<Table, Integer> TableDao;
	private Dao<User, Integer> UserDao;

	public DatabaseHelper(Context context) {
		super(context, context.getExternalFilesDir("") + File.separator + "audiomemo.sqlite3", null, DATABASE_VERSION);
		Log.d("sqlite path", context.getExternalFilesDir("") + File.separator + "audiomemo.sqlite3");
		this.setContext(context);
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			Log.d("DatabaseHelper", "onCreate");
			TableUtils.createTable(connectionSource, Table.class);
			TableUtils.createTable(connectionSource, Card.class);
			TableUtils.createTable(connectionSource, Game.class);
			TableUtils.createTable(connectionSource, Player.class);
			//TableUtils.createTable(connectionSource, MemoryTable.class);
			TableUtils.createTable(connectionSource, Pair.class);
			TableUtils.createTable(connectionSource, User.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			Log.d("DatabaseHelper", "onUpgrade");
			if (oldVersion == 31 && newVersion == 32) {
				TableUtils.createTable(connectionSource, User.class);
				return;
			}
			TableUtils.dropTable(connectionSource, Card.class, true);
			TableUtils.dropTable(connectionSource, Game.class, true);
			//TableUtils.dropTable(connectionSource, MemoryTable.class, true);
			TableUtils.dropTable(connectionSource, Pair.class, true);
			TableUtils.dropTable(connectionSource, Player.class, true);
			TableUtils.dropTable(connectionSource, Table.class, true);
			TableUtils.dropTable(connectionSource, User.class, true);
			onCreate(database, connectionSource);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Dao<Card, Integer> getCardDao() throws SQLException {
		if (CardDao == null) {
			CardDao = getDao(Card.class);
		}
		return CardDao;
	}

	public Dao<Game, Integer> getGameDao() throws SQLException {
		if (GameDao == null) {
			GameDao = getDao(Game.class);
		}
		return GameDao;
	}

	public Dao<MemoryTable, Integer> getMemoryTableDao() throws SQLException {
		if (MemoryTableDao == null) {
			MemoryTableDao = getDao(MemoryTable.class);
		}
		return MemoryTableDao;
	}

	public Dao<Pair, Integer> getPairDao() throws SQLException {
		if (PairDao == null) {
			PairDao = getDao(Pair.class);
		}
		return PairDao;
	}

	public Dao<Player, Integer> getPlayerDao() throws SQLException {
		if (PlayerDao == null) {
			PlayerDao = getDao(Player.class);
		}
		return PlayerDao;
	}

	public Dao<Table, Integer> getTableDao() throws SQLException {
		if (TableDao == null) {
			TableDao = getDao(Table.class);
		}
		return TableDao;
	}
	
	public Dao<User, Integer> getUserDao() throws SQLException {
		if (UserDao == null) {
			UserDao = getDao(User.class);
		}
		return UserDao;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}
}
