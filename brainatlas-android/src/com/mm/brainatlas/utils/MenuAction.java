package com.mm.brainatlas.utils;

public enum MenuAction {
	SHOW_DISEASES(0), SHOW_PARTS(1), SHOW_SOURCES(2), CONTACT_US(3), EXIT(4), BACK(5), ;

	private final int id;

	private MenuAction(final int id) {
		this.id = id;
	}

}
