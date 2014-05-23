package com.mm.brainatlas;

import android.content.Context;

import com.mm.brainatlas.data.BookSourceInfo;
import com.mm.brainatlas.data.LectureSourceInfo;
import com.mm.brainatlas.data.WebsiteSourceInfo;
import com.mm.brainatlas.data.impl.SourceInfo;
import com.mm.brainatlas.utils.Pair;

public final class SourceInfoFactory {		
	
	public static Pair<SourceInfo, Integer> create(Context context, String[] data, int arguments, int...resources) {
		SourceInfo info = null;
		String[] parsed1 = parse(data[0]);
		String[] parsed2 = parse(data[1]);
		arguments = compareArguments(parsed1, parsed2);
		if (arguments == 1) {
			info = new BookSourceInfo(context.getText(resources[0]).toString());
		} else if (arguments == 2) {
			if (parsed1.length == 3) {
				info = new WebsiteSourceInfo(context.getText(resources[0]).toString(), context.getText(resources[1]).toString());
			} else {
				info = new LectureSourceInfo(context.getText(resources[0]).toString(), context.getText(resources[1]).toString());
			}
		}
		return new Pair<SourceInfo, Integer>(info, arguments);
	}
	
	
	@Deprecated
	public static Pair<SourceInfo, Integer> createForTest(String data, int args) {
		SourceInfo info = null;
		if (args > 0) {
			info = new BookSourceInfo(data);
		}
		return new Pair<SourceInfo, Integer>(info, args);
	}

	private static int compareArguments(String[] parsed1, String[] parsed2) {
		if (parsed1.length > parsed2.length) {
			return 0;
		} else if (parsed1.length < parsed2.length) {
			return 1;
		} else if (parsed1[1].equals(parsed2[1])) {
			return 2;
		} else {
			return 1;
		}
	}

	private static String[] parse(String data) {
		return data.split("_");
	}
	
}
