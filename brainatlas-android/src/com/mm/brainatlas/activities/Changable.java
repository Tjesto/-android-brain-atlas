package com.mm.brainatlas.activities;

public interface Changable {
	public static enum Direction {
		LEFT, RIGHT, UNKNOWN;
	}
	void changeView();
}
