package com.hebo.heboproject.views;


import java.util.Comparator;

import com.hebo.heboproject.utils.PingYinUtil;

public class PinyinComparator implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		 String str1 = PingYinUtil.getPingYin((String) o1);
	     String str2 = PingYinUtil.getPingYin((String) o2);
	     return str1.compareTo(str2);
	}

}
