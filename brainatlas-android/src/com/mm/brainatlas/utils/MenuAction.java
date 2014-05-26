package com.mm.brainatlas.utils;

public enum MenuAction {
	SHOW_DISEASES(0), SHOW_PARTS(1), SHOW_SOURCES(2), /*HELP_ME(3),*/ EXIT(3);

	private final int id;

	private MenuAction(final int id) {
		this.id = id;
	}

}
