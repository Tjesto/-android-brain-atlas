package com.mm.brainatlas.data;

public class LectureSourceInfo extends SourceInfo {

	private final String lecturerName;
	private final String lectureHyperlink;
	
	public LectureSourceInfo(String lecturerName, String lectureHyperlink) {
		super();
		this.lecturerName = lecturerName;
		this.lectureHyperlink = lectureHyperlink;
	}

	@Override
	public String getTitleString() {
		return lecturerName;
	}

	@Override
	public String getLink() {
		return lectureHyperlink;
	}

}
