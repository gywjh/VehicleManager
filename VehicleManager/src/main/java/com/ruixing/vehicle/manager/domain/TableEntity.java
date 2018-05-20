package com.ruixing.vehicle.manager.domain;

import java.util.List;

public class TableEntity<T> {

	private List<T> rows;

	private int results;

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public int getResults() {
		return results;
	}

	public void setResults(int results) {
		this.results = results;
	}

}
