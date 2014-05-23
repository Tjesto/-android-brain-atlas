package com.mm.brainatlas.activities.impl;

public interface Changable {
	public static enum Direction {
		LEFT, RIGHT, UNKNOWN;
	}
	void changeView();
}
