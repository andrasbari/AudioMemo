package net.bandesz.auditorymemorygame.model;

import java.io.Serializable;
import java.util.ArrayList;

public class MemoryTable implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8452628888040621316L;
	private int row_count;
	private int col_count;
	private ArrayList<Pair> pairs = new ArrayList<Pair>();

	public MemoryTable() {
		
	}
	
	public MemoryTable(int row_count, int col_count) throws Exception {
		if (row_count * col_count % 2 == 0) {
			this.row_count = row_count;
			this.col_count = col_count;
		} else {
			throw new Exception("A táblán csak páros számú kártya helyezhető el!");
		}
	}

}
